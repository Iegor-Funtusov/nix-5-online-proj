package Figures;

import Enums.ColorEnum;
import Enums.FiguresEnum;

public class Pawn extends Figure{

    public Pawn(){
        super.setEnumType(FiguresEnum.PAWN);
    }

    @Override
    protected boolean checkPossibilityOfMove(byte xCoord, byte yCoord) {
        //Пешка ходит только на один вперёд
        if(yCoord != this.getY())
            return false;

        if (this.getColor()== ColorEnum.WHITE && xCoord != (this.getX()-1))
            return false;
        if (this.getColor()==ColorEnum.BLACK && xCoord != (this.getX()+1))
            return false;

        return true;
    }
}