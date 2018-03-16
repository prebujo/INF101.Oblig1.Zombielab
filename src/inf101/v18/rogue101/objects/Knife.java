package inf101.v18.rogue101.objects;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.rogue101.game.IGame;

public class Knife implements IWeapon {
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
		return 10;
	}

	@Override
	public String getName() {
		return "knife";
	}

	@Override
	public int getSize() {
		return 2;
	}

	@Override
	public String getSymbol() {
		return "🔪";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		return 0;
	}

	@Override
	public int getAttack() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 20;
	}

}
