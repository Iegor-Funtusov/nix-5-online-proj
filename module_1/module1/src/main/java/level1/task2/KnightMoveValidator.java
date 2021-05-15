package level1.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KnightMoveValidator {

    public static boolean moveValidator() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter start coordinates X and Y:\n " +
                           "Enter X coordinate for define row (e.g. 1) and press Enter:  ");
    int Xstart = Integer.parseInt(br.readLine());
        System.out.println("Enter Y coordinate for define column (e.g. 2) and press Enter:  ");
    int Ystart = Integer.parseInt(br.readLine());

        System.out.println("Knight start position: X - " + Xstart + " Y - " + Ystart);

        System.out.println("Enter coordinates for expected knight position (X and Y):\n" +
                           "Enter X coordinate for define row:  ");
    int Xend = Integer.parseInt(br.readLine());
        System.out.println("Enter Y coordinate for define column:  ");
    int Yend = Integer.parseInt(br.readLine());

    int dX = Math.abs(Xstart - Xend);
    int dY = Math.abs(Ystart - Yend);

        if((dX ==1&&dY ==2)||dX ==2&&dY ==1)
    {
        System.out.println("Move is valid. Figure moved.");
        System.out.println("Knight end position: X - " + Xend + " Y - " + Yend);
        return true;
    }
        System.out.println("Move is impossible. Figure is not moved.");
        return false;
    }
}


