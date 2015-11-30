package games.chess.model;

import java.util.List;
import java.util.Set;

/**
 * Created by Satya on 14/06/14.
 */
class Rook extends Piece {
    private final boolean moved;
    public Rook(Pose2d position, Player player, ChessBoard board) {
        super(position, player, board);
        moved = false;
    }

    private Rook(Pose2d position, Player player, ChessBoard board, List<Move> moves) {
        super(position, player, board, moves);
        moved = true;
    }

    public boolean isMoved() {
        return moved;
    }

    @Override
    public List<Move> getAllPossibleMoves() {
        getEastMoves();
        getWestMoves();
        getNorthMoves();
        getSouthMoves();
        return allPossibleMoves;
    }

    private void getWestMoves() {
        final int j = this.getCurrentPosition().getRow();

        for (int i=this.getCurrentPosition().getColumn(); i>=0; --i) {
            this.addMove(new Pose2d(j,i));
        }
    }

    private void getEastMoves() {
        final int j = this.getCurrentPosition().getRow();

        for (int i=this.getCurrentPosition().getColumn(); i<ChessBoard.COLS; ++i) {
            this.addMove(new Pose2d(j,i));
        }
    }

    private void getNorthMoves() {
        final int i = this.getCurrentPosition().getColumn();

        for (int j=this.getCurrentPosition().getRow(); j<ChessBoard.ROWS; ++j) {
            this.addMove(new Pose2d(j,i));
        }
    }

    private void getSouthMoves() {
        final int i = this.getCurrentPosition().getColumn();

        for (int j=this.getCurrentPosition().getRow(); j>=0; --j) {
            this.addMove(new Pose2d(j,i));
        }
    }
}

