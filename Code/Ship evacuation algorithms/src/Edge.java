
public class Edge {
	
private int attractiveness;
private int edgeID;
private float pheremones;
private Node node;
private Ship s;
private int weight;

	public Edge(int flow, int edgeID, Node node, int weight)
	{
		pheremones = 0;
		attractiveness = 100;
		this.edgeID = edgeID;
		this.attractiveness = flow;
		this.node = node;
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
	
	public float getPheremones()
	{
		return pheremones;
	}
	
	public void addPheremones(double d)
	{
		this.pheremones += d;
	}
	
	public float getPheremonesAndAttractiveness()
	{
		return pheremones + attractiveness;
	}
}
