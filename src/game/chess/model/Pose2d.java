package game.chess.model;

/**
 * Created by Satya on 14/06/14.
 */
public class Pose2d implements Position {
    private final int row;
    private final int col;

    private Pose2d(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;

        Pose2d position = (Pose2d) that;

        if (this.col != position.col) return false;
        if (this.row != position.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
    public static Pose2d create(final int row, final int col) {
        return new Pose2d(row, col);
    }
}
