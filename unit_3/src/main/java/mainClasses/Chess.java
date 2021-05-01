package mainClasses;

import Figures.*;
import mainClasses.Board;

import java.util.Scanner;
public class Chess {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        board.boardPlacing();
        while (true) {
            System.out.println("Choose the piece that you want: \n" +
                    "\'K\' = King | \'Q\' = Queen | \'R\' = Rook | \'B\' = Bishop | \'N\' = Knight | \'P\' = Pawn");
            String figure = sc.nextLine();
            while (!Control.correctFigure(figure)) {
                System.out.println("Incorrect input. Enter again");
                figure = sc.nextLine();
            }

            System.out.println("Choose the color (\'W\' - White, \'B\' - Black)");
            String color = sc.nextLine();
            while (!Control.correctColor(color)) {
                System.out.println("Incorrect input. Enter again");
                color = sc.nextLine();
            }

            System.out.println("Choose the place (e.g. e5 or c1)");
            String place = sc.nextLine();

            while (!Control.correctPlace(place)) {
                System.out.println("Place for piece is not correct. Enter again");
                place = sc.nextLine();
            }


            char letter = place.charAt(0);
            char number = place.charAt(1);
            char fig = figure.charAt(0);

            if (fig == 'P' || fig == 'p') {
                Pawn pawn = new Pawn();
                pawn.pawn(place, color);
            } else if (fig == 'R' || fig == 'r') {
                Rook rook = new Rook();
                rook.rook(place, color);
            } else if (fig == 'K' || fig == 'k') {
                King king = new King();
                king.king(place, color);
            } else if (fig == 'B' || fig == 'b') {
                Bishop bishop = new Bishop();
                bishop.bishop(place, color);
            } else if (fig == 'Q' || fig == 'q') {
                Queen queen = new Queen();
                queen.queen(place, color);
            }
            else if (fig == 'N' || fig == 'n') {
                Knight knight = new Knight();
                knight.knight(place, color);
            }
            System.out.println("If you want to exit from program \'E\' else - \'N\'");
            String exitLine = sc.nextLine();
            exitLine = exitLine.toLowerCase();
            char exit = exitLine.charAt(0);

            if (exit == 'e' || exit == 'E')
                return;
        }
    }
}
