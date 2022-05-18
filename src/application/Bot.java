package application;

import java.util.ArrayList;
import java.util.Random;

public class Bot {
    ArrayList<Move> moves;
    float[] scores; //this array will store the score of each Move of this.moves
    int numMoves; //size of moves
    
    Bot(ArrayList<Move> legalMoves) {
        this.moves = legalMoves;
        this.numMoves = this.moves.size();
        this.scores = new float[this.numMoves];
        for (int i = 0; i < this.scores.length; i++) {
            this.scores[i] = 0; //for the moment, each move has a score of 0
        }
    }
    
    
    /*
     * static evaluation functions:
     *    - corner grab
     *    - stability
     *    - mobility
     *    - placement
     *    - frontier disks
     *    - disk difference
     *    (- parity)
     * 
     * each function has to return a value in the [-1, 1] range and another function will return a linear combination of these values, using weights that may vary in time.
     */
    
    public float[] staticEvalFrontier() { //returns, for this.move.get(i), the number of empty squares adjacent to the opponent minus the number of empty squares adjacent to the player
    	Position[] dirs = new Position[] {new Position(-1, -1), new Position(-1, 0), new Position(-1, 1), new Position(0, -1), new Position(0, 1), new Position(1, -1), new Position(1, 0), new Position(1, 1)}; // list of all possible directions
    	float[] res =new float[this.numMoves]; //result
    	for (int i=0; i<this.numMoves; i++) { //goes through this.moves
    		int res_i = 0; //result for consideredMove
    		Move consideredMove = this.moves.get(i); //the move tested during this iteration
    		Color[][] tmpGrid = consideredMove.currentGameState.copygrid(); //stores the current grid in order to restore it later
    		consideredMove.flipDisks(); //plays consideredMove
    		Color[][] consideredGrid = consideredMove.currentGameState.grid; //grid after consideredMove has been played
    		boolean[][] testedSquares = new boolean[8][8]; //array where the (i,j) component equals true if and only if consideredGrid[i][j] has been counted as empty (used to not count an empty square multiple times
    		for (int x=0; x<8; x++) {		//goes through
    			for (int y=0; y<8; y++) {	//the grid
    				if(consideredGrid[x][y] == Color.WHITE) { //if the considered square is of bot's color (white)
    					for (Position dir : dirs) { //tests all adjacent squares
    						if ((new Position(x+dir.x, y+dir.y)).inGrid()) { //checks is the adjacent square is in the grid
	    						if (consideredGrid[x+dir.x][y+dir.y] == Color.EMPTY && testedSquares[x+dir.x][y+dir.y] != true) { //if the considered adjacent square is empty and not counted yet
	    							res_i --; //updates the result for considered move
	    							testedSquares[x+dir.x][y+dir.y] = true; //updates testedSquares: the square (x+dir.x, y+dir.y) has now been counted
	    						}
    						}
    					}
    				}
    			}
    		}
    		testedSquares = new boolean[8][8]; //resets testedSquares
    		for (int x=0; x<8; x++) {		//goes through
    			for (int y=0; y<8; y++) {	//the grid
    				if(consideredGrid[x][y] == Color.BLACK) { //if the considered square is of human player's color (white)
    					for (Position dir : dirs) { //tests all adjacent squares
    						if ((new Position(x+dir.x, y+dir.y)).inGrid()) { //checks is the adjacent square is in the grid
	    						if (consideredGrid[x+dir.x][y+dir.y] == Color.EMPTY && testedSquares[x+dir.x][y+dir.y] != true) { //if the considered adjacent square is empty and not counted yet
	    							res_i ++; //updates the result for considered move
	    							testedSquares[x+dir.x][y+dir.y] = true; //updates testedSquares: the square (x+dir.x, y+dir.y) has now been counted
	    						}
    						}
    					}
    				}
    			}
    		}
    		res[i] = (float) (res_i*0.03125); //normalizes the result for consideredMove and adds it to the res array
    		consideredMove.currentGameState.grid = tmpGrid; //restores the grid at its former state
    	}
    	return res;
    }
    
	/**
     * staticEvalCornerGrab is a method used to evaluate whether the move grabs a corner
     * @return a list of floats where res[i] is the evaluation of this.moves.get(i) : res[i]=1 if a corner is grabbed
     */
    private float[] staticEvalCornerGrab() {
        float[] res = new float[this.numMoves];
        for (int i = 0; i < this.scores.length; i++) { //for each move considered by the bot
            int x = this.moves.get(i).position.x;
            int y = this.moves.get(i).position.y;
            if ((x==0 && y==0) || (x==0 && y==7) || (x==7 && y==0) || (x==7 && y==7)) res[i] = 1; //if it's a corner
            else res[i] = 0;
        }
        return res;        
    }
    
	/**
     * staticEvalDiskDifference is a method that calculates the difference between the scores of player and opponent
     * @return a list of floats where res[i] is the evaluation of this.moves.get(i) : res[i]=scores[player]-scores[opponent]
     */
    private float[] staticEvalDiskDifference() { // difference between the scores of player and opponent
    	float[] res = new float[this.numMoves]; // will contain the evaluations of each move
    	for (int i=0; i<this.scores.length; i++) { // for each move considered by the bot
    		Move moveconsidered = this.moves.get(i);
    		Color[][] copygrid = moveconsidered.currentGameState.copygrid();
    		moveconsidered.flipDisks(); // plays the move
			GameState aftermove = moveconsidered.currentGameState;
    		int[] scores = aftermove.scores(); // calculates the scores after move is played
    		res[i]= (scores[1]-scores[0])/64;
    		moveconsidered.currentGameState.grid = copygrid ; // goes back to the way the game state was prior to the move
    	}
    	return res;
    }
    
	/**
     * staticEvalMobility is a method that evaluates the number of the opponent's possible moves after the player plays this.moves.get(i)
     * @return a list of floats where res[i] is the evaluation of this.moves.get(i) : the greater the opponent mobility is, the smaller res[i] is
     */
    private float[] staticEvalMobility() { // minimizes the opponent's mobility : returns the opposite of the number of the opponent's possible moves for each move played
    	float[] res = new float[this.numMoves]; // will contain the evaluations of each move
    	for (int i=0; i<this.scores.length; i++) { // for each move considered by the bot
    		Move moveconsidered = this.moves.get(i);
    		Color[][] copygrid = moveconsidered.currentGameState.copygrid();
    		moveconsidered.flipDisks(); // plays the move
    		GameState aftermove = moveconsidered.currentGameState;
    		ArrayList<Move> opponentmobility = aftermove.validPositions(moveconsidered.player.Opponent()); // list of the opponent's possible moves after the move is played
    		res[i]= -opponentmobility.size()/25; // supposing that the highest number of possible moves is 25
    		moveconsidered.currentGameState.grid = copygrid ; // goes back to the way the game state was prior to the move
    	}
    	return res;
    }
    
	/**
     * staticEvalPlacement is a method that evaluates the position of the move played
     * @return a list of floats where res[i] is the evaluation of this.moves.get(i) : res[i] is the placement score associated with the position of the considered move
     */
    private float[] staticEvalPlacement() { // evaluates position of the move played
    	float[] res = new float[this.numMoves]; // will contain the evaluations of each move
    	float[][] placements = { // describes placement scores
    			{30, -8, 8, 6, 6, 8, -8, 30},
    			{-8, -24, -4, -3, -3, -4, -24, -8},
    			{8, -4, 7, 4, 4, 7, -4, 8},
    			{6, -3, 4, 0, 0, 4, -3, 6},
    			{6, -3, 4, 0, 0, 4, -3, 6},
    			{8, -4, 7, 4, 4, 7, -4, 8},
    			{-8, -24, -4, -3, -3, -4, -24, -8},
    			{30, -8, 8, 6, 6, 8, -8, 30},
    	};
    	for (int i=0; i<8; i++) {
    		for (int j=0; j<8; j++) {
    			placements[i][j]= placements[i][j]/30; // placement[i][j] must be between -1 and 1
    		}
    	}
    	
    	for (int i=0; i<this.scores.length; i++) { // for each move considered by the bot
    		Move moveconsidered = this.moves.get(i);
    		int x = moveconsidered.position.x;
    		int y = moveconsidered.position.y; // gets position of considered move
    		res[i]=placements[x][y]; // associates placement score
    	}
    	return res;
    }
    
    private float staticEvalStabilityOneMove(int moveIndex) { //returns, for this.moves.get(i), the number of stable disk after it was played minus the number of stable disks before it was played
        float res = 0; //result
        Move move = this.moves.get(moveIndex); //the move that is considered, before being played
        Color[][] tmpGrid = move.currentGameState.copygrid(); //temporary grid that will be restored after computation
        Color[][] gridNotPlayed = move.currentGameState.grid; //the grid that is considered, before this.moves.get(moveIndex) being played
        for (int i=0; i<8; i++) {        //goes through
            for (int j=0; j<8; j++) {    //gridNotPlayed
                if (gridNotPlayed[i][j] == move.player) { //only test player's disks
                    Move moveToTest = new Move(move.player, new Position(i, j), move.currentGameState); //move that needs to has its stability tested
                    if (moveToTest.isStable()) res--;
                }
            }
        }
        move.flipDisks(); //plays the move
        Color[][] gridPlayed = move.currentGameState.grid; //the grid that is considered, after this.moves.get(moveIndex) being played
        for (int i=0; i<8; i++) {        //goes through
            for (int j=0; j<8; j++) {    //gridNotPlayed
                if (gridPlayed[i][j] == move.player) { //only test player's disks
                    Move moveToTest = new Move(move.player, new Position(i, j), move.currentGameState); //move that needs to has its stability tested
                    if (moveToTest.isStable()) res++;
                }
            }
        }
        move.currentGameState.grid = tmpGrid; //restores the former grid
        return res;
    }
    
    public float[] staticEvalStability() { //evaluate the stability of the move played: (new stable pos - former stable pos)/64 
		float[] res = new float[this.numMoves]; //results
		for (int i = 0; i < this.numMoves; i++) {
			res[i] = 0; //for the moment, all res are equal to 0
		}
		for (int i = 0; i < this.numMoves; i++) {
			res[i] = (float) (this.staticEvalStabilityOneMove(i) * 0.015625); //evaluate the stability number of this.moves.get(i) and divides it by 64 to normalize it
		}
		return res;
	}

    /**
     * displayMoves is a test function used to display the positions of the moves evaluated
     */
    void displayMoves() {
    	for (int i=0; i<this.numMoves; i++) {
    		System.out.println(this.moves.get(i).toString());
    	}
    }


	/**
     * combine is a method used to associate to this.scores a linear combination of the static evaluations ; the weights may vary during the game
     */
    void combine(int turn, Color player) { // linear combination, calculates scores
    	float[] cornerscores = this.staticEvalCornerGrab();
        float[] stabilityscores = this.staticEvalStability();
        float[] mobilityscores = this.staticEvalMobility();
        float[] placementscores = this.staticEvalPlacement();
        float[] frontierscores = this.staticEvalFrontier();
        float[] differencescores = this.staticEvalDiskDifference();
        float corner = 64; //weighted very highly at all times
        float stability = 43; //weighted highly at all times
        float mobility = 64-turn; //weighted highly at the beginning and decreases to zero at the end
        float placement = 10; //not a very important criteria in itself, used to decide between two similar moves
        float frontier = (float) (20-0.3125*turn); //medium weight, decreasing at the end
        float difference = turn; //from zero to very high at the end
        float random = (float) 0.01; // adds a small part of random
        if (player==Color.WHITE) {
        	for (int i=0; i<this.scores.length; i++) { // if the player is the maximizing player
                this.scores[i]= corner * cornerscores[i] + mobility * mobilityscores[i] + placement * placementscores[i] + difference * differencescores[i] + random * (float)Math.random() + stability * stabilityscores[i] + frontier * frontierscores[i];
        	}
        }
        else {
        	for (int i=0; i<this.scores.length; i++) { // if the player is the minimizing player
        		this.scores[i]= - corner * cornerscores[i] - mobility * mobilityscores[i] - stability * stabilityscores[i]  - difference * differencescores[i] - random * (float)Math.random() - placement * placementscores[i] - frontier * frontierscores[i];
        	}
        }
    }
    
	/**
     * max is a method that returns the move with the highest score calculated by static evaluation
     * @return the move that has the highest evaluation, ie the best move to play
     */
    Move max() { // returns move with the highest score (static evaluation)
    	float max = this.scores[0];
    	ArrayList<Move> movesmax = new ArrayList<Move>();
    	for (int i=1; i<this.scores.length; i++) { // looks for the highest score
    		if (this.scores[i]>max) {
    			max = this.scores[i];
    		}
    	}
    	for (int i=0; i<this.scores.length; i++) { // creates an array list of moves with the highest score
    		if (this.scores[i]==max) {
    			movesmax.add(this.moves.get(i));
    		}
    	}
    	Random random = new Random();
    	int randomnb = random.nextInt(movesmax.size());
    	return movesmax.get(randomnb); // returns a move at random among the array of moves with the highest score
    }
    
    /**
     * min is a method that returns the move with the lowest score calculated by static evaluation
     * @return the move that has the lowest static evaluation
     */
    Move min() { // returns move with the lowest score (static evaluation)
    	float min = this.scores[0];
    	ArrayList<Move> movesmin = new ArrayList<Move>();
        for (int i=1; i<this.scores.length; i++) { // looks for the highest score
            if (this.scores[i]<min) {
                min = this.scores[i];
            }
        }
        for (int i=0; i<this.scores.length; i++) { // creates an array list of moves with the lowest score
        	if (this.scores[i]==min) {
        		movesmin.add(this.moves.get(i));
        	}
        }
        Random random = new Random();
        int randomnb = random.nextInt(movesmin.size());
        return movesmin.get(randomnb); // returns a move at random among the array of moves with the lowest score
    }

	/**
     * simpleBot is the bot that we are currently using
     * @param player is the player who is about to play, ie the bot
     * @param turn describes where we are in the game (beginning, middle, end)
     * @return the best move to play using static only evaluation
     */
    Move simpleBot(Color player, int turn) {
    	this.combine(turn, player); // calculates the scores
    	if (player == Color.WHITE) { // the maximizing player is playing
    		return this.max(); // returns the move with the highest static evaluation
    	}
    	else { // the minimizing player is playing
    		return this.min(); // returns the move with the lowest static evaluation
    	}
    }
    
    /* unused code (however it is works)
	this is our try of the minimax function

    float singleminimax(Move move, int depth, Color player, int turn, int iter, Bot bot) {
    	if (depth ==0) {
    		return bot.scores[iter];
    	}
    	float maxEval = - Float.MAX_VALUE;
		ArrayList<Move> children = move.children();
		Bot botchildren = new Bot(children);
		botchildren.combine(turn+1, player.Opponent());
    	if (player==Color.WHITE) {
    		for (Move child : children) {
    			float eval = singleminimax(child, depth -1, player.Opponent(), turn +1, children.indexOf(child), botchildren);
    			maxEval = Float.max(eval, maxEval);    			
    		}
    		return maxEval;
    	}
    	else {
    		float minEval = Float.MAX_VALUE;
    		for (Move child : children) {
    			float eval = singleminimax(child, depth-1, player.Opponent(), turn +1, children.indexOf(child), botchildren);
    			minEval = Float.min(eval, minEval);
    		}
    		return minEval;	
    	}
    }
    Move minimax(ArrayList<Move> moves, int depth, Color player, int turn) {
    	this.combine(turn, player);
    	float[] evaluation = new float[this.numMoves];
    	for (int i=0; i<moves.size(); i++) {
    		evaluation[i]=singleminimax(moves.get(i), depth, player, turn, i, this);	
    	}
    	this.scores = evaluation;    	
    	for (int i=0; i<moves.size(); i++) {
    	System.out.println("scores["+i+"] = " + this.scores[i]);
    	}	
    	if (player==Color.WHITE) {
    		Move res = this.max();
    		System.out.println("the best move to play is " + res.toString());
    		System.out.println("voici la grille res");
    		res.currentGameState.displayGrid();
    		return res;
    	}
    	else {
    		Move res = this.min();
    		System.out.println("ths best move to play is " + res.toString());
    		System.out.println("voici la grille res");
    		res.currentGameState.displayGrid();
    		return res;
    	}
    }
    */


}

