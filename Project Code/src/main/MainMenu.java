package main;

import java.util.ArrayList;

import move.Move;
import pokemon.Pokemon;

public class MainMenu {

	public static void displayMainMenuOptions()
	{
		System.out.println("MAIN MENU");
		System.out.println("---------");
		System.out.println("1. Battle");
		System.out.println("2. View/Edit Trainer");
		System.out.println("3. Create A Trainer");
		System.out.println("4. View Pokedex");
		System.out.println("5. View Movedex");
		System.out.println("0. Exit");
		System.out.print("Input: ");
	}
	
	public static void displayBattleTypes()
	{
		System.out.println("\nSelect Battle Type");
		System.out.println("1. Single Battle");
		System.out.println("2. Double Battle(Not available)");
		System.out.println("0. Back");
		System.out.print("Choice: ");
	}
	
	public static void displayMovedex(ArrayList<Move> movedex)
	{
		System.out.println("\nMovedex");
		System.out.println("-------");
		System.out.println("Tm #\t Name");
		for(Move move : movedex)
		{
			move.displayMovedexInformation();
		}
		System.out.println();
	}
	
	public static void displayPokedex(ArrayList<Pokemon> pokedex)
	{
		System.out.println("\nPokedex");
		System.out.println("-------");
		System.out.println("Dex #\t Name \t\t Type(s)");
		for (Pokemon pokemon : pokedex)
		{
			pokemon.displayPokedexInformation();
		}
		System.out.println();
	}
}
