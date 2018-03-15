//DELOPPG B2
package inf101.v18.rogue101.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.grid.GridDirection;
import inf101.v18.rogue101.game.IGame;
import inf101.v18.rogue101.objects.Backpack;
import inf101.v18.rogue101.objects.Door;
import inf101.v18.rogue101.objects.Empty_Backpack;
import inf101.v18.rogue101.objects.IContainer;
import inf101.v18.rogue101.objects.IItem;
import inf101.v18.rogue101.objects.IPlayer;
import inf101.v18.rogue101.objects.IWeapon;
import inf101.v18.rogue101.objects.Key;
import javafx.scene.input.KeyCode;

public class PlayerDELC implements IPlayer {
	
	private int hp = getMaxHealth(); //lager en feltvariabel for hp slik som rabbit
	private final int dam = 10; //setter base damage som feltvariabel
	private final int def = 10; //setter base defence som feltvariabel
	private final int maxHP = 100; //setter base maxHP som feltvariabel
	private final int atk = 10; //setter base attack som feltvariabel
	private int vis = 0; // setter base visibility til 0
	private int inv_cap = 5; //starter med inventory capacity 4 (en ting i hver lomme 2x pluss en ting i hver hånd og på ryggen
	private IWeapon wieldedWeap = null; //feltvariabel for weapon spilleren bruker
	private IItem wieldedArmor = null; //feltvariabel for armor spilleren har på seg
	private IContainer<IItem> backpack = new Empty_Backpack(); //feltvariabel for evtl ryggsekk spilleren har på seg
	private IContainer<IItem> inv = new Backpack(); //feltvariabel for Items spilleren har på seg går som en Backpack
	private ArrayList<IWeapon> weapons = new ArrayList<IWeapon>(); //list of weapons carried by player
	
	
	@Override
	public boolean draw(ITurtle painter, double w, double h) {
		return false;
	}

	@Override
	public int getAttack() {  //setter attack slik at player stort sett vinner over Rabbit (logisk!:)
		return atk + wieldedWeapAttack();
	}

	@Override
	public int getCurrentHealth() { //returnerer spillerens hp
		return hp;
	}

	@Override
	public int getDamage() {
		return dam + wieldedWeapDam();
	}

	@Override
	public int getDefence() {
		return def;
	}

	@Override
	public int getMaxHealth() { //satt spillerens helse lik 50
		return 100;
	}

	@Override
	public String getName() { //satt spillerens navn lik player
		return "player";
	}

	@Override
	public int getSize() {
		return 5;
	}

	@Override
	public String getSymbol() {
		return hp > 0 ? "🙂" : "😫"; //satt spillerens symbol til @ og * hvis man dør
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		hp -= amount;
		return amount;
	}
	

	
	@Override //DELOPPG B2 c) //fullførte keyPressed klassen slik at den håndtere enhver KeyCode
	public void keyPressed(IGame game, KeyCode key) {
		showStatus(game);
        if (key == KeyCode.LEFT) {
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
        	dropInventoryItem(game);        	
        }
        else if(key == KeyCode.W) { //kaller change weap metoden
        	changeWeapon(game);
        }
        else if(key == KeyCode.N) { //kaller drop backpack metoden
        	dropBackpack(game);
        }
        else if(key == KeyCode.Q) { //kaller useFAK (use first aid kit) metoden
        	useFAK(game);
        }
        	
        
        //DELOPPG B2 e)
        //man kan også legge til på slutten her en måte å håndtere ikke godkjente keys men jeg så ikke det som nødvendig enda
        showStatus(game);  //viser status for hver gang spilleren beveger seg.
        showInventory(game);
        showBackpack(game);
    }
	






	//DELOPPG B2 d)
	private void tryToMove(IGame game, GridDirection dir) {
		//bruker canGo for å sjekke om spilleren kan gå i retningen, altså lovlig celle og ikke opptatt
		if(game.canGo(dir)) {		
			game.move(dir);
		}
		//DELOPPG. B6 c) 
		//sjekker så om det er lovlig å bevege seg i retning fra der vi er uavhengig om det er noen der
		//(tidligere skrev jeg ut Outch! og mistet hp når spilleren ikke kunne gå i denne retningen)
		else if(game.getLocation().canGo(dir)) {//altså hvis det er lovlig å gå denne veien
			//Så sjekker jeg først om jeg kan åpne en dør i den retningen
			boolean attack = true;
			//sjekker om det er en dør i retningen jeg skal gå...
			if(game.hasDoor(dir)) {
				IItem key = new Key(); //dummy for å sjekke navn
				//hvis det er det må jeg sjekke om jeg har nøkkel
				if(inv.contains(key)) { //laget en metode som sjekker om name er lik navnet på objektet som blir sendt inn
				//hvis jeg har et item med navn key skal jeg finne det.
					for(IItem it : inv.itemSet()) {				
						if (it.getName() == "key") {  //må først sjekke om jeg har en key i inventory 
							attack = !game.openDoor(dir);// da skal jeg åpne døren
							inv.remove(it);  //og fjerne key fra inventory
							return;
						}
					}
				}
				else if (backpack.contains(key)) { //sjekker også om jeg har en key i backpack
					for(IItem it : backpack.itemSet()) {				
						if (it.getName() == "key") {
							attack = !game.openDoor(dir);
							backpack.remove(it);
							return;
						}
					}
				}
			}
			
			else game.attack(dir, game.getMap().getAll(game.getLocation(dir)).get(0));	
		}
		else {
			game.displayMessage("A much harder impenetrable wall.. Your efforts are in vain..");
			hp--;
		}
	}
	
	//DELOPPG B2 e)
	public void showStatus(IGame game) {		//showStatus viser spilleres hp sammen med beskrivende tekst.
		//DELOPPG B4 d) passer på at hvis jeg ikke holder et item skriver jeg ut en tom streng
		String weapName = "";
		int armorHP = 0;
		int carryCap = carryCap();
		if(wieldedWeap != null)	//hvis jeg holder et våpen
			weapName = wieldedWeap.getName();	//oppdaterer jeg Strengen
		if(wieldedArmor != null)
			armorHP = wieldedArmor.getCurrentHealth();
		if(backpack != null) 
			carryCap += backpack.getFreeSpace();
		
		//skriver ut status med HP (hit points) og Item
		game.displayStatus("HP: " + hp + " Weapon: " + weapName + " ArmorHP: " + armorHP + " Capacity: " +carryCap);
		
	}
	
	private void showInventory(IGame game) {		//showStatus viser spilleres hp sammen med beskrivende tekst.
		//DELOPPG B4 d) passer på at hvis jeg ikke holder et item skriver jeg ut en tom streng
		String s = "";
		for(IItem it: inv.itemSet()) {
			s += it.getName();
			s += "x";
			s += (inv.getItemAmount(it)+1);
			s += " ";			
		}
			
		game.displayInventory("Inv: " +s);
	}
	
	private void showBackpack(IGame game) {
		//DELOPPG B4 d) passer på at hvis jeg ikke holder et item skriver jeg ut en tom streng
		String s = "";
		for(IItem it: backpack.itemSet()) {
			s += it.getName();
			s += "x";
			s += (backpack.getItemAmount(it)+1);
			s += " ";			
		}
			
		game.displayBackpack("Pack: " +s);
	}
	//DELIOPPG. C 

	
	public void pickUp(IGame game) {  //metode for å plukke opp første item som ligger på en location
		
		List<IItem> list = game.getLocalItems();  //henter ut liste over items
		if(list.size() == 0) {		//hvis det ikker er noen items på location skal jeg
			game.displayMessage("There is nothing here..");  //skrive ut en melding
			return;											//og avslutte metoden
		}
		
		IItem item = game.pickUp(list.get(0));
		if (!inv.add(item))
			if(!backpack.add(item))
				System.out.println("Inventory full..");
			else
				System.out.println("added" + item.getName() + " to backpack..");
		else
			System.out.println("added " + item.getName() + " to inventory..");
		
		if(item instanceof IWeapon && !weapons.contains(item))
			weapons.add((IWeapon) item);
		if(item.getName() == "torch")
			vis += 1;
		if(item.getName() == "backpack")
			backpack = (IContainer<IItem>) item;			
	}
	
	public void dropInventoryItem(IGame game) {	//metode for å slippe et item
		if(inv.size() <= 0 && backpack.size() <= 0) {  //hvis jeg ikke holder noe blir det skrevet 
			game.displayMessage("You are not holding anything.."); //ut en melding
			return;											//og metoden avsluttes
		}
		if(inv.size() > 0) {
			for(IItem it : inv.itemSet()) { 
				if(it instanceof IItem) { //hvis jeg holder på et item
					if(game.drop(it)) {	//og hvis jeg kan slippe det ned her
						if(it.getName() == "torch")
							vis--;
						inv.remove(it); //og fjerner da dette Item fra inventory
						return;
					}
					else {
						game.displayMessage("Cant drop item here.."); //informerer brukeren at det ikke var mulig
						return;
					}
				}
			}
		}
		else {
			for(IItem it : backpack.itemSet()) { 
				if(it instanceof IItem) { //hvis jeg holder på et item
					if(game.drop(it)) {	//og hvis jeg kan slippe det ned her
						if(it.getName() == "torch")
							vis--;
						inv.remove(it); //og fjerner da dette Item fra inventory
						return;
					}
					else {
						game.displayMessage("Cant drop item here.."); //informerer brukeren at det ikke var mulig
						return;
					}				
				}
			}
		}				
	}
	
	public void dropBackpack(IGame game) {
		if(backpack.getName() == "no") {
			game.displayMessage("Not wearing a backpack");
			return;
		}
		else {
			game.drop(backpack);
			inv.remove(backpack);
			backpack = new Empty_Backpack();
		}			
	}
	

	@Override
	public int getVisibility() {
		// TODO Auto-generated method stub
		return vis;
	}
	//hvis jeg har et wielded Weapon retunerer jeg attack fra det.
	public int wieldedWeapAttack() {
		if(wieldedWeap != null)
			return wieldedWeap.getAttack();
		else
			return 0;
	}
	
	//hvis jeg har et wielded Weapon retunerer jeg damage fra det.
	public int wieldedWeapDam() {
		if(wieldedWeap != null)
			return wieldedWeap.getDamage();
		else
			return 0;
	}
	
	
	private void changeWeapon(IGame game) {  //metode for å endre mellom weapons
	if(weapons.size()==0) {		//hvis jeg ikke har noen weapons
		game.displayMessage("You dont have a Weapon..");
		return;
	}
	//hvis ikke lagrer jeg det våpenet jeg har midlertidig
	IWeapon tempWeap = wieldedWeap;
	//fjerner det første weapon i listen
	wieldedWeap = weapons.remove(0);
	//legger til det midlertidige våpenet i våpensamlingen
	weapons.add(tempWeap);
	}
	private void dropBackpackItem(IGame game) {
		// TODO Auto-generated method stub
		
	}
	
	private int carryCap(){
			return (inv_cap - inv.size() + backpack.freeSpace());

	}

	private void useFAK(IGame game) {
		for(IItem it : inv.itemSet())
			if(it.getName() == "FAK") {
				inv.remove(it);
				hp = Math.min(hp+100, getMaxHealth());
				return;
			}
		game.displayMessage("You have no First aid Kit..");		
	}

}
