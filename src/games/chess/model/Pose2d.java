package games.chess.model;

/**
 * Created by Satya on 14/06/14.
 */
public class Pose2d implements Position {
    private final int row;
    private final int column;

    public Pose2d(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;

        Pose2d position = (Pose2d) that;

        if (this.column != position.column) return false;
        if (this.row != position.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
