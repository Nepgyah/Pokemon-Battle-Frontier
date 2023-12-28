package pokemon.pokedex;

import java.util.ArrayList;
import java.util.Arrays;

import pokemon.Pokemon;
import types.Flying;
import types.Normal;

public class P_016_Pidgey extends Pokemon implements Normal, Flying{

    private static final long serialVersionUID = 1L;

    public P_016_Pidgey()
    {
        super();
        this.name = this.nickname = "Pidgey";
        this.pokedex_number = 16;
        this.height = .3;
        this.weight = 1.8;
        this.base_max_hp = 40;
        this.base_attack = 45;
        this.base_defense = 40;
        this.base_special_attack = 35;
        this.base_special_defense = 35;
        this.base_speed = 56;

        this.learnable_moves = new ArrayList<Integer>(Arrays.asList(0,1, 19, 33));
    }
    
    @Override
    public Pokemon copy() {
        P_016_Pidgey copy = new P_016_Pidgey();
        copy.setIV_Values();
        return copy;
    }

}