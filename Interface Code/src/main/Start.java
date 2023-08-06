package main;

import gameInterface.MainMenu;
import java.util.ArrayList;
import pokemon.pokedex.P_025_Pikachu;
import pokemon.Pokemon;
import utilities.startup;
import trainer.Trainer;
import move.Move;
import java.util.Timer;
import java.util.TimerTask;
import client.Client;

public class Start {
    public static void main(String[] args) {

        System.out.println("Program Start");
        
        // Initialize base data (Pokemon / Moves / Items)
        System.out.println("Loading Pokemon");
        ArrayList<Pokemon> pokedex = startup.initializePokedex();
        ArrayList<Move> movedex = startup.initializeMovedex();
        
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
        
//        Timer timer = new Timer();
//        
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("DONE!");
//            }
//        };
//        
//        timer.schedule(task, 3000);
//        ash.displayPartyDetailed();

        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
//                new MainMenu(pokedex).setVisible(true);
                  new Client(pokedex, trainers).setVisible(true);
            }
        });
    }
}
