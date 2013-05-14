import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Ant {
	private List<Node> nodePath;
	private List<Edge> edgesTaken;
	private Node currentNode;
	private int pheromones = 40;
	private int humanID;
	
	public Ant(Node startNode, int humanID)
	{
		currentNode  = startNode;
		nodePath = new ArrayList<Node>();
		edgesTaken = new ArrayList<Edge>();
		nodePath.add(currentNode);
		this.humanID = humanID;
	}
	
	public void addNodeToList(Node n)
	{
		nodePath.add(n);
	}
	
	public Node chooseNextPath()
	{
		List<Edge> tempList = new ArrayList<Edge>();
		List<Edge> listOfPaths = new ArrayList<Edge>(currentNode.getPaths());
		
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
			totalPheremonesAndAtracctiveness += e.getPheremonesAndAttractivenessForThatHuman(humanID);
			//totalPheremonesAndAtracctiveness += e.getPheremonesAndAttractiveness();
		}
		
		float chosenPath;
		
		Random rand = new Random();
		
		float randDuouble = 0;
		randDuouble = rand.nextFloat();
		
		chosenPath = randDuouble * totalPheremonesAndAtracctiveness;
		
		float sum = 0;
		for (Edge e : listOfPaths)
		{
			sum += e.getPheremonesAndAttractivenessForThatHuman(humanID);
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
	
	public void despencePheromones(double deadlyness)
	{
		if(edgesTaken.size() == 0)
		{
			return;
		}
		int pher = pheromones/edgesTaken.size();
		
		pher -= pher*deadlyness;
		
		
		if(pher == 0)
		{
			pher = 1;
		}
		
		
		
		for(Edge e : edgesTaken)
		{
			//e.addPheremones(pher);
			e.addPheremonesForThatHuman(pher, humanID);
		}
	}
	
	private void setNewCurrentNode(Node newNode)
	{
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
}
