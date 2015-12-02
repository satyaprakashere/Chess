package game.chess.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Satya on 14/06/14.
 */
public class Knight extends Piece {
    public Knight(Pose2d position, Player player, ChessBoard board) {
        super(position, player, board);
    }
    @Override
    public Piece withPosition(Pose2d pos) {
        return new Knight(pos, this.player, this.board);
    }
    @Override
    public Set<Move> allPossibleMoves() {
        Set<Move> allPossibleMoves = new HashSet<>(16);

        int x = this.position().col();
        int y = this.position().row();
        for (int i=-2; i<=2; ++i) {
            for (int j=-2; j<=2; ++j) {
                Pose2d pos = Pose2d.create(y + j, x + i);
                if(i+j==3) {
                    if (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.position, pos))) {
                        allPossibleMoves.add(new Move(this.position, pos, this, false));
                    }
                }
            }
        }
        return allPossibleMoves;
    }

}
