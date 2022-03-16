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
	
	/*
	int sandwicheck(Move move,Position dir, boolean invert) { //counts how many opponent's disks the disk played with move can sandwich in direction dir ((0, -1) = up, (-1, 1) = lef down diag...)
		int res = 0; //result
		Position curPos = new Position(move.position.x+dir.x, move.position.y+dir.y); //position the algorithm is currently checking
		while ((curPos.inGrid())) { //while it's in the grid
			if (this.grid[curPos.x][curPos.y]==0) break; //break out of while loop if curPos is an empty square
			int curDisk = grid[curPos.x][curPos.y]; //color of the disk in curPos, 0 if no disk in curPos
			if (curDisk==move.player) { // disk of the player's color encountered
				if (invert) { // going from move to curDisk
					return res;
				}
				// (else) going from curDisk to move
				return 0;
			}
			res += 1;
			curPos.x += dir.x; // updates curPos to go
			curPos.y += dir.y; // in direction dir
		}
		if (invert) { // going from move to cur Disk
			return 0;
		}
		return res; // going from curDisk to move
	}
	*/
	
	int sandwicheck(Move move,Position dir, boolean invert) { //counts how many opponent's disks the disk played with move can sandwich in direction dir ((0, -1) = up, (-1, 1) = lef down diag...)
        int res = 0; //result
        Position curPos = new Position(move.position.x+dir.x, move.position.y+dir.y); //position the algorithm is currently checking
        if (curPos.inGrid()) {
            return res;
        }
        int curDisk = this.grid[curPos.x][curPos.y]; //color of the disk in curPos, null if no disk in curPos
        while (curPos.inGrid() && this.grid[curPos.x][curPos.y]!=0) { //while it's in the grid and not in empty square
            curDisk = this.grid[curPos.x][curPos.y]; //updates current disk
            if (curDisk==move.player) { // disk of the player's color encountered
                if (invert) { // going from move to curDisk
                    return res;
                }
                // (else) going from curDisk to move
                return 0;
            }
            res += 1;
            curPos.x += dir.x; // updates curPos to go
            curPos.y += dir.y; // in direction dir
        }
        if (invert) { // going from move to cur Disk
            return 0;
        }
        return res; // going from curDisk to move
    }
	
	void displayGrid() { //test function to display GameState grid in console
        System.out.println("  A B C D E F G H"); // labels columns
        for (int i=0; i<this.grid.length; i++) {
            String line_i= Integer.toString(i+1) + " "; // labels line number i
            for (int j=0; j<this.grid.length; j++) {
                if (this.grid[i][j] == 0) { // if the square is empty
                    line_i=line_i + ". ";
                }
                else if (this.grid[i][j]==1) {// if the square in position (i,j) is BLACK
                    line_i=line_i + "x ";
                }
                else if (this.grid[i][j]==2) {// if the square in position (i,j) is WHITE
                        line_i=line_i + "o ";
                }
            }
            System.out.println(line_i);
        }
    }
	
	int[] scores() { // returns the scores of black, white
        int black=0; 
        int white=0;
        for (int i=0; i<this.grid.length; i++) {
            for (int j=0; j<this.grid.length; j++) {
                if (this.grid[i][j]==1) {
                    black++;
                }
                else if (this.grid[i][j]==2) {
                    white++;
                }
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
    
    /*
    List<Move> validPositions(int player) { //return the list of possibles moves for player
    	ArrayList<Move> res = new ArrayList<Move>(); //result
    	Position[] dirs = new Position[] {new Position(-1, -1), new Position(-1, 0), new Position(-1, 1), new Position(0, -1), new Position(0, 1), new Position(1, -1), new Position(1, 0), new Position(1, 1)}; // list of all possible directions
    	ArrayList<Position> playerPos = new ArrayList<Position>(); // positions of the player in the grid
    	for (int i=0; i<8; i++) { //goes trough the grid
    		for (int j=0; j<8; j++) {
    			if (player==this.grid[i][j]) { //a disk of the color of player is found
    				playerPos.add(new Position(i, j)); //its position is added to playerPos
    			}
    		}
    	}
    	for (int i=0; i<playerPos.size(); i++) { //goes through the list of positions of the player in the grid
    		for (Position dir: dirs) { //checks all possible directions
    			Move movPosDir = new Move(player, playerPos.get(i), this); // the move played in playerPos[point]
    			int count = this.sandwicheck(movPosDir, dir, false); //count the number of disks that could be flipped if a disk was played from move.position in direction dir
    			if (count>0) { // the move can flip disks and thus is a valid move
    				Position movPos = new Position(movPosDir.position.x+dir.x*count, movPosDir.position.y+dir.y*count); // calculates where to play in order to do the move
    				Move move = new Move(player, movPos, this); // move to play
    				if (!move.isIn(res)) res.add(move);
    			}
    		}
    	}
    	return res;
    }
    */
    
    public ArrayList<Move> validPositions(int player) {
    	ArrayList<Move> res = new ArrayList<Move>();
    	for (int i=0; i<8; i++) {
    		for (int j=0; j<8; j++) {
    			Move tested = new Move(player, new Position(i, j), this);
    			if (tested.isValid()) res.add(tested);
    		}
    	}
    	return res;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}



















