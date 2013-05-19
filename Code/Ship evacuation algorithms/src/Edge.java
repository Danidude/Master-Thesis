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
private int currentFlow;

	public Edge(int attractiveness, int edgeID, Node node, int weight, int flow)
	{
		pheremones = 0; 
		//attractiveness = 100;
		this.edgeID = edgeID;
		this.attractiveness = attractiveness;
		this.node = node;
		this.weight = weight;
		this.flow = flow;
		this.currentFlow = 0;
		pheromonesForEachHuman = new HashMap<Integer, Double>();
	}
	
	public Node getNode()
	{
		return node;
	}	
	
	public double getWeight()
	{
		if(node == null)
		{
			System.out.println(edgeID);
		}
		
		double howManyTurnsNeeded = 1;
		int ab = node.currentHumansInNode.size();
		
		if(node.currentHumansInNode.size()/9 >= 3.5)
		{
			howManyTurnsNeeded = (double)node.movementAllowenceNeeded/(120.0/12.0);
		}
		else if(node.currentHumansInNode.size()/9 >= 3.2)
		{
			howManyTurnsNeeded = (double)node.movementAllowenceNeeded/(120.0/6.0);
		}
		else if(node.currentHumansInNode.size()/9 >= 1.9)
		{
			
			howManyTurnsNeeded = (double)node.movementAllowenceNeeded/(120.0/1.8);
		}
		else{
			howManyTurnsNeeded = node.movementAllowenceNeeded/120;
		}
		
		return howManyTurnsNeeded;
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
	
	public double getPheremonesForThatHuman(int humanID, boolean fromEdge)
	{
		if(fromEdge)
		{
			if(!pheromonesForEachHuman.containsKey(humanID))
			{
				pheromonesForEachHuman.put(humanID, 0.0);
			}
			return pheromonesForEachHuman.get(humanID);
		}
		else
		{
			return node.getPheremonesForThatHuman(humanID);
		}
		
	}
	
	public void addPheremonesForThatHuman(double d, int humanID, boolean fromEdge)
	{
		if(fromEdge)
		{
			pheromonesForEachHuman.put(humanID, pheromonesForEachHuman.get(humanID)+d);
			if(pheromonesForEachHuman.get(humanID) ==  null || pheromonesForEachHuman.get(humanID) < 0.0)
			{
				System.out.println("It is done. Check evaporate.");
			}
		}
		else
		{
			node.addPheremonesForThatHuman(d, humanID);
		}
		
	}
	
	public double getPheremonesAndAttractivenessForThatHuman(int humanID, boolean fromEdge)
	{
		if(fromEdge)
		{
			if(!pheromonesForEachHuman.containsKey(humanID))
			{
				pheromonesForEachHuman.put(humanID, 0.0);
			}
			
			if(node.getChanceOfDeath() > 0)
			{
				
				double f =  pheromonesForEachHuman.get(humanID) + ((double)attractiveness); //- (double)((double)attractiveness*node.getChanceOfDeath()));
				if(f <= 0)
				{
					return 0;
				}
				return f;
			}
			else
			return  pheromonesForEachHuman.get(humanID) + attractiveness;
		}
		else
		{
			return node.getPheremonesAndAttractivenessForThatHuman(humanID)+attractiveness;
		}
		
	}
	
	public void setPheremonesForThatHuman(double pheremones, int humanID, boolean fromEdge)
	{
		if(fromEdge)
		{
			if(pheremones < 0)
			{
				System.out.println("Adding negativ!");
			}
			
			pheromonesForEachHuman.put(humanID, pheremones);
			if(pheromonesForEachHuman.get(humanID) ==  null)
			{
				System.out.println("It is done");
			}
		}
		else
		{
			node.setPheremonesForThatHuman(pheremones, humanID);
		}
		
	}
	
	public void resetPheremonesForEachHuman(boolean fromEdge)
	{
		if(fromEdge)
		{
			pheromonesForEachHuman.clear();
		}
		else
		{
			node.resetPheremonesForEachHuman();
		}
		
	}
	
	public int getFlow()
	{
		return flow;
	}
	
	public int getCurrentFlow()
	{
		return currentFlow;
	}
	
	public void incrementCurrentFlow()
	{
		currentFlow += 1;
	}
	public boolean mayMoveToNextNode()
	{
		if(node.getNodeDensety() > 3 && currentFlow <= 3)
		{
			return true;
		}
		else if(node.getNodeDensety() > 2 && currentFlow <= 6)
		{
			return true;
		}
		else if(currentFlow <= 12)
		{
			return true;
		}
		return false;
	}
	
	public void resetCurrentFlow()
	{
		currentFlow = 0;
	}
}
