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
	
	boolean is_valid() {
		return true;
	}
	
	int disks_flipped() {
		return 0;
	}

}