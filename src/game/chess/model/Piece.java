package game.chess.model;

import java.util.Set;

/**
 * Created by Satya on 14/06/14.
 */

// TODO : withPosition static factory method for all pieces :D
public abstract class Piece implements Movable<Piece> {
    //TODO : withPosition a static function for calculating all possible moves
    protected final Pose2d position;
    protected final Player player;
    //TODO : Do we need that ???
    protected final ChessBoard board;
//    protected Set<Move> allPossibleMoves;

    public Piece(final Pose2d position, final Player player, final ChessBoard board) {
        this.position = position;
        this.player = player;
        this.board = board;
//        this.allPossibleMoves = new HashSet<>();
    }

    public Move isMovableTo(Pose2d pos) {
        if (this.isSafeToMove(pos) && this.allPossibleMoves().contains(new Move(this.position, pos, this, false))) {
            return new Move(this.position, pos, this, false);
        }
        return null;
    }
    public boolean isSafeToMove(Pose2d pos) {
        return this.board.isFree(pos) || this.board.containsEnemy(this.position, pos);
    }

    public Piece moveTo(Pose2d position) {
//        if (this.isMovableTo(position)) {
//            this.board.setPiece(position, this);
//            this.board.setPiece(this.position, null);
//            this.position = position;
//        }
        return this;
    }

    public Pose2d position() {
        return this.position;
    }

    public Player player() {
        return this.player;
    }

    public abstract Set<Move> allPossibleMoves();

    public abstract Piece withPosition(Pose2d pos);
}
