package ua.com.nkrasnovoronka.unit3;

import ua.com.nkrasnovoronka.unit3.board.ChessBoard;
import ua.com.nkrasnovoronka.unit3.chesspiace.AbstractFigure;
import ua.com.nkrasnovoronka.unit3.chesspiace.Color;
import ua.com.nkrasnovoronka.unit3.chesspiace.impl.*;
import ua.com.nkrasnovoronka.unit3.util.Constants;
import ua.com.nkrasnovoronka.unit3.util.UserInput;

public class ChessMoveGame {
    public void start() {
        UserInput userInput = new UserInput();
        ChessBoard chessBoard = new ChessBoard();
        boolean nextAction = false;
        putFigure(userInput, chessBoard);
        while (true) {
            if(nextAction){
                putFigure(userInput, chessBoard);
            }
            movingFigure(userInput, chessBoard);
            chessBoard.drawBoard();
            System.out.println("What do next?\n1 - Start new game 2 - Continue : another number to exit");
            nextAction = nextAction(userInput, chessBoard);

        }
    }

    private void putFigure(UserInput userInput, ChessBoard chessBoard) {
        AbstractFigure figure = choseFigure(userInput);
        String startPosition = addFigureToBoardPosition(userInput);

        chessBoard.addFigureToBoard(startPosition, figure);
        chessBoard.drawBoard();
    }

    private boolean nextAction(UserInput userInput, ChessBoard chessBoard) {
        boolean startNew = false;
        int i = userInput.readUserOption();
        switch (i) {
            case 1 -> {
                startNew = true;
                chessBoard.clearBoard();
            }

            case 2 -> startNew = false;
            default -> {
                System.out.println("Closing program");
                System.exit(0);
            }
        }
        return startNew;
    }

    private void movingFigure(UserInput userInput, ChessBoard chessBoard) {
        while (true) {
            String[] movePosition = readMovePosition(userInput);
            boolean b = chessBoard.moveFigure(movePosition[0], movePosition[1]);
            if (b) {
                break;
            }
        }
    }

    private String addFigureToBoardPosition(UserInput userInput) {
        String startPosition = "";
        do {
            startPosition = userInput.readFigureStartPosition();
        } while (!userInput.isValidPosition(startPosition, Constants.START_POSITION_REGEXP));
        return startPosition;
    }

    private String[] readMovePosition(UserInput userInput) {
        String movePosition = "";
        do {
            movePosition = userInput.readMovePosition();
        } while (!userInput.isValidPosition(movePosition, Constants.MOVE_POSITION_REGEXP));
        return movePosition.split(" ");
    }

    private AbstractFigure choseFigure(UserInput userInput) {
        AbstractFigure figure;
        System.out.println("Chose figure");
        System.out.println("1-Bishop 2-King 3-Knight 4-Pawn 5-Queen 6-Rook");
        int i = userInput.readUserOption();
        switch (i) {
            case 1 -> figure = new Bishop();
            case 2 -> figure = new King();
            case 3 -> figure = new Knight();
            case 4 -> figure = new Pawn();
            case 5 -> figure = new Queen();
            case 6 -> figure = new Rook();
            default -> {
                System.out.println("Using pawn figure");
                figure = new Pawn();
            }
        }

        System.out.println("Chose color");
        System.out.println("1-White 2-Black");
        int i1 = userInput.readUserOption();
        switch (i1) {
            case 1 -> figure.setColor(Color.WHITE);
            case 2 -> figure.setColor(Color.BLACK);
            default -> {
                System.out.println("Using black color");
                figure.setColor(Color.BLACK);
            }
        }

        return figure;
    }
}
