import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
		
		int numberOfPassangers = 100;
		
		int numberOfRepetitions = 200;
		
		String fileName = "test en.";
		
		
		Random rand = new Random();
		int leathalStartNode;
		
		//Kjører ACO og djixstra simuleringen.
		ResultsHandler results = new ResultsHandler(graph, fileName, numberOfPassangers);
		int maxTurns = 0;
		for(int i = 0; i<numberOfRepetitions; i++)
		{
			leathalStartNode = rand.nextInt(graph.size()-1);
			
			//System.out.println("A Starting");
			int b = results.runSimulationWithACO(graph, exits, fileName, leathalStartNode);
			
			//System.out.println("D Starting");
			int a = results.runSimulation(exits, fileName, leathalStartNode);
			
			maxTurns = returnTheBiggest(a, b, maxTurns);
			
			results.replaceHumans(s.exits);
			results.resetPheremones(edges);
			
			double prosnet = ((double)i*100)/(double)numberOfRepetitions;
			
			if(prosnet%5 == 0)
			System.out.println(prosnet+"% done.");
			
			
		}
		
		System.out.println(maxTurns);
		
		
		/*
		System.out.println();
		System.out.println("Dijistra survivers: "+djixSurv+" ACO survivers: "+acoSurv);*/
		
		dataPresenter dp = new dataPresenter(numberOfRepetitions, fileName, maxTurns);
		
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