package pokemon.pokedex;

import java.util.ArrayList;
import java.util.Arrays;

import pokemon.Pokemon;
import types.Electric;
import types.Normal;

public class P_023_Ekans extends Pokemon implements Electric, Normal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public P_023_Ekans() {
		super();
		this.name = this.nickname = "Ekans";
		this.pokedex_number = 23;
                this.height = 2;
                this.weight = 6.9;
		this.base_max_hp = 30;
		this.base_attack = 60;
		this.base_defense = 44;
		this.base_special_attack = 40;
		this.base_special_defense = 54;
		this.base_speed = 55;
		
		this.learnable_moves = new ArrayList<Integer>(Arrays.asList(0, 39, 85, 392));

	}
	
	public P_023_Ekans copy()
	{
		P_023_Ekans copy = new P_023_Ekans();
		copy.setIV_Values();
		return copy;
	}
}
