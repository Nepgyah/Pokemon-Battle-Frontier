package move.movedex;

import move.Move;
import move.modifiers.Recharge;
import move.modifiers.SpecialAttack;
import types.Normal;

public class M_063_HyperBeam extends Move implements Normal, SpecialAttack, Recharge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_063_HyperBeam() {
		super(63, "Hyper Beam", 5, 150, .9);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Move copy() {
		// TODO Auto-generated method stub
		return new M_063_HyperBeam();
	}

}
