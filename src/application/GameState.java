package application;

import java.util.*;

public class GameState {
	int currentPlayer; // 1=BLACK; 2=WHITE
	int[][] grid;
	Move lastMove;
	GameState previous;
	GameState future;

	public GameState() {
		this.currentPlayer = 1;
		this.grid = new int[][]{
		             {0, 0, 0, 0, 0, 0, 0, 0},
		             {0, 0, 0, 0, 0, 0, 0, 0},
		             {0, 0, 0, 0, 0, 0, 0, 0},
		             {0, 0, 0, 2, 1, 0, 0, 0},
		             {0, 0, 0, 1, 2, 0, 0, 0},
		             {0, 0, 0, 0, 0, 0, 0, 0},
		             {0, 0, 0, 0, 0, 0, 0, 0},
		             {0, 0, 0, 0, 0, 0, 0, 0}
		             }; //initializing boardgame grid (0 = empty square)
	}
	
	int sandwicheck(Move move, Position dir) {
		int res = 0;
		Position curPos = move.position;
		if(!curPos.inGrid()) return res; //the Move tested is not in the grid and thus not legal
		int curDisk = this.grid[curPos.x][curPos.y];
		int opponent;
		if (curDisk==1) opponent = 2; 
		else opponent = 1;
		while (this.grid[curPos.x][curPos.y]!=opponent) { //if no opponent's disk is reached, we can continue to count disks
			res++; //we can sandwich one more disk
			curPos.x += dir.x; //updates curPos to go
			curPos.y += dir.y; //in direction dir
			if (!curPos.inGrid()) return 0; // if we go out of the grid then no disk can be sandwiched
		}		
		return res;
	}
	
	void displayGrid() { //test function to display GameState grid in console
        System.out.println("  A B C D E F G H"); // labels columns
        for (int i=0; i<this.grid.length; i++) {
            String line_i= Integer.toString(i+1) + " "; // labels line number i
            for (int j=0; j<this.grid.length; j++) {
                if (this.grid[i][j] == 0) line_i=line_i + ". "; // if the square is empty
                else if (this.grid[i][j]==1) line_i=line_i + "x "; // if the square in position (i,j) is BLACK
                else if (this.grid[i][j]==2) line_i=line_i + "o "; // if the square in position (i,j) is WHITE
            }
            System.out.println(line_i);
        }
    }
	
	int[] scores() { // returns the scores of black, white
        int black=0; 
        int white=0;
        for (int i=0; i<this.grid.length; i++) {
            for (int j=0; j<this.grid.length; j++) {
                if (this.grid[i][j]==1) black++;
                else if (this.grid[i][j]==2) white++;
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
    
    public ArrayList<Move> validPositions(int player) {
    	ArrayList<Move> res = new ArrayList<Move>();
    	for (int i=0; i<8; i++) {
    		for (int j=0; j<8; j++) {
    			if (this.grid[i][j]==0) { //this square needs to be empty
    				Move tested = new Move(player, new Position(i, j), this);
    				if (tested.isValid() && !tested.isIn(res)) res.add(tested); //the tested move needs to be legal and we prevent doubles from appearing in res
    			}
    		}
    	}
    	return res;
    }
}
