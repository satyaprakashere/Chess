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
//    @FXML
//    protected void onClick(MouseEvent e) {
//        System.out.println(e.getX());
//        System.out.println(e.getSceneX());
//
//        for (Node view : pane01.getChildren()) {
//            if (view instanceof ImageView){
//                ((ImageView)view).setImage(new Image(ChessFx.class.getResourceAsStream("chess/view/images/bqueen.png")));
//            }
//        }
//    }
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
            List<Move> moves = chessBoard.getPiece(new Pose2d(row, col)).getAllPossibleMoves();
            currentPiece = chessBoard.getPiece(new Pose2d(row, col));
            for(Move move : moves) {
                Pane pane = board[move.getTargetPosition().getRow()][move.getTargetPosition().getColumn()];
                Rectangle r = new Rectangle(0, 0, pane.getWidth(), pane.getHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.RED);
                r.setStrokeWidth(2);
                pane.getChildren().add(r);
                for (Node view : pane.getChildren()) {
                    if (view instanceof ImageView) {
                        ((ImageView) view).setImage(new Image(ChessFx.class.getResourceAsStream("view/images/bqueen.png")));
                    }
                }
            }
            playing = true;
        } else {
            Pane pane = board[row][col];
            currentPiece.moveTo(new Pose2d(row, col));
            playing = false;
        }

    }
}
