
public class Edge {
	
private int attractiveness;
private int edgeID;
private float pheremones;
private Node node;
private Ship s;
private int weight;
private int flow;

	public Edge(int attractiveness, int edgeID, Node node, int weight, int flow)
	{
		pheremones = 0;
		//attractiveness = 100;
		this.edgeID = edgeID;
		this.attractiveness = attractiveness;
		this.node = node;
		this.weight = weight;
		this.flow = flow;
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
	
	public void setPheremones(int pheremones)
	{
		this.pheremones = pheremones;
	}
	
	public int getFlow()
	{
		return flow;
	}
}
