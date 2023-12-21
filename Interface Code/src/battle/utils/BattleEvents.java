package battle.utils;

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
     * Adds a event of a pokemon taking damage from an attack.
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param textArea textarea containing event description
     * @param userStatus current battle status of the pokemon conducting the move
     * @param damage value of damage taken
     * @param target pokemon receiving the attack
     * @param targetHP numerical representation of health points of the target
     * @param targetHPBar visual representation of health points of the target
     */
    public static void addDamageEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, int damage, Pokemon target, JLabel targetHP, JProgressBar targetHPBar) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                // Update the HP Bar
                targetHP.setText(Integer.toString(target.getCurrent_hp()));
                targetHPBar.setValue(target.getCurrent_hp());

                if (target.getCurrent_hp() < (target.getCurrent_max_hp() / 2)) {
                    targetHPBar.setForeground(PokeColors.yellowHP);
                }
                if (target.getCurrent_hp() < (target.getCurrent_max_hp() / 4)) {
                    targetHPBar.setForeground(Color.red);
                }
            }
        });
    }
    
    /**
     * Adds a event of a pokemon healing their health points
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param textArea textarea containing event description
     * @param healAmount value of the amount healed from the move
     * @param user pokemon receiving the heal
     * @param userHP numerical representation of health points of the user
     * @param userHPBar visual representation of health points of the user
     */
    public static void addHealingEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, int healAmount, Pokemon user, JLabel userHP, JProgressBar userHPBar) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                user.healHP(healAmount);
                userHP.setText(Integer.toString(user.getCurrent_hp()));
                userHPBar.setValue(user.getCurrent_hp());

                if (user.getCurrent_hp() > (user.getCurrent_max_hp() / 2)) {
                    userHPBar.setForeground(PokeColors.greenHP);
                }
                if (user.getCurrent_hp() < (user.getCurrent_max_hp() / 2)) {
                    userHPBar.setForeground(PokeColors.yellowHP);
                }
            }
        });
    }
    
    /**
     * Adds/removes a status application event
     * @param eventQueue queue of events that happen during a single turn in pokemon
     * @param textArea textarea containing event description
     * @param status (If applicable) the current status of the pokemon
     * @param name name of the pokemon receiving the status change
     * @param labels numerical representation of the pokemon receiving the change
     */
    public static void addStatusEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, String status, String name, JLabel [] labels) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                if(status != null)
                {
                    labels[4].setText(status);
                    if (status.equals("PAR")) {
                        labels[4].setForeground(Color.YELLOW);
                        textArea.setText("Enemy " + name + " was paralyzed!");
                    }
                    if (status.equals("PSN")) {
                        labels[4].setForeground(Color.MAGENTA);
                        textArea.setText("Enemy " + name + " was poisoned!");
                    }
                    if (status.equals("BRN")) {
                        labels[4].setForeground(Color.RED);
                        textArea.setText("Enemy " + name + " was burned!");
                    }
                    if (status.equals("SLP")) {
                        labels[4].setForeground(Color.DARK_GRAY);
                        textArea.setText("Enemy " + name + " was put to sleep!");
                    }
                    if (status.equals("FRZ")) {
                        labels[4].setForeground(Color.CYAN);
                        textArea.setText("Enemy " + name + " was frozen solid!");
                    }
                } else {
                    labels[4].setText("");
                }
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
