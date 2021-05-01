package Figures;

public class Bishop extends Figure{
    public Bishop(){
        super.setEnumType(FiguresEnum.BISHOP);
    }

    @Override
    public boolean move(byte xCoord, byte yCoord) {
        return false;
    }

    @Override
    protected boolean checkPossibilityOfMove(byte xCoord, byte yCoord) {
        return false;
    }
}