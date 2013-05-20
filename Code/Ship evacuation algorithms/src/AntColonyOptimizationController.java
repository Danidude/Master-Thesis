import java.util.List;


public class AntColonyOptimizationController {
	int numberOfAnts;
	List<Edge> edges;
	List<Node> nodes;
	Node currentNode;
	List<Node> bestPath;
	Ant ant;
	double evaporationRate = 0.5;
	int humanID;
	boolean pheremonsFromEdge;
	boolean lookingForShortest;
	boolean goingForMostSafePath;
	
	public AntColonyOptimizationController(int howManyAnts, List<Node> nodes, List<Edge> edges, boolean pheremonsFromEdge, boolean lookingForShortest, boolean goingForMostSafePath)
	{
		this.nodes = nodes;
		numberOfAnts = howManyAnts;
		this.edges = edges;
		this.pheremonsFromEdge = pheremonsFromEdge;
		this.lookingForShortest = lookingForShortest;
		this.goingForMostSafePath = goingForMostSafePath;
	}
	
	public void changeCurrentNode(Node newNode, int humanID)
	{
		currentNode = newNode;
		this.humanID = humanID;
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
			int counter = 0;
			while(!ant.isAtExit())
			{
				if(counter > 5000)
				{
					//ant.reset();
					counter = 0;
					
				}
				else if(counter%500 == 0 && counter != 0)
				{
					for(Edge e: edges)
					{
						e.setPheremonesForThatHuman(0, humanID, pheremonsFromEdge);
					}
					ant.reset();
					System.out.println("Reset pheremoes."+ant.getHumanID());
				}
				
				ant.chooseNextPath();
				
				counter++;
			}
			counter = 0;
			
			double deadlyness = getHigestDeadlyness(ant.getPath());
			double densety = getHigestDensety(ant.getEdgesTaken());
			
			ant.rebuildPath();
			
			ant.despencePheromones(deadlyness, densety, lookingForShortest);
			
			
			if(goingForMostSafePath)
			{
				checkSolutionLethalFirst(ant.getPath());
				//checkSolutionShortestFirst(ant.getPath());
			}
			else
			{
				//checkSolutionShortestFirst(ant.getPath());
				shortestFirstFound(ant.getPath());
			}
			
			
			
			if(i%2 == 0)
			{
				Evaporate();
			}
		}
		//printPath(bestPath);
		return bestPath;
	}
	
	private void createAnt(Node startNode)
	{
		ant = new Ant(startNode, humanID, pheremonsFromEdge);
		
		//System.out.println("New ant");
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
	private void checkSolutionLethalFirst(List<Node> newPath)
	{
		checkHowLeathal(newPath, true);
	}
	
	private void checkSolutionShortestFirst(List<Node> newPath)
	{
		checkShortestPath(newPath, true);
	}
	
	private void checkHowLeathal(List<Node> newPath, boolean first)
	{
		if(bestPath == null && newPath.get(newPath.size()-1).isExit())
		{
			bestPath = newPath;
			return;
		}
		float bestPathSurv = howLethalIsList(bestPath);
		float newPathSurv = howLethalIsList(newPath);
		if(first)
		{
			if(bestPathSurv < newPathSurv)
			{
				bestPath = newPath;
			}
			else if(bestPathSurv == newPathSurv)
			{
				checkShortestPath(newPath, false);
			}
		}
		else if(!first)
		{
			if(bestPathSurv < newPathSurv)
			{
				bestPath = newPath;
			}
			else if(bestPathSurv == newPathSurv)
			{
				
			}
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
	
	private void checkShortestPath(List<Node> newPath, boolean first)
	{
		if(newPath.get(newPath.size()-1).isExit())
		if(!first)
		{
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
		else if(first)
		{
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
				checkHowLeathal(newPath, false);
			}
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
		System.out.println("with chances of survival: "+pathSurv);
	}
	
	public void Evaporate()
	{
		for(Node n : nodes)
		{
			for(Edge e : n.getPaths())
			{
				e.addPheremonesForThatHuman(-(e.getPheremonesForThatHuman(humanID, pheremonsFromEdge)*evaporationRate), humanID, pheremonsFromEdge);
				//e.addPheremones(-e.getPheremones()*evaporationRate);
				
				
				e.addPheremonesForThatHuman(-(e.getPheremonesForThatHuman(humanID, pheremonsFromEdge)*(e.getNode().getChanceOfDeath())), humanID, pheremonsFromEdge);
				//e.addPheremones(-e.getPheremones()*n.getChanceOfDeath());
			}
		}
	}
	
	private void shortestFirstFound(List<Node> newPath)
	{
		if(bestPath == null && newPath.get(newPath.size()-1).isExit())
		{
			bestPath = newPath;
			return;
		}
		else if(newPath.size() < bestPath.size())
		{
			bestPath = newPath;
		}
	}
	
	private double getHigestDeadlyness(List<Node> list)
	{
		double d = 0;
		for(Node n : list)
		{
			if(n.getChanceOfDeath() > d)
			{
				d = n.getChanceOfDeath();
				
				if(d > 1)
				{
					System.out.println("ACO higestdeadlyness");
				}
			}
		}
		
		
		return d;
	}
	
	private double getHigestDensety(List<Edge> list)
	{
		double d = 1.0;
		for(Edge e : list)
		{
			if(d < e.getWeight())
			{
				d = e.getWeight();
			}
		}
		return d;
	}
	
}
