package assignment2;

import java.util.Scanner;

public class Driver {
	
	public static void main(String[] args) {
		//default showCode will be false
		//starts up the ONE scanner for the whole project
		//start game instance
		boolean showCode = false;
		System.out.println("Welcome to Mastermind.");
		Scanner userInput = new Scanner(System.in);
		if(args.length > 0) {
			if(args[0].equals("1")) {
				showCode = true;
			}
		}
		Game game = new Game(showCode, userInput); 
	}

	
}
