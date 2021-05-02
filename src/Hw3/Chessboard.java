import Figures.*;

public class Chessboard {
    private int FIELD_SIZE = 8;
    char[][] cells;

    Chessboard() {
        cells = new char[FIELD_SIZE][FIELD_SIZE];
        this.clearChessboard();
    }

    public void clearChessboard(){
        for(int i = 0 ; i < FIELD_SIZE ; i++){
            for(int j = 0 ; j < FIELD_SIZE ; j++)
                if((i + j) % 2 == 0)
                    cells[i][j] = '0';
                else
                    cells[i][j] = '#';
        }
    }


    public void drawField(){
        for(int i = 0; i < FIELD_SIZE; i++){
            for (int j = 0 ; j < FIELD_SIZE; j++)
                System.out.print(cells[i][j] + " ");
            System.out.println();
        }
    }



    public void putFigure(Figure figure, FiguresEnum figureType){
        this.clearChessboard();

        switch (figureType){
            case PAWN : {
                cells[figure.getX()][figure.getY()] = 'P';
            } break;

            case KNIGHT : {
                cells[figure.getX()][figure.getY()] = 'H';      //Чтобы отличать с королём, будет H - Horse
            } break;

            case BISHOP : {
                cells[figure.getX()][figure.getY()] = 'B';
            } break;

            case ROOK : {
                cells[figure.getX()][figure.getY()] = 'R';
            } break;

            case QUEEN : {
                cells[figure.getX()][figure.getY()] = 'Q';
            } break;

            case KING : {
                cells[figure.getX()][figure.getY()] = 'K';
            } break;
        }
    }

    public int getFIELD_SIZE() {
        return FIELD_SIZE;
    }
}