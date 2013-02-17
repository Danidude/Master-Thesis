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
			Node n = nodeQueue.poll();
			
			// Visit each edge connected to the current node 
			for (Edge e : n.getPaths()){
				Node node = e.getNode();
				int weight = e.getWeight();
				
				// 
				double distance = node.minDistance + weight;
				if(distance < n.minDistance){
					nodeQueue.remove(node);
					node.minDistance = distance;
					node.previous = n;
					nodeQueue.add(node);
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
