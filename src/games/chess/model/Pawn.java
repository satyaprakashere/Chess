package games.chess.model;

import java.util.*;

/**
 * Created by Satya on 14/06/14.
 */
public class Pawn extends Piece {
    public Pawn(Pose2d position, Player player, ChessBoard board) {
        super(position, player, board);
    }

    @Override
    public List<Move> getAllPossibleMoves() {
        List<Move> allPossibleMoves = new ArrayList<>();

        Move move = new Move(this.currentPosition, new Pose2d(this.currentPosition.getRow() + (this.player.getRow() == 0 ? 1 : -1), this.currentPosition.getColumn()), this, false);

        if (!this.board.isFree(move.getTargetPosition())) {
            allPossibleMoves.add(new Move(this.currentPosition, new Pose2d(move.getTargetPosition().getRow(), move.getTargetPosition().getColumn() + 1), this, false));
            allPossibleMoves.add(new Move(this.currentPosition, new Pose2d(move.getTargetPosition().getRow(), move.getTargetPosition().getColumn() + 1), this, false));
        } else {
            allPossibleMoves.add(move);
        }
        return allPossibleMoves;
    }
}
