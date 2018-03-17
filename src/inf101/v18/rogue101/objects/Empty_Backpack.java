package inf101.v18.rogue101.objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import inf101.v18.rogue101.game.IGame;

public class Empty_Backpack implements IContainer<IItem>{

	private final int SIZE_CAP = 0;
	Map<IItem, Integer> items = new HashMap<IItem, Integer>();

	@Override
	public Set<IItem> itemSet(){
		return items.keySet();
	}
	
	@Override
	public int getCurrentHealth() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public int getDefence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "no";
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean remove(IItem item) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public IItem get(IItem item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(IItem item) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int size_cap(){
		return SIZE_CAP;
	}
	
	@Override
	public int getItemAmount(IItem item) {
		// TODO Auto-generated method stub
		return 0;
	}

}
