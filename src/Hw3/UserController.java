import Figures.*;

import java.util.Scanner;

public class UserController {
    private Chessboard chessboard = new Chessboard();


    void userInterface(){
        Scanner scanner = new Scanner(System.in);
        int choose = 0;
        Figure figure = null;

        do{
            System.out.println("Do you want to choose new figure? 1-yes, 0-no");
            choose = scanner.nextInt();
            if(choose == 1){
                figure = selectNewFigure();

                if(figure == null){
                    System.out.println("Error!");
                }
                chessboard.drawField();
                if(moveFigure(figure)){
                    System.out.println("Figure was successfully moved");
                    chessboard.drawField();
                }
                else{
                    System.out.println("Moving was not successful");
                }
            }
            else{
                if(figure == null){
                    System.out.println("You didn't choose the figure");
                }
                else{
                    if(moveFigure(figure)){
                        System.out.println("Figure was successfully moved");
                        chessboard.drawField();
                    }
                    else{
                        System.out.println("Moving was not successful");
                    }
                }
            }

            System.out.println("Do you want to continue? 1-yes, 0-no");
            choose = scanner.nextInt();



        } while(choose != 0);

    }


    private Figure selectNewFigure(){
        Scanner scanner = new Scanner(System.in);
        int choose = 0;

        System.out.println("Choose which figure you want to be like");
        System.out.println(" 1-Pawn   2-Knight   3-Bishop   4-Rook   5-Queen   6-King");
        choose = scanner.nextInt();

        switch (choose){
            case 1: {
                System.out.println("You choose Pawn");
                Figure pawn = new Pawn();
                System.out.println("Choose the color of figure: 1-white, 2-black");
                choose = scanner.nextInt();
                if(choose == 1){
                    pawn.setColor(ColorEnum.WHITE);
                    //default position of a figure
                    pawn.setX((byte)6);
                    pawn.setY((byte)3);
                }
                else{
                    pawn.setColor(ColorEnum.BLACK);
                    pawn.setX((byte)1);
                    pawn.setY((byte)3);
                }
                chessboard.putFigure(pawn,FiguresEnum.PAWN);
                return pawn;
            }

            case 2: {
                System.out.println("You choose Knight");
                Figure knight = new Knight();
                System.out.println("Choose the color of figure: 1-white, 2-black");
                choose = scanner.nextInt();
                if(choose == 1){
                    knight.setColor(ColorEnum.WHITE);
                    //default position of a figure
                    knight.setX((byte)7);
                    knight.setY((byte)1);
                }
                else{
                    knight.setColor(ColorEnum.BLACK);
                    knight.setX((byte)0);
                    knight.setY((byte)1);
                }
                chessboard.putFigure(knight,FiguresEnum.KNIGHT);
                return knight;
            }

            case 3: {
                System.out.println("You choose Bishop");
                Figure bishop = new Bishop();
                System.out.println("Choose the color of figure: 1-white, 2-black");
                choose = scanner.nextInt();
                if(choose == 1){
                    bishop.setColor(ColorEnum.WHITE);
                    //default position of a figure
                    bishop.setX((byte)7);
                    bishop.setY((byte)2);
                }
                else{
                    bishop.setColor(ColorEnum.BLACK);
                    bishop.setX((byte)0);
                    bishop.setY((byte)2);
                }
                chessboard.putFigure(bishop,FiguresEnum.BISHOP);
                return bishop;
            }

            case 4: {
                System.out.println("You choose Rook");
                Figure rook = new Rook();
                System.out.println("Choose the color of figure: 1-white, 2-black");
                choose = scanner.nextInt();
                if(choose == 1){
                    rook.setColor(ColorEnum.WHITE);
                    //default position of a figure
                    rook.setX((byte)7);
                    rook.setY((byte)0);
                }
                else{
                    rook.setColor(ColorEnum.BLACK);
                    rook.setX((byte)0);
                    rook.setY((byte)0);
                }
                chessboard.putFigure(rook,FiguresEnum.ROOK);
                return rook;
            }

            case 5: {
                System.out.println("You choose Queen");
                Figure queen = new Queen();
                System.out.println("Choose the color of figure: 1-white, 2-black");
                choose = scanner.nextInt();
                if(choose == 1){
                    queen.setColor(ColorEnum.WHITE);
                    //default position of a figure
                    queen.setX((byte)7);
                    queen.setY((byte)4);
                }
                else{
                    queen.setColor(ColorEnum.BLACK);
                    queen.setX((byte)0);
                    queen.setY((byte)4);
                }
                chessboard.putFigure(queen,FiguresEnum.QUEEN);
                return queen;
            }

            case 6: {
                System.out.println("You choose King");
                Figure king = new King();
                System.out.println("Choose the color of figure: 1-white, 2-black");
                choose = scanner.nextInt();
                if(choose == 1){
                    king.setColor(ColorEnum.WHITE);
                    //default position of a figure
                    king.setX((byte)7);
                    king.setY((byte)3);
                }
                else{
                    king.setColor(ColorEnum.BLACK);
                    king.setX((byte)0);
                    king.setY((byte)3);
                }
                chessboard.putFigure(king,FiguresEnum.KING);
                return king;
            }

            default: {
                System.out.println("!Incorrect value entered!");
                return null;
            }
        }
    }



    private boolean moveFigure(Figure figure){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter coordinates on which you want to move the figure:");
        byte newX = scanner.nextByte();
        byte newY = scanner.nextByte();

        if(newX < 0 || newX > chessboard.getFIELD_SIZE()){
            System.out.println("Incorrect value!");
            return false;
        }
        if(newY < 0 || newY > chessboard.getFIELD_SIZE()){
            System.out.println("Incorrect value!");
            return false;
        }

        if(figure.move(newX,newY)){
            chessboard.putFigure(figure,figure.getEnumType());
            return true;
        }
        return false;
    }


    private void drawChessDesk(){
        chessboard.drawField();
    }

    //parse move

}