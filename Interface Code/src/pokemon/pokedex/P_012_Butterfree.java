package pokemon.pokedex;

import java.util.ArrayList;
import java.util.Arrays;

import pokemon.Pokemon;
import types.Bug;
import types.Normal;

public class P_012_Butterfree extends Pokemon implements Bug, Normal{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public P_012_Butterfree() {
		super();
		this.name = this.nickname = "Butterfree";
		this.pokedex_number = 12;
                
		this.base_max_hp = 60;
		this.base_attack = 45;
		this.base_defense = 50;
		this.base_special_attack = 80;
		this.base_special_defense = 80;
		this.base_speed = 70;
		
		this.learnable_moves = new ArrayList<Integer>(Arrays.asList(1, 39, 85, 86));

	}
	
	public P_012_Butterfree copy()
	{
		P_012_Butterfree copy = new P_012_Butterfree();
		copy.setIV_Values();
		return copy;
	}
}
