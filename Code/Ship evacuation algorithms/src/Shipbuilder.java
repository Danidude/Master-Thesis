
public class Shipbuilder {
	
	public Ship createCelebrityXpedition()
	{
		Ship celebX = new Ship(4);
		return null;
	}
	
	private Ship createPanormaFlor()
	{
		/**
		 * Number of rooms: 9x2
		 * Number of hallways:10
		 * Number of exits:1
		 * Number of diningroom:10
		 * Number of steairs:3
		 * 
		 * Number of total nodes: 42
		 */
		
		
		
		return null;
	}
	
	private Node createRoom(Node.NodeType nt, int NodeID)
	{
		int capacity = 0;
        
        switch (nt) {
        case STAIRS: capacity = 10; break;
        case GUESTROOM: capacity = 6; break;
        case HALLWAY: capacity = 7; break;
        case DININGROOM: capacity = 10; break;
        case GAMEROOM: capacity = 10; break;
        case SHOP: capacity = 10; break;
        }
        Node n = new Node(NodeID, nt, capacity);
        
		return n;
	}
}
