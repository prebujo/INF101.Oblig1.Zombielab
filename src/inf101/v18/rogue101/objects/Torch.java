package inf101.v18.rogue101.objects;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.rogue101.game.IGame;

public class Torch implements IItem{
	private int hp = getMaxHealth();

	@Override
	public boolean draw(ITurtle painter, double w, double h) {
		return false;
	}

	@Override
	public int getCurrentHealth() {
		return hp;
	}

	@Override
	public int getDefence() {
		return 50;
	}

	@Override
	public int getMaxHealth() {
		return 300;
	}

	@Override
	public String getName() {
		return "torch";
	}

	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public String getSymbol() {
		return "\u001b[93m"+"🔅"+ "\u001b[30m";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		return 0;
	}

}
