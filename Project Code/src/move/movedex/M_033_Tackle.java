package move.movedex;

import move.Move;
import move.modifiers.MultiStrike;
import move.modifiers.OneHitKO;
import move.modifiers.PhysicalAttack;
import types.Normal;

public class M_033_Tackle extends Move implements PhysicalAttack, Normal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_033_Tackle() {
		super(33, "Tackle", 30, 35, 1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public M_033_Tackle copy()
	{
		return new M_033_Tackle();
	}
	
}
