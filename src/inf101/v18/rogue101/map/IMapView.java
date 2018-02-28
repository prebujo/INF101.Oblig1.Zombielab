package inf101.v18.rogue101.map;

import java.util.List;

import inf101.v18.gfx.gfxmode.ITurtle;
import inf101.v18.gfx.textmode.Printer;
import inf101.v18.grid.GridDirection;
import inf101.v18.grid.IArea;
import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.game.IllegalMoveException;
import inf101.v18.rogue101.objects.IActor;
import inf101.v18.rogue101.objects.IItem;

public interface IMapView {
	boolean canGo(ILocation from, GridDirection dir);

	boolean canGo(ILocation to);

	ILocation go(ILocation from, GridDirection dir) throws IllegalMoveException;

	boolean hasActors(ILocation loc);

	List<IActor> getActors(ILocation loc);

	boolean hasItems(ILocation loc);

	List<IItem> getItems(ILocation loc);

	List<IItem> getAll(ILocation loc);

	boolean hasWall(ILocation loc);

	void remove(ILocation loc, IItem item);

	void add(ILocation loc, IItem item);

	boolean has(ILocation loc, IItem target);
	
	ILocation getLocation(int x, int y);
	ILocation getLocation(IItem item);

	IArea getArea();

	int getWidth();

	int getHeight();

}
