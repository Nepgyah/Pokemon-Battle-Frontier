package main;
import java.util.ArrayList;
import java.util.Scanner;

import battle.*;
import main.utility.initialize;
import main.utility.startup;
import move.Move;
import pokemon.*;
import trainer.*;

public class Main {

	public static void main(String[] args) {
		startup.checkTrainerFile();
		
		// Initialize
		ArrayList<Pokemon> pokedex = initialize.initializePokedex(); 
		ArrayList<Move> movedex = initialize.initializeMovedex();		
	
		ArrayList<String> trainerNames = new ArrayList<>();	
		Scanner keyboardInput = new Scanner(System.in);							
		Trainer left_trainer, right_trainer, selectedTrainer;				
		
		Trainer Ash = initialize.createAsh(pokedex, movedex);
		Trainer Ritchie = initialize.createRitchie(pokedex, movedex);
		
		int userChoice = 0;
		boolean endProgramFlag = false;
		
		while(endProgramFlag == false)
		{
			trainerNames = TrainerUtilities.updateTrainerNames();
			MainMenu.displayMainMenuOptions();
			userChoice = keyboardInput.nextInt();
			switch(userChoice)
			{
			case 0:
				endProgramFlag = true;
				break;
			case 1:
				new SingleBattle(Ash, Ritchie, keyboardInput, false).Battle();
				/*
				System.out.println("Select trainer 1.");
				left_trainer = TrainerUtilities.getTrainer(savedTrainerNames, kb);
				// User cancels battle
				if (left_trainer == null) break;
				
				System.out.println("Select trainer 2.");
				right_trainer = TrainerUtilities.getTrainer(savedTrainerNames, kb);
				// User cancels battle
				if (left_trainer == null) break;
				
				// TODO: Add code for selecting battle level
				
				// Setup and start battle
				boolean console = false;
				System.out.print("Enter 1 to enable console information: ");
				userChoice = kb.nextInt();
				if (userChoice == 1) console = true;
				new Single_Battle(left_trainer, right_trainer, kb, console).Battle();
				*/
				
				/* NOTE - healing after battle is not required because
				 * the results are not saved to the files. Starting a
				 * new battle simply takes the base save data as reference
				 */
				break;
				
			case 2:
				if (trainerNames.isEmpty())
				{
					System.out.println("No Trainer Data");
					break;
				}
				selectedTrainer = TrainerUtilities.getTrainer(trainerNames, keyboardInput);
				if (selectedTrainer == null)
				{
					break;
				}
				new TrainerHandler(selectedTrainer, movedex, keyboardInput).runEditor();
				break;

			case 3:
				TrainerUtilities.createTrainer(keyboardInput, pokedex, movedex);
				break;
			
			case 4:
				MainMenu.displayPokedex(pokedex);
				break;
				
			case 5:
				MainMenu.displayMovedex(movedex);
				break;
			}
		}
		keyboardInput.close();
		System.out.println("End of program");
		System.exit(0);
	}

}
