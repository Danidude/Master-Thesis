import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Node implements Comparable<Node> {
	public enum NodeType { STAIRS, GUESTROOM, HALLWAY, DININGROOM, GAMEROOM, SHOP, EXERCISEROOM, MASSAGEROOM, HOTTUBROOM, GUESTRELATIONS, LIBRARY,  }
	private double chanceOfDeath;
	public int capacity;
	private NodeType nodeType;
	private ArrayList<Edge> listOfPaths;
	public ArrayList<Human> currentHumansInNode;
	private int nodeID;	
	private int florNumber;
	private boolean isExit;
	private int amountOfPheromones;
	public boolean hasWayToExit = false;
	public double minDistance = Double.POSITIVE_INFINITY;	
	public Node previous;
	public int movementAllowenceNeeded;
	
	public Node(int NodeID, NodeType nt, int cap, int fNumber)
	{
		currentHumansInNode = new ArrayList<Human>();
		this.nodeType = nt;
		this.capacity = cap;
		this.nodeID = NodeID;
		this.florNumber = fNumber;
		listOfPaths = new ArrayList<Edge>();
		isExit = false;
		chanceOfDeath = 0.0;
		capacity = 10;
		movementAllowenceNeeded = 8;
	}
	
	public Node(){
		
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
	
	public boolean isExit() {
		return isExit;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}
	
	public double getChanceOfDeath() {
		return chanceOfDeath;
	}

	public void setChanceOfDeath(double chanceOfDeath) {
		this.chanceOfDeath = chanceOfDeath;
	}
	public void testOverCapacityInNode()
	{
		if(isExit)
			return;
		int numberOfLiving = currentHumansInNode.size();
		Random deathRandom = new Random();
		for(Iterator<Human> it = currentHumansInNode.iterator(); it.hasNext();)
		{
			Human h = it.next();
			
			if(chanceOfDeath+(currentHumansInNode.size()/200) > deathRandom.nextDouble())
			{
				h.isDead = true;
				numberOfLiving--;
				it.remove();
				if(numberOfLiving <= capacity)
				{
					return;
				}
			}
		}
	}
}
