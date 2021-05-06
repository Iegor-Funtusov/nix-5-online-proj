package ChessConsole;

public class Board {
    public void printEmptyBoard(){


        String [][] Board = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1)
                    Board[i][j] = "__";
                else
                    Board[i][j] = "  ";
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(Board[i][j]);
                if(j == 7)
                    System.out.println("  " + (i+1));
            }
        }
        System.out.println("a b c d e f g h");
    }

    public void printUpdBoard(String place, String figure){

        char flag = place.charAt(0);
        int letter = ValidContr.letterToInt(flag);
        int number = Character.getNumericValue(place.charAt(1));

        String [][] Board = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (letter == j && number-1 == i) {
                    Board[i][j] = figure;
                    continue;
                }
                if(i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1)
                    Board[i][j] = "__";
                else
                    Board[i][j] = "  ";
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(Board[i][j]);
                if(j == 7)
                    System.out.println("  " + (i+1));
            }
        }
        System.out.println("a b c d e f g h");
    }
}
