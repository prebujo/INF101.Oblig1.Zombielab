package inf101.v18.rogue101.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.grid.GridDirection;
import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.game.IGame;
import inf101.v18.rogue101.objects.IItem;
import inf101.v18.rogue101.objects.INonPlayer;

public class Rabbit implements INonPlayer {
	private int food = 0;
	private int hp = getMaxHealth();

	@Override
	public void doTurn(IGame game) {
		if (food == 0)
			hp--;
		else
			food--;
		if (hp < 1)
			return;

		for (IItem item : game.getLocalItems()) {
			if (item instanceof Carrot) {
				System.out.println("found carrot!");
				int eaten = item.handleDamage(game, this, 5);
				if (eaten > 0) {
					System.out.println("ate carrot worth " + eaten + "!");
					food += eaten;
					game.displayMessage("You hear a faint crunching (" + getName() + " eats " + item.getArticle() + " "
							+ item.getName() + ")");
					return;
				}
			}
		}
		// TODO: prøv forskjellige varianter her
		List<GridDirection> possibleMoves = game.getPossibleMoves();
		
		if (!possibleMoves.isEmpty()) { //hvis det ikke er noen possible moves skal objektet ikke gjøre noe
			for(GridDirection dir : possibleMoves) { //gjennomgår for hver direction i possibleMoves
				ILocation loc = game.getLocation(dir); //henter Location i hver lovlige retning
				for(IItem neighb_item : game.getMap().getItems(loc)) // henter items i hver location
					if(neighb_item instanceof Carrot) { //hvis det finnes en carrot skal jeg bevege meg dit
						game.move(dir);
						return;
					}						
			}
			Collections.shuffle(possibleMoves); //Hvis jeg har gjennomgått alle possibleMoves uten å gjøre noe 
			game.move(possibleMoves.get(0));    //skal jeg gå i en tilfeldig retning.
		}
		/*if(game.canGo(GridDirection.NORTH))
			game.move(GridDirection.NORTH);
		*/
	}

	@Override
	public boolean draw(ITurtle painter, double w, double h) {
		return false;
	}

	@Override
	public int getAttack() {
		return 1000;
	}

	@Override
	public int getCurrentHealth() {
		return hp;
	}

	@Override
	public int getDamage() {
		return 1000;
	}

	@Override
	public int getDefence() {
		return 1000;
	}

	@Override
	public int getMaxHealth() {
		return 10;
	}

	@Override
	public String getName() {
		return "rabbit";
	}

	@Override
	public int getSize() {
		return 4;
	}

	@Override
	public String getSymbol() {
		return hp > 0 ? "R" : "¤";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		hp -= amount;
		return amount;
	}

}
