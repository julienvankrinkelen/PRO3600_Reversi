package application;

public class GameState {
	int currentPlayer; // 1=BLACK; 2=WHITE
	int[][] grid;
	Move lastMove;
	GameState previous;
	GameState future;

	public GameState() {
		this.currentPlayer = 1;
		this.grid = new int[8][8];
		// following 4 lines set up the starting disk pattern
		this.grid[3][3] = 2;
		this.grid[4][4] = 2;
		this.grid[3][4] = 1;
		this.grid[4][3] = 1;
	}
	
	boolean in_grid(Position pos) { //checks if a position is in the 8x8 grid
		int x = pos.x;
		int y = pos.y;
		return 0<=x && 0<=y && x<=7 && y<= 7;
	}
	
	int sandwicheck(Move move) {
		int res = 0; //result
		Position curPos = new Position(move.position.x, move.position.y); //position the algorithm is currently checking
		if (!in_grid(curPos)) {
			return res;
		}
		int curDisk = this.grid[curPos.x][curPos.y]; //color of the disk in curPos, null if no disk in curPos
		
		return res;
	}
	
    void display_grid() { // fonction de test pour afficher la grille de GameState
        System.out.println("  A B C D E F G H"); // labels columns
        for (int i=0; i<this.grid.length; i++) {
            String line_i= Integer.toString(i+1); // labels line number i
            for (int j=0; j<this.grid.length; j++) {
                switch (this.grid[i][j]) {
                case 1: // if the square in position (i,j) is BLACK
                    line_i=line_i + Integer.toString(1)+ " ";
                    break;
                case 2: // if the square in position (i,j) is WHITE
                    line_i=line_i + Integer.toString(2) + " ";
                    break;
                default: // if the square is empty
                    line_i=line_i + "  ";
                }
                System.out.println(line_i);
                System.out.println();
            }
        }
    }
}
