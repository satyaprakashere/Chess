package game.chess.model;

/**
 * Created by Satya on 14/06/14.
 */
public class Move {
    private final Pose2d startPosition;
    private final Pose2d targetPosition;
//    private final Piece movingPiece;
//    private final boolean IS_CASTLING;

    public Move(Pose2d startPosition, Pose2d targetPosition, Piece movingPiece, boolean IS_CASTLING) {
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
//        this.movingPiece = movingPiece;
//        this.IS_CASTLING = IS_CASTLING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (!startPosition.equals(move.startPosition)) return false;
        return targetPosition.equals(move.targetPosition);

    }

    @Override
    public int hashCode() {
        int result = startPosition.hashCode();
        result = 31 * result + targetPosition.hashCode();
        return result;
    }

    public Pose2d getTargetPosition() {
        return targetPosition;
    }

//    @Override
//    public String toString() {
//        return "Move " +
//                ", movingPiece=" + movingPiece +
//                "{startPosition=" + startPosition +
//                "to targetPosition=" + targetPosition +
//                ( IS_CASTLING  ? ", CASTLING }" : "}");
//    }

}
