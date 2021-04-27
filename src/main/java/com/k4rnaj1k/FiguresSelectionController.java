package com.k4rnaj1k;

import java.net.URL;
import java.util.ResourceBundle;

import com.k4rnaj1k.heroes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FiguresSelectionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPaneFigs;

    @FXML
    private Label queen;

    @FXML
    private Label king;

    @FXML
    private Label rook;

    @FXML
    private Button doneBtn;

    @FXML
    private Label bishop;

    @FXML
    private Label knight;

    @FXML
    private Label pawn;

    @FXML
    private Label currfig;

    @FXML
    private CheckBox blackfiguresCheckBox;

    @FXML
    void onDoneClicked(ActionEvent event) {
        Stage stage = (Stage) doneBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onKingClicked(MouseEvent event) {
        ChessBoardController.selectedFigure = new King(king.getText());
        updateCurFigLabel();
    }

    @FXML
    void onQueenClicked(MouseEvent event) {
        ChessBoardController.selectedFigure = new Queen(queen.getText());
        updateCurFigLabel();
    }

    @FXML
    void onRookClicked(MouseEvent event) {
        ChessBoardController.selectedFigure = new Rook(rook.getText());
        updateCurFigLabel();
    }

    @FXML
    void onBishopClicked(MouseEvent event) {
        ChessBoardController.selectedFigure = new Bishop(bishop.getText());
        updateCurFigLabel();
    }

    @FXML
    void onKnightClicked(MouseEvent event) {
        ChessBoardController.selectedFigure = new Knight(knight.getText());
        updateCurFigLabel();
    }

    @FXML
    void onPawnClicked(MouseEvent event) {
        ChessBoardController.selectedFigure = new Pawn(pawn.getText());
        updateCurFigLabel();
    }

    @FXML
    void onfiguresStateChange(ActionEvent event) {
        if (!blackfiguresCheckBox.isSelected()) {
            king.setText("♔");
            queen.setText("♕");
            rook.setText("♖");
            bishop.setText("♗");
            knight.setText("♘");
            pawn.setText("♙");
        } else {
            king.setText("♚");
            queen.setText("♛");
            rook.setText("♜");
            bishop.setText("♝");
            knight.setText("♞");
            pawn.setText("♟");
        }
    }

    @FXML
    void initialize() {
        updateCurFigLabel();
    }

    void updateCurFigLabel() {
        currfig.setText(ChessBoardController.selectedFigure.toString());
    }

}
