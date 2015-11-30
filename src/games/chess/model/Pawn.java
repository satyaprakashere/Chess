package games.chess.model;

import java.util.*;

/**
 * Created by Satya on 14/06/14.
 */
public class Pawn extends Piece {
    public Pawn(Pose2d position, Player player, ChessBoard board) {
        super(position, player, board);
    }

    private Pawn(Pose2d position, Player player, ChessBoard board, List<Move> moves) {
        super(position, player, board, moves);
    }


    @Override
    public List<Move> getAllPossibleMoves() {
        Move move = new Move(this.currentPosition, new Pose2d(this.currentPosition.getRow() + (this.player.getRow() == 0 ? 1 : -1), this.currentPosition.getColumn()), this, false);

        if (!this.board.isFree(move.getTargetPosition())) {
            this.addMove(new Pose2d(move.getTargetPosition().getRow(), move.getTargetPosition().getColumn()+1));
            this.addMove(new Pose2d(move.getTargetPosition().getRow(), move.getTargetPosition().getColumn()-1));
        } else {
            allPossibleMoves.add(move);
        }
        return allPossibleMoves;
    }
}
