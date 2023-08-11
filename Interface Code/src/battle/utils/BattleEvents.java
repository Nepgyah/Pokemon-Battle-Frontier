package battle.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import pokemon.Pokemon;

public class BattleEvents {
    
    public static void addGenericEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, String event) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText(event);
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
                    targetHPBar.setForeground(Color.yellow);
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

                if (user.getCurrent_hp() < (user.getCurrent_max_hp() / 2)) {
                    userHPBar.setForeground(Color.yellow);
                }
                if (user.getCurrent_hp() < (user.getCurrent_max_hp() / 4)) {
                    userHPBar.setForeground(Color.red);
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
                iconLabel.setIcon(pokemon.getIcon());
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
}
