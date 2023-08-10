package move.movedex;

import move.Move;
import move.modifiers.PhysicalAttack;
import move.status_effect.ApplyFrozen;
import types.Normal;

public class M_000_Test extends Move implements PhysicalAttack, Normal {

    private static final long serialVersionUID = 1L;

    public M_000_Test() {
        super(0, "Test", 30, 35, 0);
    }

    @Override
    public M_000_Test copy()
    {
        return new M_000_Test();
    }
	
}