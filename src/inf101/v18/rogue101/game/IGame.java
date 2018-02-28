package inf101.v18.rogue101.game;

import java.util.Collection;
import java.util.List;

import inf101.v18.grid.GridDirection;
import inf101.v18.grid.ILocation;
import inf101.v18.rogue101.map.IMapView;
import inf101.v18.rogue101.objects.IItem;

public interface IGame {
	IMapView getMap();

	ILocation move(GridDirection dir);

	ILocation attack(GridDirection dir, IItem target);

	ILocation rangedAttack(GridDirection dir, IItem target);

	Collection<IItem> getLocalItems();

	/**
	 * Get the current actor's location.
	 * <p>
	 * You should only call this from an IActor that is currently doing its move.
	 * 
	 * @return Location of the current actor
	 */
	ILocation getLocation();
	
	/**
	 * Get the neighbouring map location in direction <code>dir</code>
	 * <p>
	 * Same as <code>getLocation().go(dir)</code>
	 * 
	 * @param dir A direction
	 * @return A location, or <code>null</code> if the location would be outside the map
	 */
	ILocation getLocation(GridDirection dir);

	IItem pickUp(IItem item);

	boolean drop(IItem item);

	boolean canGo(GridDirection dir);

	/**
	 * Get a list of all locations that are visible from the current location.
	 * <p>
	 * The location list is sorted so that nearby locations come earlier in the
	 * list. E.g., if <code>l = getVisible()<code> and <code>i < j</code>then
	 * <code>getLocation().gridDistanceTo(l.get(i)) < getLocation().gridDistanceTo(l.get(j))</code>
	 * 
	 * @return A list of grid cells visible from the {@link #getLocation()}
	 */
	List<ILocation> getVisible();
	
	int getWidth();
	
	int getHeight();

	IItem createItem(String symbol);

	void addItem(IItem item);

	void addItem(String sym);

	void displayMessage(String s);

	void formatMessage(String s, Object... args);

	void displayStatus(String s);

	void formatStatus(String s, Object...args);

	void displayDebug(String s);

	void formatDebug(String s, Object... args);
}
