package battle.utils;

import static battle.utils.Swaps.setPokemonLabels;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import pokemon.Pokemon;
import trainer.Trainer;
import utilities.PokeColors;

/**
 * Contains logic to create events that are carried out and displayed as text events during a pokemon turn.
 */
public class BattleEvents {
    
    
    public static void addHPUpdateEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, 
            int visualHP, int maxHP,
            JLabel hpLabel, JProgressBar hpBar) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                hpLabel.setText(Integer.toString(visualHP));
                hpBar.setValue(visualHP);

                if (visualHP < (maxHP / 2)) {
                    hpBar.setForeground(PokeColors.yellowHP);
                }
                if (visualHP < (maxHP / 4)) {
                    hpBar.setForeground(Color.red);
                }
            }
        }); 
    }
    
   
    public static void addGiveStatusEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, 
            String name, String status, JLabel statusLabel) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                statusLabel.setText(status);
                if (status.equals("PAR")) {
                    statusLabel.setForeground(Color.YELLOW);
                    textArea.setText("Enemy " + name + " was paralyzed!");
                }
                if (status.equals("PSN")) {
                    statusLabel.setForeground(Color.MAGENTA);
                    textArea.setText("Enemy " + name + " was poisoned!");
                }
                if (status.equals("BRN")) {
                    statusLabel.setForeground(Color.RED);
                    textArea.setText("Enemy " + name + " was burned!");
                }
                if (status.equals("SLP")) {
                    statusLabel.setForeground(Color.DARK_GRAY);
                    textArea.setText("Enemy " + name + " was put to sleep!");
                }
                if (status.equals("FRZ")) {
                    statusLabel.setForeground(Color.CYAN);
                    textArea.setText("Enemy " + name + " was frozen solid!");
                }
            }
        }); 
    }
    
    public static void addRemoveStatusEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, JLabel statusLabel) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                statusLabel.setText("");
            }
        }); 
    }
    public static void setPokemonLabels(Pokemon pokemon, Boolean isLeftSide, int visualHP, String visualStatus, JLabel [] labelArray, JProgressBar hpBar) {
        labelArray[0].setText(pokemon.getName());
        labelArray[1].setText(Integer.toString(pokemon.getLevel()));
        labelArray[2].setText(Integer.toString(visualHP));
        labelArray[3].setText(Integer.toString(pokemon.getCurrent_max_hp()));
        if (visualStatus != null) {
            labelArray[4].setText(visualStatus);
        } else {
            labelArray[4].setText("");
        }
        labelArray[4].setForeground(getStatusColor(visualStatus));
        if (isLeftSide) {
            labelArray[5].setIcon(pokemon.getBackIcon());
        } else {
            labelArray[5].setIcon(pokemon.getFrontIcon());
        }
        hpBar.setMaximum(pokemon.getCurrent_max_hp());
        hpBar.setValue(visualHP);
        if (pokemon.getCurrent_hp() > pokemon.getCurrent_max_hp() / 2) {
            hpBar.setForeground(PokeColors.greenHP);
        } else if (pokemon.getCurrent_hp() > pokemon.getCurrent_max_hp() / 4) {
            hpBar.setForeground(PokeColors.yellowHP);
        } else {
            hpBar.setForeground(PokeColors.redHP);
        }
    }
    
    private static Color getStatusColor(String status) {
        if(status != null) {
            if (status.equals("PAR")) return Color.YELLOW;
            if (status.equals("PSN")) return Color.MAGENTA;
            if (status.equals("BRN")) return Color.RED;
            if (status.equals("SLP")) return Color.DARK_GRAY;
            if (status.equals("FRZ")) return Color.CYAN;
        }
        return Color.BLACK;
    }
    
    
        public static void addSwapEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea,
                String previousName, Pokemon pokemon, int visualHP, String visualStatus,
                Boolean isLeftSide, JLabel [] labelArray, JProgressBar hpBar) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText(previousName + ", switch out!" + "\nCome back!");
            }
        });       
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                setPokemonLabels(pokemon, isLeftSide, visualHP, visualStatus, labelArray, hpBar);
                textArea.setText("Go " + pokemon.getName() + "!");
            }
        });
    }
    
    /**
     * Adds a generic text event such as declaring a pokenon swap or move use.
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param textArea textarea containing event description
     * @param event description of the event
     */
    public static void addGenericEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, String event) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText(event);
            }
        });
    }
    
    /**
     * Adds a event that returns the pokemon's icon to the screen/
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param pokemon pokemon whose icon is set to return
     * @param iconLabel corresponding label of icon from the battle window
     */
    public static void addIconReturnEvent(ArrayList<TimerTask> eventQueue, Pokemon pokemon, JLabel iconLabel) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                iconLabel.setIcon(pokemon.getFrontIcon());
            }
        });
    }
    
    /**
     * Adds a event that removes the pokemon's icon from the screen.
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param iconLabel corresponding label of icon from the battle window
     */
    public static void addIconRemoveEvent(ArrayList<TimerTask> eventQueue, JLabel iconLabel) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                iconLabel.setIcon(null);
            }
        });
    }
    
    /**
     * Adds a event to describe who won the pokemon battle.
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param textArea textarea containing event description
     * @param winner trainer who won the battle
     * @param loser trainer who lost the battle
     */
    public static void addWinnerEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, Trainer winner, Trainer loser) {
         eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText("...");
            }
        });
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText(loser.getName() + " is out of usable pokemon...");
            }
        });
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText(winner.getName() + " wins!");
            }
        });
    }
    
    /**
     * Adds a event to close down the window and return to the main menu
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param textArea textarea containing event description
     * @param battleFrame frame containing the battle
     * @param clientFrame frame contained the main menu and navigation
     * @param navCard card layout holding all possible panels to show
     * @param clientNavPanel panel holding panels for navigation
     */
    public static void addWindowCloseEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, 
            JFrame battleFrame, JFrame clientFrame, CardLayout navCard, JPanel clientNavPanel) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText("Match done!");
            }
        });
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                battleFrame.dispose();
            }
        });
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                clientFrame.setState(JFrame.NORMAL);
                navCard.show(clientNavPanel, "navCard");
            }
        });
    }
}
