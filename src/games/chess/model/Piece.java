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
    protected List<Move> allPossibleMoves;
    protected Pose2d currentPosition;
    protected final Player player;
    //TODO : Do we need that ???
    protected final ChessBoard board;

    public Piece(final Pose2d position, final Player player, final ChessBoard board) {
        this.allPossibleMoves = new ArrayList<>();
        this.currentPosition = position;
        this.player = player;
        this.board = board;
    }

    protected Piece(final Pose2d position, final Player player, final ChessBoard board, final List<Move> moves) {
        this.allPossibleMoves = new ArrayList<>();
        this.currentPosition = position;
        this.player = player;
        this.board = board;
    }

    public boolean isMovableTo(Pose2d position) {
        if (this.board.getPiece(position).getPlayer() == this.player) {
            return  false;
        } else {
            for (Move move : allPossibleMoves) {
                if (move.getTargetPosition().equals(position)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Piece moveTo(Pose2d position) {
        if (this.isMovableTo(position)) {
            this.board.setPiece(position, this);
            this.currentPosition = position;
        }
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

    protected Piece addMove(Pose2d pos) {
        if (board.containsEnemy(currentPosition,pos) || board.isFree(pos)) {
            Move move = new Move(currentPosition, pos, this, false);
            allPossibleMoves.add(move);
        }
        return this;
    }

    public abstract List<Move> getAllPossibleMoves();
}
