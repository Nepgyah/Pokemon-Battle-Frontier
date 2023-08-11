package battle.modes.singlebattle;

import move.Move;
import pokemon.Pokemon;
import trainer.Trainer;
import battle.utils.*;
import battle.gui.utils.pokemonPanel;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import move.modifiers.TwoTurn;

public class SingleBattleController{
   
    private Timer timer;
    private ArrayList<TimerTask> eventQueue = new ArrayList<>();
    
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
    
    JLabel [] leftLabels, rightLabels;
    JTextArea textArea;
    JProgressBar leftHPBar, rightHPBar;
    JButton fightButton, bagButton, pokemonButton;
    pokemonPanel leftPokemonPanel, rightPokemonPanel;
    SingleBattleMovePanel leftMovePanel;
    SingleBattleMovePanel rightMovePanel;
    
    public SingleBattleController(Trainer leftTrainer, Trainer rightTrainer, 
            boolean showConsole, 
            JTextArea textArea, 
            JLabel [] leftLabels, JLabel [] rightLabels,
            JProgressBar leftHPBar, JProgressBar rightHPBar,
            JButton [] controlButtons) {
        
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
        this.leftHPBar = leftHPBar;
        this.rightHPBar = rightHPBar;
        
        // Control Buttons
        this.fightButton = controlButtons[0];
        this.pokemonButton = controlButtons[1];
        this.bagButton = controlButtons[2];

        
        Swaps.setPokemonLabels(leftPokemon, leftLabels, leftHPBar);
        Swaps.setPokemonLabels(rightPokemon, rightLabels, rightHPBar);
        
        System.out.println("CONTROL CONSOLE: Initialization complete");
    }
    
    public void runTurn() {
        
        disableControls();
        if (showConsole) {
            System.out.println("CONTROL CONSOLE: Running turn");
//            consoleFlags();
        }
        if (leftSwap == true) {
            leftPrevName = leftPokemon.getName();
            Swaps.swapPokemon(leftTrainer, leftNextPokemon);
            leftPokemon = leftTrainer.getParty().get(0);
        
            Swaps.addSwapEvent(eventQueue, leftPrevName, leftPokemon, leftLabels, leftHPBar, textArea);
            
            leftPokemonPanel.setPokemonButtons();
            leftMovePanel.setMoveButtons(leftPokemon.getMoveset());
        }
        if (rightSwap == true) {
            rightPrevName = rightPokemon.getName();
            Swaps.swapPokemon(rightTrainer, rightNextPokemon);
            rightPokemon = rightTrainer.getParty().get(0);
            
            Swaps.addSwapEvent(eventQueue, rightPrevName, rightPokemon, rightLabels, rightHPBar, textArea);
            
            rightPokemonPanel.setPokemonButtons();
            rightMovePanel.setMoveButtons(rightPokemon.getMoveset());
        }
        
        // Right Going first
        if (leftPokemon.getBattle_speed() < rightPokemon.getBattle_speed()) {
            // Right Turn
            if (rightMove != null) {  
                if(rightMove instanceof TwoTurn && rightPokemon.isInTwoTurn() == false) {
                    BattleEvents.addGenericEvent(eventQueue, textArea, rightPokemon.getName() + ((TwoTurn)rightMove).getTurnDescription());
                    eventQueue.add(new TimerTask() {
                        @Override
                        public void run() {
                            rightLabels[5].setIcon(null);
                        }
                    });
                    rightPokemon.setInTwoTurn(true);
                } else {
                    UseMove.useMove(
                    rightPokemon, leftPokemon,
                        rightMove, leftMove,
                        rightLabels, leftLabels,
                        rightHPBar, leftHPBar,
                        eventQueue,
                        textArea    
                    );
                    rightMove = null;
                    if(rightPokemon.isInTwoTurn() == true) rightPokemon.setInTwoTurn(false);
                }
            }
            // Left Turn
            if (leftMove != null) {
                if(leftMove instanceof TwoTurn && leftPokemon.isInTwoTurn() == false)
                {
                    BattleEvents.addGenericEvent(eventQueue, textArea, leftPokemon.getName() + ((TwoTurn)leftMove).getTurnDescription());
                    eventQueue.add(new TimerTask() {
                        @Override
                        public void run() {
                            leftLabels[5].setIcon(null);
                        }
                    });
                    leftPokemon.setInTwoTurn(true);
                } else {
                    UseMove.useMove(
                        leftPokemon, rightPokemon,
                        leftMove, rightMove,
                        leftLabels, rightLabels,
                        leftHPBar, rightHPBar,
                        eventQueue,
                        textArea 
                    );
                    leftMove = null;
                    if(leftPokemon.isInTwoTurn() == true) leftPokemon.setInTwoTurn(false);
                } 
            }
        } 
        
        // Left Going First
        if (leftPokemon.getBattle_speed() > rightPokemon.getBattle_speed()) {
            // Left turn
            if( leftMove != null) {
                if(leftMove instanceof TwoTurn && leftPokemon.isInTwoTurn() == false)
                {
                    BattleEvents.addGenericEvent(eventQueue, textArea, leftPokemon.getName() + ((TwoTurn)leftMove).getTurnDescription());
                    eventQueue.add(new TimerTask() {
                        @Override
                        public void run() {
                            leftLabels[5].setIcon(null);
                        }
                    });
                    leftPokemon.setInTwoTurn(true);
                } else {
                    UseMove.useMove(
                        leftPokemon, rightPokemon,
                    leftMove, rightMove,
                            leftLabels, rightLabels,
                    leftHPBar, rightHPBar,
                    eventQueue,
                        textArea 
                    );
                    leftMove = null;
                    if(leftPokemon.isInTwoTurn() == true) leftPokemon.setInTwoTurn(false);
                }            
            }
            if (rightMove != null) { 
                if(rightMove instanceof TwoTurn && rightPokemon.isInTwoTurn() == false) {
                    BattleEvents.addGenericEvent(eventQueue, textArea, rightPokemon.getName() + ((TwoTurn)rightMove).getTurnDescription());
                    eventQueue.add(new TimerTask() {
                        @Override
                        public void run() {
                            rightLabels[5].setIcon(null);
                        }
                    });
                    rightPokemon.setInTwoTurn(true);
                } else {
                    UseMove.useMove(
                    rightPokemon, leftPokemon,
                 rightMove, leftMove,
               rightLabels, leftLabels,
                rightHPBar, leftHPBar,
                        eventQueue,
                        textArea    
                    );
                    rightMove = null;
                    if(rightPokemon.isInTwoTurn() == true) rightPokemon.setInTwoTurn(false);
                }
            }
        }
        
        BattleEvents.addGenericEvent(eventQueue, textArea, "End of Turn!");
        
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                if (leftPokemon.isInTwoTurn() == true) {
                    textArea.setText("What will " + rightPokemon.getName() + " do?");
                    enableControls();
                }
                if (leftPokemon.isInTwoTurn() == true && rightPokemon.isInTwoTurn() == true) {
                    runTurn();
                }
                else {
                    if (!showConsole) {
                        System.out.println("Left Trainer");
                        UseMove.displayBattleStatsToConsole(leftPokemon);
                        System.out.println("\nRight Trainer");
                        UseMove.displayBattleStatsToConsole(rightPokemon);
                    }
                    enableControls();
                }
            }
        });
        
        for (int i = 0; i < eventQueue.size(); i++) {
            timer.schedule(eventQueue.get(i), i * 2000);
        }

        // RESET 
        eventQueue.clear();
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
    
    // Methods for window to call
    public void setMoveChoice(int pos){
        if (leftTrainerTurn) {
            leftMove = leftPokemon.getMoveset()[pos];
            if (showConsole) System.out.println("CONTROL CONSOLE: Left Pokemon move selected -> " + leftMove.getName());
            if (rightPokemon.isInTwoTurn() == true) {
                System.out.println("Right Pokemon in Two Turn (1/2)");
                runTurn();
            } else {
                leftTrainerTurn = false;
                textArea.setText("What will " + rightPokemon.getName() + " do?");
            }
        } else {
            rightMove = rightPokemon.getMoveset()[pos];
            if (showConsole) System.out.println("CONTROL CONSOLE: Right Pokemon move selected -> " + rightMove.getName());
            runTurn();
            if (leftPokemon.isInTwoTurn() == false) {
                leftTrainerTurn = true;
            }
        }
    }
    
    public void setPokemonSwap(int pos) {
        if(leftTrainerTurn) {
            leftNextPokemon = pos;
            System.out.println("CONTROL CONSOLE: " + leftTrainer.getName() + " is swapping " + leftPokemon.getName() + " for " + leftTrainer.getParty().get(pos).getName());
            textArea.setText("What will " + rightPokemon.getName() + " do?");
            leftSwap = true;
            leftTrainerTurn = false;
        } else {
            rightNextPokemon = pos;
            System.out.println("CONTROL CONSOLE: " + rightTrainer.getName() + " is swapping " + rightPokemon.getName() + " for " + rightTrainer.getParty().get(pos).getName());
            rightSwap = true;
            leftTrainerTurn = true;
            runTurn();
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
    
    // Methods for the window to call
    public void setLeftPokePanel(pokemonPanel panel) {
        this.leftPokemonPanel = panel;
    }
    
    public void setRightPokePanel(pokemonPanel panel) {
        this.rightPokemonPanel = panel;
    }
    
    public void setLeftMovePanel(SingleBattleMovePanel panel) {
        this.leftMovePanel = panel;
    }
    
    public void setRightMovePanel(SingleBattleMovePanel panel) {
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
