package move.movedex;

import move.Move;
import move.modifiers.HealsHP;
import move.modifiers.StatusMove;
import move.modifiers.TargetSelf;
import types.Normal;

public class M_105_Recover extends Move implements StatusMove, TargetSelf, HealsHP, Normal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_105_Recover() {
		super(105, "Recover", 5, 0, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getHealRatio() {
		// TODO Auto-generated method stub
		return .5;
	}

	@Override
	public Move copy() {
		// TODO Auto-generated method stub
		return new M_105_Recover();
	}

}
