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

public class BattleEvents {
    
    public static void addGenericEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, String event) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText(event);
            }
        });
    }
    
    // Used for status effects and recoil
    public static void addSelfDamageEffect(ArrayList<TimerTask> eventQueue, JTextArea textArea, int damage, Pokemon user, JLabel userHP, JProgressBar userHPBar) {
        user.takeDamage(damage);
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                userHP.setText(Integer.toString(user.getCurrent_hp()));
                userHPBar.setValue(user.getCurrent_hp());

                if (user.getCurrent_hp() < (user.getCurrent_max_hp() / 2)) {
                    userHPBar.setForeground(PokeColors.yellowHP);
                }
                if (user.getCurrent_hp() < (user.getCurrent_max_hp() / 4)) {
                    userHPBar.setForeground(Color.red);
                }
            }
        });
    }
 
    public static void addDamageEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, String userStatus, int damage, Pokemon target, JLabel targetHP, JProgressBar targetHPBar) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                 if (userStatus == "BRN") {
                    target.takeDamage(damage / 2);
                } else {
                    target.takeDamage(damage);
                }
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
    
    public static void addStatusEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, String status, String name, JLabel [] labels) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
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
            }
        });
    }
    
    public static void addIconReturnEvent(ArrayList<TimerTask> eventQueue, Pokemon pokemon, JLabel iconLabel) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                iconLabel.setIcon(pokemon.getFrontIcon());
            }
        });
    }
    
    public static void addIconRemoveEvent(ArrayList<TimerTask> eventQueue, JLabel iconLabel) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                iconLabel.setIcon(null);
            }
        });
    }
    
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
