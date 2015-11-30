package games.chess.model;

import java.util.List;
import java.util.Set;

/**
 * Created by Satya on 14/06/14.
 */
public final class Bishop extends Piece {

    public Bishop(Pose2d position, Player player, ChessBoard board) {
        super(position, player, board);
    }

    private Bishop(Pose2d position, Player player, ChessBoard board, List<Move> moves) {
        super(position, player, board, moves);
    }

    @Override
    public List<Move> getAllPossibleMoves() {
        getNorthEastMoves();
        getNorthWestMoves();
        getSouthEastMoves();
        getSouthWestMoves();
        return allPossibleMoves;
    }

    private void getNorthEastMoves() {
        int i=this.getCurrentPosition().getColumn();
        int j=this.getCurrentPosition().getRow();
        while (i<ChessBoard.COLS &&  j<ChessBoard.ROWS) {
            this.addMove(new Pose2d(j,i));
            ++i;
            ++j;
        }
    }

    private void getNorthWestMoves() {
        int i=this.getCurrentPosition().getColumn();
        int j=this.getCurrentPosition().getRow();
        while( i>=0 && j<ChessBoard.ROWS) {
            this.addMove(new Pose2d(j,i));
            --i;
            ++j;
        }
    }

    private void getSouthEastMoves() {
        int i=this.getCurrentPosition().getColumn();
        int j=this.getCurrentPosition().getRow();

        while( i<ChessBoard.COLS && j>=0) {
            this.addMove(new Pose2d(j,i));
            ++i;
            --j;
        }
    }

    private void getSouthWestMoves() {
        int i=this.getCurrentPosition().getColumn();
        int j=this.getCurrentPosition().getRow();

        while( i>=0 && j>=0) {
            this.addMove(new Pose2d(j,i));
            --i;
            --j;
        }
    }
}
