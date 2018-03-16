package inf101.v18.rogue101.examples;


import java.util.Collections;
import java.util.List;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.grid.GridDirection;
import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.game.IGame;
import inf101.v18.rogue101.objects.IActor;
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

		//DELOPPG B6 endret på denne nå som jeg skal angripe. Sjekker først alle fire retninger om det er en IActor der
		List<GridDirection> possibleMoves = GridDirection.FOUR_DIRECTIONS; //endret denne fra game.getPossibleMoves();

		for(GridDirection dir : possibleMoves) { //gjennomgår for hver direction i possibleMoves
			ILocation loc = game.getLocation(dir); //henter Location i hver retning
			for(IItem neighb_item : game.getMap().getAll(loc)) { // henter items i hver location
				if(neighb_item instanceof IActor) { //hvis det finnes en actor skal jeg angripe
					game.attack(dir, neighb_item);
					return;
				}
				else if(neighb_item instanceof Carrot) { //hvis det finnes en carrot beveger kaninen seg mot den.
					game.move(dir);
					return;
				}
			}
		}
		// hvis det ikke finnes hverken Carrots eller IActors skal kaninen bare hoppe til en tilfeldig mulig retning
		
		possibleMoves = game.getPossibleMoves();
		if(possibleMoves.size() == 0) //hvis kaninen nå ikke har noen mulige moves gjør den ingenting.
			return;
		Collections.shuffle(possibleMoves); //shuffler mulige moves
		game.move(possibleMoves.get(0));    //og kaninen beveger seg i en tilfeldig retning
	}

	@Override
	public boolean draw(ITurtle painter, double w, double h) {
		return false;
	}

	@Override
	public int getAttack() {  //endret rabbit til å blir litt mer realistisk svak
		return 10;
	}

	@Override
	public int getCurrentHealth() {
		return hp;
	}

	@Override
	public int getDamage() {
		return 10;
	}

	@Override
	public int getDefence() { //endret til mer realistisk rabbit så spilleren overlever og Player Test funker
		return 10;
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
		return hp > 0 ? "\u001b[33m"+"R"+"\u001b[30m" : "¤";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		hp -= amount;
		return amount;
	}

}
