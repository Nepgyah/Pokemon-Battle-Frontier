package utilities;

import java.util.ArrayList;
import pokemon.Pokemon;
import pokemon.pokedex.*;
import move.Move;
import trainer.Trainer;
import move.movedex.*;

public class startup {
    
    public static ArrayList<Pokemon> initializePokedex()
    {
        ArrayList<Pokemon> pokedex = new ArrayList<>();
        
        pokedex.add(new P_001_Bulbasaur());
        pokedex.add(new P_025_Pikachu());
        
        return pokedex;
    }
    
    public static ArrayList<Move> initializeMovedex()
    {
        ArrayList<Move> movedex = new ArrayList<>();

        movedex.add(new M_033_Tackle());
        return movedex;
    }
    
    public static Trainer createAsh(ArrayList<Pokemon> pokedex, ArrayList<Move> movedex)
    {
        Trainer ash = new Trainer("Ash");

        ash.addToParty(pokedex.get(1).copy());
        for(Pokemon pokemon : ash.getParty())
        {
            pokemon.setLevel(20);
            pokemon.assignRandomMoves(movedex);
        }
        return ash;
    }
    
     public static Trainer createGary(ArrayList<Pokemon> pokedex, ArrayList<Move> movedex)
    {
        Trainer ash = new Trainer("Ash");

        ash.addToParty(pokedex.get(0).copy());
        for(Pokemon pokemon : ash.getParty())
        {
            pokemon.setLevel(20);
            pokemon.assignRandomMoves(movedex);
        }
        return ash;
    }
}
