package item.itemdex;

import item.Item;
import item.curing.CuresPoison;
import item.modifiers.TriggeredByStatus;

public class PechaBerry extends Item implements TriggeredByStatus, CuresPoison{

    public PechaBerry() {
        super("Pecha Berry", "A hold item that heals poisoning in battle.");
    }

    @Override
    public Item copy() {
        return new PechaBerry();
    }
    
}
