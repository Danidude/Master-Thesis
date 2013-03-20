import java.util.ArrayList;
import java.util.List;


public class Human {
	
	private List<Integer> familiarTies;
	private double chanceOfPanic;
	private boolean panicState;
	private Node node;
	private int humanID;
	private boolean escaped;
	
	public Human (List<Integer> familiarTies, boolean panicState, Node node, int humanID, boolean escaped){
		this.familiarTies = familiarTies;
		this.panicState = panicState;
		this.node = node;
		this.humanID = humanID;
		this.escaped = escaped;
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
}
