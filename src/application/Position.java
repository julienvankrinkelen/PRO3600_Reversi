package application;

public class Position {
	int x;
	int y;
	
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + Integer.toString(this.x) + ", "  + Integer.toString(this.y) + ")";
	}
}