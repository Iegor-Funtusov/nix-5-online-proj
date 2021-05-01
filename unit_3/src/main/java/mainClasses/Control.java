package mainClasses;

import java.util.Scanner;

public class Control {
    public static boolean correctPlace (String place) {
        place = place.toLowerCase();
        if (place.length() != 2)
            return false;

        char letter = place.charAt(0);
        char number = place.charAt(1);

        if (!Character.isLetter(letter) || !Character.isDigit(number)) {
            return false;
        }

        if (number >= '9' || number <= '0')
            return false;

        if (!(letter >= 'a' && letter <= 'h'))
            return false;
        return true;
    }

    public static boolean correctFigure (String figure) {
        figure = figure.toLowerCase();
        if (figure.length() != 1)
            return false;

        char letter = figure.charAt(0);
        if (letter == 'k' || letter == 'q' || letter == 'r'
        || letter == 'b' || letter == 'n' || letter == 'p')
            return true;
        return false;
    }

    public static boolean correctColor (String color) {
        color = color.toLowerCase();
        if (color.length() != 1)
            return false;

        char letter = color.charAt(0);
        if (letter == 'w' || letter == 'b')
            return true;
        return false;
    }

    public static void figure (String place, String color, String WhiteF, String BlackF) {
        Board board =  new Board();
        color = color.toLowerCase();
        char colorP = color.charAt(0);
        if (colorP == 'w') {
            board.updatedBoard(place, WhiteF);
        }
        else {
            board.updatedBoard(place, BlackF);
        }
    }


    public static int Flag (char flag) {

        switch (flag){
            case 'a':
                flag = 0;
                return flag;
            case 'b':
                flag = 1;
                return flag;
            case 'c':
                flag = 2;
                return flag;
            case 'd':
                flag = 3;
                return flag;
            case 'e':
                flag = 4;
                return flag;
            case 'f':
                flag = 5;
                return flag;
            case 'g':
                flag = 6;
                return flag;
            case 'h':
                flag = 7;
                return flag;
        }
        return flag;
    }

    public static boolean movePawn(String place, String move, char color) {
        int departure = Character.getNumericValue(place.charAt(1));
        int dest = Character.getNumericValue(move.charAt(1));

        char flagPlace = place.charAt(0);
        char flagMove = move.charAt(0);
        int letterDep = Control.Flag(flagPlace);
        int letterDest = Control.Flag(flagMove);
        if (color == 'w')
        {
            if (departure - 1 == dest && letterDep == letterDest)
                return true;
        }
        else {
            if(departure == dest - 1 && letterDep == letterDest){
                return true;
            }
        }
        return false;
    }

    public static boolean moveRook (String place, String move, char color) {
        int departure = Character.getNumericValue(place.charAt(1));
        int dest = Character.getNumericValue(move.charAt(1));

        char flagPlace = place.charAt(0);
        char flagMove = move.charAt(0);
        int letterDep = Control.Flag(flagPlace);
        int letterDest = Control.Flag(flagMove);

        if (color == 'w' || color == 'b')
        {
            if(departure == dest || letterDep == letterDest)
                return true;
        }
        return false;
    }
    public static boolean moveKing (String place, String move, char color) {
        int departure = Character.getNumericValue(place.charAt(1));
        int dest = Character.getNumericValue(move.charAt(1));

        char flagPlace = place.charAt(0);
        char flagMove = move.charAt(0);
        int letterDep = Control.Flag(flagPlace);
        int letterDest = Control.Flag(flagMove);
        if (color == 'w' || color == 'b'){
            if (((departure - 1 == dest || departure == dest - 1) &&
                    (letterDep-1 == letterDest) || (letterDep == letterDest-1))
            || ((departure - 1 == dest || departure == dest - 1) && (letterDep == letterDest))
                    || ((letterDep - 1 == letterDest) && (departure == dest))
            )
                return true;
        }
        return false;
    }
    public static boolean moveBishop (String place, String move, char color) {
        int departure = Character.getNumericValue(place.charAt(1));
        int dest = Character.getNumericValue(move.charAt(1));

        char flagPlace = place.charAt(0);
        char flagMove = move.charAt(0);
        int letterDep = Control.Flag(flagPlace);
        int letterDest = Control.Flag(flagMove);

        if (color == 'w' || color == 'b') {
            if((departure-dest) == (letterDest-letterDep)||
                    (dest - departure) == (letterDest - letterDep)||
                    (dest-departure) == letterDep - letterDest ||
                    (departure - dest) == letterDep - letterDest) {
                return true;
            }
        }
        return false;
    }

    public static boolean moveQueen (String place, String move, char color) {
        int departure = Character.getNumericValue(place.charAt(1));
        int dest = Character.getNumericValue(move.charAt(1));

        char flagPlace = place.charAt(0);
        char flagMove = move.charAt(0);
        int letterDep = Control.Flag(flagPlace);
        int letterDest = Control.Flag(flagMove);

        if (color == 'w' || color == 'b') {
            if((departure-dest) == (letterDest-letterDep)||
                    (dest - departure) == (letterDest - letterDep)||
                    (dest-departure) == letterDep - letterDest ||
                    (departure - dest) == letterDep - letterDest
            || ((departure == dest) || (letterDep == letterDest))) {
                return true;
            }
        }
        return false;
    }
    public static boolean moveKnight (String place, String move, char color) {
        int departure = Character.getNumericValue(place.charAt(1));
        int dest = Character.getNumericValue(move.charAt(1));

        char flagPlace = place.charAt(0);
        char flagMove = move.charAt(0);
        int letterDep = Control.Flag(flagPlace);
        int letterDest = Control.Flag(flagMove);

        if (color == 'w' || color == 'b') {
            if(((departure-2 == dest) && (letterDep-1 == letterDest || letterDep+1 == letterDest)) ||
                    ((departure-1 == dest || departure+1 == dest) && (letterDep+2 == letterDest)) ||
                    ((departure+2 == dest) && (letterDep+1 == letterDest || letterDep-1 == letterDest))||
                    ((departure-1 == dest || departure + 1 == dest) && (letterDep-2 == letterDest))) {
                return true;
            }
        }
        return false;
    }

    public static boolean exit(char exit) {
        if (exit == 'n' || exit == 'e' || exit == 'N' || exit == 'E')
            return false;
        return true;
    }

}
