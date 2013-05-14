import java.util.HashMap;
import java.util.Map;


public class Edge {
	
private int attractiveness;
private int edgeID;
private float pheremones;
private Map<Integer, Double> pheromonesForEachHuman;
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
		pheromonesForEachHuman = new HashMap<Integer, Double>();
	}
	
	public Node getNode()
	{
		return node;
	}	
	
	public int getWeight()
	{
		if(node == null)
		{
			System.out.println(edgeID);
		}
		return node.movementAllowenceNeeded;
	}
	
	/*public float getPheremones()
	{
		return pheremones;
	}
	
	public void addPheremones(double d)
	{
		this.pheremones += d;
	}
	
	public float getPheremonesAndAttractiveness()
	{
		if(node.getChanceOfDeath() > 0)
		{
			
			float f = pheremones + (float)attractiveness; //- (float)(attractiveness*node.getChanceOfDeath());
			if(f <= 0)
			{
				return 1;
			}
			return f;
		}
		else
		return pheremones + attractiveness;
	}
	
	public void setPheremones(int pheremones)
	{
		this.pheremones = pheremones;
	}*/
	
	//-------------
	
	public double getPheremonesForThatHuman(int humanID)
	{
		if(!pheromonesForEachHuman.containsKey(humanID))
		{
			pheromonesForEachHuman.put(humanID, 0.0);
		}
		return pheromonesForEachHuman.get(humanID);
	}
	
	public void addPheremonesForThatHuman(double d, int humanID)
	{
		pheromonesForEachHuman.put(humanID, pheromonesForEachHuman.get(humanID)+d);
		if(pheromonesForEachHuman.get(humanID) ==  null)
		{
			System.out.println("It is done");
		}
	}
	
	public double getPheremonesAndAttractivenessForThatHuman(int humanID)
	{
		if(!pheromonesForEachHuman.containsKey(humanID))
		{
			pheromonesForEachHuman.put(humanID, 0.0);
		}
		
		if(node.getChanceOfDeath() > 0)
		{
			
			double f =  pheromonesForEachHuman.get(humanID) + ((double)attractiveness); //- (double)((double)attractiveness*node.getChanceOfDeath()));
			if(f <= 1)
			{
				return 1;
			}
			return f;
		}
		else
		return  pheromonesForEachHuman.get(humanID) + attractiveness;
	}
	
	public void setPheremonesForThatHuman(double pheremones, int humanID)
	{
		pheromonesForEachHuman.put(humanID, pheremones);
		if(pheromonesForEachHuman.get(humanID) ==  null)
		{
			System.out.println("It is done");
		}
	}
	
	public void resetPheremonesForEachHuman()
	{
		pheromonesForEachHuman.clear();
	}
	
	public int getFlow()
	{
		return flow;
	}
}
