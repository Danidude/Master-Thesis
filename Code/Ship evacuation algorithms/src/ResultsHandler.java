import java.util.List;


public class ResultsHandler {

	// Computes the probability of surviving by walking along the provided path
	public double calculateDanger(List<Node> path){
		double danger = 0.0;
		for(Node n : path){
			if(danger == 0.0){
				danger = 1.0 - n.getChanceOfDeath();
			}
			else if(n.getChanceOfDeath()==0){
				
			}
			else{
				danger *= 1.0 - n.getChanceOfDeath();
			}			
		}
		return danger;	
	}
	
	// Starts a fire on the ship, the parameters are the chance of spread and the source node
	public void startFire(double chanceOfFire, Node source){
		
	}
}
