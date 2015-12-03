package game.chess.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import game.chess.model.*;

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
            currentPiece = chessBoard.pieceAtPosition(Pose2d.create(row, col));
            if (currentPiece == null || currentPiece.player() != currentPlayer) {
                return;
            }
            Pane pane = board[row][col];
            Rectangle r = new Rectangle(0, 0, pane.getWidth(), pane.getHeight());
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.GREEN);
            r.setStrokeWidth(4);
            pane.getChildren().add(r);

            Set<Move> moves = currentPiece.allPossibleMoves();
            if (moves == null) {
                System.out.println("fdsfsd");
            }
            for(Move move : moves) {
                pane = board[move.getTargetPosition().row()][move.getTargetPosition().col()];
                r = new Rectangle(0, 0, pane.getWidth(), pane.getHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.RED);
                r.setStrokeWidth(2);
                pane.getChildren().add(r);
            }
            playing = true;
        } else {

            if (chessBoard.pieceAtPosition(Pose2d.create(row, col)) != null && chessBoard.pieceAtPosition(Pose2d.create(row, col)).player() == currentPlayer) {
                return;
            }
            Pane pane = board[row][col];
            if(currentPiece.isMovableTo(Pose2d.create(row, col)) != null) {
                Pane oldPane = board[currentPiece.position().row()][currentPiece.position().col()];
                Image image = null;
                Rectangle r = null;

                for (Node view : oldPane.getChildren()) {
                    if (view instanceof ImageView) {
                        image = ((ImageView) view).getImage();
                        ((ImageView) view).setImage(null);
                    }
                    if (view instanceof Rectangle) {
                        r = (Rectangle) view;
                    }
                }
                oldPane.getChildren().removeAll(r);

                Set<Move> moves = currentPiece.allPossibleMoves();
                for(Move move : moves) {
                    Pane p = board[move.getTargetPosition().row()][move.getTargetPosition().col()];
                    for (Node rect : p.getChildren()) {
                        if (rect instanceof Rectangle) {
                            r = (Rectangle)rect;
                        }
                    }
                    p.getChildren().removeAll(r);

                }


                for (Node view : pane.getChildren()) {
                    if (view instanceof ImageView) {
                        ((ImageView) view).setImage(image);
                    }
                }
                chessBoard.movePiece(currentPiece, Pose2d.create(row, col));

                playing = false;
                currentPlayer = currentPlayer == Player.WHITE ? Player.BLACK : Player.WHITE;
            }

        }

    }
}
