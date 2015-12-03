package game.chess;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChessFx extends Application {
    private Stage primaryStage;
    BorderPane rootLayout;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Chess");

        initRootLayout();
        showPersonOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ChessFx.class.getResource("view/javafx/ChessFx.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(ChessFx.class.getResource("view/javafx/ChessBoard.fxml"));
            GridPane chessBoard = loader1.load();
            chessBoard.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            for (Node node : chessBoard.getChildren()) {
                Integer x = GridPane.getRowIndex(node);
                Integer y = GridPane.getColumnIndex(node);
                int row = x == null ? 0 : x;
                int col = y == null ? 0 : y;
                if (node instanceof Pane) {
                    node.setId("pane"+row+"_"+col);
                }
                row = 8-row-1;

                if ((row+col)%2 == 1) {
                    node.setStyle("-fx-background-color: rgb(255,255,255);");
                } else {
                    node.setStyle("-fx-background-color: rgb(128,128,128);");
                }

                if(row == 1) {
                    ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/bpawn.png")));
                    if (node instanceof Pane){
                        ((Pane)node).getChildren().add(img);
                    }
                }
                else if(row == 6) {
                    ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/wpawn.png")));
                    if (node instanceof Pane){
                        ((Pane)node).getChildren().add(img);
                    }
                }
                else if (row == 0) {
                    if (col == 0 || col== 7) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/brook.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    } else if (col == 1 || col == 6) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/bknight.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    } else if (col == 2 || col == 5) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/bbishop.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }
                    else if (col == 4) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/bking.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }
                    else if (col == 3) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/bqueen.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }

                } else if (row == 7) {
                    if (col == 0 || col== 7) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/wrook.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    } else if (col == 1 || col == 6) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/wknight.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    } else if (col == 2 || col == 5) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/wbishop.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }
                    else if (col == 4) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/wking.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }
                    else if (col == 3) {
                        ImageView img = new ImageView(new Image(ChessFx.class.getResourceAsStream("view/images/wqueen.png")));
                        if (node instanceof Pane){
                            ((Pane)node).getChildren().add(img);
                        }
                    }
                }
                else {
                    ImageView img = new ImageView();
                    if (node instanceof Pane){
                        ((Pane)node).getChildren().add(img);
                    }
                }

            }

            rootLayout.setCenter(chessBoard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ChessFx.launch(args);
    }
}
