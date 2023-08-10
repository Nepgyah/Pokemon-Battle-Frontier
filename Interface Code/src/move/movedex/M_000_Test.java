package move.movedex;

import move.Move;
import move.modifiers.PhysicalAttack;
import move.status_effect.ApplyParalyze;
import move.status_effect.ApplySleep;
import types.Normal;

public class M_000_Test extends Move implements PhysicalAttack, Normal, ApplySleep {

    private static final long serialVersionUID = 1L;

    public M_000_Test() {
        super(0, "Test", 30, 35, 1);
    }

    @Override
    public M_000_Test copy()
    {
        return new M_000_Test();
    }

    @Override
    public double getSleepChance() {
        return 1;
    }
	
}