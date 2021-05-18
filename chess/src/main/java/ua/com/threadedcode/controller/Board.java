package ua.com.threadedcode.controller;

import ua.com.threadedcode.figure.Figure;
import ua.com.threadedcode.figure.Knight;

public class Board {
    private final int boardSizeX = 8;
    private final int boardSizeY = 8;
    private Player player1, player2;
    private Figure[][] cellBoard = new Figure[boardSizeX][boardSizeY];

    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        initBoard();
    }

    private void initBoard() {
        cellBoard[1][0] = new Knight(1, 0, "knight_left", player1);
        cellBoard[6][0] = new Knight(6, 0, "knight_right", player1);
    }

    public Figure[][] getCellBoard() {
        return cellBoard;
    }

    public Player getPlayer(int x, int y, String figureColor) {

        if (figureColor.equals(player1.getFigureColor())) {
            return player1;
        }
        return player2;
    }

    public Figure getFigure(int x, int y, Player player) {
        if (!cellBoard[x][y].getPlayer().getFigureColor().equals(player.getFigureColor()) || cellBoard[x][y] == null) {
            throw new Error("Oooops");
        }
        return cellBoard[x][y];
    }

    public boolean isValidCellBoardBoundary(int targeX, int targetY, Figure figure) {
        if (targeX < 0 || targeX > (boardSizeX - 1) || targetY < 0 || targetY > (boardSizeY - 1)) {
            try {
                throw new Exception("illegal move, try more");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("currentX " + figure.getX() + " , currentY: " + figure.getY());
            }
        }
        return true;
    }

    public void moveFigure(int targetX, int targetY, Figure figure) {
        if (figure.isValidPath(figure.getX(), figure.getY(), targetX, targetY) &&
                isValidCellBoardBoundary(targetX, targetY, figure)
        ) {
            cellBoard[figure.getX()][figure.getY()] = null;
            figure.setX(targetX);
            figure.setY(targetY);
            cellBoard[figure.getX()][figure.getY()] = figure;
        } else {
            try {
                throw new Exception("illegal move, try more");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("currentX " + figure.getX() + " , currentY: " + figure.getY());
            }
        }
    }
}
