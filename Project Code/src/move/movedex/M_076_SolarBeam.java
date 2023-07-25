package move.movedex;

import move.Move;
import move.modifiers.SpecialAttack;
import move.modifiers.TwoTurn;
import types.Grass;

public class M_076_SolarBeam extends Move implements SpecialAttack, Grass, TwoTurn {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_076_SolarBeam() {
		super(76, "Solar Beam", 10, 120, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTurnDescription() {
		// TODO Auto-generated method stub
		return " absorbed light!";
	}

	@Override
	public boolean getTargetability() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Move copy() {
		// TODO Auto-generated method stub
		return new M_076_SolarBeam();
	}

}
