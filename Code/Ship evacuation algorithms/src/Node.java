import java.util.List;
import java.util.Random;


public class Node {
	private enum NodeType { STAIRS, GUESTROOM, HALLWAY, DININGROOM, GAMEROOM, SHOP }
	private static final RandomEnum<NodeType> randomEnum =
			new RandomEnum<NodeType>(NodeType.class);
	private float chanceOfDeath;
	private int capacity;
	private NodeType nodeType;
	private List<Vertex> listOfPaths;
	public int NodeID;	
	private boolean isExit;
	private int amountOfPheromones;
	public boolean hasWayToExit = false;
	
	public Node()
	{
		
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
