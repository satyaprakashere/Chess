package games.chess.model;

/**
 * Created by Satya on 14/06/14.
 */
public class Move {
    private final Pose2d startPosition;

    public Pose2d getTargetPosition() {
        return targetPosition;
    }

    private final Pose2d targetPosition;
    private final Piece movingPiece;
    private final boolean IS_CASTLING;

    public Move(Pose2d startPosition, Pose2d targetPosition, Piece movingPiece, boolean IS_CASTLING) {
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
        this.movingPiece = movingPiece;
        this.IS_CASTLING = IS_CASTLING;
    }

    @Override
    public String toString() {
        return "Move " +
                ", movingPiece=" + movingPiece +
                "{startPosition=" + startPosition +
                "to targetPosition=" + targetPosition +
                ( IS_CASTLING  ? ", CASTLING }" : "}");
    }

}
