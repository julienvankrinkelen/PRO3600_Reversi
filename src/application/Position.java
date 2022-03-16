package application;

public class Position {
	int x;
	int y;
	
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Position pos) {
		return this.x==pos.x && this.y==pos.y;
	}
	
	public String toString() {
		return "(" + Integer.toString(this.x) + ", "  + Integer.toString(this.y) + ")";
	}
	
	public boolean inGrid() {
		return 0<=this.x && 0<=this.y && this.x<=7 && this.y<=7;
	}
}