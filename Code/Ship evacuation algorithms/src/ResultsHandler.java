import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class ResultsHandler {
List<Human> startHumans = new ArrayList<Human>();

	public ResultsHandler(List<Node> graph){
		
		// The probability the humans have family members on board the ship
		double chanceOfFamily = 1.0;
		// Get the location of all humans in the graph
		HumanHandler humanHandler = new HumanHandler();

		List<Human> humans = new ArrayList<Human>(humanHandler.createHumans(2));
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
		while(testIfFinished(humans)==false){
			for(Human h : humans){
				
				// If the passenger is currently in a node that counts as an exit the passenger has escaped
				if(exits.contains(h.getNode())){
					h.setEscaped(true);
					System.out.println("Human " + h.getHumanID() + " escaped");
				}
				
				else{
					dijkstra.findPath(h.getNode());

					// Get the distance and path to all exits
					Map<Double, List<Node>> path = dijkstra.getDistancePaths(exits);

					// Sort the paths based on the shortest distance
					SortedSet<Double> keys = new TreeSet<Double>(path.keySet());
					List<Node> shortestPath = path.get(keys.first());

					//Saves the nodeID from the current node the human is in.
					int prevNode = h.getNode().getID();
					
					// Do one step
					h.setNode(shortestPath.get(1));

					//Prints what node the passanger was in and what node it is in now
					System.out.println("Human " + h.getHumanID() + " is trying to escape from node "+prevNode+" to node "+h.getNode().getID());
				}
			}			
		}
		
	
		// For each time frame or step do until all passengers are either off the ship or dead

		/* Check the capacity of each room in the graph, if the capacity in a 
		 *room exceeds the capacity of the path to the exit split the humans into
		 *groups and recalculate the paths
		 */

		// Move the passengers

	}
	
	//Runs the simulation and finding the exits using ACO insted of djikstra.
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
					
					//Prints what node the passanger was in and what node it is in now
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
