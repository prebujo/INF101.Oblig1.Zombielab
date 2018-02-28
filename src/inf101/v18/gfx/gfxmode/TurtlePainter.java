package inf101.v18.gfx.gfxmode;

import java.util.ArrayList;
import java.util.List;

import inf101.v18.gfx.IPaintLayer;
import inf101.v18.gfx.Screen;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TurtlePainter implements IPaintLayer, ITurtle {

	static class TurtleState {
		protected Point pos;
		protected Direction dir;
		protected Direction inDir;
		protected double penSize = 1.0;
		protected Paint ink = Color.BLACK;

		public TurtleState() {
		}

		public TurtleState(TurtleState s) {
			pos = s.pos;
			dir = s.dir;
			inDir = s.inDir;
			penSize = s.penSize;
			ink = s.ink;
		}
	}

	private final Screen screen;
	private final GraphicsContext context;
	private final List<TurtleState> stateStack = new ArrayList<>();

	private TurtleState state = new TurtleState();
	private final Canvas canvas;
	private boolean path = false;

	public TurtlePainter(Screen screen, Canvas canvas) {
		this.screen = screen;
		this.canvas = canvas;
		this.context = canvas.getGraphicsContext2D();
		stateStack.add(new TurtleState());
		state.dir = new Direction(1.0, 0.0);
		state.pos = new Point(screen.getWidth() / 2, screen.getHeight() / 2);
	}

	@Override
	public void clear() {
		context.clearRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void debugTurtle() {
		System.err.println("[" + state.pos + " " + state.dir + "]");
	}

	@Override
	public IShape shape() {
		ShapePainter s = new ShapePainter(context);
		return s.at(getPos()).rotation(getAngle()).strokePaint(state.ink);
	}

	@Override
	public ITurtle line(Point to) {
		// context.save();
		context.setStroke(state.ink);
		context.setLineWidth(state.penSize);
		context.strokeLine(state.pos.getX(), state.pos.getY(), to.getX(), to.getY());
		// context.restore();
		return this;
	}

	@Override
	public double getAngle() {
		return state.dir.toDegrees();
	}

	@Override
	public Direction getDirection() {
		return state.dir;
	}

	public double getHeight() {
		return screen.getHeight();
	}

	public Screen getScreen() {
		return screen;
	}

	@Override
	public Point getPos() {
		return state.pos;
	}

	public double getWidth() {
		return screen.getWidth();
	}

	public ITurtle curveTo(Point to, double startControl, double endAngle, double endControl) {
		Point c1 = state.pos.move(state.dir, startControl);
		Point c2 = to.move(Direction.fromDegrees(endAngle + 180), endControl);
		if (!path) {
			// context.save();
			context.setStroke(state.ink);
			context.setLineWidth(state.penSize);
			context.beginPath();
			context.moveTo(state.pos.getX(), state.pos.getY());
		}
		context.bezierCurveTo(c1.getX(), c1.getY(), c2.getX(), c2.getY(), to.getX(), to.getY());
		state.inDir = state.dir;
		state.pos = to;
		state.dir = Direction.fromDegrees(endAngle);

		if (!path) {
			context.stroke();
			// context.restore();
		}
		return this;
	}

	@Override
	public ITurtle jump(double dist) {
		state.inDir = state.dir;
		state.pos = state.pos.move(state.dir, dist);
		return this;
	}

	@Override
	public ITurtle jumpTo(double x, double y) {
		state.inDir = state.dir;
		state.pos = new Point(x, y);
		return this;
	}

	@Override
	public ITurtle jumpTo(Point to) {
		state.inDir = state.dir;
		state.pos = to;
		return this;
	}

	@Override
	public ITurtle draw(double dist) {
		Point to = state.pos.move(state.dir, dist);
		return drawTo(to);
	}

	@Override
	public ITurtle drawTo(double x, double y) {
		Point to = new Point(x, y);
		return drawTo(to);
	}

	@Override
	public ITurtle drawTo(Point to) {
		if (path) {
			context.lineTo(to.getX(), to.getY());
		} else {
			line(to);
		}
		state.inDir = state.dir;
		state.pos = to;
		return this;
	}

	@Override
	public IPainter restore() {
		if (stateStack.size() > 0) {
			state = stateStack.remove(stateStack.size() - 1);
		}
		return this;
	}

	@Override
	public IPainter save() {
		stateStack.add(new TurtleState(state));
		return this;
	}

	@Override
	public IPainter setInk(Paint ink) {
		state.ink = ink;
		return this;
	}

	@Override
	public ITurtle setPenSize(double pixels) {
		if (pixels < 0)
			throw new IllegalArgumentException("Negative: " + pixels);
		state.penSize = pixels;
		return this;
	}

	@Override
	public ITurtle turn(double degrees) {
		state.dir = state.dir.turn(degrees);
		return this;
	}

	@Override
	public ITurtle turnAround() {
		return turn(180);
	}

	@Override
	public ITurtle turnLeft() {
		return turn(90);
	}

	@Override
	public ITurtle turnLeft(double degrees) {
		if (degrees < 0)
			throw new IllegalArgumentException("Negative: " + degrees + " (use turn())");
		state.dir = state.dir.turn(degrees);
		return this;
	}

	@Override
	public ITurtle turnRight() {
		return turn(-90);
	}

	@Override
	public ITurtle turnRight(double degrees) {
		if (degrees < 0)
			throw new IllegalArgumentException("Negative: " + degrees + " (use turn())");
		state.dir = state.dir.turn(-degrees);
		return this;
	}

	@Override
	public ITurtle turnTo(double degrees) {
		state.dir = state.dir.turnTo(degrees);
		return this;
	}

	@Override
	public ITurtle turnTowards(double degrees, double percent) {
		state.dir = state.dir.turnTowards(new Direction(degrees), percent);
		return this;
	}

	@Override
	public void layerToFront() {
		screen.moveToFront(this);
	}

	@Override
	public void layerToBack() {
		screen.moveToBack(this);
	}

	@Override
	public ITurtle turtle() {
		TurtlePainter painter = new TurtlePainter(screen, canvas);
		painter.stateStack.set(0, new TurtleState(state));
		return painter;
	}

	@SuppressWarnings("unchecked")
	public <T> T as(Class<T> clazz) {
		if (clazz == GraphicsContext.class)
			return (T) context;
		else
			return null;
	}

}