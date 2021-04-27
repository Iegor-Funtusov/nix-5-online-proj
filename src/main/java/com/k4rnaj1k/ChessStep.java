package com.k4rnaj1k;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;

public class ChessStep extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/chessBoard.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("icon.png"));
        stage.setTitle("Chess board");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
