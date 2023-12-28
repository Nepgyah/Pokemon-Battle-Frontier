package start;

import java.util.ArrayList;
import pokemon.Pokemon;
import utilities.*;
import people.Trainer;
import move.Move;
import client.Client;
import item.Item;

public class Start {
    public static void main(String[] args) {
        boolean showConsole = true;
        if (showConsole) System.out.println("Program Start");
        
        // Initialize base data (Pokemon / Moves / Items)
        if (showConsole) System.out.println("Loading Moves");
        ArrayList<Item> itemdex = Startup.initializeItemdex();      
        
        if (showConsole) System.out.println("Loading Pokemon");
        ArrayList<Pokemon> pokedex = Startup.initializePokedex();
        if (showConsole) ConsoleCommands.displayPokedex(pokedex);
        
        if (showConsole) System.out.println("Loading Moves");
        ArrayList<Move> movedex = Startup.initializeMovedex();     
        if (showConsole) ConsoleCommands.displayMovedex(movedex);
        
        // Load custom data (Save Trainers / Settings )
        if (showConsole) System.out.println("Loading Saved Data");
        
        if (showConsole) System.out.println("Loading Success");
        if (showConsole) System.out.println("Loading Interface");

        ArrayList<Trainer> trainers = new ArrayList<>();
        
        Trainer ash = Startup.createAsh(pokedex, movedex, itemdex);
        Trainer gary = Startup.createGary(pokedex, movedex, itemdex);
        trainers.add(ash);
        trainers.add(gary);

        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
//                new MainMenu(pokedex).setVisible(true);
                  new Client(pokedex, trainers, showConsole).setVisible(true);
            }
        });
    }
}
