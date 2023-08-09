package battle.gamemodes;

import move.Move;
import pokemon.Pokemon;
import trainer.Trainer;
import battle.BattleMechanics;
import battle.BattleUtilities;
import battle.gui.utilities.pokemonPanel;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class SingleBattleController{
   
    private Timer timer;
    private TimerTask task;
    private ArrayList<TimerTask> eventTextList = new ArrayList<>();
    
    private Trainer leftTrainer, rightTrainer;
    private Pokemon leftPokemon, rightPokemon;
    private Move leftMove, rightMove;
    private int leftNextPokemon, rightNextPokemon;
    private String leftPrevName, rightPrevName;
    
    boolean leftTrainerTurn = true,
            leftSwap = false, 
            rightSwap = false,
            leftPlayerForfeit, 
            rightPlayerForfeit, 
            leftWins, 
            rightWins, 
            showConsole;
    
    boolean leftMoveMade = false, rightMoveMade = false;
    
    JLabel leftHpLabel, rightHpLabel;
    JLabel [] leftLabels, rightLabels;
    JTextArea textArea;
    JProgressBar leftHpBar, rightHpBar;
    JButton fightButton, bagButton, pokemonButton;
    pokemonPanel leftPokemonPanel, rightPokemonPanel;
    movePanel leftMovePanel, rightMovePanel;
    
    public SingleBattleController(Trainer leftTrainer, Trainer rightTrainer, 
            boolean showConsole, 
            JTextArea textArea, 
            JLabel [] leftLabels, JLabel [] rightLabels,
            JProgressBar leftHpBar, JProgressBar rightHpBar,
            JButton fightButton, JButton bagButton, JButton pokemonButton) {
        
        super();
        
        System.out.println("CONTROL CONSOLE: Initializing battle controller");
        this.timer = new Timer();
        
        this.leftTrainer = leftTrainer;
        this.rightTrainer = rightTrainer;
        
        this.leftPokemon = leftTrainer.getParty().get(0);
        this.rightPokemon = rightTrainer.getParty().get(0);
        this.showConsole = showConsole;
        
        // GUI Window variables
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
    
    public void runTurn() {
        
        disableControls();
        if (showConsole) {
            System.out.println("CONTROL CONSOLE: Executing current turn");
            consoleFlags();
        }
        if (leftSwap == true) {
            leftPrevName = leftPokemon.getName();
            BattleUtilities.swapPokemon(leftTrainer, leftNextPokemon);
            leftPokemon = leftTrainer.getParty().get(0);
        
            addSwapEvent(leftPrevName, leftPokemon, leftLabels, leftHpBar);
            
            leftPokemonPanel.setPokemonButtons();
            leftMovePanel.setMoveButtons(leftPokemon.getMoveset());
        }
        if (rightSwap == true) {
            rightPrevName = rightPokemon.getName();
            BattleUtilities.swapPokemon(rightTrainer, rightNextPokemon);
            rightPokemon = rightTrainer.getParty().get(0);
            
            addSwapEvent(rightPrevName, rightPokemon, rightLabels, rightHpBar);
            
            rightPokemonPanel.setPokemonButtons();
            rightMovePanel.setMoveButtons(rightPokemon.getMoveset());
        }
        
        // Right Going first
        if (leftPokemon.getBattle_speed() < rightPokemon.getBattle_speed()) {
            if (rightMove != null) {
                textArea.setText(rightPokemon.getName() + " used " + rightMove.getName() + "!");
                
                BattleMechanics.useMove(rightPokemon, leftPokemon, rightMove, leftMove);
                
                addAttackEvent(rightPokemon, leftPokemon, rightMove, leftHpLabel, leftHpBar);
            }
            if (leftMove != null) {
                textArea.setText(leftPokemon.getName() + " used " + leftMove.getName() + "!");
                
                BattleMechanics.useMove(leftPokemon, rightPokemon, leftMove, rightMove);
                
                addAttackEvent(leftPokemon, rightPokemon, leftMove, rightHpLabel, rightHpBar);
            }
        } 
        
        // Left Going First
        if (leftPokemon.getBattle_speed() > rightPokemon.getBattle_speed()) {
            // Left turn
            if( leftMove != null) {
                textArea.setText(leftPokemon.getName() + " used " + leftMove.getName() + "!");
                
                BattleMechanics.useMove(leftPokemon, rightPokemon, leftMove, rightMove);
                
                 addAttackEvent(leftPokemon, rightPokemon, leftMove, rightHpLabel, rightHpBar);
            }
            if (rightMove != null) {
                textArea.setText(rightPokemon.getName() + " used " + rightMove.getName() + "!");
                
                BattleMechanics.useMove(rightPokemon, leftPokemon, rightMove, leftMove);
                
                addAttackEvent(rightPokemon, leftPokemon, rightMove, leftHpLabel, leftHpBar);
            }
        }
        
        eventTextList.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText("TURN DONE!");
                enableControls();
            }
        });
        
        int val = 0;
        for (int i = 0; i < eventTextList.size(); i++) {
            val = (i * 2000);
            System.out.println("DELAY -> " + Integer.toString(val));
            timer.schedule(eventTextList.get(i), val);
        }
        
        if (showConsole) {
            System.out.println("CONTROL CONSOLE: Turn results");
            System.out.println("Left Pokemon - " + leftPokemon.getName() + " " + leftPokemon.getCurrent_hp() + "/" + leftPokemon.getCurrent_max_hp());
            System.out.println("Right Pokemon - " + rightPokemon.getName() + " " + rightPokemon.getCurrent_hp() + "/" + rightPokemon.getCurrent_max_hp());
        }
        
        // RESET 
        eventTextList.clear();
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
        System.out.print("RIGHT: ");
        if (rightMove != null) {
            System.out.println("ATTACKING - " + rightPokemon.getName() + " -> " + rightMove.getName());
        }
        if (rightSwap == true) {
            System.out.println("SWAPPING - " + rightPokemon.getName());
        }
    }
    
    private void addSwapEvent(String previousName, Pokemon pokemon, JLabel [] labelArray, JProgressBar hpBar) {
        eventTextList.add(new TimerTask() {
                @Override
                public void run() {
                    textArea.setText(previousName + ", switch out!" + "\nCome back!");
                }
            });
            eventTextList.add(new TimerTask() {
                @Override
                public void run() {
                    setPokemonLabels(pokemon, labelArray, hpBar);
                    textArea.setText("Go " + pokemon.getName() + "!");
                }
            });
    }
    
    private void addAttackEvent(Pokemon atkPokemon, Pokemon defPokemon, Move move, JLabel hpLabel, JProgressBar hpBar) {
        eventTextList.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText(atkPokemon.getName() + " used " + move.getName() + "!");
            }
        });
        eventTextList.add(new TimerTask() {
            @Override
            public void run() {
                hpLabel.setText(Integer.toString(defPokemon.getCurrent_hp()));
                updateHPBar(defPokemon, hpBar);
            }
        });
    }
    // Methods for window to call
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
   
    // Methods to control GUI components
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
    
    // Methods for the window to call
    public void setLeftPokePanel(pokemonPanel panel) {
        this.leftPokemonPanel = panel;
    }
    
    public void setRightPokePanel(pokemonPanel panel) {
        this.rightPokemonPanel = panel;
    }
    
    public void setLeftMovePanel(movePanel panel) {
        this.leftMovePanel = panel;
    }
    
    public void setRightMovePanel(movePanel panel) {
        this.rightMovePanel = panel;
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
