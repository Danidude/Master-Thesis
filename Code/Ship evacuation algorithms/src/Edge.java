
public class Edge {
	
private int flow;
private int edgeID;
private float pheremones;
private int nodeID;

	public Edge(int flow, int edgeID, int nodeID)
	{
		this.edgeID = edgeID;
		this.flow = flow;
		this.nodeID = nodeID;
	}
	
	public int getNodeID()
	{
		return nodeID;
	}
}
