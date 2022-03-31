package application;

import java.util.*;

/**
 * Move is a java class that contains a player, a position the player wants to play in and the current game state. It is used to check whether a move is valid and to play it.
 */

public class Move {
	Color player;
	Position position;
	GameState currentGameState;

	/**
	 * This the constructor of Move.
	 * @param player is BLACK or WHITE
	 * @param position is a position the player wants to play in (may not be valid).
	 * @param currentGameState contains the information of the current grid.
	 */
	
	Move(Color player, Position position, GameState currentGameState) {
		this.player=player;
		this.position=position;
		this.currentGameState=currentGameState;
	}

	/**
	 * equals is a method that verifies whether two moves are equal. 
	 * @param move is the move tested.
	 * @return true if the two moves are equal, false if they are not.
	 */
	
	public boolean equals(Move move) {
		return this.player==move.player && this.position.equals(move.position);
	}

	/**
	 * toString is a method used to convert the position from the move into a string, like this : (x,y).
	 */
	
	public String toString() {
		return position.toString();
	}
	
	/**
	 * isValid is a method that checks whether a sandwich can be made from this position, ie if the move is valid.
	 * @return true if the move is valid, false if the move is not valid.
	 */

	boolean isValid() {
		if (!this.position.inGrid()) return false;
		Position[] dirs = new Position[] {new Position(-1, -1), new Position(-1, 0), new Position(-1, 1), new Position(0, -1), new Position(0, 1), new Position(1, -1), new Position(1, 0), new Position(1, 1)}; // list of all possible directions
		for (Position dir: dirs) { //checks if disks can be sandwiched in a direction
			if (this.currentGameState.sandwicheck(this, dir)>0) return true; //if a sandwich can be made in this direction
		}
		return false; //if no sandwich can be made from this position, then the move is not legal
	}
	
	/**
     * flipDisks is a method that plays the valid move and flips the sandwiched opponents disks. 
     * @return an array of the positions of the disks that can be flipped by playing said move.
     */
    
    public ArrayList<Position> flipDisks() { // return the number of disks that can be flipped by playing this and if (flip), flips them
        Position[] dirs = new Position[] {new Position(-1, -1), new Position(-1, 0), new Position(-1, 1), new Position(0, -1), new Position(0, 1), new Position(1, -1), new Position(1, 0), new Position(1, 1)}; // list of all possible directions
        ArrayList<Position> res = new ArrayList<Position>(); // will contain the positions of the disks that can be flipped
        int num=0; // sum of every disc flipped
        for (int i=0; i<dirs.length; i++) { // for every direction
            int count_i = this.currentGameState.sandwicheck(this, dirs[i]); // number of discs that can be flipped in said direction
            num=num+count_i; // adds number of flipped discs
            for (int j=1; j<count_i +1; j++) {
                int x = this.position.x + (dirs[i].x)*j;
                int y = this.position.y + (dirs[i].y)*j; // coordinates of the disc to flip
                this.currentGameState.grid[x][y] = this.player; // flips the disc
                Position pos = new Position(x,y);
                res.add(pos); // adds position to the array
            }
        }
        if (num>0) { // if the move is valid
            this.currentGameState.grid[this.position.x][this.position.y] = this.player; // plays the move
        }
        return res;
    }

	/**
     * isIn checks whether a move is already in an ArrayList of moves.
     * @param moves is an ArrayList. 
     * @return true if said move is in the list, false if it is not.
     */
    
    public boolean isIn(ArrayList<Move> moves) {
    	for (int i=0; i<moves.size(); i++) {
    		if (this.equals(moves.get(i))) {
    			return true;
    		}
    	}
    	return false;
    }
}
