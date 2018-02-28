package inf101.v18.rogue101.objects;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.gfx.textmode.BlocksAndBoxes;
import inf101.v18.rogue101.events.IEvent;
import inf101.v18.rogue101.game.IGame;

public class Dust implements IItem {

	@Override
	public int getDefence() {
		return 0;
	}

	@Override
	public int getMaxHealth() {
		return 0;
	}

	@Override
	public int getCurrentHealth() {
		return 0;
	}

	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public String getSymbol() {
		return BlocksAndBoxes.BLOCK_HALF;
	}

	
	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		return 0;
	}


	@Override
	public boolean draw(ITurtle painter, double w, double h) {
		return false;
	}

	@Override
	public String getName() {
		return "thick layer of dust";
	}

}