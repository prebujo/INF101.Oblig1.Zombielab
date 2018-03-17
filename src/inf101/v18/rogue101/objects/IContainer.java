package inf101.v18.rogue101.objects;

import java.util.Set;

public interface IContainer<T extends IItem> extends IItem{
	/**
	 *
	 * @return Set<IItem> to iterate through items in Container
	 *         {@link #containsItem()}
	 */	
	Set<T> itemSet();
	
	/**
	 * @param item 
	 * @return boolean to indicate success or not in removing item from Container
	 *         {@link #containsItem()}
	 */
	boolean remove(T item);

	/**
	 * @param item
	 * @return The Item from Container if it exist
	 *         {@link #containsItem()}
	 */
	T get(T item);
	
	/**
	 * @param item
	 * @return boolean to indicate if item was added to container
	 *         {@link #freeSpace()}
	 */
	boolean add(T item);
	
	default boolean contains(T item) {
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
	
	default int size() {
		return 0;
	}

	default int getItemAmount(T item) {
		return 0;
	}
	
	
	

}
