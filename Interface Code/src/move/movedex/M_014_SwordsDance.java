package move.movedex;

import move.Move;
import move.modifiers.ApplyStatChange;
import move.modifiers.StatusMove;
import move.statistic_effects_user.UserAttackPlusTwo;
import types.Normal;

public class M_014_SwordsDance extends Move implements StatusMove, Normal, ApplyStatChange, UserAttackPlusTwo{

    public M_014_SwordsDance() {
        super(14, "Swords Dance", 20, 0, 1);
    }
    
    @Override
    public double getApplyChance() {
        return 1;
    }
    
    @Override
    public Move copy() {
        return new M_014_SwordsDance();
    }
}
