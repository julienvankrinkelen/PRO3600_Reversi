package application;

import java.util.*;

public class Game {

    GameState currentGame;

    Game(GameState currentGame) {
        this.currentGame=currentGame;
    }

    void startReversi() {
    	System.out.println("--- Starting Reversi ---");
    	Scanner sc= new Scanner(System.in);
    	boolean endOfGame = false;
		ArrayList<Move> validPositions = this.currentGame.validPositions(this.currentGame.currentPlayer); //calculates the list of legal moves to play in
    	while(!endOfGame) {
    		System.out.println("\n   Current board");
    		this.currentGame.displayGrid();
    		if (this.currentGame.currentPlayer==1) System.out.println("x's turn");
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
    		if (this.currentGame.currentPlayer==1) this.currentGame.currentPlayer = 2;	//changes
    		else this.currentGame.currentPlayer = 1;									//player
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

    void playIn(Move move) {
    	move.flipDisks();
    }

    void undo() {

    }

    void redo() {

    }
}