package inf101.v18.gfx.gfxmode;

import inf101.v18.gfx.IPaintLayer;
import javafx.scene.paint.Paint;

public interface IPainter extends IPaintLayer {

	IPainter restore();

	IPainter save();

	IPainter setInk(Paint ink);

	IShape shape();

	ITurtle turtle();
}