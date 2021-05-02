package Figures;

public class Bishop extends Figure{
    public Bishop(){
        super.setEnumType(FiguresEnum.BISHOP);
    }

    @Override
    protected boolean checkPossibilityOfMove(byte xCoord, byte yCoord) {
        //Слон ходит только по диагонали
        if (Math.abs(xCoord - this.getX()) == Math.abs(yCoord - this.getY())){
            return true;
        }
        return false;
    }
}