package game.chess.model;

import java.util.*;

/**
 * Created by Satya on 14/06/14.
 */
public class Pawn extends Piece {

    private boolean isMoved = false;
    public Pawn(Pose2d position, Player player, ChessBoard board) {
        super(position, player, board);
    }
    public Pawn(Pose2d position, Player player, ChessBoard board, boolean isMoved) {
        super(position, player, board);
        this.isMoved = isMoved;
    }
    @Override
    public Piece withPosition(Pose2d pos) {
        return new Pawn(pos, this.player, this.board, true);
    }
    public Piece moveTo(Pose2d position) {
        this.board.setPiece(position, this);
        this.board.setPiece(this.position, null);
//        this.position = position;
        this.isMoved = true;
        return this;
    }
    @Override
    public Set<Move> allPossibleMoves() {
        final Set<Move> allPossibleMoves = new HashSet<>(16);

        final Move move = new Move(this.position, Pose2d.create(this.position.row() + (this.player.getRow() == 0 ? 1 : -1), this.position.col()), this, false);

        final Pose2d pos1 = Pose2d.create(this.position.row() + (this.player.getRow() == 0 ? 1 : -1), this.position.col() + 1);
        final Pose2d pos2 = Pose2d.create(this.position.row() + (this.player.getRow() == 0 ? 1 : -1), this.position.col() - 1);

        if (this.board.containsEnemy(this.position, pos1) || this.board.containsEnemy(this.position, pos2)) {
            if (!this.board.isOutOfBounds(pos1) && (this.board.isFree(pos1) || this.board.containsEnemy(this.position, pos1))) {
                allPossibleMoves.add(new Move(this.position, pos1, this, false));
            }
            if (!this.board.isOutOfBounds(pos2) && (this.board.isFree(pos2) || this.board.containsEnemy(this.position, pos2))) {
                allPossibleMoves.add(new Move(this.position, pos2, this, false));
            }
        } else {
            allPossibleMoves.add(move);
        }
        if (!this.isMoved) {
            Move move2 = new Move(this.position, Pose2d.create(this.position.row() + (this.player.getRow() == 0 ? 2 : -2), this.position.col()), this, false);
            allPossibleMoves.add(move2);
        }
        return allPossibleMoves;
    }
}
