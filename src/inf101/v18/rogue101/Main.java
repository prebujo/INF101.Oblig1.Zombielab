package inf101.v18.rogue101;

import java.io.PrintWriter;
import java.io.StringWriter;

import inf101.v18.gfx.Screen;
import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.gfx.textmode.Printer;
import inf101.v18.gfx.textmode.TextMode;
import inf101.v18.rogue101.game.IGame;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	public static final int LINE_MAP_BOTTOM = 20;
	public static final int LINE_STATUS = 21;
	public static final int LINE_MSG1 = 22;
	public static final int LINE_MSG2 = 23;
	public static final int LINE_MSG3 = 24;
	public static final int LINE_DEBUG = 25;
	public static final int COLUMN_MAP_END = 40;
	public static final int COLUMN_RIGHTSIDE_START = 41;
	private Screen screen;
	private ITurtle painter;
	private Printer printer;
	private IGame game;
	private boolean grid = true;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		screen = Screen.startPaintScene(primaryStage, Screen.CONFIG_PIXELS_STEP_SCALED); // Screen.CONFIG_SCREEN_FULLSCREEN_NO_HINT);

		printer = screen.createPrinter();
		painter = screen.createPainter();
		printer.setTextMode(TextMode.MODE_80X25, true);
		if (grid)
			printer.drawCharCells();
		printer.setAutoScroll(false);
		screen.setKeyPressedHandler((KeyEvent event) -> {
			KeyCode code = event.getCode();
			if (event.isShortcutDown()) {
				if (code == KeyCode.Q) {
					System.exit(0);
				} else if (code == KeyCode.R) {
					printer.cycleMode(true);
					if (grid)
						printer.drawCharCells();
//					game.draw();
					return true;
				} else if (code == KeyCode.A) {
					screen.cycleAspect();
					if (grid)
						printer.drawCharCells();
					return true;
				} else if (code == KeyCode.G) {
					grid = !grid;
					if (grid)
						printer.drawCharCells();
					else
						screen.clearBackground();
					return true;
				} else if (code == KeyCode.F) {
					screen.setFullScreen(!screen.isFullScreen());
					return true;
				} else if (code == KeyCode.L) {
					printer.redrawTextPage();
					return true;
				}
			} else if (code == KeyCode.ENTER) {
				try {
					//game.doTurn();
//					game.draw();
				} catch (Exception e) {
					printer.printAt(1, 25, "Exception: " + e.getMessage(), Color.RED);
					e.printStackTrace();
				}
				return true;
			} else {
				try {
//					game.keyPressed(code);
//					game.draw();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						StringWriter sw = new StringWriter();
						PrintWriter writer = new PrintWriter(sw);
						e.printStackTrace(writer);
						writer.close();
						String trace = sw.toString().split("\n")[0];
						game.displayDebug("Exception: " + trace);
					} catch (Exception e2) {
						System.err.println("Also got this exception trying to display the previous one");
						e2.printStackTrace();
						game.displayDebug("Exception: " + e.getMessage());
					}
				}
				return true;
			}
			return false;
		});
		/*
		 * screen.setKeyTypedHandler((KeyEvent event) -> { if (event.getCharacter() !=
		 * KeyEvent.CHAR_UNDEFINED) { printer.print(event.getCharacter()); return true;
		 * } return false; });
		 */
		setup();

//		game = new Game(screen, painter, printer);
//		game.draw();
		primaryStage.show();

	}

	private void setup() {
	}

	public static String BUILTIN_MAP = "40 20\n" //
			+ "########################################\n" //
			+ "#...... ..C.R ......R.R......... ..R...#\n" //
			+ "#.R@R...... ..........RC..R...... ... .#\n" //
			+ "#.......... ..R......R.R..R........R...#\n" //
			+ "#R. R......... R..R.........R......R.RR#\n" //
			+ "#... ..R........R......R. R........R.RR#\n" //
			+ "###############################....R..R#\n" //
			+ "#. ...R..C. ..R.R..........C.RC....... #\n" //
			+ "#..C.....R..... ........RR R..R.....R..#\n" //
			+ "#...R..R.R..............R .R..R........#\n" //
			+ "#.R.....R........RRR.......R.. .C....R.#\n" //
			+ "#.C.. ..R.  .....R.RC..C....R...R..C. .#\n" //
			+ "#. R..............R R..R........C.....R#\n" //
			+ "#........###############################\n" //
			+ "# R.........R...C....R.....R...R.......#\n" //
			+ "# R......... R..R........R......R.RR..##\n" //
			+ "#. ..R........R.....R.  ....C...R.RR...#\n" //
			+ "#....RC..R........R......R.RC......R...#\n" //
			+ "#.C.... ..... ......... .R..R....R...R.#\n" //
			+ "########################################\n" //
	;
}
