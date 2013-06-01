import java.util.ArrayList;
import java.util.Random;


public class randomGraph {

	ArrayList<Node> listOfNodes;
	int numberOfEdges;
	public randomGraph(int numberOFNodes, int numberOfExits, int numberOfEdges)
	{
		this.numberOfEdges = numberOfEdges;
		listOfNodes	= new ArrayList<Node>();
		for(int i = 0; i < numberOFNodes; i++)
		{
			Node n = new Node(i, Node.NodeType.HALLWAY, 10, 1);
			
			listOfNodes.add(n);
		}
	}
	
	private void addEdges()
	{
		
		for(Node n : listOfNodes)
		{
			int tempNumber = numberOfEdges;
			
			tempNumber -= n.getPaths().size();
			
			if(tempNumber <= 0)
			{
				continue;
			}
			else if(n.getPaths().size() > numberOfEdges)
			{
				continue;
			}
			else
			{
				createRandomEdges(n);
			}
		}
	}
	
	private Edge createRandomEdges(Node node)
	{
		Random rand = new Random();
		
		int nodeInEdge = rand.nextInt(listOfNodes.size()-1);
		
		if(nodeInEdge == node.getID())
		{
			return createRandomEdges(node);
		}
		
		for(Edge e : node.getPaths())
		{
			if(e.getNode().getID() ==  nodeInEdge)
			{
				return createRandomEdges(node);
			}
		}
		
		Edge edge = new Edge(50, 5, listOfNodes.get(nodeInEdge), 5, 5);
		
		return edge;
	}

	private void createEdgeToExit(Node node)
	{
		
	}
	
	private void checkIfGotWayToExit()
	{
		for(Node n : listOfNodes)
		{
			
			if(n.hasWayToExit || n.isExit())
			{
				n.hasWayToExit = true;
				continue;
			}
			else
			{
				lookForExit(n);
			}
		}
		
		for(Node n : listOfNodes)
		{
			if(!n.hasWayToExit)
			{
				createEdgeToExit(n);
			}
		}
	}
	
	private void lookForExit(Node node)
	{
		ArrayList<Node> checkedNodes = new ArrayList<Node>();
		
		if((checkExitInNeighburs(node) && !node.hasWayToExit) || node.isExit())
		{
			node.hasWayToExit = true;
			
			for(Edge e : node.getPaths())
			{
				e.getNode().hasWayToExit = true;
			}
		}
	}
	
	private boolean checkExitInNeighburs(Node n)
	{
		for(Edge edge : n.getPaths())
		{
			if(edge.getNode().hasWayToExit)
			{
				return true;
			}
		}
		return false;
	}


}
