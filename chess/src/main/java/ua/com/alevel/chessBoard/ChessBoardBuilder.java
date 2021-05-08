package ua.com.alevel.chessBoard;

public class ChessBoardBuilder {

    public static char [][] chessBoardToPrint = new char[8][8];

    public static void printBoard() {
        fillChessBoard();
        for (int i = 0; i < chessBoardToPrint.length; i++) {
            for (int j = 0; j < chessBoardToPrint.length; j++) {
                if (j == 0) {
                    System.out.print(chessBoardToPrint.length - i + " | ");
                }
                System.out.print(chessBoardToPrint[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println("   |_____________________________|");
        System.out.println("\t" + "A " + "\t" + "B " + "\t" + "C " + "\t" + "D " + "\t" + "E " + "\t" + "F " + "\t" + "G " + "\t" + "H ");
    }

    public static void fillChessBoard() {
        //fill by sections
        for (int i = 2; i < chessBoardToPrint.length-2; i++) {
            for (int j = 0; j < chessBoardToPrint.length; j++) {
                if ((i + j) % 2 == 0) {
                    chessBoardToPrint[i][j] = 1;
                }
                chessBoardToPrint[i][j] += '0';
            }
        }
        //fill by pawns
        for (int i = 1; i < chessBoardToPrint.length; i += 5) {
            for (int j = 0; j < chessBoardToPrint.length; j++) {
                chessBoardToPrint[i][j] += 'P';
            }
            //fill by figures
            chessBoardToPrint[0][0]= 'R';
            chessBoardToPrint[0][7]='R';
            chessBoardToPrint[7][0]= 'R';
            chessBoardToPrint[7][7]='R';
            chessBoardToPrint[0][1]= 'k';
            chessBoardToPrint[0][6]='k';
            chessBoardToPrint[7][1]= 'k';
            chessBoardToPrint[7][6]='k';
            chessBoardToPrint[0][2]= 'B';
            chessBoardToPrint[0][5]='B';
            chessBoardToPrint[7][2]= 'B';
            chessBoardToPrint[7][5]='B';
            chessBoardToPrint[0][3]= 'K';
            chessBoardToPrint[0][4]='Q';
            chessBoardToPrint[7][3]= 'K';
            chessBoardToPrint[7][4]='Q';
        }
    }
}
