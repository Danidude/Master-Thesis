import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class Dijkstra {
	public void findPath(Node source){
		
		// The shortest distance from the source node to this node.
		source.minDistance = 0;
		
		// Visit each node starting with the smallest minDistance
		PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>();
		nodeQueue.add(source);
		
		while (!nodeQueue.isEmpty()){
			Node current = nodeQueue.poll();
			
			// Visit each edge connected to the current node 
			for (Edge e : current.getPaths()){
				Node destination = e.getNode();
				int weight = e.getWeight();
				
				// 
				double distance = current.minDistance + weight;
				if(distance < destination.minDistance){
					nodeQueue.remove(destination);
					destination.minDistance = distance;
					destination.previous = current;
					nodeQueue.add(destination);
				}
			}
		}
	}
	public List<Node> getShortestPath(Node exit){
		List<Node> path = new ArrayList<Node>();
	    for (Node node = exit; node != null; node = node.previous)
	        path.add(node);

	    Collections.reverse(path);
	    return path;
	}
}
