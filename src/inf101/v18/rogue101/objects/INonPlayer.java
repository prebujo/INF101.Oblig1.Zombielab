package inf101.v18.rogue101.objects;

import inf101.v18.rogue101.game.IGame;

public interface INonPlayer extends IActor {
	void doTurn(IGame game);
}
