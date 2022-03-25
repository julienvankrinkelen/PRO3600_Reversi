package application;

/**
 * Position is a java class used for the position (x,y) of each disk.
 *  
 */

public class Position {
	int x;
	int y;
	
	/**
	 * This is the constructor of position.
	 * @param x is an integer between 0 and 7 and represents the line.
	 * @param y is an integer between 0 and 7 and represents the column.
	 */

	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * equals is a method that verifies whether two positions are equal.
	 * @param pos is the position tested. 
	 * @return true if the two positions are equal, false if they are not.
	 */

	public boolean equals(Position pos) {
		return this.x==pos.x && this.y==pos.y;
	}
	
	/**
	 * toString is a method used to convert a position into a string, like this : (x,y).
	 */

	public String toString() {
		return "(" + Integer.toString(this.x) + ", "  + Integer.toString(this.y) + ")";
	}

	/**
	 * inGrid verifies whether the position is in the 8x8 grid
	 * @return true if the position is in, false if it is not.
	 */
	
	public boolean inGrid() {
		return 0<=this.x && 0<=this.y && this.x<=7 && this.y<=7;
	}
}
