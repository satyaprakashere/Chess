package games.chess.model;

/**
 * Created by Satya on 26/06/14.
 */
public class Game {
    private ChessBoard chessBoard;
    private Player currentPlayer;

    public Game(ChessBoard chessBoard, Player currentPlayer) {
        this.chessBoard = chessBoard;
        this.currentPlayer = currentPlayer;
    }
}
