package Figures;

import Enums.FiguresEnum;

public class Rook extends Figure{
    public Rook(){
        super.setEnumType(FiguresEnum.ROOK);
    }

    @Override
    protected boolean checkPossibilityOfMove(byte xCoord, byte yCoord) {
        //Ладья ходит только по прямой вперёд или по прямой в сторону
        if(xCoord == this.getX() && yCoord != this.getY())
            return true;

        if(xCoord != this.getX() && yCoord == this.getY())
            return true;

        return false;
    }

}