package inf101.v18.rogue101.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import inf101.v18.grid.GridDirection;
import inf101.v18.rogue101.examples.Rabbit;
import inf101.v18.rogue101.game.Game;
import inf101.v18.rogue101.game.IllegalMoveException;
import inf101.v18.rogue101.map.IMapView;
import inf101.v18.rogue101.objects.IActor;
import inf101.v18.rogue101.objects.IPlayer;
import javafx.scene.input.KeyCode;

class GameTest {
	public static String TEST_MAP = "4 3\n" //
			+ "####\n" //
			+ "#@R#\n" //
			+ "####\n" //
	;

	@Test
	void testAttackPlayer() {
		// new game with our test map
		Game game = new Game(TEST_MAP);
		IMapView map = game.getMap();
		// get item from a position (perhaps add a helper to GameMap to make this easier)
		Rabbit rabbit = (Rabbit) map.getAll(map.getLocation(2, 1)).get(0);
		// set player as current actor and get the player object
		IPlayer player = (IPlayer) game.setCurrent(1, 1);

		// Alternative: start with empty map, and add objects to it manually

		// Game game = new Game("4 3\n####\n#  #\n####\n");
		// Rabbit rabbit = new Rabbit();
		// IPlayer player = new Player();
		// map.add(map.getLocation(2,1), rabbit);
		// map.add(map.getLocation(1,1), player);

		assertNotNull(rabbit);
		assertNotNull(player);

		try {
			player.keyPressed(game, KeyCode.RIGHT);
		} catch (IllegalMoveException e) {
			fail("Move right should not throw IllegalMoveException");
		}
		// Rabbit rabbit = new Rabbit();
		// IPlayer player = new Player();
		// map.add(map.getLocation(2,1), rabbit);
		// map.add(map.getLocation(1,1), player);
	}
	public static String TEST_MAP_2 = "4 3\n" //
			+ "####\n" //
			+ "#RR#\n" //
			+ "####\n" //
	;

	@Test
	void testAttackActor() {
		// new game with our test map
		Game game = new Game(TEST_MAP_2);
		IMapView map = game.getMap();
		// get item from a position (perhaps add a helper to GameMap to make this easier)
		IActor defender  = (IActor) map.getAll(map.getLocation(2, 1)).get(0);
		// set actor at (1,1) as current player, so we can attack with it
		IActor attacker = (IActor) game.setCurrent(1, 1);

		// Alternative: start with empty map, and add objects to it manually

		// Game game = new Game("4 3\n####\n#  #\n####\n");
		// Rabbit rabbit = new Rabbit();
		// IPlayer player = new Player();
		// map.add(map.getLocation(2,1), rabbit);
		// map.add(map.getLocation(1,1), player);

		assertNotNull(defender);
		assertNotNull(attacker);

		try {
			// "attacker" is now the current player; it's at position (1,1)
			// so if it attacks to the EAST, it should attack the other rabbit at (2,1)
			game.attack(GridDirection.EAST, defender);
		} catch (IllegalMoveException e) {
			fail("Move right should not throw IllegalMoveException");
		}
		// Rabbit rabbit = new Rabbit();
		// IPlayer player = new Player();
		// map.add(map.getLocation(2,1), rabbit);
		// map.add(map.getLocation(1,1), player);
	}

}
