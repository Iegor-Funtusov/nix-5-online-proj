package lev1.triangle;

import java.awt.*;
import java.util.Scanner;


public class TriangleGo {
    public static void run(){
        Scanner scanner = new Scanner(System.in);


            System.out.println("Enter coordinates for A (As x-y)");
            String [] s = scanner.next().split("-");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            Point A = new Point(x,y);
            System.out.println("Enter coordinates for B (As x-y)");
            s = scanner.next().split("-");
            x = Integer.parseInt(s[0]);
            y = Integer.parseInt(s[1]);
            Point B = new Point(x,y);
            System.out.println("Enter coordinates for C (As x-y)");
            s = scanner.next().split("-");
            x = Integer.parseInt(s[0]);
            y = Integer.parseInt(s[1]);
            Point C = new Point(x,y);
            TriangleSq.Square(A,B,C);



    }
}
