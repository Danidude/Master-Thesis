
public class Edge {
	
private int flow;
private int edgeID;
private float pheremones;
private int nodeID;
private Ship s;

	public Edge(int flow, int edgeID, int nodeID, Ship s)
	{
		this.edgeID = edgeID;
		this.flow = flow;
		this.nodeID = nodeID;
		this.s = s;
	}
	
	public int getNodeID()
	{
		return nodeID;
	}
	
	public Node getNode()
	{
		return s.fifthFlor.get(nodeID);
	}
}
