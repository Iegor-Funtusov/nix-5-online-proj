package com.k4rnaj1k;

import java.net.URL;
import java.util.ResourceBundle;

import com.k4rnaj1k.heroes.Figure;
import com.k4rnaj1k.heroes.King;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class ChessBoardController {

    private Label figure;

    public static Figure selectedFigure = new King("♔");
    private Affine affine;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Canvas canvas;

    @FXML
    private void OnCanvasClick(ActionEvent event) {
        rootPane.getChildren().remove(figure);
        loadFigureSelection();
    }

    @FXML
    void canvasOnMouseClicked(MouseEvent event) {
        try {
            selectedFigure.placeHero((int) event.getX() / 100, (int) event.getY() / 100);
            rootPane.getChildren().remove(figure);
            figure = new Label(selectedFigure.toString());
            double x = 70 + (int) (event.getX() / 100) * 100;
            double y = 75 + (int) (event.getY() / 100) * 100;
            this.figure.setLayoutX(x);
            this.figure.setLayoutY(y);
            this.figure.setStyle("-fx-background-color:white");
            figure.setFont(new Font("Times New Roman", 40));
            this.rootPane.getChildren().add(figure);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Warning");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    void initialize() {

        this.affine = new Affine();
        this.affine.appendScale(canvas.getHeight() / 8f, canvas.getWidth() / 8f);
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(affine);
        g.setFill(Color.BLACK);
        boolean color = true;
        for (int x = 0; x <= 8; x++) {
            for (int y = 0; y <= 8; y++) {
                color = !color;
                if (color)
                    g.fillRect(y, x, 1, 1);
            }
        }
        for (int i = 0; i < 8; i++) {
            char letter = (char) ('A' + (char) i);
            Label label = new Label(String.valueOf(letter));
            label.setLayoutX(75 + (i * 100));
            label.setLayoutY(10);
            label.setFont(new Font("Comic Sans", 30));

            Label label2 = new Label(String.valueOf(i + 1));
            label2.setLayoutY(75 + i * 100);
            label2.setLayoutX(20);
            label2.setFont(new Font("Comic Sans", 30));
            rootPane.getChildren().addAll(label, label2);
        }
        loadFigureSelection();
    }

    void loadFigureSelection() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            URL xmlUrl = getClass().getResource("/FiguresSelectionBoard.fxml");
            loader.setLocation(xmlUrl);
            Parent root = loader.load();
            stage.setTitle("Figures selection menu");
            stage.getIcons().add(new Image("icon.png"));
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
