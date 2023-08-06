package battle.gamemodes;

import move.Move;
import pokemon.Pokemon;
import trainer.Trainer;
import battle.BattleMechanics;
import java.awt.Color;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JTextArea;


import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JProgressBar;


public class SingleBattleController{
   
    private Timer timer;
    private TimerTask task;
    
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
    JTextArea textArea;
    JProgressBar leftHpBar, rightHpBar;
    JButton fightButton, bagButton, pokemonButton;
            
    public SingleBattleController(Pokemon leftPokemon, Pokemon rightPokemon, 
            boolean showConsole, 
            JTextArea textArea, 
            JLabel leftHp, JLabel rightHp, 
            JProgressBar leftHpBar, JProgressBar rightHpBar,
            JButton fightButton, JButton bagButton, JButton pokemonButton) {
        super();
        
        this.timer = new Timer();
        System.out.println("CONTROL CONSOLE: Initializing battle controller");
        
        this.leftPokemon = leftPokemon;
        this.rightPokemon = rightPokemon;
        this.showConsole = showConsole;
        
        this.textArea = textArea;
        this.leftHpLabel = leftHp;
        this.rightHpLabel = rightHp;
        this.leftHpBar = leftHpBar;
        this.rightHpBar = rightHpBar;
        this.fightButton = fightButton;
        this.bagButton = bagButton;
        this.pokemonButton = pokemonButton;
        
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
        
        disableControls();
        if (showConsole) System.out.println("CONTROL CONSOLE: Executing current turn");
        
        // Right Going first
        if (leftPokemon.getBattle_speed() < rightPokemon.getBattle_speed()) {
            BattleMechanics.useMove(rightPokemon, leftPokemon, rightMove, leftMove);
            leftHpLabel.setText(Integer.toString(leftPokemon.getCurrent_hp()));
            
            textArea.setText(rightPokemon.getName() + " used " + rightMove.getName() + "!");
          
            BattleMechanics.useMove(leftPokemon, rightPokemon, leftMove, rightMove);
            rightHpLabel.setText(Integer.toString(rightPokemon.getCurrent_hp()));
            
        // Left Going First
        } else {
            // Left turn
            BattleMechanics.useMove(leftPokemon, rightPokemon, leftMove, rightMove);
            textArea.setText(leftPokemon.getName() + " used " + leftMove.getName() + "!");
            rightHpLabel.setText(Integer.toString(rightPokemon.getCurrent_hp()));
            updateHPBar(rightPokemon, rightHpBar);
            
            // Right turn
            BattleMechanics.useMove(rightPokemon, leftPokemon, rightMove, leftMove);
            TimerTask taskOne = new TimerTask() {
                @Override
                public void run() {
                    textArea.setText(rightPokemon.getName() + " used " + rightMove.getName() + "!");
                    leftHpLabel.setText(Integer.toString(leftPokemon.getCurrent_hp()));
                    updateHPBar(leftPokemon, leftHpBar);
                }
            };
            
            // Post turn
            TimerTask taskTwo = new TimerTask() {
                @Override
                public void run() {
                    textArea.setText("DONE!");
                    enableControls();
                }
            };
            timer.schedule(taskOne, 3000);
            timer.schedule(taskTwo, 6000);
            
        }
        if (showConsole) {
            System.out.println("CONTROL CONSOLE: Turn results");
            System.out.println("Left Pokemon - " + leftPokemon.getName() + " " + leftPokemon.getCurrent_hp() + "/" + leftPokemon.getCurrent_max_hp());
            System.out.println("Right Pokemon - " + rightPokemon.getName() + " " + rightPokemon.getCurrent_hp() + "/" + rightPokemon.getCurrent_max_hp());
        }
       
    }
    
    // GUI Helpers
    private void updateHPBar(Pokemon pokemon, JProgressBar hpBar) {
        hpBar.setValue(pokemon.getCurrent_hp());
        if (pokemon.getCurrent_hp() < (pokemon.getCurrent_max_hp() / 2)) {
            hpBar.setForeground(Color.yellow);
        }
        if (pokemon.getCurrent_hp() < (pokemon.getCurrent_max_hp() / 4)) {
            hpBar.setForeground(Color.red);
        }
    }
    
    private void disableControls() {
        fightButton.setEnabled(false);
        bagButton.setEnabled(false);
        pokemonButton.setEnabled(false);
    }
    
    private void enableControls() {
        fightButton.setEnabled(true);
        bagButton.setEnabled(true);
        pokemonButton.setEnabled(true);
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
