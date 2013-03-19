import java.util.List;


public class AntColonyOptimizationController {
	int numberOfAnts;
	Node currentNode;
	List<Node> bestPath;
	Ant ant;
	
	public AntColonyOptimizationController(int howManyAnts, Node currentNode)
	{
		numberOfAnts = howManyAnts;
		this.currentNode = currentNode;
		
		if(currentNode.isExit())
		{
			//Tell the system the passanger is safe
		}
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
		}
		return bestPath;
	}
	
	private void createAnt(Node startNode)
	{
		ant = new Ant(startNode);
	}
	
	private Node runAnt()
	{
		return ant.chooseNextPath();
	}
	/*
	 * Might have to change it so leathal is checked before the size of the list
	 */
	private void checkSolution(List<Node> newPath)
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
	
	private void checkHowLeathal()
	{
		
	}
	
}
