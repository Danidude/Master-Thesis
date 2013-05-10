import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Ant {
	private List<Node> nodePath;
	private List<Edge> edgesTaken;
	private Node currentNode;
	private int pheromones = 100;
	
	public Ant(Node startNode)
	{
		currentNode  = startNode;
		nodePath = new ArrayList<Node>();
		edgesTaken = new ArrayList<Edge>();
		nodePath.add(currentNode);
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
		
		double totalPheremonesAndAtracctiveness = 0;
		for (Edge e : listOfPaths) 
		{
			totalPheremonesAndAtracctiveness += e.getPheremonesAndAttractiveness();
		}
		
		double chosenPath;
		
		Random rand = new Random();
		
		double randDuouble = 0;
		randDuouble = rand.nextDouble();
		
		chosenPath = randDuouble * totalPheremonesAndAtracctiveness;
		
		double sum = 0;
		for (Edge e : listOfPaths)
		{
			sum += e.getPheremonesAndAttractiveness();
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
	
	public void despencePheromones()
	{
		int pher = pheromones/edgesTaken.size();
		for(Edge e : edgesTaken)
		{
			e.addPheremones(pher);
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
