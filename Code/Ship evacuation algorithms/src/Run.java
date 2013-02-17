import java.util.List;


public class Run {
	public static void main(String [ ] args)
	{
		//Shipbuilder sb = new Shipbuilder();		
		//Ship s = sb.createCelebrityXpedition();
		//System.out.println(s.fifthFlor.size());
		
		Node n1 = new Node(0, null, 1, 1);
		Node n2 = new Node(1, null, 1, 1);
		Node n3 = new Node(2, null, 1, 1);
		Node n4 = new Node(3, null, 1, 1);
		
		n1.addEdge(new Edge(1, 1, n2, null, 1));
		n1.addEdge(new Edge(1, 1, n3, null, 2));
		n2.addEdge(new Edge(1, 1, n4, null, 1));
		n3.addEdge(new Edge(1, 1, n4, null, 1));
		
		Node[] nodes = { n1, n2, n3, n4 };
		
		Dijkstra d = new Dijkstra();
		d.findPath(n1);
		
		for (Node n : nodes){
			System.out.print("Distance to " + n.getID() + ": " + n.minDistance + " ");
			List<Node> path = d.getShortestPath(n);
			System.out.print("Path: ");
			for(Node v : path){
				System.out.print(v.getID() + " ");
			}
			System.out.println();
		}
	}
}
