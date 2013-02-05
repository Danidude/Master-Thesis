import java.util.ArrayList;
import java.util.Map;


public class Shipbuilder {
	int nodeID = 0;
	public Ship createCelebrityXpedition()
	{
		Ship celebX = new Ship(4);
		celebX = createPanormaFlor(celebX);
		return celebX;
	}
	
	private Ship createPanormaFlor(Ship s)
	{
		/**
		 * Number of rooms: 9x2
		 * Number of hallways:10
		 * Number of diningroom:10
		 * Number of steairs:3
		 * 
		 * Number of total nodes: 41
		 */
		
		ArrayList<Node> tempGUESTROOMList = createRoom(Node.NodeType.GUESTROOM, 18);
		ArrayList<Node> tempDININGROOMList = createRoom(Node.NodeType.DININGROOM, 10);
		ArrayList<Node> tempHALLWAYList = createRoom(Node.NodeType.HALLWAY, 10);
		ArrayList<Node> tempSTAIRSList = createRoom(Node.NodeType.STAIRS, 3);
		
		addList(tempGUESTROOMList, s.fifthFlor);
		addList(tempDININGROOMList, s.fifthFlor);
		addList(tempHALLWAYList, s.fifthFlor);
		addList(tempSTAIRSList, s.fifthFlor);
		
		
		return s;
	}
	
	private ArrayList<Node> createRoom(Node.NodeType nt, int numberOfRooms)
	{
		int capacity = 0;
		ArrayList<Node> tempList = new ArrayList<Node>();
        
        switch (nt) {
        case STAIRS: capacity = 10; break;
        case GUESTROOM: capacity = 6; break;
        case HALLWAY: capacity = 7; break;
        case DININGROOM: capacity = 10; break;
        case GAMEROOM: capacity = 10; break;
        case SHOP: capacity = 10; break;
        }
        for(int room = 0; room<numberOfRooms; room++)
        {
        	Node n = new Node(nodeID++, nt, capacity);
        	tempList.add(n);
        }
        
        
		return tempList;
	}
	
	private void addList(ArrayList<Node> list, Map<Integer, Node> map)
	{
		for(Node n:list)
		{
			map.put(n.NodeID, n);
		}
	}
}
