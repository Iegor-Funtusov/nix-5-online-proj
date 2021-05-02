import entity.Board;
import entity.Figure;

import java.util.Scanner;

public class ChessRunService {

    public static void main(String[] args) {
        Board board = new Board();
        boolean cycle = true;
        while (cycle) {
            Scanner s = new Scanner(System.in);
            int colour = Checker.setColor();
            int chosenfigure = Checker.setFigire();
            int x = Checker.setX(colour);
            char y = Checker.setY();
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
        int x = Checker.setX(colour);
        char y = Checker.setY();
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
                x = Checker.setX(colour);
                y = Checker.setY();
                f = Board.allfigures.get(Board.allfigures.size() - 1);
            }
        }
    }

}