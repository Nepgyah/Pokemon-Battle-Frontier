package battle.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import pokemon.Pokemon;
import trainer.Trainer;
import utilities.PokeColors;

public class Swaps {
    
    public static void swapPokemon(Trainer trainer, int partyPosition) {
        Collections.swap(trainer.getParty(), 0, partyPosition);
    }
    
    public static void addSwapEvent(ArrayList<TimerTask> eventQueue, String previousName, Pokemon pokemon, Boolean isLeftSide, JLabel [] labelArray, JProgressBar hpBar, JTextArea textArea) {
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
                setPokemonLabels(pokemon,isLeftSide, labelArray, hpBar);
                textArea.setText("Go " + pokemon.getName() + "!");
            }
        });
    }
    
    public static void addPokemonReplaceEvent(ArrayList<TimerTask> eventQueue, String trainerName, Pokemon pokemon, Boolean isLeftSide, JLabel [] labelArray, JProgressBar hpBar, JTextArea textArea) {
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                textArea.setText(trainerName + " sent out " + pokemon.getName() + "!");
            }
        });
        eventQueue.add(new TimerTask() {
            @Override
            public void run() {
                setPokemonLabels(pokemon,isLeftSide, labelArray, hpBar);
            }
        });
    }
    
    public static void setPokemonLabels(Pokemon pokemon, Boolean isLeftSide, JLabel [] labelArray, JProgressBar hpBar) {
        labelArray[0].setText(pokemon.getName());
        labelArray[1].setText(Integer.toString(pokemon.getLevel()));
        labelArray[2].setText(Integer.toString(pokemon.getCurrent_hp()));
        labelArray[3].setText(Integer.toString(pokemon.getCurrent_max_hp()));
        labelArray[4].setText(pokemon.getBattle_status());
        if (isLeftSide) {
            labelArray[5].setIcon(pokemon.getBackIcon());
        } else {
            labelArray[5].setIcon(pokemon.getFrontIcon());
        }
        hpBar.setMaximum(pokemon.getCurrent_max_hp());
        hpBar.setValue(pokemon.getCurrent_hp());
        if (pokemon.getCurrent_hp() > pokemon.getCurrent_max_hp() / 2) {
            hpBar.setForeground(PokeColors.greenHP);
        } else if (pokemon.getCurrent_hp() > pokemon.getCurrent_max_hp() / 4) {
            hpBar.setForeground(PokeColors.yellowHP);
        } else {
            hpBar.setForeground(PokeColors.redHP);
        }
        
    }
}
