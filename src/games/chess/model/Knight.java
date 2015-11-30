package games.chess.model;

import java.util.List;
import java.util.Set;

/**
 * Created by Satya on 14/06/14.
 */
public class Knight extends Piece {
    public Knight(Pose2d position, Player player, ChessBoard board) {
        super(position, player, board);
    }

    private Knight(Pose2d position, Player player, ChessBoard board, List<Move> moves) {
        super(position, player, board, moves);
    }

    @Override
    public List<Move> getAllPossibleMoves() {
        int x = this.getCurrentPosition().getColumn();
        int y = this.getCurrentPosition().getRow();
        for (int i=-2; i<=2; ++i) {
            for (int j=-2; j<=2; ++j) {
                if(i+j==3) {
                    this.addMove(new Pose2d(y + j, x + i));
                }
            }
        }
        return allPossibleMoves;
    }

}
