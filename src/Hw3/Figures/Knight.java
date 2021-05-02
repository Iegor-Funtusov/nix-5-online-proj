package Figures;

public class Knight extends Figure{

    public Knight(){
        super.setEnumType(FiguresEnum.KNIGHT);
    }


    @Override
    protected boolean checkPossibilityOfMove(byte xCoord, byte yCoord) {
        //Конь ходит буквой Г
        int x = Math.abs(xCoord - this.getX());
        int y = Math.abs(yCoord - this.getY());
        if ((x == 1 && y == 2) || (x == 2 && y == 1)){
            return true;
        }
        return false;
    }
}