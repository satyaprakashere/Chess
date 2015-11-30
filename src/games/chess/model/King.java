package games.chess.model;

import java.util.ArrayList;
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

    @Override
    public List<Move> getAllPossibleMoves() {
        List<Move> allPossibleMoves = new ArrayList<>();

        int x = this.getCurrentPosition().getColumn();
        int y = this.getCurrentPosition().getRow();
        for (int i=-1; i<=1; ++i) {
            for (int j=-1; j<=1; ++j) {
                Pose2d pos = new Pose2d(y + j, x + i);
                if(i==0 && j==0) {
                    continue;
                }
                if (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.currentPosition, pos))) {
                    allPossibleMoves.add(new Move(this.currentPosition, pos, this, false));
                }
            }
        }
        Pose2d pos = queenSideCastling();
        if (pos != null) {
            if (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.currentPosition, pos))) {
                allPossibleMoves.add(new Move(this.currentPosition, pos, this, false));
            }
        }
        pos = kingSideCastling();
        if (pos != null) {
            if (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.currentPosition, pos))) {
                allPossibleMoves.add(new Move(this.currentPosition, pos, this, false));
            }
        }
        return allPossibleMoves;
    }

    private void checkCastling(){
        queenSideCastling();
        kingSideCastling();

    }

    private Pose2d queenSideCastling() {
        if (moved) {
            return null;
        }
        for(int column = 4; column<7; column++){
            Pose2d pos = new Pose2d(getCurrentPosition().getRow(),column);
            if(!getBoard().isFree(pos)){
                return null;
            }
        }

        Pose2d queenSideTower = new Pose2d(getCurrentPosition().getRow(), 7);
        if (getBoard().containsEnemy(getCurrentPosition(), queenSideTower)) {
            return null;
        }

        if (!getBoard().isUnmovedRook(queenSideTower)) {
            return null;
        }
        return queenSideTower;
    }

    private Pose2d kingSideCastling(){
        if (moved) {
            return null;
        }
        for(int column = 2; column > 0; column--){
            Pose2d pos = new Pose2d(getCurrentPosition().getRow(),column);
            if(!getBoard().isFree(pos)){
                return null;
            }
        }
        Pose2d kingSideTower = new Pose2d(getCurrentPosition().getRow(), 0);
        if (getBoard().containsEnemy(getCurrentPosition(), kingSideTower)) {
            return null;
        }
        if (!getBoard().isUnmovedRook(kingSideTower)) {
            return null;
        }
        return kingSideTower;
    }
}
