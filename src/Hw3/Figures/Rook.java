package Figures;

public class Rook extends Figure{
    public Rook(){
        super.setEnumType(FiguresEnum.ROOK);
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