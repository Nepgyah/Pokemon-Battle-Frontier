package move.movedex;

import move.Move;
import move.modifiers.Lifesteal;
import move.modifiers.SpecialAttack;
import types.Grass;

public class M_071_Absorb extends Move implements Grass, SpecialAttack, Lifesteal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_071_Absorb() {
		super(71, "Absorb", 25, 20, 1);
	}
 
	public M_071_Absorb copy()
	{
		return new M_071_Absorb();
	}

	@Override
	public double getLifestealRatio() {
		// TODO Auto-generated method stub
		return .5;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return " had its energy drained!";
	}
}
