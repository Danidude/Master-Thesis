import java.util.ArrayList;
import java.util.List;


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
		
		if (n.movementAllowenceNeeded > movementInCurrentNode)
		{
			movementInCurrentNode += movementAllowence;
			
			movementAllowence = movementInCurrentNode - n.movementAllowenceNeeded;
			
			if (movementAllowence < 0) {
				movementAllowence = 0;
			}
		}
		
		if (movementInCurrentNode >= n.movementAllowenceNeeded)
		{
			
			if(n.currentHumansInNode.size()>=n.capacity && !panicState)
			{
				return movementAllowence;
			}
			
			while(node.currentHumansInNode.contains(this))
			{
				node.currentHumansInNode.remove(this);
			}
			node = n;
			
			
			if(!panicState)
			{
				knownExitNode.add(0, n);
			}
			else
			{
				knownExitNode.remove(0);
				n.testOverCapacityInNode();
			}
			n.currentHumansInNode.add(this);
			
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
