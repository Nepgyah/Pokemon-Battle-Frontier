package battle;

import java.util.Collections;
import java.util.Scanner;

import move.Move;
import move.modifiers.*;
import pokemon.Pokemon;
import trainer.Trainer;

// Collection of all logic and variables that are necessary for single battles
public class SingleBattle {
	
    private Trainer leftTrainer;			
    private Trainer right_trainer;			

    private Pokemon leftPokemon;	
    private Pokemon rightPokemon;	

    private Move leftSelectedMove;			
    private Move rightSelectedMove;		

    private String leftPrevName;	
    private String rightPrevName;

    boolean leftMadeMove = false;
    boolean rightMadeMove = false;

    boolean leftTurnForfeit = false;
    boolean rightTurnForfeit = false;

    private boolean leftSwapMade = false;
    private boolean rightSwapMade = false;

    private boolean leftTrainerWins = false;
    private boolean rightTrainerWins = false;

    private Scanner keyboardInput;		
    private int input;		

    private boolean showConsole;

    public SingleBattle(Trainer leftTrainer, Trainer right_trainer, Scanner scanner, boolean showConsole)
    {
        super();
        this.leftTrainer = leftTrainer;
        this.right_trainer = right_trainer;

        this.leftPokemon = leftTrainer.getParty().get(0);
        this.rightPokemon = right_trainer.getParty().get(0);

        this.showConsole = showConsole;
        this.keyboardInput = scanner;
    }

    public void Battle()
    {
        System.out.println(leftTrainer.getName() + " sent out " + leftPokemon.getNickname()+ "!");
        System.out.println(right_trainer.getName() + " sent out " + rightPokemon.getNickname() + "!");

        int turn_count = 0;

        while (leftTrainerWins == false & rightTrainerWins == false)
        {
                turn_count++;
                if (showConsole == true) System.out.println("Turn: " + turn_count);
                turn();
        }

        if (leftTrainerWins == true) System.out.println(right_trainer.getName() + " is out of usable Pokemon! " + leftTrainer.getName() + " wins!");
        else System.out.println(leftTrainer.getName() + " is out of usable Pokemon! " + right_trainer.getName() + " wins!");

        System.out.println("End of battle");
        return;
    }

    public void turn()
    {
        // TODO: Redo this part maybe?
        if (leftPokemon.isRecharging() == true || leftPokemon.isInTwoTurn() == true)
        {
            leftTurnForfeit = true;
            leftMadeMove = true;
        }

        while(leftMadeMove == false)
        {
            if (showConsole == true) BattleMechanics.displayBattleStatsToConsole(leftPokemon);
            else displayHP();
            System.out.println("\n1. Fight \n2. Pokemon \n3. Bag");
            System.out.print("What will " + leftPokemon.getNickname() + " do? ");
            input = keyboardInput.nextInt();
            switch(input)
            {
            case 1:
                leftSelectedMove = BattleUtilities.selectMove(leftPokemon, keyboardInput);
                if(leftSelectedMove != null)
                {
                    leftMadeMove = true;
                }
                break;
            case 2:
                leftPrevName = BattleUtilities.swapPokemon(leftTrainer, keyboardInput);
                if(leftPrevName == null) break;
                else
                {
                    leftPokemon = leftTrainer.getParty().get(0);
                    leftSwapMade = leftMadeMove = true;
                }
                break;
            case 3:
                System.out.println("Bag selected");
                break;
            }
        }

        if (rightPokemon.isRecharging() == true || rightPokemon.isInTwoTurn() == true)
        {
            rightTurnForfeit = true;
            rightMadeMove = true;
        }

        while(rightMadeMove == false)
        {
            if (showConsole == true) BattleMechanics.displayBattleStatsToConsole(rightPokemon);
            else displayHP();
            System.out.println("\n1. Fight \n2. Pokemon \n3. Bag");
            System.out.print("What will " + rightPokemon.getNickname() + " do? ");
            input = keyboardInput.nextInt();
            switch(input)
            {
            case 1:
                rightSelectedMove = BattleUtilities.selectMove(rightPokemon, keyboardInput);
                if(rightSelectedMove != null)
                {
                    rightMadeMove = true;
                }
                break;
            case 2:
                rightPrevName = BattleUtilities.swapPokemon(right_trainer, keyboardInput);
                rightPokemon = right_trainer.getParty().get(0);
                rightSwapMade = rightMadeMove = true;
                break;
            case 3:
                System.out.println("Bag selected");
                break;
            }
        }

        if (showConsole == true)
        {
            System.out.println("LEFT TRAINER FLAGS");
            System.out.println("leftSwapMade: " + leftSwapMade);
            if (leftSelectedMove == null) System.out.println("leftSelectedMove: null");
            else System.out.println("leftSelectedMove: Move ->" + leftSelectedMove.getName());

            System.out.println("\nRIGHT TRAINER FLAGS");
            System.out.println("rightSwapMade: " + rightSwapMade);
            if (rightSelectedMove == null) System.out.println("rightSelectedMove: null");
            else System.out.println("rightSelectedMove: Move ->" + rightSelectedMove.getName());
        }

        if (leftSwapMade == true)
        {
            System.out.println(leftPrevName + " get back! Go " + leftPokemon.getName() + "!");
            leftSwapMade = false;
        }
        if (rightSwapMade == true)
        {
            System.out.println(rightPrevName + " get back! Go " + rightPokemon.getName() + "!");
            rightSwapMade = false;
        }

        // TODO: Add item use mechanic here
        // TODO: Add check for priority moves
        if (leftPokemon.isRecharging())
        {
            System.out.println(leftPokemon.getName() + " is recharging! It can't move!");
            leftPokemon.setRecharging(false);
        }
        if (rightPokemon.isRecharging())
        {
            System.out.println(rightPokemon.getName() + " is recharging! It can't move!");
            rightPokemon.setRecharging(false);
        }

        if(leftPokemon.getBattle_speed() < rightPokemon.getBattle_speed())
        {
            if(rightSelectedMove != null )
            {
                if(rightSelectedMove instanceof TwoTurn && rightPokemon.isInTwoTurn() == false)
                {
                    System.out.println(rightPokemon.getName() + ((TwoTurn)rightSelectedMove).getTurnDescription());
                    rightPokemon.setInTwoTurn(true);
                }
                else 
                {
                    usePokemonMove(rightPokemon, leftPokemon, rightSelectedMove, leftSelectedMove);
                    rightSelectedMove = null;
                    if(rightPokemon.isInTwoTurn() == true) rightPokemon.setInTwoTurn(false);
                }
            }
            if(leftPokemon.isFainted() == false)
            {
                if(leftSelectedMove != null ) 
                {
                    if(leftSelectedMove instanceof TwoTurn && leftPokemon.isInTwoTurn() == false)
                    {
                        System.out.println(leftPokemon.getName() + ((TwoTurn)leftSelectedMove).getTurnDescription());
                        leftPokemon.setInTwoTurn(true);
                    }
                    else
                    {
                        usePokemonMove(leftPokemon, rightPokemon, leftSelectedMove, rightSelectedMove);
                        leftSelectedMove = null;
                        if(leftPokemon.isInTwoTurn() == true) leftPokemon.setInTwoTurn(false);
                    }
                }
            }
        }

        if(leftPokemon.getBattle_speed() > rightPokemon.getBattle_speed())
        {
            if(leftSelectedMove != null )
            {
                if(leftSelectedMove instanceof TwoTurn && leftPokemon.isInTwoTurn() == false)
                {
                    System.out.println(leftPokemon.getName() + ((TwoTurn)leftSelectedMove).getTurnDescription());
                    leftPokemon.setInTwoTurn(true);
                }
                else 
                {
                    usePokemonMove(leftPokemon, rightPokemon, leftSelectedMove, rightSelectedMove);
                    leftSelectedMove = null;
                    if(leftPokemon.isInTwoTurn() == true) leftPokemon.setInTwoTurn(false);
                }
            }

            if(rightPokemon.isFainted() == false)
            {
                if(rightSelectedMove != null ) 
                {
                    if(rightSelectedMove instanceof TwoTurn && rightPokemon.isInTwoTurn() == false)
                    {
                        System.out.println(rightPokemon.getName() + ((TwoTurn)rightSelectedMove).getTurnDescription());
                        rightPokemon.setInTwoTurn(true);
                    }
                    else
                    {
                        usePokemonMove(rightPokemon, leftPokemon, rightSelectedMove, leftSelectedMove);
                        rightSelectedMove = null;
                        if(rightPokemon.isInTwoTurn() == true) rightPokemon.setInTwoTurn(false);
                    }
                }
            }
        }

        postTurnCheck();
        leftMadeMove = rightMadeMove = false;
    }

    private void postTurnCheck()
    {
        BattleMechanics.postMoveStatusEffects(rightPokemon);
        BattleMechanics.postMoveStatusEffects(leftPokemon);
        int faint_count = 0;

        if (leftPokemon.isFainted() == true)
        {
            System.out.println(leftPokemon.getName() + " fainted!");
            leftPokemon.setFainted(true);

            for(Pokemon pokemon : leftTrainer.getParty())
            {
                if(pokemon.isFainted() == true) faint_count++;
            }
            if (faint_count == leftTrainer.getParty().size()) rightTrainerWins = true;
            else
            {
                System.out.println("SWAPPING FAINTED POKEMON");
                BattleUtilities.swapFaintedPokemon(leftTrainer, keyboardInput);
                leftPokemon = leftTrainer.getParty().get(0);
            }
        }
        faint_count = 0;
        if (rightPokemon.isFainted() == true)
        {
            System.out.println(rightPokemon.getName() + " fainted!");
            rightPokemon.setFainted(true);
            for(Pokemon pokemon : right_trainer.getParty())
            {
                if(pokemon.isFainted() == true) faint_count++;
            }
            if (faint_count == right_trainer.getParty().size()) leftTrainerWins = true;
            else
            {
                right_trainer.displayPartyBasic();
                System.out.print("Select your next pokemon: ");
                input = keyboardInput.nextInt();
                while(input <= 1 || input > right_trainer.getParty().size())
                {
                    System.out.print("Select a pokemon: ");
                    input = keyboardInput.nextInt();
                }
                Collections.swap(right_trainer.getParty(), 0, input-1);
                rightPokemon = right_trainer.getParty().get(0);
                System.out.println(right_trainer.getName() + " sent out " + rightPokemon.getName());
            }
        }
    }

    private void usePokemonMove(Pokemon user, Pokemon target, Move userMove, Move targetMove)
    {
        if (userMove instanceof TargetSelf) BattleMechanics.useMove(user, user, userMove, targetMove);
        else BattleMechanics.useMove(user, target, userMove, targetMove);
    }

    private void displayHP()
    {
        System.out.println();
        if(leftPokemon.getBattle_status() != null)
        {
            System.out.println("HP: " + leftPokemon.getCurrent_hp() + " / " + leftPokemon.getCurrent_max_hp() + "\t" + leftPokemon.getName() + "\t" + leftPokemon.getBattle_status());
        }
        else
        {
            System.out.println("HP: " + leftPokemon.getCurrent_hp() + " / " + leftPokemon.getCurrent_max_hp() + "\t" + leftPokemon.getName());
        }
        if(rightPokemon.getBattle_status() != null)
        {
            System.out.println("HP: " + rightPokemon.getCurrent_hp() + " / " + rightPokemon.getCurrent_max_hp() + "\t" + rightPokemon.getName() + "\t" + rightPokemon.getBattle_status());
        }
        else
        {
            System.out.println("HP: " + rightPokemon.getCurrent_hp() + " / " + rightPokemon.getCurrent_max_hp() + "\t" + rightPokemon.getName());
        }
    }
}

