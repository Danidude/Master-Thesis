import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class HumanHandler {

	// The minimum and maximum size of the families
	int minFamilySize = 2;
	int maxFamilySize = 5;
	
	public List<Human> createHumans(int number){
		List<Human> humans = new ArrayList<Human>();
		List<Integer> familyTies = new ArrayList<Integer>();
		for(int i = 0; i < number; i++){
			Human human = new Human(familyTies, false, 0, i);
			humans.add(human);
		}
		return humans;
	}
	
	public List<Human> placeHumans(List<Human> humans, List<Node> graph){
		Random randomPlace = new Random();
		for(Human h : humans){
			h.setNodeID(randomPlace.nextInt(graph.size()));
		}
		return humans;
	}
	
	public List<Human> createFamilyTies(double chanceOfFamily, List<Human> humans){
		// Use the old list to create a new list with complete family ties
		
		List<Human> hasFamily = new ArrayList<Human>();
		Random randomChance = new Random();		
		Iterator<Human> iter = humans.iterator();
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
		
		// Select random family sizes and send to the function
		hasFamily = isRelated(hasFamily);

		humans.addAll(hasFamily);	

		for(Human hu: humans){
			for(int i : hu.getFamiliarTies()){
				System.out.println("Human " + hu.getHumanID() + " is related to " + i);
			}
		}		
		return humans;
	}
	
	// All humans are now related to themselves. Lets call it a feature bug
	public List<Human> isRelated(List<Human> humans){
		List<Integer> id = new ArrayList<Integer>();
		Iterator<Human> iter = humans.iterator();
		while(iter.hasNext()){
			id.add(iter.next().getHumanID());
		}
		Iterator<Human> it = humans.iterator();
		while(it.hasNext()){
			it.next().setIDFamiliarTies(id);
		}
		return humans;
	}
}
