package move.movedex;

import move.Move;
import move.modifiers.SpecialAttack;
import move.status_effect.ApplyParalyze;
import types.Electric;

public class M_085_ThunderBolt extends Move implements Electric, SpecialAttack, ApplyParalyze {

    private static final long serialVersionUID = 1L;

    public M_085_ThunderBolt() {
        super(85, "Thunderbolt", 15, 90, 1, "A strong electrical attack that may also leave the foe paralyzed.");
    }

    public M_085_ThunderBolt copy()
    {
        return new M_085_ThunderBolt();
    }

    @Override
    public double getParalyzeChance() {
        return .1;
    }

}
