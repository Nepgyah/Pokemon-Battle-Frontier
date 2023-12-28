package battle.modes.doublebattle;

import battle.gui.utils.pokemonDetails;
import battle.utils.BattleEvents;
import battle.utils.Swaps;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import move.Move;
import pokemon.Pokemon;
import people.Trainer;

public class DoubleBattleController {
    private Timer timer;
    private ArrayList<TimerTask> queue = new ArrayList<>();
    
    private Trainer leftTrainer, rightTrainer;
    private Pokemon leftPokemonOne, leftPokemonTwo, rightPokemonOne, rightPokemonTwo;
    private Move leftMoveOne, leftMoveTwo, rightMoveOne, rightMoveTwo;
    private pokemonDetails pokemonDetailPanel;
    
    private int leftNextPokemon, rightNextPokemon, 
            leftVisualHPOne, leftVisualHPTwo,
            rightVisualHPOne, rightVisualHPTwo;
    
    private String leftPrevName, rightPrevName,
            leftVisualStatusOne, leftVisualStatusTwo,
            rightVisualStatusOne, rightVisualStatusTwo;
    
    private boolean leftSwapOne, leftSwapTwo,
            rightSwapOne, rightSwapTwo,
            leftWins = false, rightWins = false,
            showConsole;
    
    JFrame battleFrame, clientFrame;
    JLabel [] leftLabelsOne, leftLabelsTwo, rightLabelsOne, rightLabelsTwo;
    JTextArea textArea;
    JProgressBar leftHPBarOne, leftHPBarTwo, rightHPBarOne, rightHPBarTwo;
    JButton fightButton, bagButton, pokemonButton;
    
    
    public DoubleBattleController() {
        super();
    }
    
    private void runTurn() {
        // 1st Phase swaps
        if (leftSwapOne == true) {
            leftPrevName = leftPokemonOne.getName();
            Swaps.swapPokemon(leftTrainer, leftNextPokemon);
            leftPokemonOne = leftTrainer.getParty().get(0);
            
            leftVisualHPOne = leftPokemonOne.getCurrent_hp();
            leftVisualStatusOne = leftPokemonOne.getBattle_status();
            
            BattleEvents.addSwapEvent(queue, textArea, leftPrevName, leftPokemonOne, leftVisualHPOne, leftVisualStatusOne, true, leftLabelsOne, leftHPBarOne);
            //leftPokemonPanelOne.setPokemonButtons();
            //leftMovePanel.setMoveButtons(leftPokemon.getMoveset());
        }
        
        if (leftSwapTwo == true) {
            leftPrevName = leftPokemonTwo.getName();
            Swaps.swapPokemon(leftTrainer, leftNextPokemon);
            leftPokemonTwo = leftTrainer.getParty().get(1);
            
            leftVisualHPTwo = leftPokemonTwo.getCurrent_hp();
            leftVisualStatusTwo = leftPokemonTwo.getBattle_status();
            
            BattleEvents.addSwapEvent(queue, textArea, leftPrevName, leftPokemonTwo, leftVisualHPTwo, leftVisualStatusTwo, true, leftLabelsTwo, leftHPBarTwo);
            //leftPokemonPanelOne.setPokemonButtons();
            //leftMovePanel.setMoveButtons(leftPokemon.getMoveset());
        }
    }
}
