package move.movedex;

import move.Move;
import move.modifiers.SpecialAttack;
import move.status_effect.ApplyParalyze;
import types.Electric;

public class M_085_ThunderBolt extends Move implements Electric, SpecialAttack, ApplyParalyze {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_085_ThunderBolt() {
		super(85, "Thunderbolt", 15, 90, 1);
		// TODO Auto-generated constructor stub
	}

	public M_085_ThunderBolt copy()
	{
		return new M_085_ThunderBolt();
	}
	
	@Override
	public double getParalyzeChance() {
		// TODO Auto-generated method stub
		return .1;
	}

}
