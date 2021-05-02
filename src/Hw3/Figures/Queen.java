package Figures;

import Enums.FiguresEnum;

public class Queen extends Figure{
    public Queen(){
        super.setEnumType(FiguresEnum.QUEEN);
    }

    @Override
    protected boolean checkPossibilityOfMove(byte xCoord, byte yCoord) {
        //Ферзь ходит как ладья и слон 2в1
        if(xCoord == this.getX() && yCoord != this.getY())
            return true;

        if(xCoord != this.getX() && yCoord == this.getY())
            return true;

        if (Math.abs(xCoord - this.getX()) == Math.abs(yCoord - this.getY())){
            return true;
        }

        return false;
    }
}