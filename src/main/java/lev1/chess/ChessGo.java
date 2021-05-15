package lev1.chess;
import java.util.Scanner;

public class ChessGo {
    public static void chessRun(){
        Scanner scanner = new Scanner(System.in);
        int x_now=0;
        int y_now=0;
        int x_new=0;
        int y_new=0;
        System.out.println("Enter start position(As x-y)");
        String a = scanner.next();
        String[] str = a.split("-");
        x_now = Integer.parseInt(str[0]);
        y_now = Integer.parseInt(str[1]);
        while (true) {
            System.out.println("Enter new position or 'x' to exit ");
            a = scanner.next();
            str = a.split("-");
            if(str[0].equals("x")){
              return;
            }
            else{
                x_new = Integer.parseInt(str[0]);
                y_new = Integer.parseInt(str[1]);
                if (StepValid.isStepValid(x_now, y_now, x_new, y_new) && StepValid.isStepInBoard(x_new, y_new)) {
                    x_now = x_new;
                    y_now = y_new;
                    System.out.println("Successfully, now knight at " + x_now + "-" + y_now);
                } else {
                    System.out.println("Invalid area, try again");
                }
            }


        }
    }
}
