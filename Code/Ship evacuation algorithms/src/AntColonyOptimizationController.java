import java.util.List;


public class AntColonyOptimizationController {
	int numberOfAnts;
	List<Edge> edges;
	List<Node> nodes;
	Node currentNode;
	List<Node> bestPath;
	Ant ant;
	double evaporationRate = 0.8;
	
	public AntColonyOptimizationController(int howManyAnts, List<Node> nodes)
	{
		this.nodes = nodes;
		numberOfAnts = howManyAnts;
	}
	
	public void changeCurrentNode(Node newNode)
	{
		currentNode = newNode;
		if(currentNode.isExit())
		{
			//Tell the system the passanger is safe
		}
	}
	
	public List<Node> findWay()
	{
		for(int i = 0; i<numberOfAnts; i++)
		{
			createAnt(currentNode);
			while(!ant.isAtExit())
			{
				ant.chooseNextPath();
			}
			ant.despencePheromones();
			checkSolution(ant.getPath());
			
			if(i%10 == 0)
			{
				Evaporate();
			}
		}
		printPath(bestPath);
		return bestPath;
	}
	
	private void createAnt(Node startNode)
	{
		ant = new Ant(startNode);
	}
	
	/*
	 * Unused
	 */
	private Node runAnt()
	{
		return ant.chooseNextPath();
	}
	/*
	 * Might have to change it so leathal is checked before the size of the list
	 */
	private void checkSolution(List<Node> newPath)
	{
		checkHowLeathal(newPath);
	}
	
	private void checkHowLeathal(List<Node> newPath)
	{
		float bestPathSurv = howLethalIsList(bestPath);
		float newPathSurv = howLethalIsList(newPath);
		if(bestPathSurv < newPathSurv)
		{
			bestPath = newPath;
		}
		else if(bestPathSurv == newPathSurv)
		{
			checkShortestPath(newPath);
		}
	}
	
	private float howLethalIsList(List<Node> list)
	{
		if(list == null)
		{
			return 0;
		}
		float howHighSurv = 1;
		for(Node n : list)
		{
			howHighSurv *= (1 - n.getChanceOfDeath());
		}
		
		return howHighSurv;
	}
	
	private void checkShortestPath(List<Node> newPath)
	{
		if(newPath.get(newPath.size()-1).isExit())
			if(bestPath == null)
			{
				bestPath = newPath;
			}
			else if(bestPath.size() > newPath.size())
			{
				bestPath = newPath;
			}
			else if (bestPath.size() == newPath.size()) 
			{
				
			}
	}
	
	private void printPath(List<Node> path)
	{
		float pathSurv = howLethalIsList(path);
		
		System.out.print("Final path is: ");
		for(Node n : path)
		{
			System.out.print(n.getID()+" ");
		}
		System.out.println("with chances of surivel: "+pathSurv);
	}
	
	public void Evaporate()
	{
		for(Node n : nodes)
		{
			for(Edge e : n.getPaths())
			{
				e.addPheremones(e.getPheremones()*evaporationRate*e.getNode().getChanceOfDeath());
			}
		}
	}
	
}
