package Figures;

public class Pawn extends Figure{

    public Pawn(){
        super.setEnumType(FiguresEnum.PAWN);
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