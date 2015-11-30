package games.chess.model;

import java.util.ArrayList;
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

    public boolean isMoved() {
        return moved;
    }

    @Override
    public List<Move> getAllPossibleMoves() {
        List<Move> allPossibleMoves = new ArrayList<>();

        Pose2d pos = this.currentPosition;
        int x = pos.getRow();
        int y = pos.getColumn();

        int i = 1;
        pos = new Pose2d(x + i, y);
        while (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.currentPosition, pos))) {
            ++i;
            allPossibleMoves.add(new Move(this.currentPosition, pos, this, false));
            pos = new Pose2d(x + i, y);
        }
        i = 1;
        pos = new Pose2d(x - i, y);
        while (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.currentPosition, pos))) {
            ++i;
            allPossibleMoves.add(new Move(this.currentPosition, pos, this, false));
            pos = new Pose2d(x - i, y);
        }
        i = 1 ;
        pos = new Pose2d(x, y - i);
        while (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.currentPosition, pos))) {
            ++i;
            allPossibleMoves.add(new Move(this.currentPosition, pos, this, false));
            pos = new Pose2d(x, y - i);
        }
        i = 1;
        pos = new Pose2d(x, y + i);
        while (!this.board.isOutOfBounds(pos) && (this.board.isFree(pos) || this.board.containsEnemy(this.currentPosition, pos))) {
            ++i;
            allPossibleMoves.add(new Move(this.currentPosition, pos, this, false));
            pos = new Pose2d(x, y + i);
        }

        return allPossibleMoves;
    }
}

