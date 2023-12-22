/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package move.movedex;

import move.Move;
import move.status_effect.ApplyBurn;
import types.Fire;

public class M_262_WillOWisp extends Move implements Fire, ApplyBurn {

    public M_262_WillOWisp() {
        super(262, "Will-O-Wisp", 15, 0, .85);
    }

    public Move copy() {
        return new M_262_WillOWisp();
    }

    public double getBurnChance() {
        return 1;
    }
    
}
