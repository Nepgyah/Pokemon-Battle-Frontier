package gamemode.contest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import move.Move;
import pokemon.Pokemon;

public class ContestController {
    
    private Timer timer;
    private ArrayList<TimerTask> queue = new ArrayList<>();
    
    private Pokemon pokemonOne, pokemonTwo;
    private Move moveOne, moveTwo;
    
    private int judgeOneHearts, judgeTwoHearts, roundCount = 1;
    
    private Queue<Pokemon> pokeQ = new LinkedList<>();
    
    public ContestController() {
        super();
    }
    
    public void runTurn() {
        
 
    }
    
    private void useMove(Move move ) {
        // Use move
        
        // Add hearts
    }
}
