import java.util.Scanner;

public class Checker {

    public static int setColor(){
        int color;
        System.out.println("Please, choose the colour of your figures");
        System.out.println("1 -> Red");
        System.out.println("2 -> White");
        Scanner s = new Scanner(System.in);
        color = s.nextInt();
        if(color == 1 || color ==2){
            return color;}
        return setColor();
    }

    public static int setFigire(){
        int figure;
        System.out.println("Please, choose the figure");
        System.out.println("1 -> King");
        System.out.println("2 -> Queen");
        System.out.println("3 -> Knight");
        System.out.println("4 -> Bishop");
        System.out.println("5 -> Rook");
        System.out.println("6 -> Pawn");
        Scanner s = new Scanner(System.in);
        figure = s.nextInt();
        if(figure == 1 || figure == 2 || figure == 3 ||
                figure == 4 || figure == 5 || figure == 6){
            return figure;}
        return  setFigire();
    }

    public static int setX(int colour){
        Scanner s = new Scanner(System.in);
        System.out.println("Please, enter the x: 1-8");
        int x = s.nextInt();
        if(x > 0 && x <= 8){
            if (colour == 1){
                x = 9 - x;
                return x;
            }
            return x;
        }
        return setX(colour);
    }


    public static char setY(){
        Scanner s = new Scanner(System.in);
            System.out.println("Please, enter the y: A-H");
            char y = s.next().charAt(0);
            if(y == 'A' || y == 'B' || y == 'C' ||y == 'D' ||
                    y == 'E' || y == 'F' || y == 'G' || y == 'H'){
                return y;
            }
        return setY();
    }

}
