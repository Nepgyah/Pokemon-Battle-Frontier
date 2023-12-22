package item.itemdex;

import item.Item;
import item.curing.CuresParalysis;
import item.modifiers.TriggeredByStatus;

public class CherryBerry extends Item implements TriggeredByStatus, CuresParalysis{

    public CherryBerry() {
        super("Cherry Berry", "A hold item that heals paralysis in battle.");
    }

    @Override
    public Item copy() {
        return new CherryBerry();
    }
    
}
