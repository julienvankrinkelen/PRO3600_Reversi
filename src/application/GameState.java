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
	
    int sandwicheck(Move move,Position dir) { //counts how many opponent's disks the disk played with move can sandwich in direction dir ((0, -1) = up, (-1, 1) = lef down diag...)
        int res = 0; //result
        Position curPos = new Position(move.position.x+dir.x, move.position.y+dir.y); //position the algorithm is currently checking
        while ((curPos.inGrid())) { //while it's in the grid
            if (this.grid[curPos.x][curPos.y]==0) break; //break out of while loop if curPos is an empty square
            int curDisk = grid[curPos.x][curPos.y]; //color of the disk in curPos, 0 if no disk in curPos
            if (curDisk==move.player) { // disk of the player's color encountered
                return res;
            }
            res += 1;
            curPos.x += dir.x; // updates curPos to go
            curPos.y += dir.y; // in direction dir
        }
        return 0; // got out of the grid -> no sandwich
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