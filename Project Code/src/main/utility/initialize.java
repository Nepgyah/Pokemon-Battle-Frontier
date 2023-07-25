package main.utility;

import java.util.ArrayList;
import java.util.Arrays;

import move.Move;
import move.movedex.*;
import pokemon.Pokemon;
import pokemon.pokedex.*;
import trainer.Trainer;

public class initialize {
	
	public static Trainer createAsh(ArrayList<Pokemon> pokedex, ArrayList<Move> movedex)
	{
		Trainer ash = new Trainer("Ash");
	
		ash.addToParty(pokedex.get(0).copy());
		ash.addToParty(pokedex.get(2).copy());
		ash.addToParty(pokedex.get(1).copy());
		ash.addToParty(pokedex.get(4).copy());
		for(Pokemon pokemon : ash.getParty())
		{
			pokemon.setLevel(20);
			pokemon.assignRandomMoves(movedex);
		}
		return ash;
	}
	
	public static Trainer createRitchie(ArrayList<Pokemon> pokedex, ArrayList<Move> movedex)
	{
		Trainer Ritchie = new Trainer("Ritchie");
		Ritchie.addToParty(pokedex.get(4).copy());
		Ritchie.getParty().get(0).assignRandomMoves(movedex);
		Ritchie.getParty().get(0).setNickname("Sparky");
		Ritchie.getParty().get(0).setLevel(20);
		return Ritchie;
	}
	
	public static ArrayList<String> initializeTrainerTitles()
	{
		return new ArrayList<String>(Arrays.asList("Ace Trainer", "Fisherman", "Actor", "Actress", "Biker", "Office Worker"));
	}
	
	public static ArrayList<String> initializeTrainerRegions()
	{
		return new ArrayList<String>(Arrays.asList("Kanto", "Jhoto", "Hoenn", "Sinnoh", "Unova", "Kalos", "Aloha", "Galar"));
	}
	
	public static ArrayList<Pokemon> initializePokedex()
	{
		ArrayList<Pokemon> pokedex = new ArrayList<>();
		pokedex.add(new P_001_Bulbasaur());
		
		pokedex.add(new P_004_Charmander());
		
		pokedex.add(new P_007_Squirtle());
		
		pokedex.add(new P_016_Pidgey());
		
		pokedex.add(new P_025_Pikachu());
		return pokedex;
	}
	
	public static ArrayList<Move> initializeMovedex()
	{
		ArrayList<Move> movedex = new ArrayList<>();
		movedex.add(new M_001_Pound());
		 
		movedex.add(new M_019_Fly());
		
		movedex.add(new M_033_Tackle());
		
		movedex.add(new M_039_TailWhip());
		
		movedex.add(new M_055_WaterGun());
		
		movedex.add(new M_061_BubbleBeam());
		
		movedex.add(new M_063_HyperBeam());
		
		movedex.add(new M_071_Absorb());
		
		movedex.add(new M_076_SolarBeam());
		
		movedex.add(new M_082_DragonRage());
		
		movedex.add(new M_085_ThunderBolt());
		
		movedex.add(new M_087_Thunder());
		
		movedex.add(new M_091_Dig());

		movedex.add(new M_105_Recover());
		return movedex;
	}
}
