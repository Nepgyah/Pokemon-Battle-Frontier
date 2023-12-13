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
        ArrayList<Pokemon> pokedex = startup.initializePokedex();
        ArrayList<Move> movedex = startup.initializeMovedex();
        ArrayList<Item> itemdex = startup.initializeItemdex();
        
        // Load custom data (Save Trainers / Settings )
        System.out.println("Loading Saved Data");
        
        System.out.println("Loading Success");
        System.out.println("Loading Interface");
        
        // Loadup custom data
        
        // Objects for basic testin

        System.out.println("Program begin");
        ArrayList<Trainer> trainers = new ArrayList<>();
        Trainer ash = startup.createAsh(pokedex, movedex);
        Trainer gary = startup.createGary(pokedex, movedex);
        trainers.add(ash);
        trainers.add(gary);

        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
//                new MainMenu(pokedex).setVisible(true);
                  new Client(pokedex, trainers).setVisible(true);
            }
        });
    }
}
