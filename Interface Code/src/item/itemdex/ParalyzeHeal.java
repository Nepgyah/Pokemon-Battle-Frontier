package item.itemdex;

import item.Item;
import item.curing.CuresParalysis;
import item.modifiers.ItemHealStatus;

public class ParalyzeHeal extends Item implements ItemHealStatus, CuresParalysis {

    public ParalyzeHeal() {
        super("Paralyze Heal", 
            "Cures a Pokémon that is suffering from paralysis.");
    }

    @Override
    public ParalyzeHeal copy() {
        return new ParalyzeHeal();
    }
    
}
