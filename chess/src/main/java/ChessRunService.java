import entity.Board;
import entity.Figure;

import java.util.Scanner;

public class ChessRunService {

    public static void main(String[] args) {
        Board board = new Board();
        boolean cycle = true;
        while (cycle == true) {
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
            if((board.checkEmptyCell(x-1, CreateFigure.defineY(y)))!=true){
                System.out.println("This cell is not empty!");
            }
            else{
            board.setnewFigure(CreateFigure.choosenFigure(chosenfigure, colour, x - 1, CreateFigure.defineY(y)));
            board.showBoard();
            System.out.println("Where would you like to move this figure?");
            while(true){
                System.out.println("Please, enter the x: 1-8");
                x = s.nextInt();
                System.out.println("Please, enter the y: A-H");
                y = s.next().charAt(0);
                Figure f = Board.allfigures.get(Board.allfigures.size()-1);
                System.out.println(f);
                if(f.run(x-1, CreateFigure.defineY(y)) == true) {
                    board.setOneCell(f.getX(), f.getY());
                    Board.allfigures.remove(Board.allfigures.size() - 1);
                    f.setX(x - 1);
                    f.setY(CreateFigure.defineY(y));
                    board.setnewFigure(f);
                    board.showBoard();
                    break;
                }else {
                    continue;
                }
            }
            System.out.println("Would you like to continue? y/n");
            char answer = s.next().charAt(0);
            if (answer == 'n'){
                cycle = false;
            }
        }
        }
    }
}
