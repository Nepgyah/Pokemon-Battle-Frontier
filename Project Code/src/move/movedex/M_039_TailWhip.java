package move.movedex;

import move.Move;
import move.modifiers.ApplyStatChange;
import move.modifiers.StatusMove;
import move.statistic_effects_enemy.TargetDefenseMinusOne;
import types.Normal;

public class M_039_TailWhip extends Move implements StatusMove, Normal, ApplyStatChange, TargetDefenseMinusOne {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public M_039_TailWhip() {
		super(39, "Tail Whip", 30, 0, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getApplyChance() {
		// TODO Auto-generated method stub
		return 1;
	}

	public M_039_TailWhip copy()
	{
		return new M_039_TailWhip();
		
	}
	
}
