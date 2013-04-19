import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Ship {
	public Map<Integer, Node> fifthFlor;
	
	public List<Node> exits = new ArrayList<Node>();
	
	public Ship(int numberOfDecks)
	{
		fifthFlor = new HashMap<Integer, Node>();
	}
	
	public List<Node> getGraphOfShip()
	{
		List<Node> graph = new ArrayList<Node>();
		for(int i = 0; i < fifthFlor.size(); i++)
		{
			graph.add(fifthFlor.get(i));
		}
		
		return graph;
	}
}
