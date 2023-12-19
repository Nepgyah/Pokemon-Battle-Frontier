package utilities;

import item.Item;
import item.itemdex.*;
import java.util.ArrayList;
import pokemon.Pokemon;
import pokemon.pokedex.*;
import move.Move;
import trainer.Trainer;
import move.movedex.*;

public class Startup {
    
    public static ArrayList<Pokemon> initializePokedex()
    {
        ArrayList<Pokemon> pokedex = new ArrayList<>();
        pokedex.add(new P_000_Test());
        
        pokedex.add(new P_001_Bulbasaur());
        
        pokedex.add(new P_004_Charmander());
        
        pokedex.add(new P_007_Squirtle());
        
        pokedex.add(new P_012_Butterfree());
        
        pokedex.add(new P_016_Pidgey());
        
        pokedex.add(new P_019_Rattata());
        
      
        
        pokedex.add(new P_025_Pikachu());
        
        pokedex.add(new P_143_Snorlax());
        return pokedex;
    }
    
    public static ArrayList<Move> initializeMovedex() {
        ArrayList<Move> movedex = new ArrayList<>();

        movedex.add(new M_000_Test());
        
        movedex.add(new M_001_Pound());
        
        movedex.add(new M_014_SwordsDance());
        
        movedex.add(new M_033_Tackle());
        
        movedex.add(new M_039_TailWhip());
        
        movedex.add(new M_055_WaterGun());
        
        movedex.add(new M_085_ThunderBolt());
        movedex.add(new M_086_ThunderWave());
        
        movedex.add(new M_392_AquaRing());
        
        return movedex;
    }
    
    public static ArrayList<Item> initializeItemdex() {
        ArrayList<Item> itemdex = new ArrayList<>();
        
        itemdex.add(new ParalyzeHeal());
        
        itemdex.add(new SitrusBerry());
        
        itemdex.add(new SuperPotion());
        
        return itemdex;
    }
    
    public static Trainer createAsh(ArrayList<Pokemon> pokedex, ArrayList<Move> movedex, ArrayList<Item> itemdex)
    {
        Trainer ash = new Trainer("Ash");

        ash.addToParty(pokedex.get(7).copy());
        ash.addToParty(pokedex.get(1).copy());
        ash.addToParty(pokedex.get(2).copy());
        ash.addToParty(pokedex.get(3).copy());
        ash.addToParty(pokedex.get(4).copy());
        ash.addToParty(pokedex.get(8).copy());
        
        for(Pokemon pokemon : ash.getParty())
        {
            pokemon.setLevel(20);
            pokemon.assignRandomMoves(movedex);
            pokemon.resetBattleStats();
        }
        
        ash.getParty().get(0).giveItem(itemdex.get(1).copy());
        
        ash.addToBag(itemdex.get(0).copy());
        ash.addToBag(itemdex.get(2).copy());
        
        return ash;
    }
    
     public static Trainer createGary(ArrayList<Pokemon> pokedex, ArrayList<Move> movedex, ArrayList<Item> itemdex)
    {
        Trainer gary = new Trainer("Gary");
        
        gary.addToParty(pokedex.get(5).copy());
        gary.addToParty(pokedex.get(0).copy());
        
        for(Pokemon pokemon : gary.getParty())
        {
            pokemon.setLevel(20);
            pokemon.assignRandomMoves(movedex);
            pokemon.resetBattleStats();
        }
        
        gary.addToBag(itemdex.get(2).copy());
        return gary;
    }
}
