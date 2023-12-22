package item.itemdex;

import item.Item;
import item.modifiers.HealSetHP;
import item.modifiers.ItemHealsHP;

public class SuperPotion extends Item implements ItemHealsHP, HealSetHP {

    public SuperPotion() {
        super("Super Potion",
            "A spray-type medicine for treating wounds. It can be used to restore 50 HP to an injured Pokémon");
    }

    @Override
    public SuperPotion copy() {
        return new SuperPotion();
    }

    @Override
    public int getHPAmount() {
        return 50;
    }
    
}
