package application;

import java.util.*;

public class Move {
	int player;
	Position position;
	GameState currentGameState;
	
	Move(int player, Position position, GameState currentGameState) {
		this.player=player;
		this.position=position;
		this.currentGameState=currentGameState;
	}
	
	public boolean equals(Move move) {
		return this.player==move.player && this.position.equals(move.position);
	}
	
	public String toString() {
		return position.toString();
	}
	
	boolean isValid() {
		if (!this.position.inGrid()) return false;
		Position[] dirs = new Position[] {new Position(-1, -1), new Position(-1, 0), new Position(-1, 1), new Position(0, -1), new Position(0, 1), new Position(1, -1), new Position(1, 0), new Position(1, 1)}; // list of all possible directions
		for (Position dir: dirs) { //checks if disks can be sandwiched in a direction
			if (this.currentGameState.sandwicheck(this, dir, true)>0) return true; //if a sandwich can be made in this direction
		}
		return false; //if no sandwich can be made from this position, then the move is not legal
	}
	
	int disksFlipped() {
		//TODO if needed
		return 0;
	}
	
    int flipDiscs() {
        Position[] dirs = new Position[] {new Position(-1, -1), new Position(-1, 0), new Position(-1, 1), new Position(0, -1), new Position(0, 1), new Position(1, -1), new Position(1, 0), new Position(1, 1)}; // list of all possible directions
        int res=0; // sum of every disc flipped
        for (int i=0; i<dirs.length; i++) { // for every direction
            int count_i = this.currentGameState.sandwicheck(this, dirs[i], true); // number of discs that can be flipped in said direction
            res=res+count_i; // adds number of flipped discs
            for (int j=1; j<count_i +1; j++) {
                int x = this.position.x + (dirs[i].x)*j;
                int y = this.position.y + (dirs[i].y)*j; // coordinates of the disc to flip
                this.currentGameState.grid[x][y] = this.player; // flips the disc
            }
        }
        if (res>0) {
            this.currentGameState.grid[this.position.x][this.position.y] = this.player;
        }
        return res;
    }
    
    public boolean isIn(ArrayList<Move> moves) {
    	for (int i=0; i<moves.size(); i++) {
    		if (this.equals(moves.get(i))) {
    			return true;
    		}
    	}
    	return false;
    }
}