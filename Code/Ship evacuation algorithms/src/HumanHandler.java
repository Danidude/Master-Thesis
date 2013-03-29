import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class HumanHandler {

	// The only family size there can be
	int familySize = 2;
	
	// Creates a random number of humans with a "default setting" of non-panic, no relatives on board and unknown position on the ship
	public List<Human> createHumans(int number){
		List<Human> humans = new ArrayList<Human>();
		List<Integer> familyTies = new ArrayList<Integer>();
		for(int i = 0; i < number; i++){
			Human human = new Human(familyTies, false, null, i, false);
			humans.add(human);
		}
		return humans;
	}
	
	// Positions passengers in random nodes
	public List<Human> placeHumans(List<Human> humans, List<Node> graph){
		Random randomPlace = new Random();
		for(Human h : humans){
			h.setNode(graph.get(randomPlace.nextInt(graph.size()-1)));
		}
		return humans;
	}
	
	// Takes in the list of all passengers and the chance that they have a family and returns the same list, now with family ties.
	public List<Human> createFamilyTies(double chanceOfFamily, List<Human> humans){
		
		List<Human> hasFamily = new ArrayList<Human>();
		Random randomChance = new Random();		
		Iterator<Human> iter = humans.iterator();
		
		// iterates over the complete list of passengers and randomly selects which passengers have family members on the ship
		while(iter.hasNext()){
			if(randomChance.nextDouble() < chanceOfFamily){
				hasFamily.add(iter.next());
				iter.remove();
			}
			else{
				iter.next();
				iter.remove();
			}
		}
		
		// Temporary variable used to set the lower boundary of the sublists
		int min = 0;
		
		// Temporary list used to hold the results from the sub lists created below
		List<Human> otherTemp = new ArrayList<Human>();
		
		/* The family size is currently set in stone and all families are of that size. 
		 * Sub lists are created from the humans that have a family and they are put
		 * into fixed sized families.
		 */
		for(int i = 0; i <= hasFamily.size(); i+=familySize){
			List<Human> temp = new ArrayList<Human>();
			temp = hasFamily.subList(min, i);
			min = i;
			otherTemp.addAll(isRelated(temp));
		}
		hasFamily = otherTemp;
		
		// The people that have families are combined with the people that are without family members
		humans.addAll(hasFamily);	

		// Printing results for testing
		for(Human hu: humans){
			for(int i : hu.getFamiliarTies()){
				System.out.println("Human " + hu.getHumanID() + " is related to " + i);
			}
		}		
		return humans;
	}
	
	private static List<Human> isRelated(List<Human> humans){
		List<Integer> id = new ArrayList<Integer>();
		Iterator<Human> iter = humans.iterator();
		
		// Iterates over the list of humans and constructs a new list containing the IDs of the humans
		while(iter.hasNext()){
			id.add(iter.next().getHumanID());
		}
		
		// Iterates over the list of humans again and sets the family ties
		Iterator<Human> it = humans.iterator();
		while(it.hasNext()){
			/* Created this additional list to avoid changes made to one human affecting 
			 * changes made to another human by referencing the same object.
			 * This fixed a bug where all humans had to be related to themselves.
			 * */
			List<Integer> ids = new ArrayList<Integer>(id);
			it.next().setIDFamiliarTies(ids);
		}
		return humans;
	}
}
