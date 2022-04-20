package application;

import java.util.ArrayList;

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
     * TODO static evaluation functions:
     *    - corner grab
     *    - stability
     *    - mobility
     *    - placement
     *    - frontier disks
     *    - disk difference
     *    (- parity)
     * 
     * each function has to return a value in the [0, 1] range and another function will return a linear combination of these values, using weights that may vary in time.
     */
    
    private float[] staticEvalStability() { // TO DO
    	float[] res = new float[this.numMoves];
    	return res;
    }
    
    private float[] staticEvalFrontier() { // TO DO
    	float[] res = new float[this.numMoves];
    	return res;
    }
    
    private float[] staticEvalCornerGrab() { // okay
        float[] res = new float[this.numMoves];
        for (int i = 0; i < this.scores.length; i++) { //for each move considered by the bot
            int x = this.moves.get(i).position.x;
            int y = this.moves.get(i).position.y;
            // System.out.println(x+", "+y);
            if ((x==0 && y==0) || (x==0 && y==7) || (x==7 && y==0) || (x==7 && y==7)) res[i] = 1; //if it's a corner
            else res[i] = 0;
        }
        return res;        
    }
    
    private float[] staticEvalDiskDifference() { // difference between the scores of player and opponent
    	
    	float[] res = new float[this.numMoves]; // will contain the evaluations of each move
    	
    	for (int i=0; i<this.scores.length; i++) { // for each move considered by the bot
    	
    		Move moveconsidered = this.moves.get(i);
    		Color[][] copygrid = moveconsidered.currentGameState.copygrid();
    		moveconsidered.flipDisks(); // plays the move
    		
			GameState aftermove = moveconsidered.currentGameState;
    		int[] scores = aftermove.scores(); // calculates the scores after move is played
    		
    		if (moveconsidered.player==Color.BLACK) { // if the player is black
    			res[i]=(scores[0]-scores[1])/64;
    		}
    		else { // if the player is white
    			res[i]= (scores[1]-scores[0])/64;
    		}
    		
    		moveconsidered.currentGameState.grid = copygrid ; // goes back to the way the game state was prior to the move
    	}
    	return res;
    }
    
    private float[] staticEvalMobility() { // minimizes the opponent's mobility : returns the numbers of the opponent's possible moves for each move played
    	
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
    
    void displayScores() {
    	System.out.print("[");
    	for (float a : this.scores) {
    		System.out.print(a + " ");
    	}
    	System.out.println("]");
    }
    
    void combinate(float corner, float stability, float mobility, float placement, float frontier, float difference) { // linear combination, calculates scores
    	float[] cornerscores = this.staticEvalCornerGrab();
    	float[] stabilityscores = this.staticEvalStability();
    	float[] mobilityscores = this.staticEvalMobility();
    	float[] placementscores = this.staticEvalPlacement();
    	float[] frontierscores = this.staticEvalFrontier();
    	float[] differencescores = this.staticEvalDiskDifference();
    	for (int i=0; i<this.scores.length; i++) {
    		this.scores[i]= corner*cornerscores[i] + stability*stabilityscores[i] + mobility*mobilityscores[i] + placement*placementscores[i] + frontier*frontierscores[i] + difference*differencescores[i];
    	}
    }
    
    Move max() { // returns move with the highest score
    	float max = this.scores[0];
    	int indicemax = 0;
    	for (int i=1; i<this.scores.length; i++) {
    		if (this.scores[i]>max) {
    			max = this.scores[i];
    			indicemax = i;
    		}
    	}
    	return this.moves.get(indicemax);  	
    }
    
    
    
}
