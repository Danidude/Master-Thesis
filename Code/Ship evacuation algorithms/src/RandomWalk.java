import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomWalk {
	
	Random random = new Random();
	
	// Constructs a path from the source node to any exit
	public List<Node> randomWalk(Node source){
		List<Node> path = new ArrayList<Node>();
		path.add(source);
		Node currentNode = source;	
		
		// Walks in a random direction until it reaches an exit
		while(!currentNode.isExit()){
			Edge e = currentNode.getPaths().get(random.nextInt(currentNode.getPaths().size()));
			currentNode = e.getNode();
			path.add(currentNode);
		}
		return path;
	}
}
