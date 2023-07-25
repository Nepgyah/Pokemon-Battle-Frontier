package move.movedex;

import move.Move;
import move.modifiers.SpecialAttack;
import move.status_effect.ApplyParalyze;
import types.Electric;

public class M_087_Thunder extends Move implements SpecialAttack, Electric, ApplyParalyze{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_087_Thunder() {
		super(87, "Thunder", 10, 110, .7);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getParalyzeChance() {
		// TODO Auto-generated method stub
		return .1;
	}

	@Override
	public M_087_Thunder copy() {
		// TODO Auto-generated method stub
		return new M_087_Thunder();
	}

}
