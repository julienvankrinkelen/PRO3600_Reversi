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
    public ArrayList<Position> flipDisks() { // return the disks that can be flipped by playing this and flips them. 
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
    
    /**
     * Tells if a move is stable (in reversi, a move it considered to be stable if it can never be turned over, for example a corner). The algorithm used is the following:
     *    1. fill the board with the opponnent's disks except for squares that are not empty and square that are adjacent to the move tested
     *    2. for each empty adjacent square, try to play an opponent's disk
     *    3. if the disk can be sandwiched, it is not stable
     * @return a boolean telling if this is stable or not
     */
     boolean isStable() {
    	 Color[][] consideredGrid = this.currentGameState.grid.clone(); //clones grid to modify it
    	 if (consideredGrid[this.position.x][this.position.y] != this.player) { //if the move tested is of invalid form
    		 return false;
    	 }
    	 ArrayList<Position> adjEmpty = new ArrayList<Position>(); //will contain the empty squares adjacent to this.position
    	 for (int di=-1; di<=1; di++) {		//goes through the list of adjacent
    		 for (int dj=-1; dj<=1; dj++) {	//squares of this.position
    			 if (!(di==0 && dj==0)) { //ensures that the considered square is not this.position
    				 Position consideredPos = new Position(this.position.x+di, this.position.y+dj);
    				 if (consideredPos.inGrid()) {
	    				 if (consideredGrid[consideredPos.x][consideredPos.y] == Color.EMPTY) { //if the adjacent square considered is empty
	    					 adjEmpty.add(consideredPos); //adds the considered position (adjacent to this.position and empty)
	    				 }
    				 }
    			 }
    		 }
    	 }
    	 //the following lines fill consideredGrid empty squares with this.player.Opponent()'s disk
    	 Color opponent = this.player.Opponent(); //finds the opponent
    	 for (int i=0; i<8; i++) {		//goes through
    		 for (int j=0; j<8; j++) {	//consideredGrid
    			 if (consideredGrid[i][j] == Color.EMPTY) { //the square is empty
    				 consideredGrid[i][j] = opponent; //fills the square with this.player.Opponent()
    			 }
    		 }
    	 }
    	 for (Position pos: adjEmpty) { //goes through the list of adjacent empty square to empty them and try to play at their position (steps 2 and 3)
    		 consideredGrid[pos.x][pos.y] = Color.EMPTY; //empties the square at pos
    		 GameState gameStateToTry = new GameState();	//creates a new GameState
    		 gameStateToTry.grid = consideredGrid;			//with a grid equal to consideredGrid
    		 Move moveToTry = new Move(opponent, pos, gameStateToTry); //creates a new Move to test its validity
    		 Position dirToTry = new Position(this.position.x-pos.y, this.position.y-pos.y); //direction in wich this is located compared to moveToTry
    		 if (gameStateToTry.sandwicheck(moveToTry, dirToTry)>0) return false; //if this can be sandwiched (ie moveToTry is valid) then it is not stable
    	 }
    	 return true; //executed if and only if the disk is stable (see algorithm description in the javadoc)
     }
     
	/**
      * children is a method used in minimax to evaluate the opponent's valid moves after this.player plays
      * @return an array of the opponent valid moves after this.player has played in this.position 
      */
     public ArrayList<Move> children(){
     	 Color[][] copygrid = this.currentGameState.copygrid(); // creates a deep copy of the grid
     	 GameState gamestatecopy = new GameState(); // creates new GameState
     	 gamestatecopy.currentPlayer=this.player;
     	 gamestatecopy.grid = copygrid; // creates a deep copy of GameState
     	 Move movetoplay = new Move(this.player, this.position, gamestatecopy); // creates a deep copy of this
     	 movetoplay.flipDisks(); // plays the move
     	 ArrayList<Move> children = movetoplay.currentGameState.validPositions(this.player.Opponent()); // calculates the opponent's valid positions after movetoplay is played
     	 ArrayList<Move> childrencopy = new ArrayList<Move>();
     	 for (Move child : children) {  // creates a deep copy of children   		 
     		 Color[][] copycopygrid = movetoplay.currentGameState.copygrid();
     		 GameState gamestatecopycopy = new GameState();
     		 gamestatecopycopy.currentPlayer=this.player.Opponent();
        	 gamestatecopycopy.grid = copycopygrid;
        	 Move childcopy = new Move(child.player, child.position, gamestatecopycopy);
        	 childrencopy.add(childcopy); 
     	 }
     	 return childrencopy;
     }       
}
