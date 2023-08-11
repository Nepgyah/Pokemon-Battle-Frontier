package battle.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class BattleEvents {
    
    public static void addGenericEvent(ArrayList<TimerTask> eventQueue, JTextArea textArea, String event) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                System.out.println("BM: Describing event");
                textArea.setText(event);
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
}
