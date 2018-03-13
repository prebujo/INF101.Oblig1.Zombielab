//DELOPPG B2
package int101.v18.rogue101.player;

import java.util.List;

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
	

	
	@Override //DELOPPG B2 c)
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
        }
        //DELOPPG. B4 a)
        else if (key == KeyCode.P) { //hvis brukeren trykker P tasten skal jeg kjøre pickUp metoden
        	pickUp(game);        	
        }
        else if(key == KeyCode.D) { //hvis brukeren trykker D tasten skal jeg kjøre drop metoden
        	drop(game);        	
        }
        
        //DELOPPG B2 e)
        //man kan også legge til på slutten her en måte å håndtere ikke godkjente keys men jeg så ikke det som nødvendig enda
        showStatus(game);  //viser status for hver gang spilleren beveger seg.
    }
	
	//DELOPPG B2 d)
	private void tryToMove(IGame game, GridDirection dir) {
		if(game.canGo(dir)) {		//bruker canGo for å sjekke om spilleren kan gå i retningen
			game.move(dir);
		}
		else		//hvis spilleren ikke kan gå der (feltet er en wall eller Rabbit)
			hp--;	//blir spilleren pittelitt skadet og beskjeden Ouch! blir skrevet ut
			game.displayMessage("Ouch!");
		
	}
	
	//DELOPPG B2 e)
	private void showStatus(IGame game) {		//showStatus viser spilleres hp sammen med beskrivende tekst.
		//DELOPPG B4 d) passer på at hvis jeg ikke holder et item skriver jeg ut en tom streng
		String itemName = "";	//tom streng
		if(heldItem != null)	//hvis jeg holder et item
			itemName = heldItem.getName();	//oppdaterer jeg Strengen
		
		//skriver ut status med HP (hit points) og Item
		game.displayStatus("HP: " + hp + " Item: " + itemName);					
	}
	
	//DELIOPPG. B4 b)-c)
	private IItem heldItem = null; //feltvariabel for Item holdt av spiller
	
	public void pickUp(IGame game) {  //metode for å plukke opp første item som ligger på en location
		//La til metode som håndterer tilfellet at spilleren allerede holder noe.
		if(heldItem!=null) {
			game.displayMessage("Already holding " + heldItem.getName());
			return; //skal kun være mulig å holde et item dermed avsluttes metoden hvis det er tilfelle
		}		
		
		List<IItem> list = game.getLocalItems();  //henter ut liste over items
		if(list.size() == 0) {		//hvis det ikker er noen items på location skal jeg
			game.displayMessage("There is nothing here..");  //skrive ut en melding
			return;											//og avslutte metoden
		}		
		heldItem = game.pickUp(list.get(0)); //ellers plukker jeg opp første element i listen.
	}
	
	public void drop(IGame game) {	//metode for å slippe et item
		if(heldItem == null) {  //hvis jeg ikke holder noe blir det skrevet 
			game.displayMessage("You are not holding anything.."); //ut en melding
			return;											//og metoden avsluttes
			}
		if(game.drop(heldItem))	//ellers slipper jeg heldItem, hvis det går
			heldItem = null;		//og oppdaterer da feltvariabelen til å bli null
		else
			game.displayMessage("Cant drop item here.."); //informerer brukeren at det ikke var mulig
	}


}
