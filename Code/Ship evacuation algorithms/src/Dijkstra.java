import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class Dijkstra {
Node currentNode = null;
	public void findPath(Node source){

		currentNode = source;
		
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
	
	// Finds the shortest path from the source node to the exit node
	public List<Node> getShortestPath(Node exit){
		List<Node> path = new ArrayList<Node>();
		for (Node node = exit; node != null; node = node.previous)
			path.add(node);

		Collections.reverse(path);
		return path;
	}
	
	// Finds the shortest path from the source node to a list of exit nodes
	public void getShortestPaths(List<Node> exits){
		for(Node n : exits){
			System.out.print("The distance to node " + n.getID() + " from source node " + currentNode.getID() + " is " + n.minDistance);
			List<Node> path = getShortestPath(n);
			System.out.print(" and goes via Path: ");
			for(Node v : path){
				System.out.print(v.getID() + " ");
			}
			System.out.println();			
		}
		
		// Clears the nodes from data from the previous iteration
		for(Node e : exits){
			e.previous = null;
			e.minDistance = Double.POSITIVE_INFINITY;
		}
	}
	
	// Finds the shortest path from a list of source nodes to a list of exit nodes
	public void getAllPaths(List<Node> sources, List<Node> exits){
		for(Node s : sources){
			findPath(s);
			getShortestPaths(exits);
		}
	}
}