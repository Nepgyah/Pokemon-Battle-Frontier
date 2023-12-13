package move.movedex;

import move.Move;
import move.modifiers.PhysicalAttack;
import move.status_effect.ApplyPoison;
import types.Poison;

public class M_040_PoisonSting extends Move implements PhysicalAttack, Poison, ApplyPoison{

    private static final long serialVersionUID = 1L;

    public M_040_PoisonSting() {
        super(40, "Poison Sting", 35, 15, 1);
    }
    
    public M_040_PoisonSting copy()
    {
        return new M_040_PoisonSting();
    }

    @Override
    public double getPoisonChance() {
        return .2;
    }
}
