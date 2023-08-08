package battle;

import java.util.Collections;
import java.util.Scanner;

import move.Move;
//import move.movedex.M_165_Struggle;
import pokemon.Pokemon;
import trainer.Trainer;

// Collection for methods to enhance pokemon battles
public class BattleUtilities {

    public static void swapFaintedPokemon(Trainer trainer, Scanner kb)
    {
        int input = 0;
        boolean validSwap = false;
        while (validSwap == false)
        {
            trainer.displayPartyBasic();
            System.out.print("Select your next pokemon: ");
            input= kb.nextInt();
            if(input <= 1 || input > trainer.getParty().size())
            {
                    System.out.print("Select a pokemon: ");
            }
            else if(trainer.getParty().get(input-1).isFainted() == true) 
            {
                    System.out.println(trainer.getParty().get(input-1).getName() + " fainted! It can no longer battle!");
            }
            else validSwap = true;
        }
        Collections.swap(trainer.getParty(), 0, input-1);
        System.out.println(trainer.getName() + " sent out " + trainer.getParty().get(0).getName());
    }
    
    // NEW
    public static void swapPokemon(Trainer trainer, int partyPosition) {
        Collections.swap(trainer.getParty(), 0, partyPosition);
    }
    // OLD
    public static String swapPokemon(Trainer trainer, Scanner kb)
    {
        int input = -1;
        trainer.displayPartyBasic();
        while(input != 0)
        {
            System.out.print("(0 to back) Who would you like to switch out for? ");
            input = kb.nextInt();
            if (input <= 1 || input > trainer.getParty().size())
            {
                    System.out.println("Invalid input");
            }
            else if (trainer.getParty().get(input-1).isFainted() == true)
            {
                    System.out.println(trainer.getParty().get(input-1).getName() + " fainted! It can no longer battle!");
            }
            else break;
        }
        if(input == 0) return null;
        String previous = trainer.getParty().get(0).getName();
        Collections.swap(trainer.getParty(), 0, input-1);
        return previous;
    }

    public static Move selectMove(Pokemon pokemon, Scanner kb)
    {
        Move selectedMove = null;
        for(Move move : pokemon.getMoveset())
        {
//            if(move.getCurrent_pp() != 0) break;
//            else return new M_165_Struggle();
        }
        pokemon.displayCurrentMoves();
        System.out.println("0. Back");
        System.out.print("Your choice: ");
        int input = kb.nextInt();
        if (input <= 0 || input > pokemon.getLearnable_moves().size()) return null;
        else
        {
            selectedMove = pokemon.getMoveset()[input-1];
            //if(show_console == true) System.out.println("Move selected: " + selectedMove.getName());
        }
        return selectedMove;
    }
}
