import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;


public class ResultsHandler {
	List<Human> startHumansDjixstra;
	List<Human> startHumansACO;
	Map<Edge, Integer> flowOfEdges;

	List<Node> graph;
	List<Node> listOfLethalNodes;
	List<Edge> edges;
	
	boolean pheremonsFromEdge;
	boolean lookingForShortest;
	boolean goingForMostSafePath;
	
	int humansMovementAllaowence = 0;
	int flameSpreadTimer = 5;
	int howManyAnts = 200;
	
	float initialPanicChance;
	
	HumanHandler humanHandler;
	
	public ResultsHandler(List<Node> graph, String fileName, int numberOfPassangers, int flameSpeedTimer, int howManyAnts, boolean pheremonsFromEdge, boolean lookingForShortest, boolean goingForMostSafePath) throws FileNotFoundException, UnsupportedEncodingException{
		
		this.graph = graph;
		listOfLethalNodes = new ArrayList<Node>();
		flowOfEdges = new HashMap<Edge, Integer>();
		this.flameSpreadTimer = flameSpeedTimer;
		this.howManyAnts = howManyAnts;
		this.pheremonsFromEdge = pheremonsFromEdge;
		this.lookingForShortest	= lookingForShortest;
		this.goingForMostSafePath = goingForMostSafePath;
						
		// The probability the humans have family members on board the ship
		double chanceOfFamily = 1.0;
		initialPanicChance = (float) 0.001;
		
		// Get the location of all humans in the graph
		humanHandler = new HumanHandler();

		List<Human> humans = new ArrayList<Human>(humanHandler.createHumans(numberOfPassangers));
		humanHandler.createFamilyTies(chanceOfFamily, humans);
		
		List<Node> exits = new ArrayList<Node>();
		for(Iterator<Node> it = graph.iterator(); it.hasNext();)
		{
			Node n = it.next();
			
			if(n.isExit())
			{
				exits.add(n);
			}
		}
		
		// Bug : this somehow kills a few people in the process
		humans = humanHandler.placeHumans(humans, graph, exits);
		
		edges = new ArrayList<Edge>();
		for(Node n : graph)
		{
			edges.addAll(n.getPaths());
		}
		
		startHumansDjixstra = new ArrayList<Human>(humans);
		startHumansACO = cloneList(startHumansDjixstra);
		//Collections.copy(startHumansACO, startHumansDjixstra);
		
		//Sets the max human movement allowence so it may be reset after each movement.
		humansMovementAllaowence = humanHandler.getMovementAllowence();
		
		PrintWriter writer = new PrintWriter("Djixstra\\Dixstra "+fileName+".txt", "UTF-8");
		writer.println("Djixstra data:");
		writer.println("Turn number		Number of passangers in total		Passangers still living		Dead Passangers		Passangers that have survived");
		writer.close();
		
		writer = new PrintWriter("ACO\\ACO "+fileName+".txt", "UTF-8");
		writer.println("ACO data:");
		writer.println("Turn number		Number of passangers in total		Passangers still living		Dead Passangers		Passangers that have survived");
		writer.close();
		
		for(Human h : humans){
			System.out.println("Human: " + h.getHumanID() + " is at node: " + h.getNode().getID() + " Known path size: "+h.getKnownPathList().size());
			
		}
		System.out.println("----------------------");

	}
	
	// Computes the probability of surviving by walking along the provided path
	public double calculateSurvival(List<Node> path){
		double survivalChance = 0.0;
		for(Node n : path){
			if(survivalChance == 0.0){
				survivalChance = 1.0 - n.getChanceOfDeath();
			}
			else if(n.getChanceOfDeath()==0){

			}
			else{
				survivalChance *= 1.0 - n.getChanceOfDeath();
			}			
		}
		return survivalChance;	
	}

	// The main function which controls the flow of the simulation
	public int runSimulation(List<Node> exits, String fileName, ArrayList<Integer> lethalStartNode) throws IOException{
		
		List<Human> humans = new ArrayList<Human>(startHumansDjixstra);
		
		resetLethalNodes();
		
		
		for(Integer it : lethalStartNode)
		{
			graph.get(it).setChanceOfDeath(0.5);
		}
		
		addLethalNodesToList();
		
		Dijkstra dijkstra = new Dijkstra();
		
		Random randomGenerator = new Random();
		
		double chanceOfSpread = 0.5;
		
		double chanceOfDeath = 0.5;

		
		int resetNumber = 0;
		
		double numberOfSurvivers = 0;
		double deadCounter = 0;
		double turnCounter = 0;
		double totalPassanger = humans.size();
		double totalLivingPassangers = 0;
		
		
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Djixstra\\Dixstra "+fileName+".txt", true)));
		writer.println(turnCounter+",			"+totalPassanger+",					"+totalPassanger+",				"+deadCounter+",			"+numberOfSurvivers);
		int counter = 0;
		// Calculate the path off the ship for each passenger
		while(!humans.isEmpty()){
			
			// Spreads fire randomly, but should random spread at a fixed speed and between floors
			// randomSpreadFire(graph, chanceOfSpread, chanceOfDeath);
			turnCounter++;
			resetNumber++;
			
			
			if(turnCounter%5000 == 0 && turnCounter != 0)
			{
				System.out.println("Stand still?");
			}
			
			
			for(Iterator<Human> it = humans.iterator(); it.hasNext(); ){
				Human h = it.next();
				
				//counter++;
				
				if(h.isDead)
				{
					it.remove();
					deadCounter++;
					continue;
				}
				
				testIfHumanPanics(h, randomGenerator);
				
				// If the passenger is currently in a node that counts as an exit the passenger has escaped and is removed from the list of humans
				if(h.isEscaped()){
					//System.out.println("Human " + h.getHumanID() + " escaped(Djixsta) " +turnCounter);
					numberOfSurvivers++;
					h.getNode().currentHumansInNode.remove(h);
					it.remove();
				}
				else if(h.isPanicState())
				{
					movePanicedHuman(h);
					
					if(h.getNode().getChanceOfDeath() > randomGenerator.nextDouble()){
						//System.out.println("Shit son, human: " + h.getHumanID() + " just burned to death");
						h.getNode().currentHumansInNode.remove(h);
						deadCounter++;
						it.remove();
					}
				}
				else
				{
					if(goingForMostSafePath)
					//if(goingForMostSafePath && turnCounter <= 1)
					{
						dijkstra.findSafestPath(h.getNode());
					}
					else //if(turnCounter <= 1)
					{
						dijkstra.findPath(h.getNode());
					}
					
					//if(turnCounter<=1)
					//{
						// Get the distance and path to all exits
						Map<Double, List<Node>> path = dijkstra.getDistancePaths(exits, graph);
	
						// Sort the paths based on the shortest distance
						SortedSet<Double> keys = new TreeSet<Double>(path.keySet());
						
						
						
						List<Node> shortestPath = path.get(keys.first());
						
						
						//h.dijkstraNodePath = shortestPath;
					//}
						
						
					
					
					
					// Do one step
					//h.setNode(shortestPath.get(1));
					moveHumans2(h, shortestPath, humans);
					//moveHumans2(h, h.dijkstraNodePath, humans);
					//System.out.println("Human " + h.getHumanID() + " moves to node " + h.getNode().getID());
					
					
					
					// Kills humans off, dead humans should add to the room capacity
					if(h.getNode().getChanceOfDeath() > randomGenerator.nextDouble()){
						//System.out.println("Shit son, human: " + h.getHumanID() + " just burned to death");
						h.getNode().currentHumansInNode.remove(h);
						h.isDead = true;
						deadCounter++;
						it.remove();
					}
					
				}
			}
			
			/*
			 * Save the amount of surivers at this point
			 * The amount that have yet to reach the exit
			 * the ones that have reached the exit
			 * and the amount of passangers that have diead
			 * the time step of all this.
			 */
			totalLivingPassangers = totalPassanger - deadCounter;
			writer.println(turnCounter+",			"+totalPassanger+",					"+totalLivingPassangers+",				"+deadCounter+",			"+numberOfSurvivers);
			
			
			
			resetHumanMovementAllowence(humans);
			if(resetNumber >= flameSpreadTimer)
			{
				
				resetNumber = 0;
				lehtalnessSpreading();
			}
		}
		
		writer.close();
		return (int)turnCounter;
	}
	
	
	//Runs the simulation and finding the exits using ACO instead of dijkstra's.
	public int runSimulationWithACO(List<Node> graph, List<Node> exits, String fileName, ArrayList<Integer> lethalStartNode) throws IOException
	{
		List<Human> humans = new ArrayList<Human>(startHumansACO);
		
		
		AntColonyOptimizationController aco = new AntColonyOptimizationController(howManyAnts, graph, edges, pheremonsFromEdge, lookingForShortest, goingForMostSafePath);
		int resetNumber = 0;
		
		resetLethalNodes();
		
		for(Integer it : lethalStartNode)
		{
			graph.get(it).setChanceOfDeath(0.5);
		}
		
		
		
		addLethalNodesToList();
		
		Random randomGenerator = new Random();
		
		double numberOfSurvivers = 0;
		double deadCounter = 0;
		double turnCounter = 0;
		double totalPassanger = humans.size();
		double totalLivingPassangers = 0;
		
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("ACO\\ACO "+fileName+".txt", true)));
		
		writer.println(turnCounter+",			"+totalPassanger+",					"+totalPassanger+",				"+deadCounter+",			"+numberOfSurvivers);
		// Calculate the path off the ship for each passenger
		while(!humans.isEmpty())
		{
			
			turnCounter++;
			resetNumber++;
			
			if(turnCounter%5000 == 0 && turnCounter != 0)
			{
				System.out.println("Stand still?");
			}
			
			for(Iterator<Human> it = humans.iterator(); it.hasNext(); ){
				Human h = it.next();
				
				if(h.isDead)
				{
					it.remove();
					deadCounter++;
					continue;
				}
				
				testIfHumanPanics(h, randomGenerator);
				
				// If the passenger is currently in a node that counts as an exit the passenger has escaped
				if(h.isEscaped()){
					//System.out.println("Human " + h.getHumanID() + " escaped(ACO) "+turnCounter);
					numberOfSurvivers++;
					h.getNode().currentHumansInNode.remove(h);
					it.remove();
				}
				else if(h.isPanicState())
				{
					movePanicedHuman(h);
					
					if(h.getNode().getChanceOfDeath() > randomGenerator.nextDouble()){
						//System.out.println("Shit son, human: " + h.getHumanID() + " just burned to death");
						h.getNode().currentHumansInNode.remove(h);
						deadCounter++;
						it.remove();
					}
				}
				else
				{
					//finds the way.
					aco.changeCurrentNode(h.getNode(), h.getHumanID());
					aco.findWay();
					
					int prevNode = h.getNode().getID();
					
					//Do one step
					//h.setNode(aco.bestPath.get(1));
					moveHumans2(h, aco.bestPath, humans);
					aco.bestPath = null;
					/*String s = "ACO path: ";
					
					for(Node n : aco.bestPath)
					{
						s += Integer.toString(n.getID())+"-";
					}
					System.out.println(s);*/
					
					//Prints what node the passenger was in and what node it is in now
					//System.out.println("Human " + h.getHumanID() + " is trying to escape from node "+prevNode+" to node "+h.getNode().getID());
					
					// Kills humans off, dead humans should add to the room capacity
					if(h.getNode().getChanceOfDeath() > randomGenerator.nextDouble()){
						//System.out.println("Shit son, human: " + h.getHumanID() + " just burned to death");
						h.isDead = true;
						h.getNode().currentHumansInNode.remove(h);
						deadCounter++;
						it.remove();
						
					}
				}
				
			}
			resetPheremones(edges, false);
			/*
			 * Save the amount of surivers at this point
			 * The amount that have yet to reach the exit
			 * the ones that have reached the exit
			 * and the amount of passangers that have diead
			 * the time step of all this.
			 */
			totalLivingPassangers = totalPassanger - deadCounter;
			writer.println(turnCounter+",			"+totalPassanger+",					"+totalPassanger+",				"+deadCounter+",			"+numberOfSurvivers);

			resetHumanMovementAllowence(humans);
			if(resetNumber >= flameSpreadTimer)
			{
				
				resetNumber = 0;
				lehtalnessSpreading();
			}
			
		}
		writer.close();
		return (int)turnCounter;
	}
	
	private boolean testIfFinished(List<Human> humans){
		for(Human h : humans){
			if(h.isEscaped()){

			}
			else{
				return false;
			}
		}
		return true;
	}
	
	private void randomSpreadFire(List<Node> graph, double chanceOfSpread, double chanceOfDeath){
		Random spread = new Random();
		for(Node n : graph){
			if(n.getChanceOfDeath() != 0.0 && chanceOfSpread > spread.nextDouble()){
				for(Edge e : n.getPaths()){
					e.getNode().setChanceOfDeath(chanceOfDeath);
					System.out.println("The fire just spread from node " + n.getID() + " to node " + e.getNode().getID());
				}
			}
		}
	}
	
	private void lehtalnessSpreading()
	{
		List<Node> tempList = new ArrayList<Node>();
		for(Node n : listOfLethalNodes)
		{
			if(n.getChanceOfDeath() >= 0.4)
			{
				for(Edge e : n.getPaths())
				{
					if(!listOfLethalNodes.contains(e.getNode()) && !e.getNode().isExit())
					{
						e.getNode().setChanceOfDeath(e.getNode().getChanceOfDeath()+0.09);
						tempList.add(e.getNode());
					}
					
				}
			}
		}
		
		for(Node n : listOfLethalNodes)
		{
			if(n.getChanceOfDeath() >= 1)
			{
				n.setChanceOfDeath(1);
				
				n.lethalNessTimeCounter++;
				if(n.lethalNessTimeCounter > 2 && !n.haveSartetFireUpstairs && !n.nodesUpstairs.isEmpty())
				{
					for(Node n2 : n.nodesUpstairs)
					{
						if(n2.getChanceOfDeath() == 0 && !n2.isExit())
						{
							n2.setChanceOfDeath(n2.getChanceOfDeath()+0.3);
							tempList.add(n2);
						}
						
					}
					n.haveSartetFireUpstairs = true;
				}
				else if(n.haveSartetFireUpstairs)
				{
					for(Node n2 : n.nodesUpstairs)
					{
						if(n2.getChanceOfDeath() <= 1)
						n2.setChanceOfDeath(n2.getChanceOfDeath()+0.3);
					}
				}
				
				
				
				
				
				for(Edge e : n.getPaths())
				{
					if(!listOfLethalNodes.contains(e.getNode()))
					{
						e.getNode().setChanceOfDeath(e.getNode().getChanceOfDeath()+0.2);
					}
					
				}
			}
			else if(n.getChanceOfDeath() >= 0.6)
			{
				n.setChanceOfDeath(n.getChanceOfDeath()+0.10);
				n.lethalNessTimeCounter++;
				if(n.lethalNessTimeCounter > 5 && !n.haveSartetFireUpstairs && !n.nodesUpstairs.isEmpty())
				{
					for(Node n2 : n.nodesUpstairs)
					{
						if(n2.getChanceOfDeath() == 0 && !n2.isExit())
						{
							n2.setChanceOfDeath(n2.getChanceOfDeath()+0.3);
							
							if(!listOfLethalNodes.contains(n2))
							tempList.add(n2);
						}
					}
					n.haveSartetFireUpstairs = true;
				}
				else if(n.haveSartetFireUpstairs)
				{
					
					for(Node n2 : n.nodesUpstairs)
					{
						if(n2.getChanceOfDeath() <= 1)
						n2.setChanceOfDeath(n2.getChanceOfDeath()+0.3);
					}
					
				}
				
				
				
				
				
				for(Edge e : n.getPaths())
				{
					if(!listOfLethalNodes.contains(e.getNode()))
					{
						e.getNode().setChanceOfDeath(e.getNode().getChanceOfDeath()+0.05);
					}
					
				}
			}
			else
			{
				n.setChanceOfDeath(n.getChanceOfDeath() + 0.01);
			}
			
		}
		
		listOfLethalNodes.addAll(tempList);
	}
	
	/*
	 * Flytt passasjerne, men f�rst sjekk nabo nodene om det er familie meldmer der som skal f�rst flyttes til samme node.
	 * S� flytte begge to sammen.
	 */
	
	private void moveHuman(Human currentHuman, List<Node> path, List<Human> humans)
	{
		if(currentHuman.getMovementAllowence() == 0)
		{
			return;
		}
		else if(currentHuman.getMovementAllowence() < 0)
		{
			System.out.println("Error: Human movement allowence less then 0.");
			return;
		}
		
		List<Node> neighbourNodes = new ArrayList<Node>();
		List<Human> fameleyMembers = new ArrayList<Human>();
		Node startNode = currentHuman.getNode();
		
		int movementLeft = 0;
		
		//Gathers all the neighbour nodes to the start node of the current passanger
		for(Edge e : startNode.getPaths())
		{
			neighbourNodes.add(e.getNode());
		}
		
		//Moves the passangers with famaly ties to the same node of the current passanger.
		for(Human h : humans)
		{
			//Famely member with 0 in movement allowence.
			if(h.getFamiliarTies().contains(currentHuman.getHumanID()) && h.getMovementAllowence() == 0 && h.getNode() == startNode)
			{
				return;
			}
			else if(h.getFamiliarTies().contains(currentHuman.getHumanID()) && h.getNode() == startNode && h != currentHuman)
			{
				fameleyMembers.add(h);
			}
		}
		
		int prevNode = currentHuman.getNode().getID();
		
		if(path.size() < 2)
		{
			//System.out.println("Path less then one");
			
			if(path.get(0).isExit())
			{
				currentHuman.moveHuman(path.get(0));
				return;
			}
		}
		else
		{
			
		}
		
		Edge e = findEdgeToNode(path.get(0), path.get(1));
		
		if(!flowOfEdges.containsKey(e))
		{
			flowOfEdges.put(e, 0);
		}
		
		flowOfEdges.put(e, flowOfEdges.get(e)+1);
		
		//flytt currentHuman kun om det er plass til det
		if(flowOfEdges.get(e) <= e.getFlow())
		{
			currentHuman.moveHuman(path.get(1));
		}
		
		
		
		
		//System.out.println("Human " + currentHuman.getHumanID() + " is trying to escape from node "+prevNode+" to node "+currentHuman.getNode().getID());
		
		for(Human h2 : fameleyMembers)
		{
			
			
			if(prevNode == h2.getNode().getID())
			{
				flowOfEdges.put(e, flowOfEdges.get(e)+1);
				if(flowOfEdges.get(e) <= e.getFlow())
				h2.moveHuman(path.get(1));
			}
				
			
			//System.out.println("Human " + h2.getHumanID() + " is trying to escape from node "+prevNode+" to node "+h2.getNode().getID());
			if(currentHuman.getMovementAllowence() == 0)
			{
				h2.setMovementAllowence(0);
			}
			else if(h2.getMovementAllowence() == 0)
			{
				currentHuman.setMovementAllowence(0);
			}
		}
	
		if(currentHuman.getMovementAllowence() > 0 && path.size() > 2)
		{
			path.remove(0);
			moveHuman(currentHuman, path, humans);
		}
		
	}
	
	public void moveHumans2(Human currentHuman, List<Node> path, List<Human> huamns)
	{
		//Tests if the human may move
		if(!testIfHumanMayMove(currentHuman))
		{
			return;
		}
		//checks the path size, if it is less then 2, then checks if the human is in an exit node
		if(path.size() < 2)
		{
			if(path.get(0).isExit())
			{
				currentHuman.moveHuman(path.get(0));
				return;
			}
			else
			{
				System.err.println("Error: Move human, path less then 1 and not in exit node");
			}
		}
		
		
		ArrayList<Node> neighbourNodes = getNeighbourNodes(currentHuman.getNode());
		ArrayList<Human> fameleyMembers = getFamelyMembersCloseTpCurrentHuman(currentHuman, huamns, neighbourNodes);
		Node startNode = currentHuman.getNode();
		
		int lowestMovementSpeed = currentHuman.getMovementAllowence();
		
		if(fameleyMembers.isEmpty())
		{
			currentHuman.moveHuman(path.get(1));
		}
		else
		{
			for(Human h : fameleyMembers)
			{
				if(h.getNode() != startNode)
				h.moveHuman(startNode);
				
				if(h.getMovementAllowence() < lowestMovementSpeed)
				{
					lowestMovementSpeed = h.getMovementAllowence();
				}
			}
			
			if(allFamelyAtSameNode(fameleyMembers, startNode))
			{
				currentHuman.setMovementAllowence(lowestMovementSpeed);
				setMovementSpeedToHumans(fameleyMembers, lowestMovementSpeed);
				
				for(Human h: fameleyMembers)
				{
					h.moveHuman(path.get(1));
				}
				
				currentHuman.moveHuman(path.get(1));
			}
			
			if(currentHuman.getMovementAllowence() > 0)
			{
				path.remove(0);
				moveHumans2(currentHuman, path, huamns);
			}
			
		}
		
	}
	
	private void setMovementSpeedToHumans(ArrayList<Human> list, int movementSpeed)
	{
		for(Human h : list)
		{
			h.setMovementAllowence(movementSpeed);
		}
	}
	
 	private boolean allFamelyAtSameNode(ArrayList<Human> list, Node n)
	{
		for(Human h : list)
		{
			if(h.getNode() != n)
			{
				return false;
			}
		}
		return true;
	}
	
 	private ArrayList<Human> getFamelyMembersCloseTpCurrentHuman(Human currentHuman, List<Human> huamns, List<Node> nodeList)
	{
		ArrayList<Human> tempList = new ArrayList<Human>();
		ArrayList<Human> tempList2 = new ArrayList<Human>();
		
		for(Human h : huamns)
		{
			if(currentHuman.getFamiliarTies().contains(h.getHumanID()))
			{
				tempList.add(h);
			}
		}
		
		nodeList.add(currentHuman.getNode());
		
		for(Human h : tempList)
		{
			if(nodeList.contains(h.getNode()))
			{
				tempList2.add(h);
			}
		}
		
		return tempList2;
	}
	
	private ArrayList<Node> getNeighbourNodes(Node n)
	{
		ArrayList<Node> tempList = new ArrayList<Node>();
		for(Edge e: n.getPaths())
		{
			tempList.add(e.getNode());
		}
		return tempList;
	}
	
	private boolean testIfHumanMayMove(Human currentHuman)
	{
		if(currentHuman.getMovementAllowence() > 0)
		{
			return true;
		}
		
		return false;
	}
	
	public void resetHumanMovementAllowence(List<Human> humans)
	{
		for(Human h : humans)
		{
			h.setMovementAllowence(humansMovementAllaowence);
		}
		for(Edge e: edges)
		{
			e.resetCurrentFlow();
		}
	}
	
	private List<Human> cloneList(List<Human> list)
	{
		List<Human> clone = new ArrayList<Human>();
		for(Human h : list)
		{
			Human h2 = new Human(h.getFamiliarTies(), false, h.getNode(), h.getHumanID(), h.isEscaped(), h.getMovementAllowence());
			h2.setKnownPathToExit(h.getKnownPathList());
			clone.add(h2);
		}
		return clone;
	}
	
	public void replaceHumans(List<Node> exits)
	{
		humanHandler.placeHumans(startHumansACO, graph, exits);
		humanHandler.placeHumans(startHumansDjixstra, graph, exits);
		
		for(int i = 0; i<startHumansACO.size(); i++)
		{
			startHumansACO.get(i).resetValues();
			startHumansDjixstra.get(i).resetValues();
		}
	}
	
	private void resetLethalNodes()
	{
		for(Node n : listOfLethalNodes)
		{
			n.setChanceOfDeath(0);
			n.haveSartetFireUpstairs = false;
			n.lethalNessTimeCounter = 0;
		}
		
		listOfLethalNodes.removeAll(listOfLethalNodes);
	}
	
	private void addLethalNodesToList()
	{
		for(Node n : graph)
		{
			if(n.getChanceOfDeath() > 0)
			{
				listOfLethalNodes.add(n);
			}
		}
	}
	
	public void resetPheremones(List<Edge> list, boolean forAllHumans)
	{
		for(Edge e : list)
		{
			//e.setPheremones(0);
			if(forAllHumans)
			e.resetPheremonesForEachHuman(pheremonsFromEdge);
		}
	}

	private Edge findEdgeToNode(Node from, Node to)
	{
		
		
		for(int i = 0; i<from.getPaths().size(); i++)
		{
			if(from.getPaths().get(i).getNode() == to)
			{
				return from.getPaths().get(i);
			}
		}
		System.err.println("Error: did not find correct edge from a node. ResultsHandler moveHuman."+from.getID()+"-"+to.getID());
		//findEdgeToNode(from, to);
		return null;
	}
	
	private void movePanicedHuman(Human h)
	{
		if(h.getMovementAllowence() == 0)
		{
			return;
		}
		else if(h.getMovementAllowence() < 0)
		{
			System.out.println("Error: Human movement allowence less then 0.");
			return;
		}
		
		
		/*if(h.getKnownPathList() == h.getNode())
		h.removeNodeFromKnownPathToExit(h.getNode());
		
		if(h.getKnownPathList().isEmpty())
		{
			System.out.println("N� skjer det en feil."+h.getHumanID());
		}
		
		if(h.getKnownPathList().contains(h.getNode()))
		{
			int a = h.getKnownPathList().indexOf(h.getNode());
			h.removePreviusNodesInList(a-1);
			
			if(h.getKnownPathList().isEmpty())
			{
				System.out.println("N� skjer det en feil."+h.getHumanID());
			}
		
		}*/
		
		if(h.getNode() == h.getKnownPathList().get(0) && !h.getNode().isExit())
		{
			h.moveHuman(h.getKnownPathList().get(1));
		}
		else
		{
			h.moveHuman(h.getKnownPathList().get(0));
		}
		
		
		
	}

	private boolean testIfHumanPanics(Human h, Random randomGenerator)
	{
		if(!h.isPanicState())
		{
			//Test to see if human gains panic
			float panicChance = initialPanicChance;
			for(Human human : h.getNode().currentHumansInNode)
			{
				if(human.isPanicState())
				{
					panicChance+=0.001;
				}
				else
				{
					panicChance-=0.005;
				}
				
				if(panicChance < 0)
				{
					panicChance = initialPanicChance;
				}
			}
			
			if(panicChance > randomGenerator.nextFloat())
			{
				h.setPanicState(true);
				//System.out.println("Human panics"+h.getHumanID());
			}
			
		}
		else
		{
			//Test to see if the human loses its panic
			double panicChance = initialPanicChance+0.1;
			for(Human human : h.getNode().currentHumansInNode)
			{
				if(human.isPanicState())
				{
					panicChance-=0.001;
				}
				else
				{
					panicChance+=0.005;
				}
				
				if(panicChance > 0.2 || panicChance < 0)
				{
					panicChance = 0.2;
				}
			}
			
			if(panicChance > randomGenerator.nextFloat())
			{
				h.setPanicState(false);
				//System.out.println("Human comes out of panic"+h.getHumanID());
			}
		}
		
		return h.isPanicState();
	}
	
	private List<Node> findBestPathDjixstra(Map<Double, List<Node>> listOfLists, boolean seaftyFirst)
	{
		if(seaftyFirst)
		{
			
		}
		
		return null;
	}


}
