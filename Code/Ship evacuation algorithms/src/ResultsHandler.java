import java.util.List;


public class ResultsHandler {

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
	public void runSimulation(List<Node> graph){
		
		// Get the location of all humans in the graph
		
		// Calculate the path off the ship for each passenger
		
		// For each time frame or step do until all passengers are either off the ship or dead
		
			/* Check the capacity of each room in the graph, if the capacity in a 
			*room exceeds the capacity of the path to the exit split the humans into
			*groups and recalculate the paths
			*/
		
			// Move the passengers
		
	}
}
