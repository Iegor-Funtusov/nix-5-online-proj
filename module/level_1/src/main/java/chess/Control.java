package chess;

import java.util.Scanner;

public class Control {
    public static int correctNum(){
        Scanner sc = new Scanner(System.in);
        String num;
        int flag = 0;
        num = sc.nextLine();
        char exit = num.charAt(0);
        if (num.charAt(0) == '-')
            flag = 1;
        for(int i = flag; i < num.length(); i++){
            if(!Character.isDigit(num.charAt(i))){
                System.out.println("Input only integer. Repeat entering");
                return correctNum();
            }
        }
        int num1 = Integer.parseInt(num);
        if(num1 < -1000000 || num1 > 1000000)
        {
            System.out.println("Your number is too big. Input less number.");
            return correctNum();
        }
        return num1;
    }

    public static void figure (int vertical, int horizontal) {
        Board board =  new Board();
        board.updatedBoard(horizontal, vertical);
    }


    public static boolean moveKnight (int initialHor, int initialVer, int moveHor, int moveVer) {
        if(((initialVer-2 == moveVer) && (initialHor-1 == moveHor || initialHor+1 == moveHor)) ||
                ((initialVer-1 == moveVer || initialVer+1 == moveVer) && (initialHor+2 == moveHor)) ||
                ((initialVer+2 == moveVer) && (initialHor+1 == moveHor || initialHor-1 == moveHor))||
                ((initialVer-1 == moveVer || initialVer + 1 == moveVer) && (initialHor-2 == moveHor))) {
            return true;
        }
        return false;
    }
}
