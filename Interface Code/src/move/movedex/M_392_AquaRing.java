package move.movedex;

import move.Move;
import move.modifiers.ApplyStatChange;
import move.modifiers.HealOverTime;
import move.modifiers.StatusMove;
import move.modifiers.TargetSelf;
import move.statistic_effects_user.UserAttackPlusTwo;
import types.Normal;

public class M_392_AquaRing extends Move implements TargetSelf, StatusMove, Normal, HealOverTime {

    public M_392_AquaRing() {
        super(392, "Aqua Ring", 20, 0, 1, "	The user envelops itself in a veil made of water. It regains some HP on every turn.");
    }
    
    @Override
    public Move copy() {
        return new M_392_AquaRing();
    }

    @Override
    public String getTurnDescription() {
        return(" surrounded itself with a viel of water!");
    }
}
