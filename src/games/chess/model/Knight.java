package games.chess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Satya on 14/06/14.
 */
public class Knight extends Piece {
    public Knight(Pose2d position, Player player, ChessBoard board) {
        super(position, player, board);
    }

    @Override
    public List<Move> getAllPossibleMoves() {
        List<Move> allPossibleMoves = new ArrayList<>();

        int x = this.getCurrentPosition().getColumn();
        int y = this.getCurrentPosition().getRow();
        for (int i=-2; i<=2; ++i) {
            for (int j=-2; j<=2; ++j) {
                Pose2d pos = new Pose2d(y + j, x + i);
                if(i+j==3) {
                    if (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.currentPosition, pos))) {
                        allPossibleMoves.add(new Move(this.currentPosition, pos, this, false));
                    }
                }
            }
        }
        return allPossibleMoves;
    }

}
