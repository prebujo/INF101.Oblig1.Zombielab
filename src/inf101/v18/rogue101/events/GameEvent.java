package inf101.v18.rogue101.events;

import inf101.v18.rogue101.objects.IItem;

public class GameEvent<T> implements IEvent<T> {
	public static final String ATTACK_FAILURE = "attackFailure";
	public static final String ATTACK_SUCCESS = "attackSuccess";
	public static final String DEFEND_FAILURE = "defendFailure";
	public static final String DEFEND_SUCCESS = "defendSuccess";
	public static final String EATEN = "eaten";
	private String name;
	private IItem source;
	private IItem target;
	private T value;

	/**
	 * Create a new game event
	 * @param name The name is used when checking which event this is / determine its “meaning”
	 * @param source The item that caused the event, or <code>null</code> if unknown/not relevant
	 * @param target The item that receives the event, or <code>null</code> if unknown/not relevant
	 * @param value Arbitrary extra data
	 */
	public GameEvent(String name, IItem source, IItem target, T value) {
		this.name = name;
		this.source = source;
		this.target = target;
		this.value = value;
	}

	@Override
	public IItem getSource() {
		return source;
	}

	@Override
	public IItem getTarget() {
		return target;
	}

	@Override
	public String getEventName() {
		return name;
	}

	@Override
	public T getData() {
		return value;
	}

	@Override
	public void setData(T value) {
		this.value = value;
	}

}
