import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class Run {
	public static void main(String [ ] args) throws IOException
	{
		Shipbuilder sb = new Shipbuilder();		
		Ship s = sb.createCelebrityXpedition();
		//System.out.println(s.fifthFlor.size());

		// The entire graph
		//List<Node> graph = new ArrayList<Node>(createTestGraph());
		List<Node> graph = new ArrayList<Node>(s.getGraphOfShip());
		List<Edge> edges = new ArrayList<Edge>();
		
		for(Node n : graph)
		{
			edges.addAll(n.getPaths());
		}
		
		// The list of source nodes
		List<Node> sources = new ArrayList<Node>(graph);

		// The list of exit nodes
		List<Node> exits = new ArrayList<Node>(s.exits);
		//exits.add(graph.get(3));

		 
		//AntColonyOptimizationController aco = new AntColonyOptimizationController(20);
		//aco.findWay();
		
		int djixSurv = 0;
		
		int acoSurv = 0;
		
		int numberOfPassangers = 140;
		
		int numberOfRepetitions = 200;
		
		int timeForPassangersToExit = 8000;
		
		int flameSpreadTimer = 2;
		int howManyAnts = 200;
		
		boolean pheremonsFromEdge = false;//Denne forandrer på hvor de lagres
		boolean lookingForShortestPath = false; //Denne foranderer på hvordan pheremons er
		
		boolean goingForMostSafePath = false;
		
		String fileName = "Safest";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
		Date date = new Date();
		
		String tempString = dateFormat.format(date);
		
		String[] sl = tempString.split("/");
		
		String imageFileName = "Passangers-"+numberOfPassangers+" Repetitoins-"+numberOfRepetitions+" FireSpreadTime-"+flameSpreadTimer+" HowManyAnts-"+howManyAnts;
		
		fileName += imageFileName+sl[0]+"-"+sl[1]+"-"+sl[2]+"-"+sl[3];
		
		
		Random rand = new Random();
		ArrayList<Integer> leathalStartNodes = new ArrayList<Integer>();
		int leathalStartNode = 0;
		
		setExitTime(exits, timeForPassangersToExit);
		
		//Kjï¿½rer ACO og djixstra simuleringen.
		ResultsHandler results = new ResultsHandler(graph, fileName, numberOfPassangers, flameSpreadTimer, howManyAnts, pheremonsFromEdge, lookingForShortestPath, goingForMostSafePath);
		int maxTurns = 0;
		for(int i = 0; i<numberOfRepetitions; i++)
		{
			
				
				leathalStartNodes = setStartLethalNodes(graph, exits);
			
			//System.out.println("D Starting");
			int a = results.runSimulation(exits, fileName, leathalStartNodes);
			
			//System.out.println("A Starting");
			int b = results.runSimulationWithACO(graph, exits, fileName, leathalStartNodes);
			
			
			
			maxTurns = returnTheBiggest(a, b, maxTurns);
			
			results.replaceHumans(s.exits);
			results.resetPheremones(edges, true);
			
			leathalStartNodes.clear();
			
			double prosnet = ((double)i*100)/(double)numberOfRepetitions;
			
			if(prosnet%2 == 0)
			System.out.println(prosnet+"% done.");
			
			
		}
		
		System.out.println(maxTurns);
		
		
		/*
		System.out.println();
		System.out.println("Dijistra survivers: "+djixSurv+" ACO survivers: "+acoSurv);*/
		
		//String imageFileName = "Passangers-"+numberOfPassangers+" Repetitoins-"+numberOfRepetitions+" FireSpreadTime-"+flameSpreadTimer+" HowManyAnts-"+howManyAnts;
		
		dataPresenter dp = new dataPresenter(numberOfRepetitions, fileName, maxTurns, imageFileName);
		
		dp.crateGraph();
		
		//dp.test();
		
		/**exits.remove(exits.size()-1);
		 *sources.remove(sources.size()-1);
		 *
		 *Dijkstra d = new Dijkstra();
		 *d.getAllPaths(sources, exits, graph);
		 *
		 *RandomWalk rw = new RandomWalk();
		 *
		 *List<Node> test = rw.randomWalk(graph.get(0));
		 *for(Node n : test){
		 *		System.out.println(n.getID());
		 *}
		 *ResultsHandler results = new ResultsHandler();
		 *System.out.println(results.calculateSurvival(test));
		 */
	}

	
	private static int returnTheBiggest(int a, int b, int c)
	{
		if(a > b && a > c)
		{
			return a;
		}
		else if (b > a && b > c)
		{
			return b;
		}
		else
		{
			return c;
		}
	}
	
	private static ArrayList<Integer> setStartLethalNodes(List<Node> graph, List<Node> exits)
	{
		Random rand = new Random();
		int randomLethalNodes = rand.nextInt(2)+1;
		ArrayList<Integer> lethalNodes = new ArrayList<Integer>();
		for(int ij = 0; ij<randomLethalNodes; ij++)
		{
			int a = getLethalStartNode(graph, exits);
			lethalNodes.add(a);
		}
			//leathalStartNode = rand.nextInt(graph.size()-1);
			//leathalStartNodes.add(leathalStartNode);
		
		return lethalNodes;
	}
	
	private static int getLethalStartNode(List<Node> graph, List<Node> exits)
	{
		Random rand = new Random();
		int nodeID = rand.nextInt(graph.size()-1);
		for(Node n:exits)
		{
			if(n.getID() == nodeID)
			{
				nodeID = getLethalStartNode( graph, exits);
			}
		}
		return nodeID;
	}
	
	private static void setExitTime(List<Node> exits, int time)
	{
		for(Node n : exits)
		{
			n.movementAllowenceNeeded = time;
		}
	}
	
	private static List<Node> createTestGraph(){

		Node n1 = new Node(0, null, 1, 1);
		Node n2 = new Node(1, null, 1, 1);
		Node n3 = new Node(2, null, 1, 1);
		Node n4 = new Node(3, null, 1, 1);
		Node n5 = new Node(4, null, 1, 1);
		n4.setExit(true);	
		//n3.setChanceOfDeath(0.5);

		n1.addEdge(new Edge(1, 1, n2, 1, 10));
		n1.addEdge(new Edge(1, 1, n5, 1, 10));
		n2.addEdge(new Edge(1, 1, n1, 1, 10));
		n2.addEdge(new Edge(1, 1, n3, 1, 10));
		n3.addEdge(new Edge(1, 1, n2, 1, 10));
		n3.addEdge(new Edge(1, 1, n4, 1, 10));
		n4.addEdge(new Edge(1, 1, n3, 1, 10));
		n4.addEdge(new Edge(1, 1, n5, 1, 10));
		n5.addEdge(new Edge(1, 1, n1, 1, 10));
		n5.addEdge(new Edge(1, 1, n4, 1, 10));

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
		nodes.add(n4);
		nodes.add(n5);

		return nodes;
	}
}