import java.util.ArrayList;


public class Node implements Comparable<Node> {
	public enum NodeType { STAIRS, GUESTROOM, HALLWAY, DININGROOM, GAMEROOM, SHOP }
	private float chanceOfDeath;
	private int capacity;
	private NodeType nodeType;
	private ArrayList<Edge> listOfPaths;
	private int nodeID;	
	private int florNumber;
	private boolean isExit;
	private int amountOfPheromones;
	public boolean hasWayToExit = false;
	public double minDistance = Double.POSITIVE_INFINITY;	
	public Node previous;
	
	public Node(int NodeID, NodeType nt, int cap, int fNumber)
	{
		this.nodeType = nt;
		this.capacity = cap;
		this.nodeID = NodeID;
		this.florNumber = fNumber;
		listOfPaths = new ArrayList<Edge>();
	}
	
	public int getID()
	{
		return nodeID;
	}
	
	public void addEdge(Edge e)
	{
		listOfPaths.add(e);
	}
	public ArrayList<Edge> getPaths()
	{
		return listOfPaths;
	}

	@Override
	public int compareTo(Node o) {
		return Double.compare(minDistance, o.minDistance);
	}
}
