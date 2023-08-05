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
    
    SingleBattleWindow battleWindow;
    
    public SingleBattleController(Trainer leftTrainer, Trainer rightTrainer, boolean showConsole) {
        super();
        this.leftTrainer = leftTrainer;
        this.rightTrainer = rightTrainer;
        this.leftPokemon = leftTrainer.getParty().get(0);
        this.rightPokemon = rightTrainer.getParty().get(0);
        this.showConsole = showConsole;
        
        System.out.println("CONTROL CONSOLE: Initializing GUI window");
        this.battleWindow = new SingleBattleWindow(leftPokemon, rightPokemon);
    }
    
    public void initializeBattle() {
        System.out.println("CONTROL CONSOLE: Initializing battle -> " + leftTrainer.getName() + " vs " + rightTrainer.getName());
        System.out.println("CONTROL CONSOLE: Opening battle window");
        battleWindow.setVisible(true);
        battle();
    }
    
    public void battle() {
        System.out.println("CONTROL CONSOLE: Battle start");
    }
    
    private void turn() {
      
    }
}
