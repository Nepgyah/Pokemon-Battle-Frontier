package pokemon.pokedex;

import java.util.ArrayList;
import java.util.Arrays;

import pokemon.Pokemon;
import types.Normal;

public class P_143_Snorlax extends Pokemon implements Normal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public P_143_Snorlax() {
		super();
		this.name = this.nickname = "Snorlax";
		this.pokedex_number = 143;
                
		this.base_max_hp = 160;
		this.base_attack = 110;
		this.base_defense = 65;
		this.base_special_attack = 65;
		this.base_special_defense = 110;
		this.base_speed = 35;
		
		this.learnable_moves = new ArrayList<Integer>(Arrays.asList(0, 1, 14, 39));

	}
	
	public P_143_Snorlax copy()
	{
		P_143_Snorlax copy = new P_143_Snorlax();
		copy.setIV_Values();
		return copy;
	}
}
