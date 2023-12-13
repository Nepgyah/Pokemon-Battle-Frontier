package item.itemdex;

import item.Item;

public class SuperPotion extends Item {

    public SuperPotion() {
        super("Super Potion");
    }

    @Override
    public SuperPotion copy() {
        return new SuperPotion();
    }
    
}
