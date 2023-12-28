package pokemon.pokedex;

import java.util.ArrayList;
import java.util.Arrays;

import pokemon.Pokemon;
import types.Flying;
import types.Normal;

public class P_019_Rattata extends Pokemon implements Normal {

    private static final long serialVersionUID = 1L;

    public P_019_Rattata()
    {
        super();
        this.name = this.nickname = "Rattata";
        this.pokedex_number = 19;

        this.base_max_hp = 30;
        this.base_attack = 56;
        this.base_defense = 35;
        this.base_special_attack = 25;
        this.base_special_defense = 35;
        this.base_speed = 72;

        this.learnable_moves = new ArrayList<Integer>(Arrays.asList(1, 19, 33,39));
    }
    
    @Override
    public Pokemon copy() {
        P_019_Rattata copy = new P_019_Rattata();
        copy.setIV_Values();
        return copy;
    }

}