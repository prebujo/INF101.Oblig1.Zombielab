package inf101.v18.rogue101.tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.examples.Apple;
import inf101.v18.rogue101.examples.Carrot;
import inf101.v18.rogue101.map.GameMap;
import inf101.v18.rogue101.objects.Dust;
import inf101.v18.rogue101.objects.IItem;
import int101.v18.rogue101.player.Player;

class GameMapTest {

	@Test
	void testSortedAdd() {
		GameMap gameMap = new GameMap(20, 20);
		ILocation location = gameMap.getLocation(10, 10);
		// DELOPPG B3 c)
		// Legger til fem tilfeldige items i posisjonen		
		IItem item;
		for(int i = 0; i<5;i++) {
			item = RandItem(); 
			gameMap.add(location, item);
		}
		
		//sjekker om listen fra lokasjonen er sortert
		List<IItem> list = gameMap.getAll(location);
		for(int i = 0; i<list.size()-1;i++) {	// for hvert item frem til nest siste
			System.out.println(list.get(i));
			assertEquals((list.get(i).compareTo(list.get(i+1))>=0),true); //skal jeg asserte om det stemmer at det er større enn det forrige
		}
		System.out.println(list.get(list.size() -1));
	}
	
	//Metode som genererer tilfeldige items.  
	IItem RandItem() {
		//oppretter random generator
		Random rand = new Random();
		//lager en tilfeldig double mellom 0 og 1
		double num = rand.nextDouble();
		System.out.println(num);
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
	

}
