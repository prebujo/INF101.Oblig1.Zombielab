package inf101.v18.rogue101.examples;

import java.util.Collections;
import java.util.List;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.grid.GridDirection;
import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.game.IGame;
import inf101.v18.rogue101.objects.Flesh;
import inf101.v18.rogue101.objects.IActor;
import inf101.v18.rogue101.objects.IItem;
import inf101.v18.rogue101.objects.INonPlayer;

public class Zombie implements INonPlayer {

	private int hp = getMaxHealth();

	@Override
	public void doTurn(IGame game) {
		for (IItem item : game.getLocalItems()) {
			if (item instanceof Flesh) { //hvis zombie finner flesh skal den spise
				System.out.println("zombie found flesh");
				int eaten = item.handleDamage(game, this, 20);
				System.out.println("zombie ate flesh worth" + eaten);
				game.displayMessage("You hear a faint crunching.. someone or something is eating something viciously close by..");
				return;
			}
		}
		
		//sjekker så alle 4 retninger om det er noen IActor der
		List<GridDirection> possibleMoves = GridDirection.FOUR_DIRECTIONS; //endret denne fra game.getPossibleMoves();

		for(GridDirection dir : possibleMoves) { //gjennomgår for hver direction i possibleMoves
			ILocation loc = game.getLocation(dir); //henter Location i hver retning
			for(IItem neighb_item : game.getMap().getAll(loc)) { // henter items i hver location
				if(neighb_item instanceof IActor) { //hvis det finnes en actor skal jeg angripe
					game.attack(dir, neighb_item);
					return;
				}
				else if(neighb_item instanceof Flesh) { //hvis det finnes flesh beveger zombies seg mot den.
					game.move(dir);
					return;
				}
			}
		}
		// hvis det ikke finnes IActors eller flesh skal zombier bare gå til en tilfeldig mulig retning
		possibleMoves = game.getPossibleMoves();
		if(possibleMoves.size() == 0) //hvis zombien ikke har noen mulige moves gjør den ingenting.
			return;
		Collections.shuffle(possibleMoves); //shuffler mulige moves
		game.move(possibleMoves.get(0));    //og zombien beveger seg i en tilfeldig retning
	}

	@Override
	public boolean draw(ITurtle painter, double w, double h) {
		return false;
	}

	@Override
	public int getAttack() {  
		return 40;
	}

	@Override
	public int getCurrentHealth() {
		return hp;
	}

	@Override
	public int getDamage() {
		return 40;
	}

	@Override
	public int getDefence() { 
		return 30;
	}

	@Override
	public int getMaxHealth() {
		return 50;
	}

	@Override
	public String getName() {
		return "zombie";
	}

	@Override
	public int getSize() {
		return 3;
	}

	@Override
	public String getSymbol() {
		return hp > 0 ? "\u001b[32m"+"👰"+"\u001b[30m" : "¤";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		hp -= amount;
		return amount;
	}

}
