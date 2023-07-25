package move.movedex;

import move.Move;
import move.modifiers.HasRecoil;
import move.modifiers.PhysicalAttack;
import types.Normal;

public class M_165_Struggle extends Move implements PhysicalAttack, Normal, HasRecoil{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_165_Struggle() {
		super(165, "Struggle", 1, 50, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getRecoilRatio() {
		// TODO Auto-generated method stub
		return .5;
	}

	@Override
	public Move copy() {
		// TODO Auto-generated method stub
		return new M_165_Struggle();
	}

}
