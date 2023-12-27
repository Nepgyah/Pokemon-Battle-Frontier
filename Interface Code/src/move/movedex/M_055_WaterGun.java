package move.movedex;

import move.Move;
import move.modifiers.SpecialAttack;
import types.Water;

public class M_055_WaterGun extends Move implements Water, SpecialAttack{

    private static final long serialVersionUID = 1L;

    public M_055_WaterGun() {
        super(55, "Water Gun", 25, 40, 1, "Squirts water to attack the foe.");
    }

    @Override
    public M_055_WaterGun copy() {
        return new M_055_WaterGun();
    }

}
