package trainer;

import java.util.ArrayList;
import java.util.Scanner;
import main.utility.initialize;
import move.Move;
import pokemon.Pokemon;

/*
 * Class used to organize methods to edit and view a specific trainer
 * and their pokemon party
 */
public class TrainerHandler {

	Trainer trainer;
	Pokemon pokemon;
	ArrayList<Move> movedex;
	ArrayList<String> trainerTitles = initialize.initializeTrainerTitles();
	ArrayList<String> trainerRegions = initialize.initializeTrainerRegions();
	Scanner kb;
	String newName;
	int choice;
	public TrainerHandler(Trainer trainer, ArrayList<Move> movedex, Scanner kb)
	{
		this.trainer = trainer;
		this.movedex = movedex;
		this.pokemon = null;
		this.kb = kb;
		this.choice = -1;
	}
	
	// Enter the trainer handler menu
	public void runEditor()
	{
		choice = -1;
		while(choice != 0)
		{
			displayMainOptions();
			choice = kb.nextInt();
			switch(choice)
			{
			case 0:
				// TODO: Prompt unsaved data
				return;
			case 1:
				trainer.displayPartyDetailed();
				choice = -1;
				break;
			case 2:
				editTrainerInformation();
				break;
			case 3:
				pokemon = selectPokemon();
				if(pokemon == null)
				{
					choice = -1;
					break;
				}
				editPokemon();
				choice = -1;
				break;
			case 4:
				TrainerUtilities.saveTrainer(trainer);
				break;
			}
		}
	}
	
	private void displayMainOptions()
	{
		System.out.println("\nMY TRAINER MENU - " + trainer.getName());
		System.out.println("----------------");
		System.out.println("0. Exit");
		System.out.println("1. View Party");
		System.out.println("2. Edit Trainer Information");
		System.out.println("3. Edit Party Pokemon");
		System.out.println("4. Save Trainer");
		System.out.print("Choice: ");
	}
	
	private void displayEditTrainerOptions()
	{
		System.out.println("\nWhat would you like to edit?");
		System.out.println("Name: " + trainer.getName());
		System.out.println("Title: " + trainer.getTitle());
		System.out.println("Hometown: " + trainer.getHometown());
		System.out.println("0. Back");
		System.out.println("1. Name");
		System.out.println("2. Title");
		System.out.println("3. Hometown/Region");
		System.out.print("Choice: ");
	}
	
	public void editTrainerInformation()
	{
		choice = -1;
		while(choice != 0)
		{
			displayEditTrainerOptions();
			choice = kb.nextInt();
			switch(choice)
			{
			case 0:
				choice = -1;
				return;
			// Name case
			case 1:
				System.out.println("Current name: " + trainer.getName());
				kb.nextLine();
				System.out.print("Enter new name: ");
				newName = kb.nextLine();
				trainer.setName(newName);
				break;
			// Title case
			case 2:
				editTrainerTitle();
				break;
				
			// Hometown case
			case 3:
				editTrainerRegion();
				break;
			}
		}
	}
	
	private void displayEditPokemonOptions()
	{
		System.out.println("\nMY POKEMON MENU");
		System.out.println("----------------");
		System.out.println("Pokemon Selected: " + pokemon.getName() + " | " + pokemon.getNickname());
		System.out.println("What would you like to edit?");
		System.out.println("0. Back");
		System.out.println("1. Give Nickname");
		System.out.println("2. Edit Moveset");
		System.out.println("3. Give/Take Item");
		System.out.print("Choice: ");
	}
	
	private void editTrainerTitle()
	{
		int count = 0;
		System.out.println("\nAvailable titles");
		for(String title : trainerTitles)
		{
			count += 1;
			System.out.println(count + ". " + title);
		}
		System.out.print("Select a title: ");
		count = kb.nextInt();
		if(count < 0 || count > trainerTitles.size())
		{
			System.out.println("Choice cancelled");
			return;
		}
		else
		{
			trainer.setTitle(trainerTitles.get(count-1));
		}
	}
	
	private void editTrainerRegion()
	{
		int count = 0;
		System.out.println("\nRegions");
		for(String title : trainerRegions)
		{
			count += 1;
			System.out.println(count + ". " + title);
		}
		System.out.print("Select a region: ");
		count = kb.nextInt();
		if(count < 0 || count > trainerRegions.size())
		{
			System.out.println("Choice cancelled");
			return;
		}
		else
		{
			trainer.setHometown(trainerRegions.get(count-1));
		}
	}
	
	private void editPokemon() {
		while(choice != 0)
		{
			displayEditPokemonOptions();
			choice = kb.nextInt();
			switch(choice)
			{
			case 0:
				choice = -1;
				return;
			case 1:
				kb.nextLine();
				System.out.print("Enter new nickname: ");
				pokemon.giveNickname(kb.nextLine());
				System.out.println("Saved!");
				break;
			case 2:
				changeMoveset();
				break;
			case 3:
				//TODO: Add code to give or take item
				System.out.println("Give/Take Item Selected");
				break;
			}
		}
	}
	
	// TODO: Test this
	private void changeMoveset()
	{
		Move move_to_replace;
		Move move_to_insert;
		int moveset_slot;
		
		pokemon.displayCurrentMoves();
		System.out.print("Select a move to replace (0 to cancel): ");
		
		moveset_slot = kb.nextInt();
		if(moveset_slot <= 0 || moveset_slot > 4) return;
		move_to_replace = pokemon.getMoveset()[moveset_slot-1];
		System.out.println("Selected move: " + move_to_replace.getName());
		
		move_to_insert = selectFromMovedex(movedex);
		
		System.out.print("Replace " + move_to_replace.getName() + " with " + move_to_insert.getName() + "? (1 to confirm) ");
		choice = kb.nextInt();
		if(choice == 1)
		{
			pokemon.learnMove(move_to_insert.copy(), moveset_slot-1);
			System.out.println("1... 2... 3... Poof!");
			System.out.println(pokemon.getNickname() + " forgot " + move_to_replace.getName() + "... and learned " + move_to_insert.getName() + "!");
		}
	}
	
	private Move selectFromMovedex(ArrayList<Move> movedex)
	{
		int selected_tm = 0;
		Move selected_move = null;
		pokemon.displayLearnableMoves(movedex);
		selected_tm = kb.nextInt();
	
		for(Move move : pokemon.getMoveset())
		{
			if(pokemon.getLearnable_moves().get(selected_tm-1) == move.getTm_no())
			{
				System.out.println(pokemon.getNickname() + " already knows this move.");
				return null;
			}
		}
	
		for(Move move : movedex)
		{
			if(move.getTm_no() == pokemon.getLearnable_moves().get(selected_tm-1))
			{
				selected_move = move;
			}
		}
		return selected_move;
	}

	private Pokemon selectPokemon()
	{
		Pokemon selected = null;
		for(int i = 1; i < trainer.getParty().size() + 1; i++)
		{
			System.out.println(i + ". " + trainer.getParty().get(i-1).getNickname());
		}
		System.out.print("Select Pokemon to edit (0 to cancel): ");
		choice = kb.nextInt();
		if (choice == 0) return null;
		selected = trainer.getParty().get(choice-1);
		return selected;
	}
}
