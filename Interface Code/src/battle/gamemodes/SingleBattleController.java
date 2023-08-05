package battle.gamemodes;

import java.util.Collections;
import java.util.Scanner;
import move.Move;
import move.modifiers.*;
import pokemon.Pokemon;
import trainer.Trainer;
import battle.gamemodes.SingleBattleWindow;

import java.util.Scanner;

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
    
    SingleBattleWindow battleWindow;
    
    public SingleBattleController(Pokemon leftPokemon, Pokemon rightPokemon, boolean showConsole) {
        super();
        
        System.out.println("CONTROL CONSOLE: Initializing battle controller");
        
        this.leftPokemon = leftPokemon;
        this.rightPokemon = rightPokemon;
        this.showConsole = showConsole;
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
        }
    }
    
    public boolean getLeftTrainerTurn() {
        return leftTrainerTurn;
    }
}
