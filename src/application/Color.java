package application;

/**
 * Color is a java enum used to define the color of the disks (BLACK, WHITE, EMPTY).
 *
 */

public enum Color {
BLACK, WHITE, EMPTY;


Color Opponent() {
    
    switch(this) {
        case BLACK:
            return WHITE;
        case WHITE:
            return BLACK;
        default:
            return EMPTY;
    }
}
}