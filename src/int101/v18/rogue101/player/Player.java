package int101.v18.rogue101.player;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.grid.GridDirection;
import inf101.v18.rogue101.game.IGame;
import inf101.v18.rogue101.objects.IItem;
import inf101.v18.rogue101.objects.IPlayer;
import javafx.scene.input.KeyCode;

public class Player implements IPlayer {
	private int hp = getMaxHealth();

	@Override
	public boolean draw(ITurtle painter, double w, double h) {
		return false;
	}

	@Override
	public int getAttack() {
		return 1000;
	}

	@Override
	public int getCurrentHealth() {
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
	public int getMaxHealth() {
		return 50;
	}

	@Override
	public String getName() {
		return "player";
	}

	@Override
	public int getSize() {
		return 4;
	}

	@Override
	public String getSymbol() {
		return hp > 0 ? "@" : "¤";
	}

	@Override
	public int handleDamage(IGame game, IItem source, int amount) {
		hp -= amount;
		return amount;
	}

	@Override
	public void keyPressed(IGame game, KeyCode key) {
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
        showStatus(game);
    }
	private void tryToMove(IGame game, GridDirection dir) {
		if(game.canGo(dir)) {
			game.move(dir);
		}
		else
			hp--;
			game.displayMessage("Ouch!");
		
	}
	
	private void showStatus(IGame game) {
			game.displayStatus("Player hit points: " + hp);		
	}


}
