package pokemon.pokedex;

import java.util.ArrayList;
import java.util.Arrays;

import pokemon.Pokemon;
import types.Grass;
import types.Poison;

public class P_000_Test extends Pokemon implements Poison{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public P_000_Test()
	{
		super();
		this.name = this.nickname = "Missing No";
		this.pokedex_number = 0;
		this.height = .7;
                this.weight = 6.9;
		this.base_max_hp = 40;
		this.base_attack = 49;
		this.base_defense = 49;
		this.base_special_attack = 65;
		this.base_special_defense = 65;
		this.base_speed = 45;
		
		this.learnable_moves = new ArrayList<Integer>(Arrays.asList(0,1, 19, 33));
	}
	
	public P_000_Test copy() {
            P_000_Test copy = new P_000_Test();
            copy.setIV_Values();
            return copy;
	}

}
