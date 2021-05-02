import entity.Board;
import entity.Figure;

import java.util.Scanner;

public class ChessRunService {

    public static void main(String[] args) {
        Board board = new Board();
        boolean cycle = true;
        while (cycle) {
            System.out.println("Please, choose the colour of your figures");
            System.out.println("1 -> Red");
            System.out.println("2 -> White");
            Scanner s = new Scanner(System.in);
            int colour = s.nextInt();
            System.out.println("Please, choose the figure");
            System.out.println("1 -> King");
            System.out.println("2 -> Queen");
            System.out.println("3 -> Knight");
            System.out.println("4 -> Bishop");
            System.out.println("5 -> Rook");
            System.out.println("6 -> Pawn");
            int chosenfigure = s.nextInt();
            System.out.println("Please, enter the x: 1-8");
            int x = s.nextInt();
            System.out.println("Please, enter the y: A-H");
            char y = s.next().charAt(0);
            if (colour == 1) {
                x = 9 - x;
            }
            if (!(board.checkEmptyCell(x - 1, CreateFigure.defineY(y)))) {
                System.out.println("This cell is not empty!");
            } else {
                board.setnewFigure(CreateFigure.choosenFigure(chosenfigure, colour, x - 1, CreateFigure.defineY(y)));
                board.showBoard();
                boolean move = true;
                while (move) {
                    System.out.println("Where would you like to move this figure?");
                    ChessRunService.movement(colour, board);
                    System.out.println("Would you like to move this figure again? y/n");
                    char answer = s.next().charAt(0);
                    if ((answer) == 'n') {
                        move = false;
                        break;
                    }
                }
                System.out.println("Would you like to continue? y/n");
                char answer = s.next().charAt(0);
                if (answer == 'n') {
                    cycle = false;
                }
            }
        }
    }

    public static void movement(int colour, Board board) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please, enter the x: 1-8");
        int x = s.nextInt();
        if (colour == 1) {
            x = 9 - x;
        }
        System.out.println("Please, enter the y: A-H");
        char y = s.next().charAt(0);
        System.out.println(x + "  " + y);
        Figure f = Board.allfigures.get(Board.allfigures.size() - 1);
        while (true) {
            if (f.run(x - 1, CreateFigure.defineY(y))) {
                board.setOneCell(f.getX(), f.getY());
                Board.allfigures.remove(Board.allfigures.size() - 1);
                f.setX(x - 1);
                f.setY(CreateFigure.defineY(y));
                board.setnewFigure(f);
                board.showBoard();
                break;
            } else {
                System.out.println("You can not make this move!");
                System.out.println("Please, enter the x: 1-8");
                x = s.nextInt();
                if (colour == 1) {
                    x = 9 - x;
                }
                System.out.println("Please, enter the y: A-H");
                y = s.next().charAt(0);
                f = Board.allfigures.get(Board.allfigures.size() - 1);
            }
        }
    }
}