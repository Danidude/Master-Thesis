
public class Edge {
	
private int flow;
private int edgeID;
private float pheremones;
private Node node;
private Ship s;
private int weight;

	public Edge(int flow, int edgeID, Node node, Ship s, int weight)
	{
		this.edgeID = edgeID;
		this.flow = flow;
		this.node = node;
		this.s = s;
		this.weight = weight;
	}
	
	public Node getNode()
	{
		return node;
	}	
	
	public int getWeight()
	{
		return weight;
	}
}
