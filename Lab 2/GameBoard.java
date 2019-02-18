package assignment2;

import java.util.ArrayList;

public class GameBoard {
	private int numberOfWhitePegs;
	private int numberOfBlackPegs;
	private	ArrayList<Decision> allDecisions;
	private Decision currentDecision;
	
	//sets initial number of white/black pegs to 0, creates an arraylist for all decisions in game
	public GameBoard(){
		numberOfWhitePegs = 0;
		numberOfBlackPegs = 0;
		allDecisions = new ArrayList<Decision>();
	}
	
	// analyze current guess, see how many pegs, return, and then print that line
	public void printResultOfGuess() {
		currentDecision.findNumberOfPegs();
		if(hasWon(currentDecision.getNumberOfBlackPegs())) {
			Game.hasWon = true;
		}
	}
	
	//self explanatory, passed number of black pegs and checks to see if it equals assigned peg number
	public boolean hasWon(int pegs){
		if(pegs == GameConfiguration.pegNumber) {
			return true;
		}
		return false;
	}
	
	//iterates through Decision arraylist
	public void printHistory() {
		for(int i = 0; i<allDecisions.size(); i++) {
			allDecisions.get(i).printCurrentTurn();
		}
	}
	
	//pushes the given decision into the ArrayList and sets the current decision in the GameBoard
	public void addDecision(Decision s) {
		allDecisions.add(s);
		this.currentDecision = s;
		this.numberOfBlackPegs = s.getNumberOfBlackPegs();
		this.numberOfWhitePegs = s.getNumberOfWhitePegs();
	}
}
