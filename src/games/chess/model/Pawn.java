package games.chess.model;

import java.util.*;

/**
 * Created by Satya on 14/06/14.
 */
public class Pawn extends Piece {
    private boolean isMoved = false;
    public Pawn(Pose2d position, Player player, ChessBoard board) {
        super(position, player, board);
    }
    @Override
    public Piece moveTo(Pose2d position) {
        this.board.setPiece(position, this);
        this.board.setPiece(this.currentPosition, null);
        this.currentPosition = position;
        this.isMoved = true;
        return this;
    }
    @Override
    public List<Move> getAllPossibleMoves() {
        final List<Move> allPossibleMoves = new ArrayList<>();

        final int dx = this.isMoved ? 1 : 2;
        Move move = new Move(this.currentPosition, new Pose2d(this.currentPosition.getRow() + (this.player.getRow() == 0 ? dx : -dx), this.currentPosition.getColumn()), this, false);

        final Pose2d pos1 = new Pose2d(this.currentPosition.getRow() + (this.player.getRow() == 0 ? 1 : -1), this.currentPosition.getColumn() + 1);
        final Pose2d pos2 = new Pose2d(this.currentPosition.getRow() + (this.player.getRow() == 0 ? 1 : -1), this.currentPosition.getColumn() - 1);

        if (!this.board.isFree(move.getTargetPosition()) || this.board.containsEnemy(this.currentPosition, pos1) || this.board.containsEnemy(this.currentPosition, pos2)) {
            if (!this.board.isOutOfBounds(pos1) && (this.board.isFree(pos1) || this.board.containsEnemy(this.currentPosition, pos1))) {
                allPossibleMoves.add(new Move(this.currentPosition, pos1, this, false));
            }
            if (!this.board.isOutOfBounds(pos2) && (this.board.isFree(pos2) || this.board.containsEnemy(this.currentPosition, pos2))) {
                allPossibleMoves.add(new Move(this.currentPosition, pos2, this, false));
            }
        } else {
            allPossibleMoves.add(move);
        }
        return allPossibleMoves;
    }
}
