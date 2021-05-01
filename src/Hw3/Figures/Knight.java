package Figures;

public class Knight extends Figure{

    public Knight(){
        super.setEnumType(FiguresEnum.KNIGHT);
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