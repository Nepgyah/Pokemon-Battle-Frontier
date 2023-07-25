package main.utility;

import java.io.File;
import java.io.IOException;

public class startup {

	public static void checkTrainerFile()
	{
		System.out.println("Checking files");
		try
		{
			File targetFile = new File("Trainers\\names.txt");
			if (targetFile.createNewFile()) System.out.println("Warning! Trainer names 'names.txt' not located, creating new file");
			else System.out.println("names.txt found");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
