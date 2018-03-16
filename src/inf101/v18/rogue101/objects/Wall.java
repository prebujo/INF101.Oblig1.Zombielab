package inf101.v18.rogue101.objects;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.rogue101.game.IGame;

public class Wall implements IItem {
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
		return 10;
	}

	@Override
	public int getMaxHealth() {
		return 1000;
	}

	@Override
	public String getName() {
		return "wall";
	}

	@Override
	public int getSize() {
		return 3;
	}

	@Override
	public String getSymbol() {
		return "\u001b[93m"+"🏿"+"\u001b[30m";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		hp -= amount;
		return amount;
	}
}
