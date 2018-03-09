package int101.v18.rogue101.player;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.grid.GridDirection;
import inf101.v18.rogue101.game.IGame;
import inf101.v18.rogue101.objects.IItem;
import inf101.v18.rogue101.objects.IPlayer;
import javafx.scene.input.KeyCode;

public class Player implements IPlayer {
	private int hp = getMaxHealth(); //lager en feltvariabel for hp slik som rabbit

	@Override
	public boolean draw(ITurtle painter, double w, double h) {
		return false;
	}

	@Override
	public int getAttack() {  //setter attack slik som rabbit
		return 1000;
	}

	@Override
	public int getCurrentHealth() { //returnerer spillerens hp
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
	public int getMaxHealth() { //satt spillerens helse lik 50
		return 50;
	}

	@Override
	public String getName() { //satt spillerens navn lik player
		return "player";
	}

	@Override
	public int getSize() {
		return 4;
	}

	@Override
	public String getSymbol() {
		return hp > 0 ? "@" : "*"; //satt spillerens symbol til @ og * hvis man dør
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		hp -= amount;
		return amount;
	}

	@Override
	public void keyPressed(IGame game, KeyCode key) { //fullførte keyPressed klassen slik at den håndtere enhver KeyCode
        if (key == KeyCode.LEFT) {				//kan vurdere å endre til switch
            tryToMove(game, GridDirection.WEST);
        }
        else if(key == KeyCode.RIGHT) {
        	tryToMove(game,GridDirection.EAST);
        }
        else if(key == KeyCode.UP) {
        	tryToMove(game,GridDirection.NORTH);
        }
        else if(key == KeyCode.DOWN) {
        	tryToMove(game,GridDirection.SOUTH);
        }					//man kan også legge til på slutten her en måte å håndtere ikke godkjente keys men jeg så ikke det som nødvendig enda
        showStatus(game);  //viser status for hver gang spilleren beveger seg.
    }
	private void tryToMove(IGame game, GridDirection dir) {
		if(game.canGo(dir)) {		//bruker canGo for å sjekke om spilleren kan gå i retningen
			game.move(dir);
		}
		else		//hvis spilleren ikke kan gå der (feltet er en wall eller Rabbit)
			hp--;	//blir spilleren pittelitt skadet og beskjeden Ouch! blir skrevet ut
			game.displayMessage("Ouch!");
		
	}
	
	private void showStatus(IGame game) {		//showStatus viser spilleres hp sammen med beskrivende tekst.
			game.displayStatus("Player hit points: " + hp);		
	}


}
