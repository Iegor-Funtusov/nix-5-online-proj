package Figures;

public class Queen extends Figure{
    public Queen(){
        super.setEnumType(FiguresEnum.QUEEN);
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