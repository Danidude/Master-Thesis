import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Human implements Cloneable {
	
	private List<Integer> familiarTies;
	private double chanceOfPanic;
	private boolean panicState;
	private Node node;
	private int humanID;
	private boolean escaped;
	public boolean isDead;
	private int movementAllowence;
	private boolean finishedMoving;
	private List<Node> knownExitNode;
	private int movementInCurrentNode;
	
	public Human (List<Integer> familiarTies, boolean panicState, Node node, int humanID, boolean escaped, int movementAllowence){
		this.familiarTies = familiarTies;
		this.panicState = panicState;
		this.node = node;
		this.humanID = humanID;
		this.escaped = escaped;
		knownExitNode = new ArrayList<Node>();
		
		this.movementAllowence = movementAllowence;
		movementInCurrentNode = 0;
		finishedMoving = false;
		isDead = false;
	}
	
	public void setStartMovementInCurrentNode(int i)
	{
		movementInCurrentNode = i;
	}
	
	public boolean isEscaped() {
		return escaped;
	}

	public void setEscaped(boolean escaped) {
		this.escaped = escaped;
	}
	
	public int getHumanID() {
		return humanID;
	}
	
	public boolean isPanicState() {
		return panicState;
	}
	
	public void setPanicState(boolean panicState) {
		this.panicState = panicState;
	}
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}	
	
	public double getChanceOfPanic() {
		return chanceOfPanic;
	}
	
	public void setChanceOfPanice (double chance){
		this.chanceOfPanic = chance;
	}
	
	public List<Integer> getFamiliarTies() {
		return familiarTies;
	}
	
	public void setIDFamiliarTies(List<Integer> familiarTies){
		if(familiarTies.contains(this.humanID)){ 
			this.familiarTies = familiarTies;
			this.familiarTies.remove(new Integer(this.humanID)); 
			}
		else this.familiarTies = familiarTies;
	}
	
	public void addFamilyMember(Integer h){
		familiarTies.add(h);
	}
	
	public void setMovementAllowence(int i)
	{
		movementAllowence = i;
	}
	
	public int getMovementAllowence()
	{
		return movementAllowence;
	}
	
	public void decreeseMovementAllowence()
	{
		movementAllowence -= 1;
	}
	/*
	 * Moves the passanger to the next node and checks for famely members
	 */
	public int moveHuman(Node n)
	{
		int tempMovementSpeed = movementAllowence;
		if (n.movementAllowenceNeeded > movementInCurrentNode)
		{
			if(n.currentHumansInNode.size()/9 >= 3.5)
			{
				tempMovementSpeed = movementAllowence/12;
			}
			else if(n.currentHumansInNode.size()/9 >= 3.2)
			{
				tempMovementSpeed = movementAllowence/6;
			}
			else if(n.currentHumansInNode.size()/9 >= 1.9)
			{
				double a = (double)movementAllowence/1.8;
				tempMovementSpeed = (int)a;
			}
			
			
			movementInCurrentNode += tempMovementSpeed;
			
			tempMovementSpeed = movementInCurrentNode - n.movementAllowenceNeeded;
			
			if (tempMovementSpeed < 0) {
				tempMovementSpeed = 0;
			}
			
			if(n.currentHumansInNode.size()/9 >= 3.5)
			{
				movementAllowence = tempMovementSpeed*12;
			}
			else if(n.currentHumansInNode.size()/9 >= 3.2)
			{
				movementAllowence = tempMovementSpeed*6;
			}
			else if(n.currentHumansInNode.size()/9 >= 1.9)
			{
				double a = (double)tempMovementSpeed*1.8;
				movementAllowence = (int)a;
			}
		}
		boolean mayMoveToNextNode = false;
		if(n.isExit() || n == node)
		{
			mayMoveToNextNode = true;
		}
		else
		{
			for(Edge e : node.getPaths())
			{
				if(e.getNode() == n)
				{
					e.incrementCurrentFlow();
					mayMoveToNextNode = e.mayMoveToNextNode();
				}
			}
		}
		
		if (movementInCurrentNode >= n.movementAllowenceNeeded && mayMoveToNextNode)
		{
			if(n.isExit())
			{
				escaped = true;
				return 0;
			}
			
			if(n.currentHumansInNode.size()>=n.capacity && !panicState)
			{
				return movementAllowence;
			}
			
			while(node.currentHumansInNode.contains(this))
			{
				node.currentHumansInNode.remove(this);
			}
			
			
			
			if(!panicState)
			{
				knownExitNode.add(0, n);
			}
			else
			{
				knownExitNode.remove(node);
				n.testOverCapacityInNode();
			}
			n.currentHumansInNode.add(this);
			
			node = n;
			//movementAllowence -= 1;
			movementInCurrentNode = 0;
			return movementAllowence;
		}
		else
			return -1;
		
	}
	
	public void setKnownPathToExit(List<Node> list)
	{
		knownExitNode.addAll(list);
	}
	
	public List<Node> getKnownPathList()
	{
		return knownExitNode;
	}
	
	public void removeNodeFromKnownPathToExit(Node n)
	{
		if(knownExitNode.size()>1)
		knownExitNode.remove(n);
	}
	
	public void removePreviusNodesInList(int i)
	{
		if(knownExitNode.size() > 1)
		for(int k =i; k>0; k--)
		{
			knownExitNode.remove(k);
		}
		
	}
	
	public void resetValues()
	{
		isDead = false;
		escaped = false;
		movementInCurrentNode = 0;
		panicState = false;
	}
}
