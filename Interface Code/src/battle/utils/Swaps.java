package battle.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import pokemon.Pokemon;
import trainer.Trainer;

public class Swaps {
    
    public static void swapPokemon(Trainer trainer, int partyPosition) {
        Collections.swap(trainer.getParty(), 0, partyPosition);
    }
    
    public static void addSwapEvent(ArrayList<TimerTask> eventQueue, String previousName, Pokemon pokemon, JLabel [] labelArray, JProgressBar hpBar, JTextArea textArea) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                System.out.println("BM: Describing switch");
                textArea.setText(previousName + ", switch out!" + "\nCome back!");
            }
        });
        
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                System.out.println("BM: Updating labels");
                setPokemonLabels(pokemon, labelArray, hpBar);
                textArea.setText("Go " + pokemon.getName() + "!");
            }
        });
    }
    
    public static void setPokemonLabels(Pokemon pokemon, JLabel [] labelArray, JProgressBar hpBar) {
        labelArray[0].setText(pokemon.getName());
        labelArray[1].setText(Integer.toString(pokemon.getLevel()));
        labelArray[2].setText(Integer.toString(pokemon.getCurrent_hp()));
        labelArray[3].setText(Integer.toString(pokemon.getCurrent_max_hp()));
        labelArray[4].setText(pokemon.getBattle_status());
        labelArray[5].setIcon(pokemon.getIcon());
        hpBar.setMaximum(pokemon.getCurrent_max_hp());
        hpBar.setValue(pokemon.getCurrent_hp());
    }
}
