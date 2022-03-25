package application;

import java.util.*;

/**
 * Game is java class used to play a reversi game.
 *
 */

public class Game {

    GameState currentGame;

    Game(GameState currentGame) {
        this.currentGame=currentGame;
    }

	/**
     * startReversi is the method used to start a reversi game between two human opponents.
     */

    void startReversi() {
    	System.out.println("--- Starting Reversi ---");
    	Scanner sc= new Scanner(System.in);
    	boolean endOfGame = false;
		ArrayList<Move> validPositions = this.currentGame.validPositions(this.currentGame.currentPlayer); //calculates the list of legal moves to play in
    	while(!endOfGame) {
    		System.out.println("\n   Current board");
    		this.currentGame.displayGrid();
    		if (this.currentGame.currentPlayer==Color.BLACK) System.out.println("x's turn");
    		else System.out.println("o's turn");
    		boolean inputIsValid = false; //false until the input is a valid move
    		while (!inputIsValid) {
    			System.out.println("Valid moves: " + validPositions.toString());
    			System.out.print("Enter the line you want to play in:   ");
    			int xInput = sc.nextInt();
    			System.out.print("Enter the row you want to play in:   ");
    			int yInput = sc.nextInt();
    			Move input = new Move(this.currentGame.currentPlayer, new Position(xInput, yInput), this.currentGame);
    			inputIsValid = input.isValid();
    			if(!inputIsValid) System.out.println("Invalid move, please try again"); //error message if the move inputed is not valid
    			else this.playIn(input); //if the move is valid, play it
    		}
    		if (this.currentGame.currentPlayer==Color.BLACK) this.currentGame.currentPlayer = Color.WHITE;	//changes
    		else this.currentGame.currentPlayer = Color.BLACK;									//player
    		validPositions = this.currentGame.validPositions(this.currentGame.currentPlayer); //calculates the new list of legal moves to play in
    		if (validPositions.size()==0) endOfGame = true; //if there is no valid move, then the game ends
    		
    	}
    	System.out.println("\n\n"); //skips lines
    	int[] scores = this.currentGame.scores(); //scores: {blacks; whites}
    	if (scores[0] > scores[1]) { //black wins
    		System.out.println("Black player won!");
    		System.out.println("Scores: x=" + scores[0] + " | o=" + scores[1]);	
    	}
    	else {
    		if (scores[0] < scores[1]) { //white wins
        		System.out.println("White player won!");
        		System.out.println("Scores: x=" + scores[0] + " | o=" + scores[1]);
        	}
    		else { //draw
    			System.out.println("Draw!");
    			System.out.println("Scores: x=" + scores[0] + " | o=" + scores[1]);
    		}
    	}
    	System.out.println("     End board");
    	this.currentGame.displayGrid();
    }

	/**
     * playIn is a method that plays a move in the current game grid.
     * @param move is the move to be played
     * @see flipDisks
     */

    void playIn(Move move) {
    	move.flipDisks();
    }

	/**
     * [TO DO] undo is a method that will cancel the last move
     */

    void undo() {

    }

	/**
     * [TO DO] redo is a method that will cancel undo
     */

    void redo() {

    }
}
