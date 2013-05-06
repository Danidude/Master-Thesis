import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;


public class ResultsHandler {
	List<Human> startHumansDjixstra;
	List<Human> startHumansACO;
	Map<Edge, Integer> flowOfEdges;

	List<Node> graph;
	List<Node> listOfLethalNodes;
	
	int humansMovementAllaowence = 0;
	
	HumanHandler humanHandler;
	
	public ResultsHandler(List<Node> graph, String fileName, int numberOfPassangers) throws FileNotFoundException, UnsupportedEncodingException{
		
		this.graph = graph;
		listOfLethalNodes = new ArrayList<Node>();
		flowOfEdges = new HashMap<Edge, Integer>();
						
		// The probability the humans have family members on board the ship
		double chanceOfFamily = 1.0;
		
		// Get the location of all humans in the graph
		humanHandler = new HumanHandler();

		List<Human> humans = new ArrayList<Human>(humanHandler.createHumans(numberOfPassangers));
		humanHandler.createFamilyTies(chanceOfFamily, humans);
		
		// Bug : this somehow kills a few people in the process
		humans = humanHandler.placeHumans(humans, graph);
		
		
		
		startHumansDjixstra = new ArrayList<Human>(humans);
		startHumansACO = cloneList(startHumansDjixstra);
		//Collections.copy(startHumansACO, startHumansDjixstra);
		
		//Sets the max human movement allowence so it may be reset after each movement.
		humansMovementAllaowence = humanHandler.getMovementAllowence();
		
		PrintWriter writer = new PrintWriter("Djixstra\\Dixstra "+fileName+".txt", "UTF-8");
		writer.println("Djixstra data:");
		writer.println("Turn number		Number of passangers in total		Passangers still living		Dead Passangers		Passangers that have survived");
		writer.close();
		
		writer = new PrintWriter("ACO\\ACO "+fileName+".txt", "UTF-8");
		writer.println("ACO data:");
		writer.println("Turn number		Number of passangers in total		Passangers still living		Dead Passangers		Passangers that have survived");
		writer.close();
		
		for(Human h : humans){
			System.out.println("Human: " + h.getHumanID() + " is at node: " + h.getNode().getID());
			
		}
		System.out.println("----------------------");

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
	public int runSimulation(List<Node> exits, String fileName, int lethalStartNode) throws IOException{
		
		List<Human> humans = new ArrayList<Human>(startHumansDjixstra);
		
		resetLethalNodes();
		graph.get(lethalStartNode).setChanceOfDeath(0.5);
		
		addLethalNodesToList();
		
		Dijkstra dijkstra = new Dijkstra();
		
		Random deathByFire = new Random();
		
		double chanceOfSpread = 0.5;
		
		double chanceOfDeath = 0.5;

		
		int resetNumber = 0;
		
		double numberOfSurvivers = 0;
		double deadCounter = 0;
		double turnCounter = 0;
		double totalPassanger = humans.size();
		double totalLivingPassangers = 0;
		
		
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Djixstra\\Dixstra "+fileName+".txt", true)));
		writer.println(turnCounter+",			"+totalPassanger+",					"+totalPassanger+",				"+deadCounter+",			"+numberOfSurvivers);
		
		// Calculate the path off the ship for each passenger
		while(!humans.isEmpty()){
			
			// Spreads fire randomly, but should random spread at a fixed speed and between floors
			// randomSpreadFire(graph, chanceOfSpread, chanceOfDeath);
			turnCounter ++;
			resetNumber++;
			// Every human does one move
			for(Iterator<Human> it = humans.iterator(); it.hasNext(); ){
				Human h = it.next();
				
				
				// If the passenger is currently in a node that counts as an exit the passenger has escaped and is removed from the list of humans
				if(exits.contains(h.getNode())){
					//System.out.println("Human " + h.getHumanID() + " escaped(Djixsta)");
					numberOfSurvivers++;
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
					/*System.out.print("The shortest path for human " + h.getHumanID() + " off the ship is via path: ");
					for(Node n : shortestPath){
						System.out.print(n.getID() + " ");						
					}
					System.out.println();*/
					
					// Do one step
					//h.setNode(shortestPath.get(1));
					moveHuman(h, shortestPath, humans);
					//System.out.println("Human " + h.getHumanID() + " moves to node " + h.getNode().getID());
					
					// Kills humans off, dead humans should add to the room capacity
					if(h.getNode().getChanceOfDeath() > deathByFire.nextDouble()){
						//System.out.println("Shit son, human: " + h.getHumanID() + " just burned to death");
						deadCounter++;
						it.remove();
					}
					
				}
			}
			
			/*
			 * Save the amount of surivers at this point
			 * The amount that have yet to reach the exit
			 * the ones that have reached the exit
			 * and the amount of passangers that have diead
			 * the time step of all this.
			 */
			totalLivingPassangers = totalPassanger - deadCounter;
			writer.println(turnCounter+",			"+totalPassanger+",					"+totalLivingPassangers+",				"+deadCounter+",			"+numberOfSurvivers);
			
			
			if(resetNumber >= humansMovementAllaowence)
			{
				resetHumanMovementAllowence(humans);
				resetNumber = 0;
			}
		}
		
		writer.close();
		return (int)turnCounter;
	}
	
	
	//Runs the simulation and finding the exits using ACO instead of dijkstra's.
	public int runSimulationWithACO(List<Node> graph, List<Node> exits, String fileName, int lethalStartNode) throws IOException
	{
		List<Human> humans = new ArrayList<Human>(startHumansACO);
		AntColonyOptimizationController aco = new AntColonyOptimizationController(20, graph);
		int resetNumber = 0;
		
		resetLethalNodes();
		graph.get(lethalStartNode).setChanceOfDeath(0.5);
		
		addLethalNodesToList();
		
		Random deathByFire = new Random();
		
		double numberOfSurvivers = 0;
		double deadCounter = 0;
		double turnCounter = 0;
		double totalPassanger = humans.size();
		double totalLivingPassangers = 0;
		
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("ACO\\ACO "+fileName+".txt", true)));
		
		writer.println(turnCounter+",			"+totalPassanger+",					"+totalPassanger+",				"+deadCounter+",			"+numberOfSurvivers);
		// Calculate the path off the ship for each passenger
		while(!testIfFinished(humans))
		{
			resetNumber++;
			turnCounter++;
			
			for(Iterator<Human> it = humans.iterator(); it.hasNext(); ){
				Human h = it.next();
				
				// If the passenger is currently in a node that counts as an exit the passenger has escaped
				if(exits.contains(h.getNode())){
					//System.out.println("Human " + h.getHumanID() + " escaped");
					numberOfSurvivers++;
					it.remove();
				}
				else
				{
					//finds the way.
					aco.changeCurrentNode(h.getNode());
					aco.findWay();
					
					int prevNode = h.getNode().getID();
					
					//Do one step
					//h.setNode(aco.bestPath.get(1));
					moveHuman(h, aco.bestPath, humans);
					
					//Prints what node the passenger was in and what node it is in now
					//System.out.println("Human " + h.getHumanID() + " is trying to escape from node "+prevNode+" to node "+h.getNode().getID());
					
					// Kills humans off, dead humans should add to the room capacity
					if(h.getNode().getChanceOfDeath() > deathByFire.nextDouble()){
						//System.out.println("Shit son, human: " + h.getHumanID() + " just burned to death");
						deadCounter++;
						it.remove();
					}
				}
				
			}
			/*
			 * Save the amount of surivers at this point
			 * The amount that have yet to reach the exit
			 * the ones that have reached the exit
			 * and the amount of passangers that have diead
			 * the time step of all this.
			 */
			totalLivingPassangers = totalPassanger - deadCounter;
			writer.println(turnCounter+",			"+totalPassanger+",					"+totalPassanger+",				"+deadCounter+",			"+numberOfSurvivers);

			if(resetNumber >= humansMovementAllaowence)
			{
				resetHumanMovementAllowence(humans);
				resetNumber = 0;
				lehtalnessSpreading();
			}
			
		}
		writer.close();
		return (int)turnCounter;
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
	
	private void lehtalnessSpreading()
	{
		for(Node n : listOfLethalNodes)
		{
			if(n.getChanceOfDeath() >= 1)
			{
				n.setChanceOfDeath(1);
			}
			else if(n.getChanceOfDeath() >= 0.6)
			{
				n.setChanceOfDeath(n.getChanceOfDeath()+0.20);
			}
			else if(n.getChanceOfDeath() >= 0.2)
			{
				n.setChanceOfDeath(n.getChanceOfDeath() + 0.10);
			}
			else
			{
				n.setChanceOfDeath(n.getChanceOfDeath()+0.05);
			}
			
		}
		
		List<Node> tempList = new ArrayList<Node>();
		for(Node n : listOfLethalNodes)
		{
			if(n.getChanceOfDeath() >= 0.4)
			{
				for(Edge e : n.getPaths())
				{
					if(!listOfLethalNodes.contains(e.getNode()))
					{
						e.getNode().setChanceOfDeath(e.getNode().getChanceOfDeath()+0.05);
						tempList.add(e.getNode());
					}
					
				}
			}
		}
		listOfLethalNodes.addAll(tempList);
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
			//Famely member with 0 in movement allowence.
			if(h.getFamiliarTies().contains(currentHuman.getHumanID()) && h.getMovementAllowence() == 0 && h.getNode() == startNode)
			{
				return;
			}
			else if(h.getFamiliarTies().contains(currentHuman.getHumanID()) && h.getNode() == startNode && h != currentHuman)
			{
				fameleyMembers.add(h);
			}
		}
		
		int prevNode = currentHuman.getNode().getID();
		
		Edge e = findEdgeToNode(path.get(0), path.get(1));
		
		if(!flowOfEdges.containsKey(e))
		{
			flowOfEdges.put(e, 0);
		}
		
		flowOfEdges.put(e, flowOfEdges.get(e)+1);
		
		//flytt currentHuman kun om det er plass til det
		if(flowOfEdges.get(e) <= e.getFlow())
		{
			currentHuman.moveHuman(path.get(1));
		}
		
		
		
		
		//System.out.println("Human " + currentHuman.getHumanID() + " is trying to escape from node "+prevNode+" to node "+currentHuman.getNode().getID());
		
		for(Human h2 : fameleyMembers)
		{
			
			
			if(prevNode == h2.getNode().getID())
			{
				flowOfEdges.put(e, flowOfEdges.get(e)+1);
				if(flowOfEdges.get(e) <= e.getFlow())
				h2.moveHuman(path.get(1));
			}
				
			
			//System.out.println("Human " + h2.getHumanID() + " is trying to escape from node "+prevNode+" to node "+h2.getNode().getID());
			if(currentHuman.getMovementAllowence() == 0)
			{
				h2.setMovementAllowence(0);
			}
			else if(h2.getMovementAllowence() == 0)
			{
				currentHuman.setMovementAllowence(0);
			}
		}
	
		
	}
	
	public void resetHumanMovementAllowence(List<Human> humans)
	{
		for(Human h : humans)
		{
			h.setMovementAllowence(humansMovementAllaowence);
		}
		
		flowOfEdges.clear();
	}
	
	private List<Human> cloneList(List<Human> list)
	{
		List<Human> clone = new ArrayList<Human>();
		for(Human h : list)
		{
			Human h2 = new Human(h.getFamiliarTies(), false, h.getNode(), h.getHumanID(), h.isEscaped(), h.getMovementAllowence());
			clone.add(h2);
		}
		return clone;
	}
	
	public void replaceHumans()
	{
		humanHandler.placeHumans(startHumansACO, graph);
		humanHandler.placeHumans(startHumansDjixstra, graph);
		
		for(int i = 0; i<startHumansACO.size(); i++)
		{
			startHumansACO.get(i).setEscaped(false);
			startHumansDjixstra.get(i).setEscaped(false);
		}
	}
	
	private void resetLethalNodes()
	{
		for(Node n : listOfLethalNodes)
		{
			n.setChanceOfDeath(0);
		}
		
		listOfLethalNodes.removeAll(listOfLethalNodes);
	}
	
	private void addLethalNodesToList()
	{
		for(Node n : graph)
		{
			if(n.getChanceOfDeath() > 0)
			{
				listOfLethalNodes.add(n);
			}
		}
	}
	
	public void resetPheremones(List<Edge> list)
	{
		for(Edge e : list)
		{
			e.setPheremones(0);
		}
	}

	private Edge findEdgeToNode(Node from, Node to)
	{
		for(int i = 0; i<from.getPaths().size(); i++)
		{
			if(from.getPaths().get(i).getNode() == to)
			{
				return from.getPaths().get(i);
			}
		}
		System.err.println("Error: did not find correct edge from a node. ResultsHandler moveHuman.");
		return null;
	}

}
