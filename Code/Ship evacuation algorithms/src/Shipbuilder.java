import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;


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
	public Ship createShipExplorer()
	{
		Ship explorer = new Ship(5);
		currentShip = explorer;
		ArrayList<Node> seventhFloor = createSeventhFloorExplorer(explorer);
		ArrayList<Node> sixthFloor = createSixthFloorExplorer(explorer);
		
		addSeventhFloorTogheterExplorer(seventhFloor, explorer);
		addSixthFloorTogheterExplorer(sixthFloor, explorer);
		
		explorer.exits.add(explorer.seventhFloor.get(43));
		explorer.exits.add(explorer.seventhFloor.get(44));
		explorer.exits.add(explorer.sixthFlor.get(140));
		explorer.exits.add(explorer.sixthFlor.get(142));
		
		explorer.seventhFloor.get(43).setExit(true);
		explorer.seventhFloor.get(44).setExit(true);
		explorer.sixthFlor.get(140).setExit(true);
		explorer.sixthFlor.get(142).setExit(true);
		
		
		return explorer;
	}
	
	
	public Ship createCelebrityXpedition()
	{
		Ship celebX = new Ship(4);
		currentShip = celebX;
		ArrayList<Node> fightFloor = createPanormaFlor(celebX);
		ArrayList<Node> sixthFloor = createSunrise(celebX);
		ArrayList<Node> forthFloor = createVistaFloor(celebX);
		ArrayList<Node> missingStairs = createMissingStairsFromFifth(celebX);
		ArrayList<Node> thirdFloor = createMarianaFloor(celebX);
		
		addFifthFloorEdges(fightFloor, celebX);
		addSixthFloorEdges(sixthFloor, celebX);
		addForthFLoorEdges(forthFloor, celebX);
		addForthFLoorEdges(missingStairs, celebX);
		addThirdFloorEdges(thirdFloor, celebX);
		
		
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
	
	private ArrayList<Node> createSeventhFloorExplorer(Ship s)
	{
		/*
		 * Guestrooms 20
		 * Hallways 25
		 * Stairs 3
		 * 
		 * total 48
		 */
		
		ArrayList<Node> tempGUESTROOMList = createRoom(Node.NodeType.GUESTROOM, 20, 7);
		ArrayList<Node> tempHALLWAYList = createRoom(Node.NodeType.HALLWAY, 25, 7);
		ArrayList<Node> tempSTAIRSList = createRoom(Node.NodeType.STAIRS, 3, 7);
		
		ArrayList<Node> tempList = new ArrayList<Node>();
		
		tempList.addAll(tempGUESTROOMList);
		tempList.addAll(tempHALLWAYList);
		tempList.addAll(tempSTAIRSList);
		
		addList(tempList, s.seventhFloor);
		
		return tempList;
	}
	
	private ArrayList<Node> createSixthFloorExplorer(Ship s)
	{
		/*
		 * Hallways 53 (Brown outside)
		 * Observation/Grill 20
		 * Lecture hall 15
		 * Stairs 12
		 * 
		 * 99
		 */
		
		
		ArrayList<Node> tempHALLWAYList = createRoom(Node.NodeType.HALLWAY, 53, 6);
		ArrayList<Node> tempLECTUREList = createRoom(Node.NodeType.ENTERTAINMENTROOM, 15, 6);
		ArrayList<Node> tempDININGList = createRoom(Node.NodeType.DININGROOM, 20, 6);
		ArrayList<Node> tempSTAIRSList = createRoom(Node.NodeType.STAIRS, 12, 6);
		
		ArrayList<Node> tempList = new ArrayList<Node>();
		
		
		tempList.addAll(tempHALLWAYList);
		tempList.addAll(tempLECTUREList);
		tempList.addAll(tempDININGList);
		tempList.addAll(tempSTAIRSList);
		
		addList(tempList, s.sixthFlor);
		
		return tempList;
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
	
	private ArrayList<Node> createMarianaFloor(Ship s)
	{
		/*
		 * Rooms 31
		 * Hallways 14
		 * Shop 2
		 * Dining 9
		 * Stairs 4
		 * 
		 * Total 56
		 */
		
		ArrayList<Node> tempGuestrooms = createRoom(Node.NodeType.GUESTROOM, 31, 3);
		ArrayList<Node> tempHallways = createRoom(Node.NodeType.HALLWAY, 14, 3);
		ArrayList<Node> tempShop = createRoom(Node.NodeType.SHOP, 2, 3);
		ArrayList<Node> tempDining = createRoom(Node.NodeType.DININGROOM, 9, 3);
		ArrayList<Node> tempStairs = createRoom(Node.NodeType.STAIRS, 4, 3);
		
		ArrayList<Node> tempList = new ArrayList<Node>();
		
		
		tempList.addAll(tempGuestrooms);
		tempList.addAll(tempHallways);
		tempList.addAll(tempShop);
		tempList.addAll(tempDining);
		tempList.addAll(tempStairs);
		
		addList(tempList, s.thirdFloor);
		
		
		return tempList;
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
	
	
	private Edge createEdgeCeleb(int attractiveness, int node)
	{
		
		Edge edge = null;
		if(node <= 40)
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.fifthFlor.get(node), 1, 5);
		}
		else if(node <= 63)
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.sixthFlor.get(node), 1, 6);
		}
		else if(node <= 141)
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.forthFlor.get(node), 1, 4);
		}
		else if(node <= 143)
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.fifthFlor.get(node), 1, 5);
		}
		else if(node <= 203)
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.thirdFloor.get(node), 1, 7);
		}
		return edge;
	}
	
	private Edge createEdgeExplor(int attractiveness, int node)
	{
		Edge edge = null;
		
		if(node <= 47)
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.seventhFloor.get(node), 1, 7);
		}
		else
		{
			edge = new Edge(attractiveness, edgeID++, currentShip.sixthFlor.get(node), 1, 6);
		}
		
		if(edge.getNode() == null)
		{
			System.err.println("Edge did not get a node. Explorer");
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
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 1));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(41)); break; //Room to room
	        case 1: 
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 0)); //Room to room
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 18));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(42));break; //Room out to hall Room 509
	        	
	        	
	        case 2:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 3));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(43));break; //Room to room
	        case 3:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 2)); //Room to room
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 19));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(44));break; //Room out to hall Room 507
	        	
	        	
	        case 4:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 5));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(47));break;
	        case 5:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 4)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 19));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(48));break; //Room 508
	        	
	        	
	        case 6:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 7)); break;
	        case 7:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 6)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 21));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(51)); break; //505
	        	
	        	
	        case 8:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 9)); 
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(63)); break;
	        case 9:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 8)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 21));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(62)); break; //506
	        	
	        	
	        case 10:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 11));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(61));break;
	        case 11:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 10)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 22));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(54)); break; //503
	        	
	        	
	        case 12:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 13));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(55)); break;
	        case 13:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 12)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 22));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(54)); break; //504
	        	
	        	
	        case 14:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 15));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(60));break;
	        case 15:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 14)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 23));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(60));break; //501
	        	
	        	
	        case 16:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 17));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(56));break;
	        case 17:  
	        	tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 16)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 23));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(56));break; //502
	        	
	        	
	        	
	        case 18:  //Hallways
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 1)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 19));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(49));break;
	        	
	        	
	        case 19:  
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 18)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 20)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 21)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 5)); 
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(50)); break;
	        	
	        case 20:  
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 19)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 38));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(44)); break;
	        case 21:  
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 19)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 7)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 9)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 22)); 
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(52)); break;
	        	
	        	
	        case 22:  
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 21)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 23)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 11)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 13));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(54)); break;
	        case 23:  
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 22)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 15)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 17)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 24)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 30)); 
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(58)); break;
	        	
	        case 24:  
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 23)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 39));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(59)); break;
	        case 25:  
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 37)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 26)); break;
	        case 26:  
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 25)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 27)); break;
	        case 27:  
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 26)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 40)); break;
	        	
	        	
	        	
	        case 28:  //Diningroom/bar/dancefloor
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 29));
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 31));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(57)); break;
	        case 29:  
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 28)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 30)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 31)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 32)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 33));
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 34));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(57));break;
	        case 30:  
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 23));  //Entrence
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 29)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 36)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 35)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 34)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 33));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(58)); break;
	        case 31:  
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 29)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 32)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 28)); break;
	        case 32:  
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 31)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 29)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 37));
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 33));break;
	        case 33:  
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 32)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 34)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 31)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 37)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 29)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 29)); break;
	        case 34:  
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 37)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 33)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 35)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 36)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 30));
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 29)); break;
	        case 35:  
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 34)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 36)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 30)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 37)); break;
	        case 36:  
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 30)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 35)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 34)); break;
	        case 37:  
	        	tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 25)); //Exit
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 32)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 33)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 34)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 35)); break;
	        	
	        	
	        	
	        case 38:  //Stairs
	        	tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 20)); 
	        	tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 53)); break;
	        	
	        case 39:  
	        	tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 24));
	        	tempList.get(i).nodesUpstairs.add(s.sixthFlor.get(59)); break;
	        case 40:  
	        	tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 27)); break; //exit
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
			case 41: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 42)); 
			tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 43)); break;
			
			case 42: tempList.get(i).addEdge(createEdgeCeleb(roomFlowNormal, 43));
			tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 41));
			tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 44)); break; 
			
			case 43: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 41));
			tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 44)); 
			tempList.get(i).addEdge(createEdgeCeleb(roomFlowNormal, 42)); break; 
			
			case 44: tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 50));
			tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 43));
			tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 42)); break; //601
			
			
			
			//New one
			case 45: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 46)); break; //602
			case 46: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 45)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 49)); break;
			
			//New one
			case 47: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 48)); break; //603
			case 48: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 47)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 50)); break;
			
			
			
			//Hallways
			case 49: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 50)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 46)); break;
			
			case 50: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 49)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 44)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 48)); 
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 51)); break;
			
			
			case 51: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 50)); 
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 52)); 
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 53)); break;
			
			case 52: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 51)); 
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 50)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 54)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 62)); break;
			
			//Stears
			case 53: 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 51)); 
				tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 38)); break;
				
			//Hot tub room	
			case 54: tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 52)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 55)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 61)); break;
			
			case 55: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 54)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 56)); break;
			
			case 56: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 55)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 57)); break;
			
			case 57: tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 58)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 56)); break;
			
			case 58: tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 57)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 59)); break;
			
			case 59: tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 58)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 60)); break;
			
			case 60: tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 59)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 61)); break;
			
			case 61: tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 60)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 54)); break;
			
			//EXRCISE ROOM
			case 62: tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 52)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 63)); break;
			
			//MASSAGE ROOM
			case 63: tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 62)); break;
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
			case 64: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 65)); break; //419
			case 65: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 64)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 103)); break;
			
			case 66: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 67)); break; //420
			case 67: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 66)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 103)); break;
			
			case 68: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 69)); break; //417
			case 69: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 68)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 104)); break;
			
			case 70: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 71)); break; //418
			case 71: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 70)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 104)); break;
			
			case 72: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 73));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(0)); break; //415
			case 73: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 72)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 105));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(1)); break;
			
			case 74: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 75)); break; //416
			case 75: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 74)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 105)); break;
			
			case 76: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 77));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(0)); break; //411
			case 77: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 76)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 106));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(1)); break;
			
			case 78: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 79)); break; //414
			case 79: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 78)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 106)); break;
			
			case 80: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 81));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(2)); break; //409
			case 81: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 80)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 107));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(3)); break;
			
			case 82: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 83));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(4)); break; //412
			case 83: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 82)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 107));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(5)); break;
			
			case 84: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 85));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(4)); break; //410
			case 85: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 84));  
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 108));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(5)); break;
			
			case 86: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 87));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(6)); break; //407
			case 87: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 86)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 110));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(7)); break;
			
			case 88: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 89));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(8)); break; //408
			case 89: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 88)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 110));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(9)); break;
			
			case 90: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 91)); 
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(10)); break;//405
			case 91: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 90)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 111));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(11)); break;
			
			case 92: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 93));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(12)); break; //406
			case 93: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 92)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 111));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(13)); break;
			
			case 94: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 95));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(10)); break; //403
			case 95: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 94)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 112));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(11)); break;
			
			case 96: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 97));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(12));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(16)); break; //404
			case 97: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 96)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 112));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(13));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(17)); break;
			
			case 98: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 99));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(14)); break; //401
			case 99: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 98)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 113));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(15)); break;
			
			case 100: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 101));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(16)); break; //402
			case 101: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 100)); 
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 113));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(17)); break;
			
			
			
			//Hallways
			case 102: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 135)); //To stairs down
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 103)); break;
			
			case 103: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 102));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 104));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 65));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 67)); break;
			
			case 104: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 103));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 105));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 69));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 71)); break;
			
			case 105: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 104)); //Start to upper floor
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 106));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 73));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 75)); 
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(18)); break;
			
			case 106: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 105));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 107));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 77));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 79));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(18)); break;
			
			case 107: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 106));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 108));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 81));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 83));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(19)); break;
			
			case 108: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 107));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 109));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 110));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 85));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(19)); break;
			
			//Side stars hallway
			case 109: tempList.get(i).addEdge(createEdgeCeleb(doorFlowNormal, 108));
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 136));
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 137));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(20)); break;
			
			
			case 110: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 108));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 111));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 87));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 89)); 
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(21)); break;
			
			case 111: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 110));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 112));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 91));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 93)); 
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(22)); break;
			
			case 112: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 111));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 113));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 95));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 97));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(22)); break;
			
			case 113: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 112));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 114));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 99));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowAwayFromExit, 101));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(23)); break;
			
			
			//Guest relations
			case 114: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 115));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 117));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 113)); 
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(23)); break;
			
			case 115: tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 139));
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 138));
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 114)); 
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(24));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(28));break;
			
			//Library
			case 116: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 117)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 124));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(31)); break;
			
			//Launge entrence
			case 117: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 114));//Back to hallway
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 116));//Lib
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 118));//Dancefloor
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 124));//down
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(29));break;
			
			//Dancefloor
			case 118: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 117)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 120));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 119));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(30));break;
			
			//Internetcafe
			case 119: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 118));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 121));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(36));break;
			
			//launge
			case 120: tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 118));//south Dancefloor
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 121));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 124));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 123)); 
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(34)); break;
			
			case 121: tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 119));// south Internett
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 120));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 122));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(35)); break;
			
			
			case 122: tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 121));//South of internet
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 120));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 123)); break;
			
			case 123: tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 120));//South of dance
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 122));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 124));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(37)); break;
			
			
			case 124: tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 117)); //Entrence
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 125));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 116)); //lib
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 120));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 123));
			tempList.get(i).nodesUpstairs.add(s.fifthFlor.get(32)); break;
			
			
			case 125: tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 124));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 126));break;
			 
			case 126: tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 125));
			tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 127)); break;
			
			//Grill
			case 127: tempList.get(i).addEdge(createEdgeCeleb(doorFlowNormal, 126));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 128)); break;
			 
			case 128: tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 127));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 129)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 134)); break;
			
			case 129: tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 141)); //Exit stairs
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 128));
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 130)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 133)); break;
			 
			case 130: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 129)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 131)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 132)); break;
			
			case 131: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 130)); break;
			
			case 132: tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 130)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 133)); break;
			
			case 133: tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 132)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 134)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 129)); 
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 140)); break;
			
			case 134: tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 128)); 
			tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 133)); break;
			
			//Stairs
			case 135: tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 102)); 
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 200)); break; //Goes to 3rdfloor(Front)
			
			case 136: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 109)); 
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 142)); break; //Foes to fifth floor(Middle)
			
			case 137: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 109));
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 202)); break;//Goes to 3rd floor(Middle)
			
			case 138: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 115)); 
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 143)); break;//Goes to fifth floor(Guest rep)
			
			case 139: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 115)); 
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 203)); break;//Goes to 3rdfloor(Guest rep)
			
			case 140: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 133)); break;//goes to 3rd floor (Grill)
			
			case 141: tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 129)); break; //Goes to fifth floor(Grill)
			
			case 142: tempList.get(i).addEdge(createEdgeCeleb(stairFlowNormal, 136)); //Fifth floor north stairs (Middle)
			tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 20)); break;
			
			case 143: tempList.get(i).addEdge(createEdgeCeleb(stairFlowNormal, 138)); //Fifth floor middle stairs 
			tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 39)); break;
			
			default: System.out.println("Your missing something!"); break;
			}
		}
		
	}

	private void addThirdFloorEdges(ArrayList<Node> tempList, Ship s)
	{
		for(int i = 0; i<tempList.size(); i++)
		{
			switch (tempList.get(i).getID()) 
			{
			
				//Guest rooms
				case 144: tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 175)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(64)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(65)); break; //317
				
				case 145: tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 176)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(68)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(69)); break; //315
				
				case 146: tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 176));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(70)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(71)); break; //318
				
				case 147: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 148)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(72)); break;  //311
				case 148: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 147)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 177)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(73)); break;
				
				case 149: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 150));  
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(74)); break; //316
				case 150: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 149)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 177));  
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(75)); break;
				
				case 151: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 152)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(76)); break; //309
				case 152: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 151)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 178)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(77)); break;
				
				case 153: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 154));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(78)); break; //314
				case 154: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 153)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 178));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(79)); break;
				
				case 155: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 156)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(82)); break; //312
				case 156: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 155)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 179));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(83)); break;
				
				case 157: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 158));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(84)); break; //310
				case 158: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 157)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 180));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(85)); break;
				
				case 159: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 160));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(86)); break; //307
				case 160: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 159)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 182));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(87)); break;
				
				case 161: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 162));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(88)); break; //308
				case 162: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 161)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 182));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(89)); break;
				
				case 163: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 164));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(90)); break; //305
				case 164: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 163)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 183));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(91)); break;
				
				case 165: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 166));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(92)); break; //306
				case 166: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 165)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 183));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(93)); break;
				
				case 167: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 168));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(94)); break; //303
				case 168: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 167)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 184));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(95)); break;
				
				case 169: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 170));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(96)); break; //304
				case 170: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 159)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 184));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(97)); break;
				
				case 171: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 172));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(98)); break; //301
				case 172: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 171)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 185));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(99)); break;
				
				case 173: tempList.get(i).addEdge(createEdgeCeleb(roomFlowToExit, 174));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(100)); break; //302
				case 174: tempList.get(i).addEdge(createEdgeCeleb(roomFlowAwayFromExit, 173)); 
				tempList.get(i).addEdge(createEdgeCeleb(doorFlowToExit, 185));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(101)); break;
				
				
				
				//Hallways
				case 175: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 200)); //To stairs
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 176)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 144));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(102));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(103)); break;
				
				case 176: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 175)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 177)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 145)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 146)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(104)); break;
				
				case 177:  tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 176)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 178)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 148)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 150));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(105)); break;
				
				case 178:  tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 177)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 179)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 152)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 154));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(106)); break;
				
				case 179:  tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 178)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 180)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 156)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(107)); break;
				
				case 180:  tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 179)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 182)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 181)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 158));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(108)); break;
				
				//Mid
				case 181: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 202)); //Stairs
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 201)); //Stairs
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 180));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(109)); break;
				
				
				case 182: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 180)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 183)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 160)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 162));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(110)); break;
				
				case 183: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 182)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 184)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 164)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 166));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(111)); break;
				
				case 184: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 183)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 185)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 168)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 170));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(112)); break;
				
				case 185: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 184)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 186)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 172)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 174));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(113)); break; //End of hallways with rooms.
				
				case 186: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 185)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 187)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 188));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(114)); break;
				
				case 187: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 188)); //Shop
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 186)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 190));
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(117)); break;
				
				case 188: tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowToExit, 203)); //Stairs
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 186)); 
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 187));
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 191));
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowAwayFromExit, 192)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(115)); break;
				
				
				//Shop
				case 189: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 190)); break;
				case 190: tempList.get(i).addEdge(createEdgeCeleb(diningFlowAwayFromExit, 189));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 187)); break;
				
				//Dining
				case 191: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 188)); //Under Dance
				tempList.get(i).addEdge(createEdgeCeleb(hallwayFlowNormal, 192));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 193));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 194)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(120)); break;
				
				case 192: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 188)); //Under Internett cafe
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 191));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 193));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 194)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(121)); break;
				
				
				
				case 193: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 192));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 191));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 194));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 195));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 196)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(123)); break;
				
				case 194: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 192));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 191));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 193));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 195));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 196)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(122)); break;
				
				
				
				case 195: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 193));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 194));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 196));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 197)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(123)); break;
				
				case 196: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 193));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 194));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowNormal, 195));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 197)); 
				tempList.get(i).nodesUpstairs.add(s.forthFlor.get(122)); break;
				
				
				case 197: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 196));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 195));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 198)); break;
				
				case 198: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 197));
				tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 199)); break;
				
				case 199: tempList.get(i).addEdge(createEdgeCeleb(diningFlowToExit, 198)); break;
				
				//Stairs
				case 200: tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 135)); //Up floor
				tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 197)); break;
				
				case 201: tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 181)); break; //To this
				
				case 202: tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 181));
				tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 137));break; //Up floor
				
				case 203: tempList.get(i).addEdge(createEdgeCeleb(stairFlowAwayFromExit, 188));
				tempList.get(i).addEdge(createEdgeCeleb(stairFlowToExit, 139)); break; //Up floor
				
				default: System.out.println("Your missing something."); break;
			}
		}
	}
	
	private void addSeventhFloorTogheterExplorer(ArrayList<Node> tempList, Ship s)
	{
		/*
		 * From 0 to 47
		 * 
		 */
		for(int i = 0; i<tempList.size(); i++)
		{
			switch (tempList.get(i).getID()) 
			{
			case 0: tempList.get(i).addEdge(createEdgeExplor(roomFlowNormal, 1)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 2)); break;
			case 1: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 0)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 2)); break;
			case 2: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 0)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 1)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 3)); break;
			case 3: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 2)); 
			tempList.get(i).addEdge(createEdgeExplor(doorFlowToExit, 27)); break;
			
			
			case 4: tempList.get(i).addEdge(createEdgeExplor(roomFlowNormal, 5)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 6)); break;
			case 5: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 4)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 6)); break;
			case 6: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 4)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 5)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 7)); break;
			case 7: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 6)); 
			tempList.get(i).addEdge(createEdgeExplor(doorFlowToExit, 27)); break;
			
			case 8: tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 9)); break;
			case 9: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 8)); 
			tempList.get(i).addEdge(createEdgeExplor(doorFlowToExit, 28)); break;
			
			case 10: tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 11)); break;
			case 11: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 10)); 
			tempList.get(i).addEdge(createEdgeExplor(doorFlowToExit, 28)); break;
			
			case 12: tempList.get(i).addEdge(createEdgeExplor(roomFlowNormal, 13)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowNormal, 14)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 15)); break;
			case 13: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 12)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 14)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 15)); break;
			case 14: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 12)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 13)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 15)); break;
			case 15: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 12)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 13)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 14)); 
			tempList.get(i).addEdge(createEdgeExplor(doorFlowToExit, 30)); break;
			
			case 16: tempList.get(i).addEdge(createEdgeExplor(roomFlowNormal, 17)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowNormal, 18)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 19)); break;
			case 17: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 16)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 18)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 19)); break;
			case 18: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 16)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 17)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowToExit, 19)); break;
			case 19: tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 16)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 17)); 
			tempList.get(i).addEdge(createEdgeExplor(roomFlowAwayFromExit, 18)); 
			tempList.get(i).addEdge(createEdgeExplor(doorFlowToExit, 30)); break;
			
			case 20: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 21)); break;
			case 21: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 20)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 22)); break;
			case 22: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 21)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 23)); break;
			case 23: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 22)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 24)); break;
			case 24: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 23)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 25)); break;
			
			case 25: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 24)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 26)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 27)); 
			tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 45)); break;
			case 26: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 25)); break;
			
			case 27: tempList.get(i).addEdge(createEdgeExplor(doorFlowAwayFromExit, 3)); 
			tempList.get(i).addEdge(createEdgeExplor(doorFlowAwayFromExit, 7)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 25)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 28)); break;
			
			case 28: tempList.get(i).addEdge(createEdgeExplor(doorFlowAwayFromExit, 9)); 
			tempList.get(i).addEdge(createEdgeExplor(doorFlowAwayFromExit, 11)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 27)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 29)); break;
			
			case 29: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 28)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 30)); break;
			case 30: tempList.get(i).addEdge(createEdgeExplor(doorFlowAwayFromExit, 15)); 
			tempList.get(i).addEdge(createEdgeExplor(doorFlowAwayFromExit, 19)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 29)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 31)); break;
			
			case 31: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 31)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 32)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 33)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 34)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 36)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 35)); break;
			
			case 32: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 31)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 34)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 35)); 
			tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 46)); break;
			
			case 33: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 31)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 36)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 35)); 
			tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 47)); break;
			
			case 34: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 31)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 32)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 35)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 37)); break;
			
			case 35: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 31)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 32)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 33)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 34)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 36)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 37)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 38)); break;
			
			case 36: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 31)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 33)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 35)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 38)); break;
			
			case 37: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 34)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 35)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 38)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 39)); break;
			
			case 38: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 35)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 36)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 37)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 40)); break;
			
			case 39: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 37)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 41)); break;
			
			case 40: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 38)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 42)); break;
			
			case 41: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 39)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 42)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 43)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 44)); break;
			
			case 42: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 40)); 
				tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 41)); 
				tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 43)); 
				tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 44)); break;
				
			case 43: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 41)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 42)); break; //Exit
			case 44: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 41)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 42)); break; //Exit
			
			case 45: tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 136)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 25)); break;
			case 46: tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 139)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 32)); break;
			case 47: tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 141)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 33)); break;
			
			
			default: System.out.println("Your missing something."); break;
			}
		}
	}
	
	private void addSixthFloorTogheterExplorer(ArrayList<Node> tempList, Ship s)
	{
		/*
		 * 48-147
		 */
		
		for(int i = 0; i<tempList.size(); i++)
		{
			switch (tempList.get(i).getID()) 
			{
			case 48: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 49)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 71)); break;
			
			case 49: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 48)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 50)); break;
			
			case 50: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 49)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 51)); break;
			
			case 51: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 50)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 52)); break;
			
			case 52: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 51)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 53));
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 57)); break; //outside to in
			
			case 53: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 52)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 54));
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 120)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 121));break; //Observation place
			
			case 54: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 53));
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 55));
			tempList.get(i).addEdge(createEdgeExplor(stairFlowToExit, 136));
			tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 137));break; //Stairs up and down
			
			case 55: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 54)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 101)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 102)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 103)); break; //Lecturehall
			
			case 56: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 122));
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 75)); 
			tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 138)); break; //Stairs down from observation
			
			
			case 57: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 58)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 52)); break;
			case 58: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 59)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 57)); break;
			case 59: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 60)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 58)); break;
			case 60: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 61)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 59)); break;
			case 61: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 62)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 60)); break;
			
			
			case 62: tempList.get(i).addEdge(createEdgeExplor(stairFlowToExit, 139)); //Staris up
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 61)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 63)); break;
			
			
			case 63: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 62)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 64)); break;
			
			case 64: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 63)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 90)); //Go inside
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 65)); break;
			
			case 65: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 64)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 66)); break;
			case 66: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 65)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 67)); break;
			case 67: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 66)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 68)); break;
			case 68: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 67)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 69)); 
			tempList.get(i).addEdge(createEdgeExplor(stairFlowToExit, 140)); break; //Stairs to exit
			
			
			case 69: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 68)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 70)); break;
			case 70: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 69)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 123)); break;//Outdoor grill
			
			
			case 71: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 48)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 72)); break;
			case 72: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 71)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 73)); break;
			case 73: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 72)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 74)); break;
			case 74: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 73)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 75)); break;
			
			
			case 75: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 74)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 56)); //To oberservation
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 76)); break;
			
			
			case 76: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 77)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 75)); break;
			case 77: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 78)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 76)); break;
			case 78: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 79)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 77)); break;
			case 79: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 80)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 78)); break;
			case 80: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 81)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 79)); break;
			
			case 81: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 80)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 82)); 
			tempList.get(i).addEdge(createEdgeExplor(stairFlowToExit, 141)); break; //Stairs up
			
			case 82: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 81)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 83)); break;
			
			case 83: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 82)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 84)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 91)); break; //Into hall
			
			case 84: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 83)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 85)); break;
			case 85: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 84)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 86)); break;
			
			case 86: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 85)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 87)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 96)); break; //Into hall
			
			case 87: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 86)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 88)); 
			tempList.get(i).addEdge(createEdgeExplor(stairFlowToExit, 142)); break; //Up to lifeboats stairs
			
			case 88: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 87)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 89)); break;
			case 89: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 88)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 127)); break; //Outdoor grill
			
			case 90: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 64)); //To outside
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 91)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 93)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 113));
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 114));
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 92)); break;
			
			case 91: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 83)); //Outside
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 90)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 93)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 114));
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 115));
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 92)); break;
			
			case 92: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 90)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 91)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 93)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 95)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 94)); break;
			
			case 93: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 90)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 91)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 92)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 94)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 95)); break;
			
			case 94: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 92)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 93)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 95)); 
			tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 143)); break; //Stairs down
			
			case 95: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 93)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 92)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 94)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 96)); break; //outside
			
			case 96: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 95)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 97)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 86)); //Outside
			tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 144)); break; //Stairs down
			
			case 97: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 96)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 98)); break;
			
			case 98: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 97)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 99)); 
			tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 145)); break; //Stairs down
			
			case 99: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 98)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 100)); break;
			case 100: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 99)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 123)); break; 
			
			//End of hallways
			//Lecture hall
			case 101: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 55)); //Out north of lecture hall
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 102)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 104)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 105)); break;
			
			case 102: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 55)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 101)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 103)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 104)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 105)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 106)); break;
			
			case 103: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 55)); //Out north of lecture hall
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 102)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 105)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 106)); break;
			
			case 104: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 102)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 101)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 105)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 107)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 108)); break;
			
			case 105: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 102)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 101)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 103)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 104)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 106)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 107)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 108)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 109)); break;
			
			case 106:  tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 102)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 103)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 105)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 108)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 109)); break;
			
			case 107: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 104)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 108)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 105)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 110)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 111)); break;
			
			case 108: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 105)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 107)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 109)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 104)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 106)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 110)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 111)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 112)); break;
			
			case 109:  tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 106)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 108)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 105)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 111)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 112)); break;
			
			case 110: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 107)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 108)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 111)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 114)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 113)); break;
			
			case 111: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 108)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 107)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 109)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 110)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 112)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 113)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 114)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 115)); break;
			
			case 112:  tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 109)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 108)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 111)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 114)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 115)); break;
			
			case 113: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 90)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 110)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 111)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 114)); break;
			
			case 114: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 90)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 91)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 111)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 110)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 112)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 113)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 115)); break;
			
			case 115: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 91)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 112)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 111)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 114)); break;
			
			//Obersvation
			case 116: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 119)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 118)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 117)); break;
			
			case 117: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 120)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 116)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 118)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 121)); break;
			
			case 118: tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 116)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 117)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 119)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 120)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 121)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 122)); break;
			
			case 119: tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 116)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 118)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 121)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 122)); break;
			
			case 120: tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 117)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 118)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 121)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 53)); break;
			
			case 121: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 53)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 122)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 120)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 119)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 118)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 117)); break;
			
			case 122: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 56)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 121)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 118)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 119)); break;
			
			//Outdoor grill
			case 123: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 70)); 
			tempList.get(i).addEdge(createEdgeExplor(hallwayFlowNormal, 100)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 124)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 129)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 128)); break;
			
			case 124: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 123)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 125)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 128)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 129)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 130)); break;
			
			case 125: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 124)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 126)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 129)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 130)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 131)); break;
			
			case 126:tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 127)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 125)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 130)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 131)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 132));  break;
			
			case 127: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowToExit, 89)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 126)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 131)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 132)); break;
			
			case 128: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 123)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 124)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 129)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 133)); break;
			
			case 129: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 123)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 124)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 125)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 128)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 130)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 133)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 134)); break;
			
			case 130: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 124)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 126)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 125)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 129)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 131)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 133)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 134)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 135)); break;
			
			case 131: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 127)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 126)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 132)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 131)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 125)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 134)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 135)); break;
			
			case 132: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 127)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowAwayFromExit, 135)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 126)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 131)); break;
			
			case 133: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 128)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 129)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 130)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 134));
			tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 146)); break; //Stairs down
			
			case 134: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 129)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 131)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 130)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 133)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 135)); break;
			
			case 135: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 132)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 131)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 130)); 
			tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 134));
			tempList.get(i).addEdge(createEdgeExplor(stairFlowAwayFromExit, 147)); break; //Stairs down
			
			//Stairs
			case 136:tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 54)); 
			tempList.get(i).addEdge(createEdgeExplor(stairFlowToExit, 45)); break;
			
			case 137: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 54)); break;
			
			case 138: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 56)); break;
			
			case 139: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 62));
			tempList.get(i).addEdge(createEdgeExplor(stairFlowToExit, 46)); break;
			
			case 140: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 68));
			
			case 141: tempList.get(i).addEdge(createEdgeExplor(hallwayFlowAwayFromExit, 81));
			tempList.get(i).addEdge(createEdgeExplor(stairFlowToExit, 47)); break;
			
			case 142: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 87)); 
			
			case 143: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 94)); break;
			
			case 144: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 96)); break;
			
			case 145: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 98)); break;
			
			case 146: tempList.get(i).addEdge(createEdgeExplor(diningFlowNormal, 133)); break;
			
			case 147: tempList.get(i).addEdge(createEdgeExplor(diningFlowToExit, 135)); break;
			
			default: System.out.println("Your missing something floor 6"); break;
			}
		}
	}
}
