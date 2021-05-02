import entity.*;

public class CreateFigure {

    public static Figure choosenFigure(int figure, int colour, int a, int b) {
        switch (figure) {
            case 1: {
                King king = new King();
                fillParameters(king, colour, a, b);
                return king;
            }
            case 2: {
                Queen queen = new Queen();
                fillParameters(queen, colour, a, b);
                return queen;
            }
            case 3: {
                Knight knight = new Knight();
                fillParameters(knight, colour, a, b);
                return knight;
            }
            case 4: {
                Bishop bishop = new Bishop();
                fillParameters(bishop, colour, a, b);
                return bishop;
            }
            case 5: {
                Rook rook = new Rook();
                fillParameters(rook, colour, a, b);
                return rook;
            }
            case 6: {
                Pawn pawn = new Pawn();
                fillParameters(pawn, colour, a, b);
                return pawn;
            }
            default:
                System.out.println("Error");
        }
        return null;
    }

    public static void fillParameters(Figure figure,  int colour,
                                      int a, int b){
        figure.setColour(colour);
        figure.setX(a);
        figure.setY(b);
    }

    public static int defineY(char Y){
        switch (Y){
            case'A' :{
                return 0;
            }
            case 'B':{
                return 1;
            }
            case 'C':{
                return 2;
            }
            case 'D':{
                return 3;
            }
            case 'E':{
                return 4;
            }
            case 'F':{
                return 5;
            }
            case 'G':{
                return 6;
            }
            case 'H':{
                return 7;
            }
            default:
                System.out.println("Error");
        }
        return -1;
    }

}
