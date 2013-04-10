import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Run {
	public static void main(String [ ] args) throws FileNotFoundException, UnsupportedEncodingException
	{
		//Shipbuilder sb = new Shipbuilder();		
		//Ship s = sb.createCelebrityXpedition();
		//System.out.println(s.fifthFlor.size());

		// The entire graph
		List<Node> graph = new ArrayList<Node>(createTestGraph());

		// The list of source nodes
		List<Node> sources = new ArrayList<Node>(graph);

		// The list of exit nodes
		List<Node> exits = new ArrayList<Node>();
		exits.add(graph.get(3));

		 
		//AntColonyOptimizationController aco = new AntColonyOptimizationController(20);
		//aco.findWay();
		
		int djixSurv = 0;
		
		int acoSurv = 0;

		ResultsHandler results = new ResultsHandler(graph);
		djixSurv = results.runSimulation(exits);
		
		acoSurv = results.runSimulationWithACO(graph, exits);
		System.out.println();
		System.out.println("Dijistra survivers: "+djixSurv+" ACO survivers: "+acoSurv);

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

	private static List<Node> createTestGraph(){

		Node n1 = new Node(0, null, 1, 1);
		Node n2 = new Node(1, null, 1, 1);
		Node n3 = new Node(2, null, 1, 1);
		Node n4 = new Node(3, null, 1, 1);
		Node n5 = new Node(4, null, 1, 1);
		n4.setExit(true);	
		n3.setChanceOfDeath(0.5);

		n1.addEdge(new Edge(1, 1, n2, 1));
		n1.addEdge(new Edge(1, 1, n5, 1));
		n2.addEdge(new Edge(1, 1, n1, 1));
		n2.addEdge(new Edge(1, 1, n3, 1));
		n3.addEdge(new Edge(1, 1, n2, 1));
		n3.addEdge(new Edge(1, 1, n4, 1));
		n4.addEdge(new Edge(1, 1, n3, 1));
		n4.addEdge(new Edge(1, 1, n5, 1));
		n5.addEdge(new Edge(1, 1, n1, 1));
		n5.addEdge(new Edge(1, 1, n4, 1));

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
		nodes.add(n4);
		nodes.add(n5);

		return nodes;
	}
}