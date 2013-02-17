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
			
			// Visit each node connected to the current node 
			for (Edge e : n.getPaths()){
				
			}
		}
	}
}
