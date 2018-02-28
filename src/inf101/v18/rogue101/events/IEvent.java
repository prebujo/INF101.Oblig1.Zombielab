package inf101.v18.rogue101.events;

import inf101.v18.rogue101.objects.IItem;

public interface IEvent<T> {
	/**
	 * @return Extra data stored in this event
	 */
	T getData();

	/**
	 * @return The name of this event
	 */
	String getEventName();

	/**
	 * The source is the item that “caused” the event
	 * <p>
	 * Could be null if the source is unknown or not relevant.
	 * 
	 * @return The source of this event
	 */
	IItem getSource();

	/**
	 * The target is the item that is affected by the event
	 * <p>
	 * Could be null if the target is unknown or not relevant.
	 * 
	 * @return The target of this event, or null
	 */
	IItem getTarget();

	/**
	 * @param value Extra data to store in this event
	 */
	void setData(T value);
}
