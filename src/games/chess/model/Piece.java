package games.chess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Satya on 14/06/14.
 */

// TODO : create static factory method for all pieces :D
public abstract class Piece implements Movable<Piece> {
    //TODO : create a static function for calculating all possible moves
    protected Pose2d currentPosition;
    protected final Player player;
    //TODO : Do we need that ???
    protected final ChessBoard board;

    public Piece(final Pose2d position, final Player player, final ChessBoard board) {
        this.currentPosition = position;
        this.player = player;
        this.board = board;
    }

    public boolean isMovableTo(Pose2d position) {
        if (this.board.isFree(position) || this.board.containsEnemy(currentPosition, position)) {
            for (Move move : this.getAllPossibleMoves()) {
                if (move.getTargetPosition().equals(position)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Piece moveTo(Pose2d position) {
        this.board.setPiece(position, this);
        this.board.setPiece(this.currentPosition, null);
        this.currentPosition = position;
        return this;
    }

    public final Pose2d getCurrentPosition() {
        return currentPosition;
    }

    public final Player getPlayer() {
        return player;
    }

    public final ChessBoard getBoard() {
        return board;
    }

    public abstract List<Move> getAllPossibleMoves();
}
