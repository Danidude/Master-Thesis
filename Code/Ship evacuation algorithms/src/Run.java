import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Run {
	public static void main(String [ ] args)
	{
		//Shipbuilder sb = new Shipbuilder();		
		//Ship s = sb.createCelebrityXpedition();
		//System.out.println(s.fifthFlor.size());
		
		HumanHandler humanHandler = new HumanHandler();
		
		// The probability the humans have family members on board the ship
		double chanceOfFamily = 0.7;

		// The list of source nodes
		List<Node> sources = new ArrayList<Node>(createTestGraph());
		
		// The list of exit nodes
		List<Node> exits = new ArrayList<Node>(sources);
		
		// The entire graph
		List<Node> graph = new ArrayList<Node>(sources);
		
		AntColonyOptimizationController aco = new AntColonyOptimizationController(10, graph.get(0));
		
		aco.findWay();
		
		List<Human> humans = new ArrayList<Human>(humanHandler.createHumans(5));
		humanHandler.createFamilyTies(chanceOfFamily, humans);
		humans = humanHandler.placeHumans(humans, graph);	
				
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
		n4.setExit(true);	
		n3.setChanceOfDeath(0.1);
		n2.setChanceOfDeath(0.1);
		
		n1.addEdge(new Edge(1, 1, n2, 1));
		n1.addEdge(new Edge(1, 1, n3, 1));
		n1.addEdge(new Edge(1, 1, n4, 1));
		n2.addEdge(new Edge(1, 1, n1, 1));
		n2.addEdge(new Edge(1, 1, n3, 1));
		n2.addEdge(new Edge(1, 1, n4, 1));
		n3.addEdge(new Edge(1, 1, n1, 1));
		n3.addEdge(new Edge(1, 1, n2, 1));
		n3.addEdge(new Edge(1, 1, n4, 1));
		n4.addEdge(new Edge(1, 1, n2, 1));
		n4.addEdge(new Edge(1, 1, n3, 1));
		n4.addEdge(new Edge(1, 1, n1, 1));

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
		nodes.add(n4);
		
		return nodes;
	}
}