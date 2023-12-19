package item.itemdex;

import item.Item;
import item.modifiers.HealRatioHP;
import item.modifiers.HealsHP;
import item.modifiers.TriggeredByHP;

public class SitrusBerry extends Item implements HealsHP, HealRatioHP, TriggeredByHP{

    public SitrusBerry() {
        super("Sitrus Berry", "If held by a Pokémon, it heals the user's HP a little.");
    }

    public SitrusBerry copy() {
        return new SitrusBerry();
    }

    @Override
    public double getHPRatio() {
        return .25;
    }

    @Override
    public double GetHPActivePoint() {
        return .5;
    }
    
}
