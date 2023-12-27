package move.movedex;

import move.Move;
import move.modifiers.PhysicalAttack;
import types.Normal;

public class M_001_Pound extends Move implements PhysicalAttack, Normal{

	private static final long serialVersionUID = 1L;
        
	public M_001_Pound() {
            super(1, "Pound", 35, 40, 1, "Pounds the foe with forelegs or tail.");
	}
	public M_001_Pound copy()
	{
            return new M_001_Pound();
	}
}
