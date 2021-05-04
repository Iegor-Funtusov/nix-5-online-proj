package ua.com.nkrasnovoronka.unit3.board;

import ua.com.nkrasnovoronka.unit3.chesspiace.AbstractFigure;
import ua.com.nkrasnovoronka.unit3.chesspiace.Square;
import ua.com.nkrasnovoronka.unit3.util.Constants;

import java.util.Arrays;

/**
 * This class contains 2d array of squares
 */
public class ChessBoard {
    private Square[][] chessBoard = new Square[Constants.BOARD_SIZE][Constants.BOARD_SIZE];

    public ChessBoard() {
        fillBoardWithSquares();
    }

    /**
     * Filling array with Square objects
     */
    private void fillBoardWithSquares() {
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                //setting chess board coordinates
                char c = (char) (i + 97);
                String s = Character.toString(c) + (j + 1);
                chessBoard[i][j] = new Square(s);
            }
        }
    }

    public void clearBoard() {
        chessBoard = new Square[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        fillBoardWithSquares();
    }

    //to display board in console
    public void drawBoard() {
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                System.out.printf("%10s", chessBoard[j][i]);
            }
            System.out.println();
        }
    }

    public void addFigureToBoard(String a1, AbstractFigure figure) {
        findSquareByChessPosition(a1).setFigure(figure);
    }

    // find from chess coordinates like a1, b2 square in array
    private Square findSquareByChessPosition(String position) {
        return Arrays.stream(chessBoard)
                .flatMap(Arrays::stream)
                .filter(square -> square.getChessPosition().equals(position)).findFirst().orElseThrow();
    }

    // move figure from one square to another
    public boolean moveFigure(String from, String to) {
        Square fromSquare = findSquareByChessPosition(from);
        Square toSquare = findSquareByChessPosition(to);

        AbstractFigure figure = fromSquare.getFigure();
        if (figure != null) {
            if (figure.isMoveValid(fromSquare, toSquare)) {
                findSquareByChessPosition(to).setFigure(fromSquare.getFigure());
                fromSquare.setFigure(null);
                return true;
            }
        } else {
            throw new IllegalArgumentException("Can`t find figure");
        }
        return false;
    }
}
