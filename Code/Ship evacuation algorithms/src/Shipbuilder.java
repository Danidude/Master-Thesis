import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Shipbuilder {
	int nodeID = 0;
	int edgeID = 0;
	int roomFlowAwayFromExit = 12;
	int hallwayFlowAwayFromExit = 12;
	int diningFlowAwayFromExit = 12;
	int stairFlowAwayFromExit = 12;
	int doorFlowAwayFromExit = 12;
	
	int roomFlowNormal = 25;
	int hallwayFlowNormal = 25;
	int diningFlowNormal = 25;
	int stairFlowNormal = 25;
	int doorFlowNormal = 25;
	
	int roomFlowToExit = 50;
	int hallwayFlowToExit = 50;
	int diningFlowToExit = 50;
	int stairFlowToExit = 50;
	int doorFlowToExit = 50;
	
	Ship currentShip = null;
	
	public Ship createCelebrityXpedition()
	{
		Ship celebX = new Ship(4);
		currentShip = celebX;
		ArrayList<Node> fightFloor = createPanormaFlor(celebX);
		ArrayList<Node> sixthFloor = createSunrise(celebX);
		ArrayList<Node> forthFloor = createVistaFloor(celebX);
		ArrayList<Node> missingStairs = createMissingStairsFromFifth(celebX);
		
		addFifthFloorEdges(fightFloor, celebX);
		addSixthFloorEdges(sixthFloor, celebX);
		addForthFLoorEdges(forthFloor, celebX);
		addForthFLoorEdges(missingStairs, celebX);
		
		
		celebX.exits.add(celebX.fifthFlor.get(39));
		celebX.exits.add(celebX.fifthFlor.get(40));
		celebX.fifthFlor.get(39).setExit(true);
		celebX.fifthFlor.get(40).setExit(true);
		
		celebX.exits.add(celebX.forthFlor.get(140));
		celebX.exits.add(celebX.forthFlor.get(141));
		celebX.forthFlor.get(140).setExit(true);
		celebX.forthFlor.get(141).setExit(true);
		
		return celebX;
	}
	
	private ArrayList<Node> createPanormaFlor(Ship s)
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
		
		return tempList;
	}
	
	private ArrayList<Node> createSunrise(Ship s)
	{
		/**
		 * Number of rooms 1*4 + 2*2
		 * Number of hallways 4
		 * Exercise room 1
		 * Massage room 1
		 * Hot tub room 8
		 * Stairs 1
		 * 
		 * total: 23
		 */
		ArrayList<Node> tempGUESTROOMList = createRoom(Node.NodeType.GUESTROOM, 8, 6);
		ArrayList<Node> tempHALLWAYList = createRoom(Node.NodeType.HALLWAY, 4, 6);
		ArrayList<Node> tempSTAIRSList = createRoom(Node.NodeType.STAIRS, 1, 6);
		ArrayList<Node> tempEXCERCISEList = createRoom(Node.NodeType.EXERCISEROOM, 1, 6);
		ArrayList<Node> tempMASSAGEList = createRoom(Node.NodeType.MASSAGEROOM, 1, 6);
		ArrayList<Node> tempHOTTUBList = createRoom(Node.NodeType.HOTTUBROOM, 8, 6);
		
		ArrayList<Node> tempList = new ArrayList<Node>();
		
		tempList.addAll(tempGUESTROOMList);
		tempList.addAll(tempHALLWAYList);
		tempList.addAll(tempSTAIRSList);
		tempList.addAll(tempHOTTUBList);
		tempList.addAll(tempEXCERCISEList);
		tempList.addAll(tempMASSAGEList);
		
		addList(tempList, s.sixthFlor);
		
		return tempList;
	}
	
	private ArrayList<Node> createVistaFloor(Ship s)
	{
		/**
		 * Guestrooms 38
		 * Hallwats 12
		 * Guest relations room 2
		 * Library 1
		 * Launge and dancefloor 10
		 * Grill 8
		 * Stairs 7
		 * 
		 * Total 77
		 */
		
		ArrayList<Node> tempGUESTROOMList = createRoom(Node.NodeType.GUESTROOM, 38, 4);
		ArrayList<Node> tempHALLWAYList = createRoom(Node.NodeType.HALLWAY, 12, 4);
		ArrayList<Node> tempGUESTList = createRoom(Node.NodeType.GUESTRELATIONS, 2, 4);
		ArrayList<Node> tempLIBRARYList = createRoom(Node.NodeType.LIBRARY, 1, 4);
		ArrayList<Node> tempLAUNGEList = createRoom(Node.NodeType.DININGROOM, 10, 4);
		ArrayList<Node> tempGRILLList = createRoom(Node.NodeType.DININGROOM, 8, 4);
		ArrayList<Node> tempSTAIRSList = createRoom(Node.NodeType.STAIRS, 7, 4);
		
		
		ArrayList<Node> tempList = new ArrayList<Node>();
		
		
		tempList.addAll(tempGUESTROOMList);
		tempList.addAll(tempHALLWAYList);
		tempList.addAll(tempGUESTList);
		tempList.addAll(tempSTAIRSList);
		tempList.addAll(tempLIBRARYList);
		tempList.addAll(tempLAUNGEList);
		tempList.addAll(tempGRILLList);
		
		addList(tempList, s.forthFlor);
		
		return tempList;
	}
	
	
	private ArrayList<Node> createMissingStairsFromFifth(Ship s)
	{
		ArrayList<Node> tempSTAIRSList2 = createRoom(Node.NodeType.STAIRS, 2, 5);
		ArrayList<Node> tempList2 = new ArrayList<Node>();
		tempList2.addAll(tempSTAIRSList2);
		addList(tempList2, s.fifthFlor);
		return tempList2;
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
        case MASSAGEROOM: capacity = 4; break;
        case EXERCISEROOM: capacity = 8; break;
        case HOTTUBROOM: capacity = 8; break;
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
		
		Edge edge = null;
		if(node <= 40)
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.fifthFlor.get(node), 1, 10);
		}
		else if(node <= 63)
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.sixthFlor.get(node), 1, 10);
		}
		else if(node <= 141)
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.forthFlor.get(node), 1, 10);
		}
		else if(node <= 143)
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.fifthFlor.get(node), 1, 10);
		}
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
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 20)); 
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
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 24)); 
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 30)); break;
	        case 24:  
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 23)); 
	        	tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 39)); break;
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
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 23)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowNormal, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 34)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowNormal, 31)); break;
	        case 31:  
	        	tempList.get(i).addEdge(createEdge(diningFlowNormal, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 32)); break;
	        case 32:  
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 31)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 37)); break;
	        case 33:  
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowNormal, 31)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 32)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 37)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 34)); break;
	        case 34:  
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 30)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 37)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 35)); break;
	        case 35:  
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 36)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 37)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 34)); break;
	        case 36:  
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 29)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowNormal, 35)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 37)); break;
	        case 37:  
	        	tempList.get(i).addEdge(createEdge(doorFlowToExit, 25));
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 32)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 33)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 34)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 35)); 
	        	tempList.get(i).addEdge(createEdge(diningFlowToExit, 36)); break;
	        	
	        	
	        	
	        case 38:  //Stairs
	        	tempList.get(i).addEdge(createEdge(stairFlowToExit, 20)); 
	        	tempList.get(i).addEdge(createEdge(stairFlowAwayFromExit, 53)); break;
	        	
	        case 39:  
	        	tempList.get(i).addEdge(createEdge(stairFlowAwayFromExit, 24)); break;
	        case 40:  
	        	tempList.get(i).addEdge(createEdge(stairFlowAwayFromExit, 27)); break; //exit
	        default: System.out.println("You are missing something"); break;
			}
		}
		
	}

	private void addSixthFloorEdges(ArrayList<Node> tempList, Ship s)
	{
		
		for(int i = 0; i<tempList.size(); i++)
		{
			switch (tempList.get(i).getID()) {
			
			//Guest rooms
			case 41: tempList.get(i).addEdge(createEdge(roomFlowToExit, 42)); 
			tempList.get(i).addEdge(createEdge(roomFlowToExit, 43)); break;
			
			case 42: tempList.get(i).addEdge(createEdge(roomFlowNormal, 43));
			tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 41));
			tempList.get(i).addEdge(createEdge(roomFlowToExit, 44)); break;
			
			case 43: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 41));
			tempList.get(i).addEdge(createEdge(roomFlowToExit, 44)); 
			tempList.get(i).addEdge(createEdge(roomFlowNormal, 42)); break;
			
			case 44: tempList.get(i).addEdge(createEdge(doorFlowToExit, 50));
			tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 43));
			tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 42)); break;
			
			//New one
			case 45: tempList.get(i).addEdge(createEdge(roomFlowToExit, 46)); break;
			case 46: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 45)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 49)); break;
			
			//New one
			case 47: tempList.get(i).addEdge(createEdge(roomFlowToExit, 48)); break;
			case 48: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 47)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 50)); break;
			
			//Hallways
			case 49: tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 50)); 
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 46)); break;
			
			case 50: tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 49)); 
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 44)); 
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 48)); 
			tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 51)); break;
			
			
			case 51: tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 50)); 
			tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 52)); 
			tempList.get(i).addEdge(createEdge(stairFlowToExit, 53)); break;
			
			case 52: tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 51)); 
			tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 50)); 
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 54)); 
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 62)); break;
			
			//Stears
			case 53: 
				tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 51)); 
				tempList.get(i).addEdge(createEdge(stairFlowToExit, 38)); break;
				
			//Hot tub room	
			case 54: tempList.get(i).addEdge(createEdge(doorFlowToExit, 52)); 
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 55)); 
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 61)); break;
			
			case 55: tempList.get(i).addEdge(createEdge(diningFlowToExit, 54)); 
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 56)); break;
			
			case 56: tempList.get(i).addEdge(createEdge(diningFlowToExit, 55)); 
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 57)); break;
			
			case 57: tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 58)); 
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 56)); break;
			
			case 58: tempList.get(i).addEdge(createEdge(diningFlowNormal, 57)); 
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 59)); break;
			
			case 59: tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 58)); 
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 60)); break;
			
			case 60: tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 59)); 
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 61)); break;
			
			case 61: tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 60)); 
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 54)); break;
			
			//EXRCISE ROOM
			case 62: tempList.get(i).addEdge(createEdge(doorFlowToExit, 52)); 
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 63)); break;
			
			//MASSAGE ROOM
			case 63: tempList.get(i).addEdge(createEdge(doorFlowToExit, 62)); break;
			default:  System.out.println("You are missing something"); break;
			}
		}
	}

	private void addForthFLoorEdges(ArrayList<Node> tempList, Ship s)
	{
		for(int i = 0; i<tempList.size(); i++)
		{
			switch (tempList.get(i).getID()) 
			{
			
			//Guestrooms
			case 64: tempList.get(i).addEdge(createEdge(roomFlowToExit, 65)); break;
			case 65: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 64)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 103)); break;
			
			case 66: tempList.get(i).addEdge(createEdge(roomFlowToExit, 67)); break;
			case 67: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 66)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 103)); break;
			
			case 68: tempList.get(i).addEdge(createEdge(roomFlowToExit, 69)); break;
			case 69: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 68)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 104)); break;
			
			case 70: tempList.get(i).addEdge(createEdge(roomFlowToExit, 71)); break;
			case 71: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 70)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 104)); break;
			
			case 72: tempList.get(i).addEdge(createEdge(roomFlowToExit, 73)); break;
			case 73: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 72)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 105)); break;
			
			case 74: tempList.get(i).addEdge(createEdge(roomFlowToExit, 75)); break;
			case 75: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 74)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 105)); break;
			
			case 76: tempList.get(i).addEdge(createEdge(roomFlowToExit, 77)); break;
			case 77: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 76)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 106)); break;
			
			case 78: tempList.get(i).addEdge(createEdge(roomFlowToExit, 79)); break;
			case 79: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 78)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 106)); break;
			
			case 80: tempList.get(i).addEdge(createEdge(roomFlowToExit, 81)); break;
			case 81: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 80)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 107)); break;
			
			case 82: tempList.get(i).addEdge(createEdge(roomFlowToExit, 83)); break;
			case 83: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 82)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 107)); break;
			
			case 84: tempList.get(i).addEdge(createEdge(roomFlowToExit, 85)); break;
			case 85: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 84)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 108)); break;
			
			case 86: tempList.get(i).addEdge(createEdge(roomFlowToExit, 87)); break;
			case 87: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 86)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 110)); break;
			
			case 88: tempList.get(i).addEdge(createEdge(roomFlowToExit, 89)); break;
			case 89: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 88)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 110)); break;
			
			case 90: tempList.get(i).addEdge(createEdge(roomFlowToExit, 91)); break;
			case 91: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 90)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 111)); break;
			
			case 92: tempList.get(i).addEdge(createEdge(roomFlowToExit, 93)); break;
			case 93: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 92)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 111)); break;
			
			case 94: tempList.get(i).addEdge(createEdge(roomFlowToExit, 95)); break;
			case 95: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 94)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 112)); break;
			
			case 96: tempList.get(i).addEdge(createEdge(roomFlowToExit, 97)); break;
			case 97: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 96)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 112)); break;
			
			case 98: tempList.get(i).addEdge(createEdge(roomFlowToExit, 99)); break;
			case 99: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 98)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 113)); break;
			
			case 100: tempList.get(i).addEdge(createEdge(roomFlowToExit, 101)); break;
			case 101: tempList.get(i).addEdge(createEdge(roomFlowAwayFromExit, 100)); 
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 113)); break;
			
			//Hallways
			case 102: tempList.get(i).addEdge(createEdge(stairFlowAwayFromExit, 135));
			tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 103)); break;
			
			case 103: tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 102));
			tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 104));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 65));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 67)); break;
			
			case 104: tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 103));
			tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 105));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 69));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 71)); break;
			
			case 105: tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 104));
			tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 106));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 73));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 75)); break;
			
			case 106: tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 105));
			tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 107));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 77));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 79)); break;
			
			case 107: tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 106));
			tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 108));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 81));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 83)); break;
			
			case 108: tempList.get(i).addEdge(createEdge(hallwayFlowAwayFromExit, 107));
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 109));
			tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 110));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 85)); break;
			
			//Side stars hallway
			case 109: tempList.get(i).addEdge(createEdge(doorFlowNormal, 108));
			tempList.get(i).addEdge(createEdge(stairFlowToExit, 136));
			tempList.get(i).addEdge(createEdge(stairFlowAwayFromExit, 137));
			
			
			case 110: tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 108));
			tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 111));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 87));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 89)); break;
			
			case 111: tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 110));
			tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 112));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 91));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 93)); break;
			
			case 112: tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 111));
			tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 113));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 95));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 97)); break;
			
			case 113: tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 112));
			tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 114));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 99));
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 101)); break;
			
			
			//Guest relations
			case 114: tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 115));
			tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 117));
			tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 113)); break;
			
			case 115: tempList.get(i).addEdge(createEdge(stairFlowAwayFromExit, 139));
			tempList.get(i).addEdge(createEdge(stairFlowToExit, 138));
			tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 114)); break;
			
			//Library
			case 116: tempList.get(i).addEdge(createEdge(diningFlowToExit, 117)); 
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 124)); break;
			
			//Launge entrence
			case 117: tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 114));//Back to hallway
			tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 116));//Lib
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 118));//Dancefloor
			tempList.get(i).addEdge(createEdge(doorFlowAwayFromExit, 1)); break;//down
			
			//Dancefloor
			case 118: tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 117)); 
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 120));
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 119)); break;
			
			//Internetcafe
			case 119: tempList.get(i).addEdge(createEdge(diningFlowToExit, 118));
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 121)); break;
			
			//launge
			case 120: tempList.get(i).addEdge(createEdge(diningFlowNormal, 118));//Dancefloor
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 121));
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 124));
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 123)); break;
			
			case 121: tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 119));//Internett
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 120));
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 122)); break;
			
			
			case 122: tempList.get(i).addEdge(createEdge(diningFlowNormal, 121));//South of internet
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 120));
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 123)); break;
			
			case 123: tempList.get(i).addEdge(createEdge(diningFlowNormal, 120));//South of dance
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 122));
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 124)); break;
			
			
			case 124: tempList.get(i).addEdge(createEdge(diningFlowNormal, 117)); //Entrence
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 125));
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 116)); //lib
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 120));
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 123)); break;
			
			
			case 125: tempList.get(i).addEdge(createEdge(diningFlowNormal, 124));
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 126));break;
			 
			case 126: tempList.get(i).addEdge(createEdge(diningFlowNormal, 125));
			tempList.get(i).addEdge(createEdge(doorFlowToExit, 127)); break;
			
			//Grill
			case 127: tempList.get(i).addEdge(createEdge(doorFlowNormal, 126));
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 128)); break;
			 
			case 128: tempList.get(i).addEdge(createEdge(diningFlowNormal, 127));
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 129)); 
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 134)); break;
			
			case 129: tempList.get(i).addEdge(createEdge(stairFlowToExit, 141)); //Exit stairs
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 128));
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 130)); 
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 133)); break;
			 
			case 130: tempList.get(i).addEdge(createEdge(diningFlowToExit, 129)); 
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 131)); 
			tempList.get(i).addEdge(createEdge(diningFlowNormal, 132)); break;
			
			case 131: tempList.get(i).addEdge(createEdge(diningFlowToExit, 130)); break;
			
			case 132: tempList.get(i).addEdge(createEdge(diningFlowNormal, 130)); 
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 133)); break;
			
			case 133: tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 132)); 
			tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 134)); 
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 129)); 
			tempList.get(i).addEdge(createEdge(stairFlowToExit, 140)); break;
			
			case 134: tempList.get(i).addEdge(createEdge(diningFlowNormal, 128)); 
			tempList.get(i).addEdge(createEdge(diningFlowToExit, 133)); break;
			
			//Stairs
			case 135: tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 102)); break; //Goes to 3rdfloor(Front)
			
			case 136: tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 109)); 
			tempList.get(i).addEdge(createEdge(stairFlowToExit, 142)); break; //Foes to fifth floor(Middle)
			
			case 137: tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 109)); break;//Goes to 3rd floor(Middle)
			
			case 138: tempList.get(i).addEdge(createEdge(hallwayFlowNormal, 115)); 
			tempList.get(i).addEdge(createEdge(stairFlowToExit, 143)); break;//Goes to fifth floor(Guest rep)
			
			case 139: tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 115)); break;//Goes to 3rdfloor(Guest rep)
			
			case 140: tempList.get(i).addEdge(createEdge(diningFlowToExit, 133)); break;//goes to 3rd floor (Grill)
			
			case 141: tempList.get(i).addEdge(createEdge(diningFlowAwayFromExit, 129)); break; //Goes to fifth floor(Grill)
			
			case 142: tempList.get(i).addEdge(createEdge(stairFlowNormal, 136)); //Fifth floor north stairs (Middle)
			tempList.get(i).addEdge(createEdge(hallwayFlowToExit, 20)); break;
			
			case 143: tempList.get(i).addEdge(createEdge(stairFlowNormal, 138)); //Fifth floor middle stairs 
			tempList.get(i).addEdge(createEdge(stairFlowToExit, 39)); break;
			
			default: System.out.println("Your missing something!"); break;
			}
		}
		
	}
}
