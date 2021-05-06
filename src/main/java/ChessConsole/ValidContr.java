package ChessConsole;

public class ValidContr {
    public static boolean isPawnStepCorr(String area, String step, char color) {
        int departure = Character.getNumericValue(area.charAt(1));
        int dest = Character.getNumericValue(step.charAt(1));

        char flagPlace = area.charAt(0);
        char flagMove = step.charAt(0);
        int letterDep = ValidContr.letterToInt(flagPlace);
        int letterDest = ValidContr.letterToInt(flagMove);
        if (color == 'w') {
            if (departure - 1 == dest && letterDep == letterDest)
                return true;
        } else {
            if (departure == dest - 1 && letterDep == letterDest) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRookStepCorr(String area, String step, char color) {
        int departure = Character.getNumericValue(area.charAt(1));
        int dest = Character.getNumericValue(step.charAt(1));

        char flagPlace = area.charAt(0);
        char flagMove = step.charAt(0);
        int letterDep = ValidContr.letterToInt(flagPlace);
        int letterDest = ValidContr.letterToInt(flagMove);

        if (color == 'w' || color == 'b') {
            if (departure == dest || letterDep == letterDest)
                return true;
        }
        return false;
    }

    public static boolean isKingStepCorr(String area, String step, char color) {
        int departure = Character.getNumericValue(area.charAt(1));
        int dest = Character.getNumericValue(step.charAt(1));

        char flagPlace = area.charAt(0);
        char flagMove = step.charAt(0);
        int letterDep = ValidContr.letterToInt(flagPlace);
        int letterDest = ValidContr.letterToInt(flagMove);
        if (color == 'w' || color == 'b') {
            if (((departure - 1 == dest || departure == dest - 1) &&
                    (letterDep - 1 == letterDest) || (letterDep == letterDest - 1))
                    || ((departure - 1 == dest || departure == dest - 1) && (letterDep == letterDest))
                    || ((letterDep - 1 == letterDest) && (departure == dest))
            )
                return true;
        }
        return false;
    }

    public static boolean isBishStepCorr(String area, String step, char color) {
        int departure = Character.getNumericValue(area.charAt(1));
        int dest = Character.getNumericValue(step.charAt(1));

        char flagPlace = area.charAt(0);
        char flagMove = step.charAt(0);
        int letterDep = ValidContr.letterToInt(flagPlace);
        int letterDest = ValidContr.letterToInt(flagMove);

        if (color == 'w' || color == 'b') {
            if ((departure - dest) == (letterDest - letterDep) ||
                    (dest - departure) == (letterDest - letterDep) ||
                    (dest - departure) == letterDep - letterDest ||
                    (departure - dest) == letterDep - letterDest) {
                return true;
            }
        }
        return false;
    }

    public static boolean isQueenStepCorr(String area, String step, char color) {
        int departure = Character.getNumericValue(area.charAt(1));
        int dest = Character.getNumericValue(step.charAt(1));

        char flagPlace = area.charAt(0);
        char flagMove = step.charAt(0);
        int letterDep = ValidContr.letterToInt(flagPlace);
        int letterDest = ValidContr.letterToInt(flagMove);

        if (color == 'w' || color == 'b') {
            if ((departure - dest) == (letterDest - letterDep) ||
                    (dest - departure) == (letterDest - letterDep) ||
                    (dest - departure) == letterDep - letterDest ||
                    (departure - dest) == letterDep - letterDest
                    || ((departure == dest) || (letterDep == letterDest))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isKnStepCorr(String area, String step, char color) {
        int departure = Character.getNumericValue(area.charAt(1));
        int dest = Character.getNumericValue(step.charAt(1));

        char flagPlace = area.charAt(0);
        char flagMove = step.charAt(0);
        int letterDep = ValidContr.letterToInt(flagPlace);
        int letterDest = ValidContr.letterToInt(flagMove);

        if (color == 'w' || color == 'b') {
            if (((departure - 2 == dest) && (letterDep - 1 == letterDest || letterDep + 1 == letterDest)) ||
                    ((departure - 1 == dest || departure + 1 == dest) && (letterDep + 2 == letterDest)) ||
                    ((departure + 2 == dest) && (letterDep + 1 == letterDest || letterDep - 1 == letterDest)) ||
                    ((departure - 1 == dest || departure + 1 == dest) && (letterDep - 2 == letterDest))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAreaCorrect(String area) {
        area = area.toLowerCase();
        if (area.length() != 2)
            return false;
        char letter = area.charAt(0);
        char number = area.charAt(1);
        if (!Character.isLetter(letter) || !Character.isDigit(number)) {
            return false;
        }
        if (number >= '9' || number <= '0')
            return false;
        if (!(letter >= 'a' && letter <= 'h'))
            return false;
        return true;
    }

    public static boolean isFigCorrect(String figure) {
        figure = figure.toLowerCase();
        if (figure.length() != 1)
            return false;
        char letter = figure.charAt(0);
        if (letter == 'k' || letter == 'q' || letter == 'r'
                || letter == 'b' || letter == 'n' || letter == 'p')
            return true;
        return false;
    }

    public static boolean isColCorrect(String color) {
        color = color.toLowerCase();
        if (color.length() != 1)
            return false;
        char letter = color.charAt(0);
        if (letter == 'w' || letter == 'b')
            return true;
        return false;
    }

    public static void upd_board_with_figure(String place, String color, String WF, String BF) {
        Board board = new Board();
        color = color.toLowerCase();
        char colorP = color.charAt(0);
        if (colorP == 'w') {
            board.printUpdBoard(place, WF);
        } else {
            board.printUpdBoard(place, BF);
        }
    }


    public static int letterToInt(char let) {

        switch (let) {
            case 'a':
                let = 0;
                return let;
            case 'b':
                let = 1;
                return let;
            case 'c':
                let = 2;
                return let;
            case 'd':
                let = 3;
                return let;
            case 'e':
                let = 4;
                return let;
            case 'f':
                let = 5;
                return let;
            case 'g':
                let = 6;
                return let;
            case 'h':
                let = 7;
                return let;
        }
        return let;
    }



}
