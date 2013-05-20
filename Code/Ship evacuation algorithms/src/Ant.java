import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Ant {
	private List<Node> nodePath;
	private List<Edge> edgesTaken;
	private Node currentNode;
	private int pheromones = 200;
	private int humanID;
	private boolean pheremonsFromEdge;
	
	public Ant(Node startNode, int humanID, boolean pheremonsFromEdge)
	{
		currentNode  = startNode;
		nodePath = new ArrayList<Node>();
		edgesTaken = new ArrayList<Edge>();
		nodePath.add(currentNode);
		this.humanID = humanID;
		this.pheremonsFromEdge = pheremonsFromEdge;
	}
	
	public void addNodeToList(Node n)
	{
		nodePath.add(n);
	}
	
	public int getHumanID()
	{
		return humanID;
	}
	
	public Node chooseNextPath()
	{
		List<Edge> tempList = new ArrayList<Edge>();
		List<Edge> listOfPaths = new ArrayList<Edge>(currentNode.getPaths());
		
		if(currentNode.isExit())
		{
			return currentNode;
		}
		
		for(Edge e : currentNode.getPaths())
		{
			if(nodePath.contains(e.getNode()))
			{
				tempList.add(e);
			}
		}
		
		//listOfPaths.removeAll(tempList);
		
		if(listOfPaths.isEmpty())
		{
		//	reset();
		//	return null;
		}
		
		float totalPheremonesAndAtracctiveness = 0;
		for (Edge e : listOfPaths) 
		{
			totalPheremonesAndAtracctiveness += e.getPheremonesAndAttractivenessForThatHuman(humanID, pheremonsFromEdge);
			//totalPheremonesAndAtracctiveness += e.getPheremonesAndAttractiveness();
			
			if(e.getPheremonesForThatHuman(humanID, pheremonsFromEdge)<0)
			{
				System.out.println("Edges get negative pheremones"+e.getPheremonesForThatHuman(humanID, pheremonsFromEdge));
			}
		}
		
		float chosenPath;
		
		Random rand = new Random();
		
		float randDuouble = 0;
		randDuouble = rand.nextFloat();
		
		chosenPath = randDuouble * totalPheremonesAndAtracctiveness;
		
		float sum = 0;
		for (Edge e : listOfPaths)
		{
			sum += e.getPheremonesAndAttractivenessForThatHuman(humanID, pheremonsFromEdge);
			//sum += e.getPheremonesAndAttractiveness();
			if(chosenPath <= sum)
			{
				edgesTaken.add(e);
				setNewCurrentNode(e.getNode());
				return e.getNode();
			}
			
		}
		
		System.out.println(sum+" ---- "+totalPheremonesAndAtracctiveness + " ------ "+randDuouble);
		
		System.out.println("ERROR! choosepath ant");
		return null;
	}
	
	public List<Node> getPath()
	{
		return nodePath;
	}
	
	public boolean isAtExit()
	{
		return currentNode.isExit();
	}
	
	public void changePheromonesLevel(int newPhermones)
	{
		pheromones = newPhermones;
	}
	
	public void despencePheromones(double deadlyness, double densety, boolean lookingAtShortest)
	{
		
		if(deadlyness > 1.0)
		{
			deadlyness = 1;
		}
		
		int pher = 0;
		if(lookingAtShortest)
		{
			if(edgesTaken.size() == 0)
			{
				return;
			}
			pher = pheromones/howManyTurnsThePathTakes();
			
		}
		else
		{
			if(edgesTaken.size() == 0)
			{
				return;
			}
			pher = pheromones/howManyTurnsThePathTakes();
			pher -= pher*deadlyness;
		}
		
		
		if(pher <= 1.0)
		{
			pher = 1;
		}
		
		for(Edge e : edgesTaken)
		{
			//e.addPheremones(pher);
			e.addPheremonesForThatHuman(pher, humanID, pheremonsFromEdge);
		}
	}
	
	private void setNewCurrentNode(Node newNode)
	{
		/*if(nodePath.contains(newNode))
		{
			int a = nodePath.lastIndexOf(newNode);
			for(int i = a; i<=nodePath.size(); i++)
			{
				nodePath.
			}
			
			List<Node> tempList = new ArrayList<Node>();
			
			tempList = nodePath.subList(0, a);
			
			nodePath = tempList;
			currentNode = newNode;
			return;
			
		}*/
		
		
		currentNode = newNode;
		nodePath.add(newNode);
	}
	
	public void reset()
	{
		currentNode = nodePath.get(0);
		nodePath = new ArrayList<Node>();
		edgesTaken = new ArrayList<Edge>();
		nodePath.add(currentNode);
	}
	
	public void rebuildPath()
	{
		Node prevNode = null;
		boolean notFirst = true;
 		ArrayList<Edge> tempList = new ArrayList<Edge>();
		for(Node n : nodePath)
		{
			 
			if(notFirst)
			{
				prevNode = n;
				notFirst = false;
			}
			else
			{
				for(Edge e : prevNode.getPaths())
				{
					if(e.getNode() == n)
					{
						tempList.add(e);
					}
				}
				
			}
			prevNode = n;
		}
		
		edgesTaken = tempList;
	}
	
	public List<Edge> getEdgesTaken()
	{
		return edgesTaken;
	}
	
	private int howManyTurnsThePathTakes()
	{
		int turns = 1;
		for(Edge e: edgesTaken)
		{
			turns += e.getWeight();
		}
		
		return turns;
	}
}
