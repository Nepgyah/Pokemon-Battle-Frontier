package start;

import java.util.ArrayList;
import pokemon.Pokemon;
import utilities.startup;
import trainer.Trainer;
import move.Move;
import client.Client;
import item.Item;

public class Start {
    public static void main(String[] args) {

        System.out.println("Program Start");
        
        // Initialize base data (Pokemon / Moves / Items)
        System.out.println("Loading Pokemon");
        ArrayList<Item> itemdex = startup.initializeItemdex();
        
        System.out.println("Items in the game");
        for (Item item : itemdex) {
            System.out.println(item.getName());
        }
        ArrayList<Pokemon> pokedex = startup.initializePokedex();
        ArrayList<Move> movedex = startup.initializeMovedex();     
        
        // Load custom data (Save Trainers / Settings )
        System.out.println("Loading Saved Data");
        
        System.out.println("Loading Success");
        System.out.println("Loading Interface");
        
        // Loadup custom data
        
        // Objects for basic testin

        ArrayList<Trainer> trainers = new ArrayList<>();
        Trainer ash = startup.createAsh(pokedex, movedex, itemdex);
        Trainer gary = startup.createGary(pokedex, movedex);
        trainers.add(ash);
        trainers.add(gary);

        System.out.println("Testing for held items");
        for (Pokemon pokemon : ash.getParty()) {
            pokemon.displayHeldItem();
        }        
        
        System.out.println("Executing runnable");
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
//                new MainMenu(pokedex).setVisible(true);
                  new Client(pokedex, trainers).setVisible(true);
            }
        });
    }
}
