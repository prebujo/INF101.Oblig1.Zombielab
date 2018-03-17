package inf101.v18.rogue101.tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import inf101.v18.grid.GridDirection;
import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.examples.Rabbit;
import inf101.v18.rogue101.game.Game;
import inf101.v18.rogue101.map.IMapView;
import inf101.v18.rogue101.objects.IPlayer;
import inf101.v18.rogue101.player.Player;
import javafx.scene.input.KeyCode;

class PlayerTest {
	public static String TEST_MAP = "40 5\n" //
			+ "########################################\n" //
			+ "#...... ..C.R ......R.R......... ..R...#\n" //
			+ "#.R@R...... ..........RC..R...... ... .#\n" //
			+ "#... ..R........R......R. R........R.RR#\n" //
			+ "########################################\n" //
	;
	public static String TEST_MAP_CLEAN = "40 5\n" //
			+ "########################################\n" //
			+ "#...... ..... .................. ......#\n" //
			+ "#..@....... ..................... ... .#\n" //
			+ "#... .................... .............#\n" //
			+ "########################################\n" //
	;

	@Test
	void testPlayer1() {
		// new game with our test map
		Game game = new Game(TEST_MAP);
		// pick (3,2) as the "current" position; this is where the player is on the
		// test map, so it'll set up the player and return it
		IPlayer player = (IPlayer) game.setCurrent(3, 2);

		
		// find players location
		ILocation loc = game.getLocation();
		// press "UP" key
		player.keyPressed(game, KeyCode.UP);
		// see that we moved north
		assertEquals(loc.go(GridDirection.NORTH), game.getLocation());
	}
	@Test
	void testMove1() {
		//tester om jeg kan bevege meg sørover og om posisjonen blir endret med et clean map
		Game game = new Game(TEST_MAP_CLEAN);
		//Bruker Player objekt
		Player player = (Player) game.setCurrent(3, 2);
		//Finner location til spilleren
		ILocation loc = game.getLocation();
		//trykker på ned tasten
		player.keyPressed(game, KeyCode.DOWN);
		//sjekker om spilleren har flyttet seg ned.
		assertEquals(loc.go(GridDirection.SOUTH), game.getLocation());
	}
	@Test
	void testMove2() {
		//new game with the test map only with walls
		Game game = new Game(TEST_MAP_CLEAN);
		//Bruker et Player Objekt
		Player player = (Player) game.setCurrent(3, 2);
		//Finner location til spilleren
		ILocation loc = game.getLocation();
		//trykker på vestre tasten
		player.keyPressed(game, KeyCode.LEFT);
		assertEquals(loc.go(GridDirection.WEST), game.getLocation());
	}
	@Test
	void testMove3() {
		//new game with the test map only with walls
		Game game = new Game(TEST_MAP_CLEAN);
		//Bruker Player objekt
		Player player = (Player) game.setCurrent(3, 2);
		//Finner location til spilleren
		ILocation loc = game.getLocation();
		//trykker på høyre tasten
		player.keyPressed(game, KeyCode.RIGHT);
		assertEquals(loc.go(GridDirection.EAST), game.getLocation());
	}
	@Test
	void testMove4() {
		//new game with the test map only with walls
		Game game = new Game(TEST_MAP_CLEAN);
		//Bruker Player objekt
		Player player = (Player) game.setCurrent(3, 2);
		//Finner location til spilleren
		ILocation loc = game.getLocation();
		//trykker på ned tasten
		player.keyPressed(game, KeyCode.UP);
		assertEquals(loc.go(GridDirection.NORTH), game.getLocation());
	}
	@Test
	void testMoveWall() {
		//new game with the test map only with walls
		Game game = new Game(TEST_MAP_CLEAN);
		//Bruker Player objekt
		Player player = (Player) game.setCurrent(3, 2);
		//Finner location til spilleren
		ILocation loc = game.getLocation();
		
		//flytter opp to ganger som kun skal være mulig en gang pga wall
		player.keyPressed(game, KeyCode.UP);
		player.keyPressed(game, KeyCode.UP);
		//sjekker om spilleren bare har flyttet seg en posisjon
		assertEquals(loc.go(GridDirection.NORTH), game.getLocation());
	}

	//TEST FRA FORELESNING:
	@Test
	void testAttack() {
		// new game with our test map
		Game game = new Game(TEST_MAP);
		// pick (3,2) as the "current" position; this is where the player is on the
		// test map, so it'll set up the player and return it
		IMapView map = game.getMap();
		//korrigerte rabbit så den faktisk finner en rabbit på kartet
		Rabbit rabbit = (Rabbit) map.getAll(map.getLocation(2, 2)).get(0); 
		//korrigerte player slik at den faktisk finner player
		IPlayer player = (IPlayer) game.setCurrent(3,2);

		//disse testene går nå fint siden jeg korrigerte det over
		assertNotNull(rabbit);
		assertNotNull(player);
		
		int health = rabbit.getCurrentHealth();
		player.keyPressed(game, KeyCode.LEFT); //endret retning slik at riktig kanin blir angrepet
		
		// Two ways to test that the player attacked:
		
		// We can add stuff to Game so it stores the last message, then check it;
		// or have Game keep track of the last action(s) performed (e.g., attack, move, etc) and
		// then ask about it
		String lastMessage = game.getLastMessage();
		assertTrue(lastMessage.contains("attack") || lastMessage.contains("hits"), "moving right should result in attack");

		// Or we can check that the poor rabbit lost some health. NOTE: this will only
		// happen if the attack was successful (by default the rabbit is extremely tough and
		// the player has no chance of actually damaging it!).
		//endret angrep og forsvar hos kaninen slik at denne testen også går..
		assertTrue(rabbit.getCurrentHealth() < health, "Expected " + rabbit.getCurrentHealth() + " < " + health);
	}


	
}
