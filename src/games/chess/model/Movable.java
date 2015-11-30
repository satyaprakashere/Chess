package games.chess.model;

/**
 * Created by Satya on 14/06/14.
 */
public interface Movable <T> {
    T moveTo(Pose2d position);
}