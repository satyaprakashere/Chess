package game.chess.model;

/**
 * Created by Satya on 26/06/14.
 */
public class GameSession {
    private ChessBoard chessBoard;
    private Player player1;
    private Player player2;

    public GameSession(ChessBoard chessBoard, Player currentPlayer) {
        this.chessBoard = chessBoard;
//        this.currentPlayer = currentPlayer;
    }
}
