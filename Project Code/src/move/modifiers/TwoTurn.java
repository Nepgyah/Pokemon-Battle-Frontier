package move.modifiers;

public interface TwoTurn {

	/* Returns string description of the 1st half of the move
	 * (Ex: Diglett burrowed underground)
	 */
	String getTurnDescription();
	
	// Returns if the opponent can still target the user
	boolean getTargetability();
}
