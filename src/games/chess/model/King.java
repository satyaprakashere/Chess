package games.chess.model;

import java.util.List;
import java.util.Set;

/**
 * Created by Satya on 14/06/14.
 */
public class King extends Piece {
    private final boolean moved;
    public King(final Pose2d position, final Player player, final ChessBoard board) {
        super(position, player, board);
        moved = false;
    }

    private King(final Pose2d position, final Player player, final ChessBoard board, final List<Move> moves) {
        super(position, player, board, moves);
        moved = true;
    }

    @Override
    public List<Move> getAllPossibleMoves() {
        int x = this.getCurrentPosition().getColumn();
        int y = this.getCurrentPosition().getRow();
        for (int i=-1; i<=1; ++i) {
            for (int j=-1; j<=1; ++j) {
                if(i==0 && j==0) {
                    continue;
                }
                this.addMove(new Pose2d(y + j, x + i));
            }
        }
        return allPossibleMoves;
    }

    private void checkCastling(){
        queenSideCastling();
        kingSideCastling();

    }

    private void queenSideCastling() {
        if (moved) {
            return;
        }
        for(int column = 4; column<7; column++){
            Pose2d pos = new Pose2d(getCurrentPosition().getRow(),column);
            if(!getBoard().isFree(pos)){
                return;
            }
        }

        Pose2d queenSideTower = new Pose2d(getCurrentPosition().getRow(), 7);
        if (getBoard().containsEnemy(getCurrentPosition(), queenSideTower)) {
            return;
        }

        if (!getBoard().isUnmovedRook(queenSideTower)) {
            return;
        }
        addMove(queenSideTower);
    }

    private void kingSideCastling(){
        if (moved) {
            return;
        }
        for(int column = 2; column > 0; column--){
            Pose2d pos = new Pose2d(getCurrentPosition().getRow(),column);
            if(!getBoard().isFree(pos)){
                return;
            }
        }
        Pose2d kingSideTower = new Pose2d(getCurrentPosition().getRow(), 0);
        if (getBoard().containsEnemy(getCurrentPosition(), kingSideTower)) {
            return;
        }
        if (!getBoard().isUnmovedRook(kingSideTower)) {
            return;
        }
        addMove(kingSideTower);
    }
}
