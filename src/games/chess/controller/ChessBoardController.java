package games.chess.controller;

import games.chess.ChessFx;
import games.chess.model.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Set;

/**
 * Created by Satya on 15/06/14.
 */
public class ChessBoardController {
    @FXML private GridPane chessBoardView;
    private ChessBoard chessBoard = new ChessBoard();
    Player currentPlayer = Player.WHITE;
    boolean playing = false;
    Piece currentPiece;
    private Pane board[][] = new Pane[8][8];
    private boolean UI_LOADED = false;
    public ChessBoardController() {
    }

    @FXML
    protected void onMouseClick(MouseEvent e) {
        if(UI_LOADED == false) {
            for (Node node : chessBoardView.getChildren()) {
                if (node instanceof Pane){
                    Pane pane = (Pane)node;
                    board[Character.getNumericValue(pane.getId().charAt(4))][Character.getNumericValue(pane.getId().charAt(6))] = pane;
                }
            }
            UI_LOADED = true;
        }

        double width = chessBoardView.getWidth();
        double height = chessBoardView.getHeight();
        int col = (int)(e.getX()/width*8);
        int row = (int)(e.getY()/height*8);
        System.out.println(row + " : " + col);
        if(playing == false) {
            currentPiece = chessBoard.getPiece(new Pose2d(row, col));
            if (currentPiece.getPlayer() != currentPlayer) {
                return;
            }
            List<Move> moves = currentPiece.getAllPossibleMoves();
            for(Move move : moves) {
                Pane pane = board[move.getTargetPosition().getRow()][move.getTargetPosition().getColumn()];
                Rectangle r = new Rectangle(0, 0, pane.getWidth(), pane.getHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.RED);
                r.setStrokeWidth(2);
                pane.getChildren().add(r);
            }
            playing = true;
        } else {

            if (chessBoard.getPiece(new Pose2d(row, col)).getPlayer() != currentPlayer) {
                return;
            }
            Pane pane = board[row][col];
            if(currentPiece.isMovableTo(new Pose2d(row, col))) {
                Pane oldPane = board[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()];
                Image image = null;
                for (Node view : oldPane.getChildren()) {
                    if (view instanceof ImageView) {
                        image = ((ImageView) view).getImage();
                        ((ImageView) view).setImage(null);
                    }
                }
                for (Node view : pane.getChildren()) {
                    if (view instanceof ImageView) {
                        ((ImageView) view).setImage(image);
                    }
                }
                currentPiece.moveTo(new Pose2d(row, col));

                playing = false;
                currentPlayer = currentPlayer == Player.WHITE ? Player.BLACK : Player.WHITE;
            }

        }

    }
}
