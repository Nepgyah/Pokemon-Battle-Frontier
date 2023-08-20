package move.movedex;

import move.Move;
import move.modifiers.*;
import move.status_effect.*;
import types.*;

public class M_000_Test extends Move implements StatusMove, Poison, ApplyLeech{

    private static final long serialVersionUID = 1L;

    public M_000_Test() {
        super(0, "Test", 30, 0, 1);
    }

    @Override
    public M_000_Test copy()
    {
        return new M_000_Test();
    }

}