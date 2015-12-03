package game.chess.model;

import java.util.HashSet;
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
    public Piece withPosition(Pose2d pos) {
        return new King(pos, this.player, this.board);
    }
    @Override
    public Set<Move> allPossibleMoves() {
        Set<Move> allPossibleMoves = new HashSet<>(16);

        int x = this.position().col();
        int y = this.position().row();
        for (int i=-1; i<=1; ++i) {
            for (int j=-1; j<=1; ++j) {
                Pose2d pos = Pose2d.create(y + j, x + i);
                if(i==0 && j==0) {
                    continue;
                }
                if (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.position, pos))) {
                    allPossibleMoves.add(new Move(this.position, pos, this, false));
                }
            }
        }
        Pose2d pos = queenSideCastling();
        if (pos != null) {
            if (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.position, pos))) {
                allPossibleMoves.add(new Move(this.position, pos, this, false));
            }
        }
        pos = kingSideCastling();
        if (pos != null) {
            if (this.isSafeToMove(pos)) {
                allPossibleMoves.add(new Move(this.position, pos, this, false));
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
            Pose2d pos = Pose2d.create(position().row(),column);
            if(!this.board.isFree(pos)){
                return null;
            }
        }

        Pose2d queenSideTower = Pose2d.create(position().row(), 7);
        if (this.board.containsEnemy(position(), queenSideTower)) {
            return null;
        }

        if (!this.board.isUnmovedRook(queenSideTower)) {
            return null;
        }
        return queenSideTower;
    }

    private Pose2d kingSideCastling(){
        if (moved) {
            return null;
        }
        for(int column = 2; column > 0; column--){
            Pose2d pos = Pose2d.create(position().row(),column);
            if(!this.board.isFree(pos)){
                return null;
            }
        }
        Pose2d kingSideTower = Pose2d.create(position().row(), 0);
        if (this.board.containsEnemy(position(), kingSideTower)) {
            return null;
        }
        if (!this.board.isUnmovedRook(kingSideTower)) {
            return null;
        }
        return kingSideTower;
    }
}
