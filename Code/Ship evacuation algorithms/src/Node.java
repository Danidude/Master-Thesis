import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


public class Node implements Comparable<Node> {
	public enum NodeType { STAIRS, GUESTROOM, HALLWAY, DININGROOM, GAMEROOM, SHOP, EXERCISEROOM, MASSAGEROOM, HOTTUBROOM, GUESTRELATIONS, LIBRARY, ENTERTAINMENTROOM  }
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
	public ArrayList<Node> nodesUpstairs;
	private Map<Integer, Double> pheromonesForEachHuman;
	public int lethalNessTimeCounter;
	public boolean haveSartetFireUpstairs;
	
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
		capacity = 32;
		movementAllowenceNeeded = 300;
		nodesUpstairs = new ArrayList<Node>();
		lethalNessTimeCounter = 0;
		haveSartetFireUpstairs = false;
		pheromonesForEachHuman = new HashMap<Integer, Double>();
		
		
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
		if(chanceOfDeath > 1.0)
		{
			this.chanceOfDeath = 1.0;
			return;
		}
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
	
	public double getNodeDensety()
	{
		return (double)currentHumansInNode.size()/((double)movementAllowenceNeeded*3.0/100.0);
	}
	
	public double getPheremonesForThatHuman(int humanID)
	{
		if(!pheromonesForEachHuman.containsKey(humanID))
		{
			pheromonesForEachHuman.put(humanID, 0.0);
		}
		return pheromonesForEachHuman.get(humanID);
	}
	
	public void addPheremonesForThatHuman(double d, int humanID)
	{
		
		pheromonesForEachHuman.put(humanID, pheromonesForEachHuman.get(humanID)+d);
		if(pheromonesForEachHuman.get(humanID) ==  null || pheromonesForEachHuman.get(humanID) < 0.0)
		{
			System.out.println("It is done. Check evaporate.");
		}
	}
	
	public double getPheremonesAndAttractivenessForThatHuman(int humanID)
	{
		if(!pheromonesForEachHuman.containsKey(humanID))
		{
			pheromonesForEachHuman.put(humanID, 0.0);
		}
		
		if(chanceOfDeath > 0)
		{
			
			double f =  pheromonesForEachHuman.get(humanID); //- (double)((double)attractiveness*node.getChanceOfDeath()));
			if(f <= 0)
			{
				return 0;
			}
			return f;
		}
		else
		return  pheromonesForEachHuman.get(humanID);
	}
	
	public void setPheremonesForThatHuman(double pheremones, int humanID)
	{
		if(pheremones < 0)
		{
			System.out.println("Adding negativ!");
		}
		
		pheromonesForEachHuman.put(humanID, pheremones);
		if(pheromonesForEachHuman.get(humanID) ==  null)
		{
			System.out.println("It is done");
		}
	}
	
	public void resetPheremonesForEachHuman()
	{
		pheromonesForEachHuman.clear();
	}
}
