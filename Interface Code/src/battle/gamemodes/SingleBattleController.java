package battle.gamemodes;

import java.util.Collections;
import java.util.Scanner;

import move.Move;
import move.modifiers.*;
import pokemon.Pokemon;
import trainer.Trainer;

public class SingleBattleController {
    
    private Trainer leftTrainer, rightTrainer;
    private Pokemon leftPokemon, rightPokemon;
    private Move leftMove, rightMove;
    private String leftPrevName, rightPrevName;
    
    boolean leftPlayerForfeit, rightPlayerForfeit, leftSwapMade, rightSwapMade, leftWins, rightWins, showConsole;
    private Scanner keyboardInput;
    private int input;
    
    public SingleBattleController(Trainer leftTrainer, Trainer rightTrainer, Scanner scanner, boolean showConsole) {
        super();
        this.leftTrainer = leftTrainer;
        this.rightTrainer = rightTrainer;
        this.leftPokemon = leftTrainer.getParty().get(0);
        this.rightPokemon = rightTrainer.getParty().get(0);
        this.showConsole = showConsole;
        this.keyboardInput = scanner;
    }
    
    public void Battle() {
        if (showConsole) {
            System.out.println(leftTrainer.getName() + " sent out " + leftPokemon.getNickname()+ "!");
            System.out.println(rightTrainer.getName() + " sent out " + rightPokemon.getNickname() + "!");
        }
    }
}
