package application;

import java.util.*;

public class GameState {
	Color currentPlayer;
	Color grid[][];
	Move lastMove;
	GameState previous;
	GameState future;

	public GameState() {
		this.currentPlayer = Color.BLACK;
		this.grid = new Color[][]{
		             {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY},
		             {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY},
		             {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY},
		             {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.WHITE, Color.BLACK, Color.EMPTY, Color.EMPTY, Color.EMPTY},
		             {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.BLACK, Color.WHITE, Color.EMPTY, Color.EMPTY, Color.EMPTY},
		             {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY},
		             {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY},
		             {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY}
		             }; //initializing boardgame grid
	}
	
    int sandwicheck(Move move,Position dir) { //counts how many opponent's disks the disk played with move can sandwich in direction dir ((0, -1) = up, (-1, 1) = lef down diag...)
        int res = 0; //result
        Position curPos = new Position(move.position.x+dir.x, move.position.y+dir.y); //position the algorithm is currently checking
        while ((curPos.inGrid())) { //while it's in the grid
            if (this.grid[curPos.x][curPos.y]==Color.EMPTY) break; //break out of while loop if curPos is an empty square
            Color curDisk = grid[curPos.x][curPos.y]; //color of the disk in curPos, 0 if no disk in curPos
            if (curDisk==move.player) { // disk of the player's color encountered
                return res;
            }
            res += 1;
            curPos.x += dir.x; // updates curPos to go
            curPos.y += dir.y; // in direction dir
        }
        return 0; // got out of the grid -> no sandwich
    }
	
    void displayGrid() { //test function to display this.grid in console
        System.out.println("  A B C D E F G H"); // labels columns
        for (int i=0; i<this.grid.length; i++) {
            System.out.print(Integer.valueOf(i+1) + " ");
            for (int j=0; j<this.grid.length; j++) {
                switch (this.grid[i][j]) {
                    case EMPTY: //square is empty
                        System.out.print(". ");
                        break;
                    case BLACK: //black disk
                        System.out.print("x ");
                        break;
                    case WHITE: //white disk
                        System.out.print("o ");
                        break;
                }
            }
            System.out.println(); //skips line
        }
    }
	
	int[] scores() { // returns the scores of black, white
        int black=0; 
        int white=0;
        for (int i=0; i<this.grid.length; i++) {
            for (int j=0; j<this.grid.length; j++) {
                if (this.grid[i][j]==Color.BLACK) black++;
                else if (this.grid[i][j]==Color.WHITE) white++;
            }
        }
        int[] score = {black, white};
        return score;
    }

    void displayScores() {
        int[] score = this.scores();
        System.out.println("BLACK = " + score[0]);
        System.out.println("WHITE = " + score[1]);
    }
    
    public ArrayList<Move> validPositions(Color player) {
    	ArrayList<Move> res = new ArrayList<Move>();
    	for (int i=0; i<8; i++) {
    		for (int j=0; j<8; j++) {
    			if (this.grid[i][j]==Color.EMPTY) { //this square needs to be empty
    				Move tested = new Move(player, new Position(i, j), this);
    				//System.out.println("Testing: " + tested.toString());;
    				if (tested.isValid() && !tested.isIn(res)) {
    					res.add(tested); //the tested move needs to be legal and we prevent doubles from appearing in res
    				}
    				//System.out.println("Current res: " + res.toString() + "\n"); //debuging
    			}
    		}
    	}
    	return res;
    }
}