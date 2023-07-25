package move.movedex;

import move.Move;
import move.modifiers.PhysicalAttack;
import move.modifiers.TwoTurn;
import types.Flying;

public class M_019_Fly extends Move implements PhysicalAttack, Flying, TwoTurn{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_019_Fly() {
		super(19, "Fly", 15, 90, .95);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTurnDescription() {
		// TODO Auto-generated method stub
		return " flew up high!";
	}

	@Override
	public boolean getTargetability() {
		return false;
	}

	@Override
	public Move copy() {
		return new M_019_Fly();
	}

}
