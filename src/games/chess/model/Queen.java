package games.chess.model;

import java.util.List;
import java.util.Set;

/**
 * Created by Satya on 14/06/14.
 */
class Queen extends Piece {

    public Queen(Pose2d position, Player player, ChessBoard board) {
        super(position, player, board);
    }

    private Queen(Pose2d position, Player player, ChessBoard board, List<Move> moves) {
        super(position, player, board, moves);
    }

    @Override
    public List<Move> getAllPossibleMoves() {
        //TODO : check boundary conditions
        int x = this.getCurrentPosition().getColumn();
        int y = this.getCurrentPosition().getRow();
        for (int i=-ChessBoard.COLS; i<=ChessBoard.COLS; ++i) {
            for (int j=-ChessBoard.ROWS; j<=ChessBoard.ROWS; ++j) {
                if(i==0 && j==0) {
                    continue;
                }
                this.addMove(new Pose2d(y + j, x + i));
            }
        }
        return allPossibleMoves;
    }
}
