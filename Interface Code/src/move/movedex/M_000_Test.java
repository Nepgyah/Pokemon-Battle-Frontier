package move.movedex;

import move.Move;
import move.modifiers.*;
import move.status_effect.*;
import types.*;

public class M_000_Test extends Move implements PhysicalAttack, Normal, TwoTurn{

    private static final long serialVersionUID = 1L;

    public M_000_Test() {
        super(0, "Test", 30, 10, 1);
    }

    @Override
    public M_000_Test copy()
    {
        return new M_000_Test();
    }

    @Override
    public String getTurnDescription() {
        return " used their zhonyas hourglass!";
    }

    @Override
    public boolean getTargetable() {
        return false;
    }

}