import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Shipbuilder {
	int nodeID = 0;
	int edgeID = 0;
	int roomFlowAwayFromExit = 5;
	int hallwayFlowAwayFromExit = 5;
	int diningFlowAwayFromExit = 5;
	int stairFlowAwayFromExit = 5;
	int doorFlowAwayFromExit = 5;
	
	int roomFlowNormal = 25;
	int hallwayFlowNormal = 25;
	int diningFlowNormal = 25;
	int stairFlowNormal = 25;
	int doorFlowNormal = 25;
	
	int roomFlowToExit = 100;
	int hallwayFlowToExit = 100;
	int diningFlowToExit = 100;
	int stairFlowToExit = 100;
	int doorFlowToExit = 100;
	
	Ship currentShip = null;
	
	public Ship createCelebrityXpedition()
	{
		Ship celebX = new Ship(4);
		currentShip = celebX;
		celebX = createPanormaFlor(celebX);
		celebX.exits.add(celebX.fifthFlor.get(40));
		celebX.fifthFlor.get(40).setExit(true);
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
		
		addList(tempList, s.fifthFlor);
		
		addFifthFloorEdges(tempList, s);
		
		

		
		
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
	
	private Edge createEdge(int attractiveness, int node)
	{
		Edge edge = new Edge(attractiveness, edgeID++, currentShip.fifthFlor.get(node), 1, 10);
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
	        	tempList.get(i).addEdge(createEdge(roomFlowToExit, 1)); break; //Room to room
	        case 1: 
	        	tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 0)); //Room to room
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 18)); break; //Room out to hall
	        case 2:  
	        	tempList.get(i).addEdge(createEdge(roomFlowToExit, 3)); break; //Room to room
	        case 3:  
	        	tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 2)); //Room to room
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 19)); break; //Room out to hall
	        case 4:  
	        	tempList.get(i).addEdge(createEdge(roomFlowToExit, 5)); break;
	        case 5:  
	        	tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 4)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 19)); break;
	        case 6:  
	        	tempList.get(i).addEdge(createEdge(roomFlowToExit, 7)); break;
	        case 7:  
	        	tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 6)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 21)); break;
	        case 8:  
	        	tempList.get(i).addEdge(createEdge(roomFlowToExit, 9)); break;
	        case 9:  
	        	tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 8)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 21)); break;
	        case 10:  
	        	tempList.get(i).addEdge(createEdge(roomFlowToExit, 11)); break;
	        case 11:  
	        	tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 10)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 22)); break;
	        case 12:  
	        	tempList.get(i).addEdge(createEdge(roomFlowToExit, 13)); break;
	        case 13:  
	        	tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 12)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 22)); break;
	        case 14:  
	        	tempList.get(i).addEdge(createEdge(roomFlowToExit, 15)); break;
	        case 15:  
	        	tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 14)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 23)); break;
	        case 16:  
	        	tempList.get(i).addEdge(createEdge(roomFlowToExit, 17)); break;
	        case 17:  
	        	tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 16)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 23)); break;
	        	
	        	
	        	
	        case 18:  //Hallways
	        	tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 1)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 19)); break;
	        case 19:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 18)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 20)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 21)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 5)); break;
	        case 20:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 19)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 38)); break;
	        case 21:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 19)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 7)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 9)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 22)); break;
	        case 22:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 21)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 23)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 11)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 13)); break;
	        case 23:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 22)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 15)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 17)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 24)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 30)); break;
	        case 24:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 23)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 39)); break;
	        case 25:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 37)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 26)); break;
	        case 26:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 25)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 27)); break;
	        case 27:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 26)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 40)); break;
	        	
	        	
	        	
	        case 28:  //Diningroom/bar/dancefloor
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 29)); break;
	        case 29:  
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 28)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowNormal, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 34)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 35)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 36)); break;
	        case 30:  
	        	tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 23)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowNormal, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 34)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowNormal, 31)); break;
	        case 31:  
	        	tempList.get(i).addEdge(createEdge(diningFlowNormal, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 32)); break;
	        case 32:  
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 31)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 37)); break;
	        case 33:  
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 31)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 32)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 37)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 34)); break;
	        case 34:  
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 37)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 35)); break;
	        case 35:  
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 36)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 37)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 34)); break;
	        case 36:  
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowNormal, 35)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 37)); break;
	        case 37:  
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 25));
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 32)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 34)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 35)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 36)); break;
	        	
	        	
	        	
	        case 38:  //Stairs
	        	tempList.get(i).addEdge(createEdge(stairFlowToExit, 20)); break;
	        case 39:  
	        	tempList.get(i).addEdge(createEdge(stairFlowToExit, 24)); break;
	        case 40:  
	        	tempList.get(i).addEdge(createEdge(stairFlowAwayFromExit, 27)); break; //exit
	        default: break;
			}
		}
		
	}
}
