package pt.ipbeja.app.model;
/**
 * Position in the board
 *
 * @author anonymized
 * @version 2024/04/14
 */
public record Position(int line, int col) {

    @Override
    public String toString() {
        return line + ", " + col;
    }

    public int getCol() {
        return col;
    }

    public int getLine() {
        return line;
    }


}
