package assignment2;

import java.util.ArrayList;

public class Decision {
	String input; 
	char[] inputToArray;
	private int numberOfWhitePegs;
	private int numberOfBlackPegs;
	String checking = null;
	ArrayList<String> remainingToCheck = new ArrayList<String>();
	ArrayList<String> checkDuplicates = new ArrayList<String>();
	String secretCode = Game.secretCode; 
	
	public Decision(String input) {
		this.input = input;
	}
	
	public boolean inputHistory() {
		return(input.equals("HISTORY") ? true : false);
	}
	
	//checks if guess is valid by checking to see if 
	//a, it wasn't asking for history
	//b, length is == pegNumbers
	//c, colors are all valid
	public boolean validGuess() {
		if(inputHistory()) {
			return true;
		}
		
		if(input.length() != GameConfiguration.pegNumber) {
			return false;
		}
		inputToArray = input.toCharArray();
		for(String s : GameConfiguration.colors) {
			checking += s;
		}
		
		for(int i = 0; i<GameConfiguration.pegNumber; i++) {
			if(checking.indexOf(inputToArray[i]) == -1){
				return false;
			}
		}
		return true;
	}
	
	public void findNumberOfPegs() {
		setNumberOfBlackPegs();
		setNumberOfWhitePegs();
		printCurrentTurn();
		System.out.println();
	}

	//finds black pegs by comparing exact indexes to see if charAt matches secret code index
	//whatever is not found is pushed into a remainingToCheck Arraylist
	//whatever is found is changed to '0' in order to not deal with repetition with white pegs
	public void setNumberOfBlackPegs() {
		for(int i = 0; i<inputToArray.length; i++) {
			if(inputToArray[i] == secretCode.charAt(i)) {
				numberOfBlackPegs++;
				StringBuilder st = new StringBuilder(secretCode);
				st.setCharAt(i, '0');
				secretCode = st.toString();
				
			}else {
				remainingToCheck.add(Character.toString(inputToArray[i]));
			}
		}
	}
	
	//white pegs checks through the remaining to check array list and also make sure there are no duplicates
	//checks for no duplicates bc pushing to a second arraylist checkDuplicates
	public void setNumberOfWhitePegs() {
		for(int i = 0; i<remainingToCheck.size(); i++) {
			if((secretCode.indexOf(remainingToCheck.get(i)) != -1) && (checkDuplicates.contains(remainingToCheck.get(i)) == false)){
				numberOfWhitePegs++;
				checkDuplicates.add(remainingToCheck.get(i));
			}
		}
	}
	
	//each Decision can print itself
	public void printCurrentTurn(){
		System.out.println(input + " -> " + numberOfBlackPegs + "b_" + numberOfWhitePegs + "w");
		
	}
	
	//getters
	public int getNumberOfBlackPegs() {
		return numberOfBlackPegs;
	}
	public int getNumberOfWhitePegs() {
		return numberOfWhitePegs;
	}
	
	

	
}
