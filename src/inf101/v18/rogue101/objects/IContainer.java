package inf101.v18.rogue101.objects;

import java.util.List;
import java.util.Set;

public interface IContainer<T extends IItem> extends IItem{
	
	Set<IItem> itemSet();
	
	/**
	 * @param item 
	 * @return boolean to indicate success or not in removing item from Container
	 *         {@link #containsItem()}
	 */
	boolean remove(IItem item);

	/**
	 * @param item
	 * @return The Item from Container if it exist
	 *         {@link #containsItem()}
	 */
	IItem get(IItem item);
	
	/**
	 * @param item
	 * @return boolean to indicate if item was added to container
	 *         {@link #freeSpace()}
	 */
	boolean add(IItem item);
	
	default boolean contains(IItem item) {
		return false;
	}
	
	/**
	 * @return boolean value if there is any free space in container   
	 * {@link #getFreeSpace()}     
	 */
	default int freeSpace() {
		return 0;
	}
	/**
	 * @return store size Capacity of container
	 *            
	 */
	default int getCap() {
		return 0;
	}
	
	/**
	 * @return free space in container   
	 */
	default int getFreeSpace() {
		return 0;
	}
	/**
	 * Get all items (both IActors and other IItems) at the given location
	 * <p>
	 * The returned list either can't be modified, or modifying it won't affect the
	 * map.
	 * 
	 * @param loc
	 * @return A list of items
	 */
	List<IItem> getAll();
	
	default int size() {
		return 0;
	}

	default int getItemAmount(IItem item) {
		return 0;
	}
	
	
	

}
