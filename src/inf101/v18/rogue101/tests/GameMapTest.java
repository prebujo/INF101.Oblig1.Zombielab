package inf101.v18.rogue101.tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.examples.Apple;
import inf101.v18.rogue101.examples.Carrot;
import inf101.v18.rogue101.examples.Rabbit;
import inf101.v18.rogue101.map.GameMap;
import inf101.v18.rogue101.objects.Dust;
import inf101.v18.rogue101.objects.IItem;
import inf101.v18.rogue101.player.Player;

class GameMapTest {

	@Test  //DELOPPG B3 c)
	void testSortedAdd() {
		GameMap gameMap = new GameMap(20, 20);
		ILocation location = gameMap.getLocation(10, 10);
		// DELOPPG B3 c)
		// Legger til fem tilfeldige items i posisjonen		
		IItem item;
		for(int i = 0; i<5;i++) {	//genererer 5 tilfeldige items og plasserer de på den valgte location
			item = RandItem(); 		//kaller på RandItem metoden som ligger under.
			gameMap.add(location, item); //legger til item på location
		}
		
		//sjekker om listen fra lokasjonen er sortert
		List<IItem> list = gameMap.getAll(location);  //henter ut items fra location
		for(int i = 0; i<list.size()-1;i++) {	// for hvert item frem til nest siste
			assertEquals((list.get(i).compareTo(list.get(i+1))>=0),true); //skal jeg asserte om det stemmer at det er større enn det neste
		}
	}
	
	//Metode som genererer tilfeldige items. 
	IItem RandItem() {
		//oppretter random generator
		Random rand = new Random();
		//lager en tilfeldig double mellom 0 og 1
		double num = rand.nextDouble();
		//deler opp tilfellene i 4 like tilfeldige tilfeller mellom 0 og 1
		if(num < 0.25)
			return new Dust();
		else if (num < 0.5)
			return new Player();
		else if (num < 0.75)
			return new Carrot();
		else
			return new Apple();		
	}
	
	//DELOPPG B5 c)
	@Test
	void testNeighborhoodDist() { //metode for å teste at alle items i en liste har avstand mindre enn dist
		
		GameMap gameMap = new GameMap(20, 20);  //oppretter et kart
		ILocation location = gameMap.getLocation(10, 10); //setter location/center midt i kartet
		
		for (int i = 0; i<3; i++) {	//tester for distanse 1-3 kan utvides innenfor kartets begrensninger		
			int dist = i+1;		//setter dist
			List<ILocation> list = gameMap.getNeighbourhood(location, dist); //oppretter nabo liste
			for (ILocation neighb:list) {	//gjennomgår hvert element i nabolisten
				assertEquals(location.gridDistanceTo(neighb)<=dist, true); //og tester om avstanden er innenfor dist
			}
		}
	}
	
	@Test
	void testNeighborhoodSizeCenter() {  //tester nabolaget til en location som er midt i et stort kart
		GameMap gameMap = new GameMap(20, 20);
		ILocation location = gameMap.getLocation(10, 10); //velger posisjon midt i kartet
		List<ILocation> list = gameMap.getNeighbourhood(location, 1); //henter naboer rundt location
		assertEquals(list.size(), 8);		//naboer skal være 3*3 -1 = 8
		
		list = gameMap.getNeighbourhood(location, 2); //sjekker for dist = 2
		assertEquals(list.size(), 24); // 5*5 -1 = 24
		
		list = gameMap.getNeighbourhood(location, 3); //sjekker for dist = 3
		assertEquals(list.size(), 48); //7*7-1 =48
	}
	@Test
	void testNeighborhoodSizeCorner() { //tester nabolaget til et hjørne location
		GameMap gameMap = new GameMap(20, 20);
		ILocation location = gameMap.getLocation(19, 19); //setter location til hjørnet
		List<ILocation> list = gameMap.getNeighbourhood(location, 1); //henter naboer med dist 1
		assertEquals(list.size(), 3);	//antall naboer skal være 2*2 -1
		
		list = gameMap.getNeighbourhood(location, 2); //tester for dist 2
		assertEquals(list.size(), 8); //3*3 -1 = 8
		
		list = gameMap.getNeighbourhood(location, 3); //tester for dist 3
		assertEquals(list.size(), 15); //4*4-1 = 15
	}
	@Test
	void testNeighborhoodSizeEdge() { //tester nabolaget til en kant location
		GameMap gameMap = new GameMap(20, 20);
		ILocation location = gameMap.getLocation(19, 10);  //velger en kant location
		List<ILocation> list = gameMap.getNeighbourhood(location, 1); //henter ut nabolag rundt kant med dist 1
		assertEquals(list.size(), 5);	//Kantnabolag skal være lik 2*3 - 1 = 5
		
		list = gameMap.getNeighbourhood(location, 2); //utvider til å teste med dist 2
		assertEquals(list.size(), 14); // 3*5 -1 = 14
		
		list = gameMap.getNeighbourhood(location, 3); //utvider med å teste med dist 3
		assertEquals(list.size(), 27); // 4*7 -1 = 27
	}
	
	//DELOPPG B5 d)
	@Test
	void testNeighborhoodOrder() { //metode for å teste at alle locations i en nabo-liste avstand <= den neste i listen
		
		GameMap gameMap = new GameMap(20, 20);  //oppretter et kart
		ILocation location = gameMap.getLocation(10, 10); //setter location/center midt i kartet
		int dist = 3; //tester for distanse på 3. Denne kan endres for å utvide testen
		List<ILocation> list = gameMap.getNeighbourhood(location, dist); //oppretter nabo liste
		
		for (int i = 0; i<list.size()-1;i++) {	//sjekker alle elementene i en liste frem til nest siste		
			assertEquals(location.gridDistanceTo(list.get(i))<=location.gridDistanceTo(list.get(i+1)), true);
			//ethvert element i listen må ha gridDistance mindre eller lik den som kommer etter.
		}
	}
	
	//TESTER FRA FORELESNING:
	@Test
	void testAddContains1() {
		// test with rabbit first then carrot
		GameMap gameMap = new GameMap(20, 20);
		ILocation location = gameMap.getLocation(10, 10);
		addContainsProperty(gameMap, location, new Rabbit());
		addContainsProperty(gameMap, location, new Carrot());
	}

	@Test
	void testAddContains2() {
		// test with carrot first then rabbit
		GameMap gameMap = new GameMap(20, 20);
		ILocation location = gameMap.getLocation(10, 10);
		addContainsProperty(gameMap, location, new Carrot());
		addContainsProperty(gameMap, location, new Rabbit());
	}

	/**
	 * After adding an item, that location in the map should contain the item.
	 * 
	 * @param map
	 * @param loc
	 * @param item
	 */
	void addContainsProperty(GameMap map, ILocation loc, IItem item) {
		map.add(loc, item);
		assertTrue(map.getAll(loc).contains(item));
	}

	@Test
	void testAddAddsOne() {
		GameMap gameMap = new GameMap(20, 20);
		// add stuff at various locations, see that size of item list increases by one
		
		// (Dette kan gå galt både ved at tingen ikke ble lagt til, og ved at den
		// blir lagt til flere ganger, og oppførselen kan avhenge av hva som er på stedet fra
		// så vi bør prøve med mange varianter.)
		
		ILocation location = gameMap.getLocation(10, 10);
		addSizeProperty(gameMap, location, new Rabbit());

		location = gameMap.getLocation(8, 10);
		gameMap.add(location, new Carrot()/* erstatt med noe som er større enn rabbit */);
		addSizeProperty(gameMap, location, new Rabbit());

		location = gameMap.getLocation(9, 10);
		gameMap.add(location, new Carrot() /* mindre enn rabbit */);
		addSizeProperty(gameMap, location, new Rabbit());
	}

	/**
	 * After adding an item to the map, the list of items on that location should
	 * increase by one.
	 * 
	 * @param map
	 * @param loc
	 * @param item
	 */
	void addSizeProperty(GameMap map, ILocation loc, IItem item) {
		int size = map.getAll(loc).size();
		map.add(loc, item);
		assertEquals(size + 1, map.getAll(loc).size());
	}

	
	

}
