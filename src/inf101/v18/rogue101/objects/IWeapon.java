package inf101.v18.rogue101.objects;

public interface IWeapon extends IItem {
	
	/**
	 * @return This Weapon's attack score (used if Actor is wielding a Weapon
	 *         to increase Actors Attack score
	 */
	int getAttack();
	
	/**
	 * @return The damage this Weapon deals on a successful attack (used together
	 *         with wieldedWeap i PlayerDELC
	 *         
	 */
	int getDamage();

}
