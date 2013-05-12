import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Dijkstra {

	// Sets the current node globally to allow other functions to print the results. For testing purposes only.
	Node currentNode = null;

	// Finds the shortest path from the source node to all nodes in the graph
	public void findPath(Node source){

		currentNode = source;
		source.minDistance = 0.0;

		// Visit each node, starting with the smallest minDistance
		PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>();
		nodeQueue.add(source);

		while (!nodeQueue.isEmpty()){
			Node current = nodeQueue.poll();

			// Visit each edge connected to the current node 
			for (Edge e : current.getPaths()){
				Node destination = e.getNode();
				int weight = e.getWeight();

				// Finds the shortest distance to the destination
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
	
	public void findSafestPath(Node source){

		currentNode = source;
		source.minDistance = 0.0;

		// Visit each node, starting with the smallest minDistance
		PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>();
		nodeQueue.add(source);

		while (!nodeQueue.isEmpty()){
			Node current = nodeQueue.poll();

			// Visit each edge connected to the current node 
			for (Edge e : current.getPaths()){
				Node destination = e.getNode();
				int weight = e.getWeight();

				// Finds the shortest distance to the destination
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

	// Prints the shortest path from the source node to a list of exit nodes
	public void printShortestPaths(List<Node> exits){
		for(Node n : exits){
			System.out.print("The distance to node " + n.getID() + " from source node " + currentNode.getID() + " is " + n.minDistance);
			List<Node> path = getShortestPath(n);
			System.out.print(" and goes via path: ");
			for(Node v : path){
				System.out.print(v.getID() + " ");
			}
			System.out.println();			
		}		
	}

	// Returns the shortest paths from the source node to a list of exit nodes
	public Map<Node, List<Node>> getShortestPaths(List<Node> exits){
		Map<Node, List<Node>> map = new HashMap<Node, List<Node>>();
		for(Node n : exits){
			List<Node> path = getShortestPath(n);
			map.put(currentNode, path);
		}		
		return map;
	}

	public Map<Double, List<Node>> getDistancePaths(List<Node> exits, List<Node> graph){
		Map<Double, List<Node>> map = new HashMap<Double, List<Node>>();
		for(Node n : exits){
			List<Node> path = getShortestPath(n);
			map.put(n.minDistance, path);

			// Clears the nodes from data from the previous iteration
			for(Node e : graph){
				e.previous = null;
				e.minDistance = Double.POSITIVE_INFINITY;
			}
		}
		return map;
	}

	// Finds the shortest path from a list of source nodes to a list of exit nodes	
	public Map<Node, List<Node>> getAllPaths(List<Node> sources, List<Node> exits, List<Node> graph){
		Map<Node, List<Node>> map = new HashMap<Node, List<Node>>();
		for(Node s : sources){
			findPath(s);
			printShortestPaths(exits);

			// Clears the nodes from data from the previous iteration
			for(Node e : graph){
				e.previous = null;
				e.minDistance = Double.POSITIVE_INFINITY;
			}
		}
		return map;
	}
}