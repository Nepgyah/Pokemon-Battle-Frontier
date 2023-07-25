package move.movedex;

import move.Move;
import move.modifiers.PhysicalAttack;
import move.modifiers.TwoTurn;
import types.Ground;

public class M_091_Dig extends Move	implements PhysicalAttack, Ground, TwoTurn {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_091_Dig() {
		super(91, "Dig", 10, 80, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Move copy() {
		// TODO Auto-generated method stub
		return new M_091_Dig();
	}

	@Override
	public String getTurnDescription() {
		// TODO Auto-generated method stub
		return " burrowed its way underground!";
	}

	@Override
	public boolean getTargetability() {
		// TODO Auto-generated method stub
		return false;
	}

}
