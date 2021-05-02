import entities.Board;
import entities.Piece;

import utils.KoordsUtils;
import utils.PieceUtils;

import java.util.Objects;
import java.util.Scanner;

public class Chess {

    public static void main(String[] args) {

        Board board = new Board();
        boolean flag = true;

        while (flag) {

            System.out.println("""
                    Choose the colour:
                    1. White
                    2. Black""");
            Scanner s = new Scanner(System.in);
            int colour = s.nextInt();

            System.out.println("""
                    Choose the piece type:
                    1 - King
                    2 - Queen
                    3 - Horse
                    4 - Bishop
                    5 - Rook
                    6 - Pawn""");
            int chosenfigure = s.nextInt();

            System.out.println("Enter the cell koords");
            System.out.println("x: 1-8");
            int x = s.nextInt();

            System.out.println("y: A-H");
            char y = s.next().charAt(0);

            if(!(board.ifEmpty(x - 1, KoordsUtils.defineY(y)))){
                System.out.println("Cell isn't empty");
            }
            else{

                boolean turn = true;
                while(turn){
                    board.setPiece(Objects.requireNonNull(PieceUtils.switchPiece(chosenfigure, colour, x - 1, KoordsUtils.defineY(y))));
                    board.printBoard();
                    System.out.println("Enter the cell koords");

                    while(true){

                        System.out.println("x: 1-8");
                        x = s.nextInt();
                        System.out.println("y: A-H");
                        y = s.next().charAt(0);
                        Piece f = Board.pieces.get(Board.pieces.size()-1);
                        System.out.println(f);
                        if(f.move(x - 1, KoordsUtils.defineY(y))) {
                            board.fillEmptyCell(f.getX(), f.getY());
                            Board.pieces.remove(Board.pieces.size() - 1);
                            f.setX(x - 1);
                            f.setY(KoordsUtils.defineY(y));
                            board.setPiece(f);
                            board.printBoard();
                            break;
                        }
                    }

                    System.out.println("1 - Next turn or 2 - New piece?");
                    int ans = s.nextInt();
                    if (ans == 2){
                        turn = false;
                    }
                }
            }
            System.out.println("Do you want to exit the game? y/n");
            char ans = s.next().charAt(0);
            if (ans == 'y'){
                flag = false;
            }
        }
    }
}