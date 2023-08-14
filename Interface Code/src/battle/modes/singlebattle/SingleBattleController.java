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
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
            leftWins = false, 
            rightWins = false, 
            showConsole;
    
    JFrame battleFrame, clientFrame;
    JLabel [] leftLabels, rightLabels;
    JTextArea textArea;
    JProgressBar leftHPBar, rightHPBar;
    JButton fightButton, bagButton, pokemonButton;
    pokemonPanel leftPokemonPanel, rightPokemonPanel;
    SingleBattleMovePanel leftMovePanel;
    SingleBattleMovePanel rightMovePanel;
    
    JPanel detailPanel, navPanel;
    CardLayout battleCard, navCard;
    
    public SingleBattleController(JFrame clientFrame, JPanel navPanel, 
            JFrame battleFrame, JPanel detailPanel,
            Trainer leftTrainer, Trainer rightTrainer, 
            boolean showConsole, 
            JTextArea textArea, 
            JLabel [] leftLabels, JLabel [] rightLabels,
            JProgressBar leftHPBar, JProgressBar rightHPBar,
            JButton [] controlButtons) {
        
        super();
        
        System.out.println("CONTROL CONSOLE: Initializing battle controller");
        this.timer = new Timer();
        
        this.battleFrame = battleFrame;
        this.clientFrame = clientFrame;
        this.leftTrainer = leftTrainer;
        this.rightTrainer = rightTrainer;
        
        this.leftPokemon = leftTrainer.getParty().get(0);
        this.rightPokemon = rightTrainer.getParty().get(0);
        this.showConsole = showConsole;
        
        // GUI Window variables
        this.detailPanel = detailPanel;
        this.navPanel = navPanel;
        
        this.battleCard = (CardLayout) detailPanel.getLayout();
        this.navCard = (CardLayout) navPanel.getLayout();
        this.textArea = textArea;
        this.leftLabels = leftLabels;
        this.rightLabels = rightLabels;
        this.leftHPBar = leftHPBar;
        this.rightHPBar = rightHPBar;
        
        // Control Buttons
        this.fightButton = controlButtons[0];
        this.pokemonButton = controlButtons[1];
        this.bagButton = controlButtons[2];

        Swaps.setPokemonLabels(leftPokemon, true, leftLabels, leftHPBar);
        Swaps.setPokemonLabels(rightPokemon, false, rightLabels, rightHPBar);
        
        System.out.println("CONTROL CONSOLE: Initialization complete");
    }
    
    public void runTurn() {
        
        disableControls();
        battleCard.show(detailPanel, "waitingPanel");
        if (showConsole) {
            System.out.println("CONTROL CONSOLE: Running turn");
//            consoleFlags();
        }
        if (leftSwap == true) {
            leftPrevName = leftPokemon.getName();
            Swaps.swapPokemon(leftTrainer, leftNextPokemon);
            leftPokemon = leftTrainer.getParty().get(0);
        
            Swaps.addSwapEvent(eventQueue, leftPrevName, leftPokemon, true, leftLabels, leftHPBar, textArea);
            
            leftPokemonPanel.setPokemonButtons();
            leftMovePanel.setMoveButtons(leftPokemon.getMoveset());
        }
        if (rightSwap == true) {
            rightPrevName = rightPokemon.getName();
            Swaps.swapPokemon(rightTrainer, rightNextPokemon);
            rightPokemon = rightTrainer.getParty().get(0);
            
            Swaps.addSwapEvent(eventQueue, rightPrevName, rightPokemon, false,rightLabels, rightHPBar, textArea);
            
            rightPokemonPanel.setPokemonButtons();
            rightMovePanel.setMoveButtons(rightPokemon.getMoveset());
        }
        
        // Right Going first
        if (leftPokemon.getBattle_speed() < rightPokemon.getBattle_speed()) {
            // Right Turn
            if (rightPokemon.isRecharging()) {
                BattleEvents.addGenericEvent(eventQueue, textArea, rightPokemon.getName() + " is recharging. It can't move!");
                rightPokemon.setRecharging(false);
            } else if (rightMove != null) {  
                if(rightMove instanceof TwoTurn && rightPokemon.isInTwoTurn() == false) {
                    BattleEvents.addGenericEvent(eventQueue, textArea, rightPokemon.getName() + ((TwoTurn)rightMove).getTurnDescription());
                    BattleEvents.addIconRemoveEvent(eventQueue, rightLabels[5] );
                    rightPokemon.setInTwoTurn(true);
                } else {
                    Mechanics.useMove(
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
            if (leftPokemon.isFainted() == true) {
                BattleEvents.addGenericEvent(eventQueue, textArea, leftPokemon.getName() + " fainted!");
                BattleEvents.addIconRemoveEvent(eventQueue, leftLabels[5]);
                if (Mechanics.didLose(leftTrainer)) {
                    rightWins = true;
                    BattleEvents.addWinnerEvent(eventQueue, textArea, rightTrainer, leftTrainer);
                } else {
                    leftMove = null;
                }
            } else if (leftPokemon.isRecharging()) {
                BattleEvents.addGenericEvent(eventQueue, textArea, leftPokemon.getName() + " is recharging. It can't move!");
                leftPokemon.setRecharging(false);
            } else if (leftMove != null) {
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
                    Mechanics.useMove(
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
            if (leftPokemon.isRecharging()) {
                BattleEvents.addGenericEvent(eventQueue, textArea, leftPokemon.getName() + " is recharging. It can't move!");
                leftPokemon.setRecharging(false);
            } else if( leftMove != null) {
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
                    Mechanics.useMove(
                    leftPokemon, rightPokemon,
                leftMove, rightMove,
                leftLabels, rightLabels,
                leftHPBar, rightHPBar,
                        eventQueue, textArea 
                    );
                    leftMove = null;
                    if(leftPokemon.isInTwoTurn() == true) leftPokemon.setInTwoTurn(false);
                }            
            }
            // Right turn
            if (rightPokemon.isFainted() == true) {
                BattleEvents.addGenericEvent(eventQueue, textArea, rightPokemon.getName() + " fainted!");
                BattleEvents.addIconRemoveEvent(eventQueue, rightLabels[5]);
                if (Mechanics.didLose(rightTrainer)) {
                    leftWins = true;
                    BattleEvents.addWinnerEvent(eventQueue, textArea, leftTrainer, rightTrainer);
                } else {
                    rightMove = null;
                }
            } else if (rightPokemon.isRecharging()) {
                BattleEvents.addGenericEvent(eventQueue, textArea, rightPokemon.getName() + " is recharging. It can't move!");
                rightPokemon.setRecharging(false);
            } else if (rightMove != null) { 
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
                    Mechanics.useMove(
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
        
        // Post move checks here
        
        if (leftPokemon.isFainted() && rightWins == false) {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    leftTrainerTurn = true;
                    textArea.setText("Swap out " + leftPokemon.getName());
                    battleCard.show(detailPanel, "leftPokemonPanel");
                }
            });
        } else if (rightPokemon.isFainted() && leftWins == false) {
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    leftTrainerTurn = false;
                    textArea.setText("Swap out " + rightPokemon.getName());
                    battleCard.show(detailPanel, "rightPokemonPanel");
                }
            });
            // WIN CONDITION
        } else if (leftWins || rightWins) {
            BattleEvents.addWindowCloseEvent(eventQueue, textArea, battleFrame, clientFrame, navCard, navPanel);
        } else {
            BattleEvents.addGenericEvent(eventQueue, textArea, "End of Turn!");
            eventQueue.add(new TimerTask() {
                @Override
                public void run() {
                    if (showConsole) {
                        System.out.println("Left Trainer");
                        Mechanics.displayBattleStatsToConsole(leftPokemon);
                        System.out.println("\nRight Trainer");
                        Mechanics.displayBattleStatsToConsole(rightPokemon);
                    }
                    // Both are either recharging or in two turn (Right doesent need a check since left make their turn first)
                    if ( (leftPokemon.isInTwoTurn() || leftPokemon.isRecharging() ) && ( rightPokemon.isInTwoTurn() || rightPokemon.isRecharging() ) ) {
                        runTurn();
                // LeftPokemon only recharging or two turn
                    } else if (leftPokemon.isInTwoTurn() || leftPokemon.isRecharging()) {
                        textArea.setText("What will " + rightPokemon.getName() + " do?");
                        enableControls();
                    } else {
                        textArea.setText("What will " + leftPokemon.getName() + " do?");
                        enableControls();
                    }
                }
            });
        }
        
        for (int i = 0; i < eventQueue.size(); i++) {
            timer.schedule(eventQueue.get(i), i * 2000);
        }

        // RESET 
        eventQueue.clear();
        leftSwap = rightSwap = false;
    }
    
    // Methods for window to call
    public void setMoveChoice(int pos){
        if (leftTrainerTurn) {
            leftMove = leftPokemon.getMoveset()[pos];
            if (!showConsole) System.out.println("CONTROL CONSOLE: Left Pokemon move selected -> " + leftMove.getName());
            if (rightPokemon.isInTwoTurn() || rightPokemon.isRecharging()) {
                runTurn();
            } else {
                leftTrainerTurn = false;
                battleCard.show(detailPanel, "waitingPanel");
                textArea.setText("What will " + rightPokemon.getName() + " do?");
            }
        } else {
            rightMove = rightPokemon.getMoveset()[pos];
            if (!showConsole) System.out.println("CONTROL CONSOLE: Right Pokemon move selected -> " + rightMove.getName());
            runTurn();
            if (leftPokemon.isInTwoTurn() == false && leftPokemon.isRecharging() == false) {
                leftTrainerTurn = true;
            }
        }
    }
    
    public void setPokemonSwap(int pos) {
        if(leftTrainerTurn) {
            // Swapping for fainted Pokemon
            if (leftTrainer.getParty().get(pos).isFainted() == true) {
                textArea.setText(leftTrainer.getParty().get(pos).getName() + " is unable to battle anymore");
                return;
            }
            // Swapping mid turn
            if (leftPokemon.isFainted()) {
                battleCard.show(detailPanel, "waitingPanel");
                Swaps.swapPokemon(leftTrainer, pos);
                leftPokemon = leftTrainer.getParty().get(0);
                Swaps.addPokemonReplaceEvent(eventQueue, leftTrainer.getName(), leftPokemon, true, leftLabels, leftHPBar, textArea);
                
                leftPokemonPanel.setPokemonButtons();
                leftMovePanel.setMoveButtons(leftPokemon.getMoveset());
                
                leftTrainerTurn = true;
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText("What will " + leftPokemon.getName() + " do?");
                        enableControls();
                        
                    }
                });
                for (int i = 0; i < eventQueue.size(); i++) {
                    timer.schedule(eventQueue.get(i), i * 2000);
                }
                eventQueue.clear();
            } else {
                leftNextPokemon = pos;
                textArea.setText("What will " + rightPokemon.getName() + " do?");
                leftSwap = true;
                leftTrainerTurn = false;
                battleCard.show(detailPanel, "waitingPanel");
            }
        } else {
            // Swapping mid turn
            if(rightPokemon.isFainted()) {
                Swaps.swapPokemon(rightTrainer, pos);
                rightPokemon = rightTrainer.getParty().get(0);
                Swaps.addPokemonReplaceEvent(eventQueue, rightTrainer.getName(), rightPokemon, false, rightLabels, rightHPBar, textArea);
                
                rightPokemonPanel.setPokemonButtons();
                rightMovePanel.setMoveButtons(rightPokemon.getMoveset());
                leftTrainerTurn = true;
                eventQueue.add(new TimerTask() {
                    @Override
                    public void run() {
                        textArea.setText("What will " + leftPokemon.getName() + " do?");
                        enableControls();
                    }
                });
                for (int i = 0; i < eventQueue.size(); i++) {
                    timer.schedule(eventQueue.get(i), i * 2000);
                }
                eventQueue.clear();
            } else {
                battleCard.show(detailPanel, "waitingPanel");
                rightNextPokemon = pos;
                rightSwap = true;
                leftTrainerTurn = true;
                runTurn();
            }
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
    public void setDetailedPanel(JPanel panel) {
        System.out.println("CONTROL CONSOLE: Linking detail panel to controller");
        this.detailPanel = panel;
    }
    
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
