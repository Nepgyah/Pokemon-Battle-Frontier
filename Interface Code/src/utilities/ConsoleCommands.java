/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.ArrayList;
import move.Move;
import pokemon.Pokemon;

/**
 *
 * @author auston
 */
public class ConsoleCommands {
    
    public static void postTurnSummary(Pokemon pokemon) {
        System.out.println("Summary for " + pokemon.getName() + " " + pokemon.getCurrent_hp() + " / " + pokemon.getCurrent_max_hp());
        
        System.out.println("Recharging? -> " + pokemon.isRecharging());
        System.out.print("Status: " + pokemon.getBattle_status());
        if (pokemon.isHoldingItem()) {
            System.out.println(" Holding: " + pokemon.getItem().getName());
        } else {
            System.out.println(" Holding no item");
        }
        
        System.out.println("Attack: " + pokemon.getBattle_attack() + " (" + pokemon.getCurrent_attack()+ ")");
        System.out.println("Defense: " + pokemon.getBattle_defense() + " (" + pokemon.getCurrent_defense()+ ")");
        System.out.println("Special Attack: " + pokemon.getBattle_special_attack() + " (" + pokemon.getCurrent_special_attack()+ ")");
        System.out.println("Special Defense: " + pokemon.getBattle_special_defense() + " (" + pokemon.getCurrent_special_defense()+ ")");
        System.out.println("Speed: " + pokemon.getBattle_speed() + " (" + pokemon.getCurrent_speed()+ ")");
        System.out.println("Current Accuracy Modifier: " + pokemon.getBattle_accuracy());
        System.out.println("Curretn Evasion Modifier: " + pokemon.getBattle_evasion());
    }
    
    public static void displayPokedex(ArrayList<Pokemon> pokedex) {
        System.out.println("Current Pokedex");
        for (int i = 0; i < pokedex.size(); i++) {
            System.out.println((i+1) + ". " + pokedex.get(i).getName());
        }
        System.out.println("");
    }
    
    public static void displayMovedex(ArrayList<Move> movedex) {
        System.out.println("Current Movedex");
        for (int i = 0; i < movedex.size(); i++) {
            System.out.println((i+1) + ". " + movedex.get(i).getName());
        }
        System.out.println("");
    }
}
