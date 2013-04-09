import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;


public class ResultsHandler {
List<Human> startHumans = new ArrayList<Human>();
List<Human> listOfHumans = new ArrayList<Human>();

	List<Node> graph;
	int humansMovementAllaowence = 0;
	
	public ResultsHandler(List<Node> graph){
		
		this.graph = graph;
						
		// The probability the humans have family members on board the ship
		double chanceOfFamily = 1.0;
		
		// Get the location of all humans in the graph
		HumanHandler humanHandler = new HumanHandler();

		List<Human> humans = new ArrayList<Human>(humanHandler.createHumans(7));
		humanHandler.createFamilyTies(chanceOfFamily, humans);
		
		// Bug : this somehow kills a few people in the process
		humans = humanHandler.placeHumans(humans, graph);
		startHumans = humans;
		
		
		//Sets the max human movement allowence so it may be reset after each movement.
		humansMovementAllaowence = humanHandler.getMovementAllowence();
		
		
		for(Human h : humans){
			System.out.println("Human: " + h.getHumanID() + " is at node: " + h.getNode().getID());
		}

	}
	
	// Computes the probability of surviving by walking along the provided path
	public double calculateSurvival(List<Node> path){
		double survivalChance = 0.0;
		for(Node n : path){
			if(survivalChance == 0.0){
				survivalChance = 1.0 - n.getChanceOfDeath();
			}
			else if(n.getChanceOfDeath()==0){

			}
			else{
				survivalChance *= 1.0 - n.getChanceOfDeath();
			}			
		}
		return survivalChance;	
	}

	// The main function which controls the flow of the simulation
	public void runSimulation(List<Node> exits){
		
		List<Human> humans = new ArrayList<Human>(startHumans);

		Dijkstra dijkstra = new Dijkstra();
		
		Random deathByFire = new Random();
		
		double chanceOfSpread = 0.5;
		
		double chanceOfDeath = 0.5;

		// Calculate the path off the ship for each passenger
		while(!humans.isEmpty()){
			
			// Spreads fire randomly, but should random spread at a fixed speed and between floors
			// randomSpreadFire(graph, chanceOfSpread, chanceOfDeath);
			
			// Every human does one move
			for(Iterator<Human> it = humans.iterator(); it.hasNext(); ){
				Human h = it.next();
				
				// If the passenger is currently in a node that counts as an exit the passenger has escaped and is removed from the list of humans
				if(exits.contains(h.getNode())){
					System.out.println("Human " + h.getHumanID() + " escaped");
					it.remove();
				}
				
				else{
					dijkstra.findPath(h.getNode());

					// Get the distance and path to all exits
					Map<Double, List<Node>> path = dijkstra.getDistancePaths(exits, graph);

					// Sort the paths based on the shortest distance
					SortedSet<Double> keys = new TreeSet<Double>(path.keySet());
					List<Node> shortestPath = path.get(keys.first());
					
					// Prints the path to the exit for testing purposes
					System.out.print("The shortest path for human " + h.getHumanID() + " off the ship is via path: ");
					for(Node n : shortestPath){
						System.out.print(n.getID() + " ");						
					}
					System.out.println();
					
					// Do one step
					h.setNode(shortestPath.get(1));
					System.out.println("Human " + h.getHumanID() + " moves to node " + h.getNode().getID());
					
					// Kills humans off, dead humans should add to the room capacity
					if(h.getNode().getChanceOfDeath() > deathByFire.nextDouble()){
						System.out.println("Shit son, human: " + h.getHumanID() + " just burned to death");
						it.remove();
					}
					
				}
			}			
		}		
	}
	
	//Runs the simulation and finding the exits using ACO instead of dijkstra's.
	public void runSimulationWithACO(List<Node> graph, List<Node> exits)
	{
		List<Human> humans = new ArrayList<Human>(startHumans);
		AntColonyOptimizationController aco = new AntColonyOptimizationController(20, graph);
		
		// Calculate the path off the ship for each passenger
		while(!testIfFinished(humans))
		{
			
			for(Human h : humans)
			{
				// If the passenger is currently in a node that counts as an exit the passenger has escaped
				if(exits.contains(h.getNode())){
					h.setEscaped(true);
					System.out.println("Human " + h.getHumanID() + " escaped");
				}
				else
				{
					//finds the way.
					aco.changeCurrentNode(h.getNode());
					aco.findWay();
					
					int prevNode = h.getNode().getID();
					//Do one step
					h.setNode(aco.bestPath.get(1));
					
					//Prints what node the passenger was in and what node it is in now
					System.out.println("Human " + h.getHumanID() + " is trying to escape from node "+prevNode+" to node "+h.getNode().getID());
				}
			}
			
		}
	}
	
	private boolean testIfFinished(List<Human> humans){
		for(Human h : humans){
			if(h.isEscaped()){

			}
			else{
				return false;
			}
		}
		return true;
	}
	
	private void randomSpreadFire(List<Node> graph, double chanceOfSpread, double chanceOfDeath){
		Random spread = new Random();
		for(Node n : graph){
			if(n.getChanceOfDeath() != 0.0 && chanceOfSpread > spread.nextDouble()){
				for(Edge e : n.getPaths()){
					e.getNode().setChanceOfDeath(chanceOfDeath);
					System.out.println("The fire just spread from node " + n.getID() + " to node " + e.getNode().getID());
				}
			}
		}
	}
	
	/*
	 * Flytt passasjerne, men f�rst sjekk nabo nodene om det er familie meldmer der som skal f�rst flyttes til samme node.
	 * S� flytte begge to sammen.
	 */
	
	private void moveHuman(Human currentHuman, List<Node> path, List<Human> humans)
	{
		if(currentHuman.getMovementAllowence() == 0)
		{
			return;
		}
		else if(currentHuman.getMovementAllowence() < 0)
		{
			System.out.println("Error: Human movement allowence less then 0.");
			return;
		}
		
		List<Node> neighbourNodes = new ArrayList<Node>();
		List<Human> fameleyMembers = new ArrayList<Human>();
		Node startNode = currentHuman.getNode();
		
		//Gathers all the neighbour nodes to the start node of the current passanger
		for(Edge e : startNode.getPaths())
		{
			neighbourNodes.add(e.getNode());
		}
		
		//Moves the passangers with famaly ties to the same node of the current passanger.
		for(Human h : humans)
		{
			if(h.getFamiliarTies().contains(currentHuman.getHumanID()) && h.getMovementAllowence() == 0 && h.getNode() == startNode)
			{
				return;
			}
			else if(h.getFamiliarTies().contains(currentHuman.getHumanID()) && h.getNode() == startNode && h != currentHuman)
			{
				fameleyMembers.add(h);
			}
		}
		
		//flytt currentHuman
		currentHuman.moveHuman(path.get(1));
		
		for(Human h2 : fameleyMembers)
		{
			h2.moveHuman(path.get(1));
			if(currentHuman.getMovementAllowence() == 0)
			{
				h2.setMovementAllowence(0);
			}
		}
	
		
	}
	
	public void resetHumanMovementAllowence(List<Human> humans)
	{
		for(Human h : humans)
		{
			h.setMovementAllowence(humansMovementAllaowence);
		}
	}
}
