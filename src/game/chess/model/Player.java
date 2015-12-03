package game.chess.model;

/**
 * Created by Satya on 14/06/14.
 */
public enum Player {
    WHITE(0), BLACK(7);
    private int row;
    Player(int row) {
        this.row = row;
    }
    public int getRow() {
        return row;
    }
}
