package inf101.v18.rogue101.objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import inf101.v18.rogue101.game.IGame;

public class Backpack implements IContainer<IItem>{
	
	private final int SIZE_CAP = 5;
	private int size = 0;
	Map<IItem, Integer> items = new HashMap<IItem, Integer>();
	
	@Override
	public Set<IItem> itemSet(){
		return items.keySet();
	}
	
	@Override
	public int getCurrentHealth() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int getDefence() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "backpack";
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return "🎒 ";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean remove(IItem item) { //fjerner et item
		// TODO Auto-generated method stub
		if(items.containsKey(item)) {
			if(items.get(item)>1)
				items.put(item, items.get(item));
			else
				items.remove(item);
			size--;
			return true;
			}
		else
			return false;	
	}

	@Override
	public IItem get(IItem item) {
		if(contains(item))
			return item;
		else
			return null;
	}
	
	@Override
	public int getItemAmount(IItem item) {
		if (contains(item))
			return items.get(item);
		else
			return 0;
	}

	@Override
	public boolean add(IItem item) {
		// Legger til et item hvis det er plass
		int new_size = size+1;
		if(new_size > SIZE_CAP)
			return false;
		else {
			size++;
			//sjekker om item finnes fra før denne burde forbedres da to objekter av samme type her blir
			//vurdert som forskjellig av containsKey i java. kunne overridet denne og implementert en egen hashmap
			//men valgte å ikke bruke tid på dette nå.
			if(items.containsKey(item))
				items.put(item, items.get(item) +1);
			else
				items.put(item, 0);
			return true;
		}
		
	}
	
	@Override
	public int size() {
		return size;
	}
	
	public int size_cap(){
		return SIZE_CAP;
	}
	
	@Override
	public int freeSpace() {
		return SIZE_CAP-size;
	}
	
	@Override
	public boolean contains(IItem item) { //returnerer true hvis jeg har et item med samme navn
		for(IItem it : items.keySet())		//denne kan forbedres til å sjekke om den har nøyaktig
			if(it.getName() == item.getName()) //det item som blir etterspurt hvis ulike objekter har
				return true;					//ulike egenskaper men det er ikke viktig så langt for spillet
		return false;
	}

}
