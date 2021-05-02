package entity;

import constants.Colour;

import java.util.ArrayList;

public class Board {
    private char [][] board;
    public static ArrayList<Figure> allfigures = new ArrayList<Figure>();

    public boolean setBoard(int a, int b){
        if(board[a][b] == '*'){
            return true;
        }
        return false;
    }

    public boolean checkEmptyCell(int x, int y){
        for (Figure figure: allfigures){
            if(figure.getX() == x && figure.getY() == y){
                return false;
            }
        }
        return true;
    }

    public void setOneCell(int x, int y){
        board[x][y] = '*';
    }

    public void setnewFigure(Figure figure){
        if(setBoard(figure.getX(),figure.getY()) == true){
            board[figure.getX()][figure.getY()] = figure.getName();
            allfigures.add(figure);
        }
    }

    public Board() {
        board = new char [8][8];
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                board[i][j] = '*';
            }
        }
    }

    public void showBoard() {
        System.out.println("      A     B     C     D     E     F     G     H   ");
        for (int i = 0; i < 8; i++) {
            System.out.println("   #################################################");
            System.out.println("   #     #     #     #     #     #     #     #     #");
            System.out.print( i+1 + "  ");
            for (int j = 0; j < 8; j++) {
                if(setBoard(i, j) == false){
                    for(Figure figure : allfigures) {
                            if(figure.getX() == i && figure.getY() == j){
                            System.out.print("#  " + figure.getColour() +board[i][j] + "  " + Colour.ANSI_RESET);
                        }
                    }
                }
                else if ((i%2!=0 && j%2==0) || (i%2==0 && j%2!=0)  ){
                System.out.print("#  "+ Colour.ANSI_CYAN+ board[i][j] + "  " + Colour.ANSI_RESET);
                }
                else {
                    System.out.print("#  " + board[i][j] + "  " );
                }
            }
            System.out.print("#");
            System.out.println(" ");
        }
        System.out.println("   #################################################");

    }


}
