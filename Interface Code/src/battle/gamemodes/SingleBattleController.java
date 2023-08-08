package battle.gamemodes;

import move.Move;
import pokemon.Pokemon;
import trainer.Trainer;
import battle.BattleMechanics;
import battle.BattleUtilities;
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
    private int leftNextPokemon, rightNextPokemon;
    private String leftPrevName, rightPrevName;
    
    boolean leftPlayerForfeit, 
            rightPlayerForfeit, 
            leftSwap = false, 
            rightSwap = false, 
            leftWins, 
            rightWins, 
            showConsole;
    private Scanner keyboardInput;
    private int input;
    
    boolean leftMoveMade = false, rightMoveMade = false;
    boolean leftTrainerTurn = true;
    
    JLabel leftHpLabel, rightHpLabel;
    JLabel [] leftLabels, rightLabels;
    JTextArea textArea;
    JProgressBar leftHpBar, rightHpBar;
    JButton fightButton, bagButton, pokemonButton;
            
    public SingleBattleController(Trainer leftTrainer, Trainer rightTrainer, 
            boolean showConsole, 
            JTextArea textArea, 
            JLabel [] leftLabels, JLabel [] rightLabels,
            JProgressBar leftHpBar, JProgressBar rightHpBar,
            JButton fightButton, JButton bagButton, JButton pokemonButton) {
        super();
        
        this.timer = new Timer();
        System.out.println("CONTROL CONSOLE: Initializing battle controller");
        
        this.leftTrainer = leftTrainer;
        this.rightTrainer = rightTrainer;
        
        this.leftPokemon = leftTrainer.getParty().get(0);
        this.rightPokemon = rightTrainer.getParty().get(0);
        this.showConsole = showConsole;
        
        this.textArea = textArea;
        this.leftLabels = leftLabels;
        this.rightLabels = rightLabels;
        this.leftHpLabel = leftLabels[2];
        this.rightHpLabel = rightLabels[2];
        this.leftHpBar = leftHpBar;
        this.rightHpBar = rightHpBar;
        this.fightButton = fightButton;
        this.bagButton = bagButton;
        this.pokemonButton = pokemonButton;
        
        setPokemonLabels(leftPokemon, leftLabels, leftHpBar);
        setPokemonLabels(rightPokemon, rightLabels, rightHpBar);
        System.out.println("CONTROL CONSOLE: Initialization complete");
   
    }
    
    // Methods for GUI
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
    
    public void setPokemonSwap(int pos) {
        if(leftTrainerTurn) {
            leftNextPokemon = pos;
            System.out.println(leftTrainer.getName() + " is swapping " + leftPokemon.getName() + " for " + leftTrainer.getParty().get(pos).getName());
            leftSwap = true;
            leftTrainerTurn = false;
        } else {
            rightNextPokemon = pos;
            System.out.println(rightTrainer.getName() + " is swapping " + rightPokemon.getName() + " for " + rightTrainer.getParty().get(pos).getName());
            rightSwap = true;
            leftTrainerTurn = true;
            runTurn();
        }
    }
    public void runTurn() {
        
        disableControls();
        if (showConsole) {
            System.out.println("CONTROL CONSOLE: Executing current turn");
//            consoleFlags();
        }
        
        if (leftSwap == true) {
            BattleUtilities.swapPokemon(leftTrainer, leftNextPokemon);
            leftPokemon = leftTrainer.getParty().get(0);
            setPokemonLabels(leftPokemon, leftLabels, leftHpBar);
        }
        if (rightSwap == true) {
            BattleUtilities.swapPokemon(rightTrainer, rightNextPokemon);
            rightPokemon = rightTrainer.getParty().get(0);
            setPokemonLabels(rightPokemon, rightLabels, rightHpBar);
        }
        // Right Going first
        if (leftPokemon.getBattle_speed() < rightPokemon.getBattle_speed()) {
            if (rightMove != null) {
                BattleMechanics.useMove(rightPokemon, leftPokemon, rightMove, leftMove);
                leftHpLabel.setText(Integer.toString(leftPokemon.getCurrent_hp()));
                textArea.setText(rightPokemon.getName() + " used " + rightMove.getName() + "!");
            }
            if (leftMove != null) {
                BattleMechanics.useMove(leftPokemon, rightPokemon, leftMove, rightMove);
                rightHpLabel.setText(Integer.toString(rightPokemon.getCurrent_hp()));
            }
     
        // Left Going First
        } 
        if (leftPokemon.getBattle_speed() > rightPokemon.getBattle_speed()) {
            // Left turn
            TimerTask taskOne;
            if( leftMove != null) {
                BattleMechanics.useMove(leftPokemon, rightPokemon, leftMove, rightMove);
                textArea.setText(leftPokemon.getName() + " used " + leftMove.getName() + "!");
                rightHpLabel.setText(Integer.toString(rightPokemon.getCurrent_hp()));
                updateHPBar(rightPokemon, rightHpBar);
            }
            if (rightMove != null) {
                BattleMechanics.useMove(rightPokemon, leftPokemon, rightMove, leftMove);
                textArea.setText(rightPokemon.getName() + " used " + rightMove.getName() + "!");
                leftHpLabel.setText(Integer.toString(leftPokemon.getCurrent_hp()));
                updateHPBar(leftPokemon, leftHpBar);
//                taskOne = new TimerTask() {
//                    @Override
//                    public void run() {
//                        textArea.setText(rightPokemon.getName() + " used " + rightMove.getName() + "!");
//                        leftHpLabel.setText(Integer.toString(leftPokemon.getCurrent_hp()));
//                        updateHPBar(leftPokemon, leftHpBar);
//                    }
//                };
            }
//            // Right turn
//            BattleMechanics.useMove(rightPokemon, leftPokemon, rightMove, leftMove);
//            TimerTask taskOne = new TimerTask() {
//                @Override
//                public void run() {
//                    textArea.setText(rightPokemon.getName() + " used " + rightMove.getName() + "!");
//                    leftHpLabel.setText(Integer.toString(leftPokemon.getCurrent_hp()));
//                    updateHPBar(leftPokemon, leftHpBar);
//                }
//            };
//            
            // Post turn
            TimerTask taskTwo = new TimerTask() {
                @Override
                public void run() {
                    textArea.setText("TURN DONE!");
                    enableControls();
                }
            };
//            timer.schedule(taskOne, 3000);
            timer.schedule(taskTwo, 6000);
            
        }
        if (showConsole) {
            System.out.println("CONTROL CONSOLE: Turn results");
            System.out.println("Left Pokemon - " + leftPokemon.getName() + " " + leftPokemon.getCurrent_hp() + "/" + leftPokemon.getCurrent_max_hp());
            System.out.println("Right Pokemon - " + rightPokemon.getName() + " " + rightPokemon.getCurrent_hp() + "/" + rightPokemon.getCurrent_max_hp());
        }
        // RESET FLAGS
        leftMove = rightMove = null;
        leftSwap = rightSwap = false;
    }
    
     private void consoleFlags() {
        System.out.println("CONTROL CONSOLE: Displaying turn flags");
        System.out.print("LEFT: ");
        if (leftMove != null) {
            System.out.println("ATTACKING - " + leftPokemon.getName() + " -> " + leftMove.getName());
        }
        if (leftSwap == true) {
            System.out.println("SWAPPING - " + leftPokemon.getName());
        }
        System.out.print("\nRIGHT: ");
        if (rightMove != null) {
            System.out.println("ATTACKING - " + rightPokemon.getName() + " -> " + rightMove.getName());
        }
        if (rightSwap == true) {
            System.out.println("SWAPPING - " + rightPokemon.getName());
        }
    }
     
    // GUI Controllers
    private void updateHPBar(Pokemon pokemon, JProgressBar hpBar) {
        hpBar.setValue(pokemon.getCurrent_hp());
        if (pokemon.getCurrent_hp() < (pokemon.getCurrent_max_hp() / 2)) {
            hpBar.setForeground(Color.yellow);
        }
        if (pokemon.getCurrent_hp() < (pokemon.getCurrent_max_hp() / 4)) {
            hpBar.setForeground(Color.red);
        }
    }
    
    private void setPokemonLabels(Pokemon pokemon, JLabel [] labelArray, JProgressBar hpBar) {
        labelArray[0].setText(pokemon.getName());
        labelArray[1].setText(Integer.toString(pokemon.getLevel()));
        labelArray[2].setText(Integer.toString(pokemon.getCurrent_hp()));
        labelArray[3].setText(Integer.toString(pokemon.getCurrent_max_hp()));
        labelArray[4].setIcon(pokemon.getIcon());
        hpBar.setMaximum(pokemon.getCurrent_max_hp());
        hpBar.setValue(pokemon.getCurrent_hp());
        
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
