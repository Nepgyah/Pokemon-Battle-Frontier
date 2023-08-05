package battle.gamemodes;

import java.util.Collections;
import java.util.Scanner;
import move.Move;
import move.modifiers.*;
import pokemon.Pokemon;
import trainer.Trainer;
import battle.gamemodes.SingleBattleWindow;
import battle.BattleMechanics;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class SingleBattleController {
    
    private Trainer leftTrainer, rightTrainer;
    private Pokemon leftPokemon, rightPokemon;
    private Move leftMove, rightMove;
    private String leftPrevName, rightPrevName;
    
    boolean leftPlayerForfeit, rightPlayerForfeit, leftSwapMade, rightSwapMade, leftWins, rightWins, showConsole;
    private Scanner keyboardInput;
    private int input;
    
    boolean leftMoveMade = false, rightMoveMade = false;
    boolean leftTrainerTurn = true;
    
    JLabel leftHpLabel, rightHpLabel;
    JLabel textArea;
    
    public SingleBattleController(Pokemon leftPokemon, Pokemon rightPokemon, boolean showConsole, JLabel textArea, JLabel leftHp, JLabel rightHp) {
        super();
        
        System.out.println("CONTROL CONSOLE: Initializing battle controller");
        
        this.leftPokemon = leftPokemon;
        this.rightPokemon = rightPokemon;
        this.showConsole = showConsole;
        this.textArea = textArea;
        this.leftHpLabel = leftHp;
        this.rightHpLabel = rightHp;
        
        System.out.println("CONTROL CONSOLE: Initialization complete");
    }
    
    public void setMoveChoice(int pos){
        if (leftTrainerTurn) {
            leftMove = leftPokemon.getMoveset()[pos];
            if (showConsole) System.out.println("CONTROL CONSOLE: Left Pokemon move selected -> " + leftMove.getName());
            leftTrainerTurn = false;
        } else {
            rightMove = rightPokemon.getMoveset()[pos];
            if (showConsole) System.out.println("CONTROL CONSOLE: Right Pokemon move selected -> " + rightMove.getName());
            leftTrainerTurn = true;
            runTurn();
        }
    }
    
    public void runTurn() {
        if (showConsole) System.out.println("CONTROL CONSOLE: Executing current turn");
        // Right Going first
        if (leftPokemon.getBattle_speed() < rightPokemon.getBattle_speed()) {
            BattleMechanics.useMove(rightPokemon, leftPokemon, rightMove, leftMove);
            leftHpLabel.setText(Integer.toString(leftPokemon.getCurrent_hp()));
            
            BattleMechanics.useMove(leftPokemon, rightPokemon, leftMove, rightMove);
            rightHpLabel.setText(Integer.toString(rightPokemon.getCurrent_hp()));
        } else {
            // Left Going First
            BattleMechanics.useMove(leftPokemon, rightPokemon, leftMove, rightMove);
            rightHpLabel.setText(Integer.toString(rightPokemon.getCurrent_hp()));
           
            BattleMechanics.useMove(rightPokemon, leftPokemon, rightMove, leftMove);
            leftHpLabel.setText(Integer.toString(leftPokemon.getCurrent_hp()));
        }
        if (showConsole) {
            System.out.println("CONTROL CONSOLE: Turn results");
            System.out.println("Left Pokemon - " + leftPokemon.getName() + " " + leftPokemon.getCurrent_hp() + "/" + leftPokemon.getCurrent_max_hp());
            System.out.println("Right Pokemon - " + rightPokemon.getName() + " " + rightPokemon.getCurrent_hp() + "/" + rightPokemon.getCurrent_max_hp());
        }
       
    }
    

    public boolean getLeftTrainerTurn() {
        return leftTrainerTurn;
    }
    
    public Pokemon getLeftPokemon() {
        return leftPokemon;
    }
    
    public Pokemon getRightPokemon() {
        return rightPokemon;
    }
}
