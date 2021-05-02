package Figures;

public class King extends Figure{
    public King(){
        super.setEnumType(FiguresEnum.KING);
    }

    @Override
    protected boolean checkPossibilityOfMove(byte xCoord, byte yCoord){
        //Король ходит только на 1 клетку в любую сторону
        if(xCoord > (byte) (this.getX()+1) || xCoord < (byte) (this.getX()-1)){
            System.out.println("Cannot move the figure in this way!");
            return false;
        }
        if(yCoord > (byte)(this.getY()+1) || yCoord < (byte)(this.getY()-1)){
            System.out.println("Cannot move the figure in this way!");
            return false;
        }
        return true;
    }

}