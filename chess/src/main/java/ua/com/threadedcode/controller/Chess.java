package ua.com.threadedcode.controller;

import ua.com.threadedcode.figure.Figure;

import java.util.Arrays;

public class Chess {
    private Board board;
    private Player player1, player2;

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String chooseColor(String color) {
        if (color.equals("white")) {
            return "white";
        } else {
            return "black";
        }
    }

    public Player getPlayer(String figureColor) {
        if (figureColor.equals("white")) {
            return player1;
        }
        return player2;
    }

    public void run(String figureColor) {
        player1 = new Player();
        player2 = new Player();

        player1.setFigureColor(chooseColor(figureColor));
        player2.setFigureColor(chooseColor(figureColor));
        board = new Board(player1, player2);
    }

    public Figure selectFigure(int x, int y, String figureColor) {
        Player player = board.getPlayer(x, y, figureColor);
        return board.getFigure(x, y, player);
    }

    public void moveFigure(int targetX, int targetY, Figure figure) {
        board.moveFigure(targetX, targetY, figure);
    }

    public void showBoard() {
        Arrays.stream(board.getCellBoard()).map(a -> Arrays.toString(a)).
                forEach(x -> System.out.printf("%s%n", x.toString()));
    }


}
