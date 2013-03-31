import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class ResultsHandler {
List<Human> startHumans = new ArrayList<Human>();

	List<Node> graph;

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

		// Calculate the path off the ship for each passenger
		while(!humans.isEmpty()){
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
					
					System.out.print("The shortest path for human " + h.getHumanID() + " off the ship is via path: ");
					for(Node n : shortestPath){
						System.out.print(n.getID() + " ");						
					}
					System.out.println();
					
					// Do one step
					h.setNode(shortestPath.get(1));
					System.out.println("Human " + h.getHumanID() + " moves to node " + h.getNode().getID());

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
}
