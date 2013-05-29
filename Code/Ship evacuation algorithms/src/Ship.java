import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Ship {
	public Map<Integer, Node> fifthFlor;
	public Map<Integer, Node> sixthFlor;
	public Map<Integer, Node> forthFlor;
	public Map<Integer, Node> thirdFloor;
	public Map<Integer, Node> seventhFloor;
	
	public List<Node> exits = new ArrayList<Node>();
	
	public Ship(int numberOfDecks)
	{
		if(numberOfDecks == 4)
		{
			fifthFlor = new HashMap<Integer, Node>();
			sixthFlor = new HashMap<Integer, Node>();
			forthFlor = new HashMap<Integer, Node>();
			thirdFloor = new HashMap<Integer, Node>();
		}
		
		if(numberOfDecks == 5)
		{
			seventhFloor = new HashMap<Integer, Node>();
			sixthFlor = new HashMap<Integer, Node>();
		}
	}
	
	public List<Node> getGraphOfShip()
	{
		List<Node> graph = new ArrayList<Node>();
		if(fifthFlor != null)
		for(Iterator<Integer> it = fifthFlor.keySet().iterator(); it.hasNext(); )
		{
			int i = it.next();
			graph.add(fifthFlor.get(i));
		}
		if(sixthFlor != null)
		for(Iterator<Integer> it = sixthFlor.keySet().iterator(); it.hasNext(); )
		{
			int i = it.next();
			graph.add(sixthFlor.get(i));
		}
		if(forthFlor != null)
		for(Iterator<Integer> it = forthFlor.keySet().iterator(); it.hasNext(); )
		{
			int i = it.next();
			graph.add(forthFlor.get(i));
		}
		if(thirdFloor != null)
		for(Iterator<Integer> it = thirdFloor.keySet().iterator(); it.hasNext(); )
		{
			int i = it.next();
			graph.add(thirdFloor.get(i));
		}
		if(seventhFloor != null)
		for(Iterator<Integer> it = seventhFloor.keySet().iterator(); it.hasNext(); )
		{
			int i = it.next();
			graph.add(seventhFloor.get(i));
		}
		
		return graph;
	}
}
