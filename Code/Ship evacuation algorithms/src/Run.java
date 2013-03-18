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
		
		// The probability the humans have family members on board the ship
		double chanceOfFamily = 1.0;

		// The list of source nodes
		List<Node> sources = new ArrayList<Node>(createTestGraph());
		
		// The list of exit nodes
		List<Node> exits = new ArrayList<Node>(sources);
		
		// The entire graph
		List<Node> graph = new ArrayList<Node>(sources);
		
		
		List<Human> humans = new ArrayList<Human>(createHumans(5));
		createFamilyTies(chanceOfFamily, humans);
		humans = placeHumans(humans, graph);	
				
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
	
	private static List<Human> createHumans(int number){
		List<Human> humans = new ArrayList<Human>();
		List<Integer> familyTies = new ArrayList<Integer>();
		for(int i = 0; i < number; i++){
			Human human = new Human(familyTies, false, 0, i);
			humans.add(human);
		}
		return humans;
	}
	
	private static List<Human> placeHumans(List<Human> humans, List<Node> graph){
		Random randomPlace = new Random();
		for(Human h : humans){
			h.setNodeID(randomPlace.nextInt(graph.size()));
		}
		return humans;
	}
	
	private static List<Human> createFamilyTies(double chanceOfFamily, List<Human> humans){
		// Use the old list to create a new list with complete family ties
		
		List<Human> hasFamily = new ArrayList<Human>();
		Random randomChance = new Random();		
		Iterator<Human> iter = humans.iterator();
		while(iter.hasNext()){
			if(randomChance.nextDouble() < chanceOfFamily){
				hasFamily.add(iter.next());
				iter.remove();
			}
			else{
				iter.next();
				iter.remove();
			}

		}
		
		// Select random family sizes and send to the function
		hasFamily = isRelated(hasFamily);

		humans.addAll(hasFamily);	

		for(Human hu: humans){
			for(int i : hu.getFamiliarTies()){
				System.out.println("Human " + hu.getHumanID() + " is related to " + i);
			}
		}		
		return humans;
	}
	// Just create a list of human IDs and add those excluding the humans own id
	private static List<Human> isRelated(List<Human> humans){
		Iterator<Human> iter = humans.iterator();
		while(iter.hasNext()){
			iter.next().setHumanFamiliarTies(humans);
		}
		return humans;
	}
}