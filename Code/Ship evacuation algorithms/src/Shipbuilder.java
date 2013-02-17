import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Shipbuilder {
	int nodeID = 0;
	int edgeID = 0;
	int roomFlow = 10;
	int hallwayFlow = 5;
	int diningFlow = 7;
	int stairFlow = 7;
	int doorFlow = 3;
	Ship currentShip = null;
	
	public Ship createCelebrityXpedition()
	{
		Ship celebX = new Ship(4);
		currentShip = celebX;
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
		
		ArrayList<Node> tempGUESTROOMList = createRoom(Node.NodeType.GUESTROOM, 18, 5);
		ArrayList<Node> tempHALLWAYList = createRoom(Node.NodeType.HALLWAY, 10, 5);
		ArrayList<Node> tempDININGROOMList = createRoom(Node.NodeType.DININGROOM, 10, 5);
		ArrayList<Node> tempSTAIRSList = createRoom(Node.NodeType.STAIRS, 3, 5);
		
		ArrayList<Node> tempList = new ArrayList<Node>();
		
		tempList.addAll(tempGUESTROOMList);
		tempList.addAll(tempHALLWAYList);
		tempList.addAll(tempDININGROOMList);
		tempList.addAll(tempSTAIRSList);
		
		addFifthFloorEdges(tempList, s);
		
		addList(tempList, s.fifthFlor);

		
		
		return s;
	}
	
	private ArrayList<Node> createRoom(Node.NodeType nt, int numberOfRooms, int florNumber)
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
        	Node n = new Node(nodeID++, nt, capacity, florNumber);
        	tempList.add(n);
        }
        
        
		return tempList;
	}
	
	private void addList(ArrayList<Node> list, Map<Integer, Node> map)
	{
		for(Node n:list)
		{
			map.put(n.getID(), n);
		}
	}
	
	private void putFloorTogehter(HashMap<Integer, Node> floor)
	{
		
	}
	
	private Edge createEdge(int flow, int nodeID)
	{
		Edge edge = new Edge(flow, edgeID++, nodeID, currentShip);
		return edge;
	}
	/*
	 * Lang liste med koblinger til hver node. Går igjenom listen og legger edges
	 * til hver node i listen manuelt.
	 */
	private void addFifthFloorEdges(ArrayList<Node> tempList, Ship s)
	{
		/*
		 * Each guest room consists of two nodes, one inne and one outer node.
		 * Hvor partall i gjesterom er indre og oddetall er ytre.
		 */
		for(int i = 0; i<tempList.size(); i++)
		{
			switch (tempList.get(i).getID()) {
	        case 0: //Guestrooms
	        	tempList.get(i).addEdge(createEdge(roomFlow, 1)); break; //Room to room
	        case 1: 
	        	tempList.get(i).addEdge(createEdge(roomFlow, 0)); //Room to room
	        	tempList.get(i).addEdge(createEdge(doorFlow, 18)); break; //Room out to hall
	        case 2:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 3)); break; //Room to room
	        case 3:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 2)); //Room to room
	        	tempList.get(i).addEdge(createEdge(doorFlow, 19)); break; //Room out to hall
	        case 4:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 5)); break;
	        case 5:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 4)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 19)); break;
	        case 6:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 7)); break;
	        case 7:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 6)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 21)); break;
	        case 8:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 9)); break;
	        case 9:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 8)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 21)); break;
	        case 10:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 11)); break;
	        case 11:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 10)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 22)); break;
	        case 12:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 13)); break;
	        case 13:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 12)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 22)); break;
	        case 14:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 15)); break;
	        case 15:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 14)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 23)); break;
	        case 16:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 17)); break;
	        case 17:  
	        	tempList.get(i).addEdge(createEdge(roomFlow, 16)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 23)); break;
	        	
	        	
	        	
	        case 18:  //Hallways
	        	tempList.get(i).addEdge(createEdge(doorFlow, 1)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 19)); break;
	        case 19:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 18)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 20)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 21)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 5)); break;
	        case 20:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 19)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 38)); break;
	        case 21:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 19)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 7)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 9)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 22)); break;
	        case 22:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 21)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 23)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 11)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 13)); break;
	        case 23:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 22)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 24)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 15)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow, 17)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 24)); 
	        	tempList.get(i).addEdge(createEdge(doorFlow*2, 30)); break;
	        case 24:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 23)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 39)); break;
	        case 25:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 37)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 26)); break;
	        case 26:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 25)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 27)); break;
	        case 27:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 26)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlow, 40)); break;
	        	
	        	
	        	
	        case 28:  //Diningroom/bar/dancefloor
	        	tempList.get(i).addEdge(createEdge(diningFlow, 29)); break;
	        case 29:  
	        	tempList.get(i).addEdge(createEdge(diningFlow, 28)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 34)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 35)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 36)); break;
	        case 30:  
	        	tempList.get(i).addEdge(createEdge(doorFlow*2, 23)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 34)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 31)); break;
	        case 31:  
	        	tempList.get(i).addEdge(createEdge(diningFlow, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 32)); break;
	        case 32:  
	        	tempList.get(i).addEdge(createEdge(diningFlow, 31)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 37)); break;
	        case 33:  
	        	tempList.get(i).addEdge(createEdge(diningFlow, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 31)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 32)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 37)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 34)); break;
	        case 34:  
	        	tempList.get(i).addEdge(createEdge(diningFlow, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 37)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 35)); break;
	        case 35:  
	        	tempList.get(i).addEdge(createEdge(diningFlow, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 36)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 37)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 34)); break;
	        case 36:  
	        	tempList.get(i).addEdge(createEdge(diningFlow, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 35)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 37)); break;
	        case 37:  
	        	tempList.get(i).addEdge(createEdge(doorFlow, 25));
	        	tempList.get(i).addEdge(createEdge(diningFlow, 32)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 34)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 35)); 
	        	tempList.get(i).addEdge(createEdge(diningFlow, 36)); break;
	        	
	        	
	        	
	        case 38:  //Stairs
	        	tempList.get(i).addEdge(createEdge(stairFlow, 20)); break;
	        case 39:  
	        	tempList.get(i).addEdge(createEdge(stairFlow, 24)); break;
	        case 40:  
	        	tempList.get(i).addEdge(createEdge(stairFlow, 27)); break;
	        default: break;
			}
		}
		
	}
}
