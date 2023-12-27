/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package move.movedex;

import move.Move;
import move.modifiers.StatusMove;
import move.status_effect.ApplyBurn;
import types.Fire;

public class M_262_WillOWisp extends Move implements Fire, ApplyBurn, StatusMove {

    public M_262_WillOWisp() {
        super(261, "Will-O-Wisp", 15, 0, .85, "A sinister, bluish white flame is shot at the foe to inflict a burn.");
    }

    public Move copy() {
        return new M_262_WillOWisp();
    }

    public double getBurnChance() {
        return 1;
    }
    
}
