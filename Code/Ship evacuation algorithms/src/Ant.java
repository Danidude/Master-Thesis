import java.util.List;
import java.util.Random;



public class Ant {
	private List<Node> nodePath;
	private Node currentNode;
	public Ant(Node startNode)
	{
		currentNode  = startNode;
		nodePath.add(currentNode);
	}
	
	public void addNodeToList(Node n)
	{
		nodePath.add(n);
	}
	
	public Node chooseNextPath()
	{
		List<Edge> listOfPaths = currentNode.getPaths();
		float totalPheremonesAndAtracctiveness = 0;
		for (Edge e : listOfPaths) 
		{
			totalPheremonesAndAtracctiveness += e.getPheremonesAndAttractiveness();
		}
		
		float chosenPath;
		
		Random rand = new Random();
		chosenPath = rand.nextFloat();
		chosenPath *= totalPheremonesAndAtracctiveness;
		
		int sum = 0;
		for (Edge e : listOfPaths)
		{
			sum += e.getPheremonesAndAttractiveness();
			if(chosenPath < sum)
			{
				setNewCurrentNode(e.getNode());
				return e.getNode();
			}
		}
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
	
	private void setNewCurrentNode(Node newNode)
	{
		currentNode = newNode;
		nodePath.add(newNode);
	}
}
