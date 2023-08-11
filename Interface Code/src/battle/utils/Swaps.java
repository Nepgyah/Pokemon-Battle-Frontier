package battle.utils;

import java.util.Collections;
import trainer.Trainer;

public class Swaps {
    public static void swapPokemon(Trainer trainer, int partyPosition) {
        Collections.swap(trainer.getParty(), 0, partyPosition);
    }
}
