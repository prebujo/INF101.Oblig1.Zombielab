package inf101.v18.rogue101.objects;

import inf101.v18.rogue101.game.IGame;
import javafx.scene.input.KeyCode;

public interface IPlayer extends IActor {
	void keyPressed(IGame game, KeyCode key);
}
