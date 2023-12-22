/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package move.movedex;

import move.Move;
import move.modifiers.StatusMove;
import move.status_effect.ApplyParalyze;
import types.Electric;

public class M_086_ThunderWave extends Move implements StatusMove, Electric, ApplyParalyze {

    public M_086_ThunderWave() {
        super(86, "Thunder Wave", 20, 0, .9);
    }

    public Move copy() {
        return new M_086_ThunderWave();
    }

    public double getParalyzeChance() {
        return 1;
    }
    
}
