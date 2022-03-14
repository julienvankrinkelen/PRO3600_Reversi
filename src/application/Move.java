package application;

public class Move {
	int player;
	Position position;
	GameState currentGameState;
	
	Move(int player, Position position, GameState currentGameState) {
		this.player=player;
		this.position=position;
		this.currentGameState=currentGameState;
	}
	
	public String toString() {
		return position.toString();
	}
	
	boolean isValid() {
		return true;
	}
	
	int disksFlipped() {
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
}