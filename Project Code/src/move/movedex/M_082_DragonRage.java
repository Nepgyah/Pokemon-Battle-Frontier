package move.movedex;

import move.Move;
import move.modifiers.SetDamage;
import move.modifiers.SpecialAttack;
import types.Dragon;

public class M_082_DragonRage extends Move implements SpecialAttack, Dragon, SetDamage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_082_DragonRage() {
		super(82, "Dragon Rage", 10, 0, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	public Move copy() {
		// TODO Auto-generated method stub
		return new M_082_DragonRage();
	}

}
