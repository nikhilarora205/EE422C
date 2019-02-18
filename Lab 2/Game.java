package assignment2;
import java.util.Scanner;

public class Game {
	Scanner userInput; 
	boolean showPattern; 
	static String secretCode; 
	int numberOfGuesses = GameConfiguration.guessNumber;
	static boolean hasWon;
	
	
	public Game(boolean showPattern, Scanner userInput) {
		//setting class instance variables from info passed through constructor
		//setting has won to false
		//calling runGame function
		this.userInput = userInput; 
		this.showPattern = showPattern; 
		hasWon = false;
		runGame(); 
		
		
	}
	
	public void runGame() {
		//initialization of game, do you want to play, create secrete code, if i want to show pattern
		//new gameboard
		System.out.println("Do you want to play a new game? (Y/N):");
		
		if(!(userInput.nextLine().equals("Y"))) {
			return;
		}else {
			secretCode = SecretCodeGenerator.getInstance().getNewSecretCode(); 
		}
		
		if(showPattern) {
			System.out.println("Secret code: " + secretCode); 
		}
		
		GameBoard gameboard = new GameBoard();
		System.out.println();
		
		//main game engine, if the number of guesses is > 0 and has won is false, keep going
		while(numberOfGuesses > 0 && hasWon == false) {
			printNumberOfGuesses();
			Decision decision = new Decision(userInput.nextLine());
			
			if(decision.inputHistory()) {
				gameboard.printHistory();
			}
			boolean isValidGuess = decision.validGuess(); 
			
			while(isValidGuess == false) {
				System.out.println("INVALID_GUESS");
				System.out.println();
				printNumberOfGuesses();
				decision = new Decision(userInput.nextLine());
				if(decision.inputHistory()) {
					gameboard.printHistory();
					break;
				}
				isValidGuess = decision.validGuess();
			}
			if(!decision.inputHistory()) {
				numberOfGuesses--;
				gameboard.addDecision(decision);
			gameboard.printResultOfGuess();
			}
		}
		if(numberOfGuesses == 0) {
			System.out.println("You lose! The pattern was " + secretCode);
			System.out.println();
			newGame();
		}
		if(hasWon) {
			System.out.println("You win!");
			System.out.println();
			newGame();
		}
		
	}
	public void newGame() {
		Game newGame = new Game(showPattern, userInput);
		
	}
	public void printNumberOfGuesses() {
		System.out.println("You have " + numberOfGuesses + " guess(es) left.");
		System.out.println("Enter guess:");
	}
	
	

}
