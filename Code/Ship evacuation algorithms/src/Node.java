import java.util.List;
import java.util.Random;


public class Node {
	public enum NodeType { STAIRS, GUESTROOM, HALLWAY, DININGROOM, GAMEROOM, SHOP }
	private static final RandomEnum<NodeType> randomEnum =
			new RandomEnum<NodeType>(NodeType.class);
	private float chanceOfDeath;
	private int capacity;
	private NodeType nodeType;
	private List<Vertex> listOfPaths;
	public int NodeID;	
	private int florNumber;
	private boolean isExit;
	private int amountOfPheromones;
	public boolean hasWayToExit = false;
	
	
	
	public Node(int NodeID, NodeType nt, int cap, int fNumber)
	{
		this.nodeType = nt;
		this.capacity = cap;
		this.NodeID = NodeID;
		this.florNumber = fNumber;
	}
	
	
	private static class RandomEnum<E extends Enum<NodeType>> {
		private static final Random randomGenerator = new Random();
		private final E[] values;

		public RandomEnum(Class<E> token){
			values = token.getEnumConstants();
		}

		public E random() {
			return values[randomGenerator.nextInt(values.length)];
		}
	}
}
