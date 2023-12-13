package item.itemdex;

import item.Item;

public class SuperPotion extends Item {

    public SuperPotion() {
        super("Super Potion",
            "A spray-type medicine for treating wounds. It can be used to restore 50 HP to an injured Pokémon");
    }

    @Override
    public SuperPotion copy() {
        return new SuperPotion();
    }
    
}
