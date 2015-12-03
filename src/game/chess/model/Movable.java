package game.chess.model;

/**
 * Created by Satya on 14/06/14.
 */
public interface Movable <T> {
//    T moveTo(Pose2d pos);
    Move isMovableTo(Pose2d pos);
//    T tryToMove(Pose2d pos);
}
