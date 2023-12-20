package item.itemdex;

import item.Item;
import item.curing.CuresPoison;
import item.modifiers.TriggeredByStatus;

public class RawstBerry extends Item implements TriggeredByStatus, CuresPoison{

    public RawstBerry() {
        super("Rawst Berry", "A hold item that heals burn in battle.");
    }

    @Override
    public Item copy() {
        return new RawstBerry();
    }
    
}
