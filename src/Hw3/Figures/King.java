package Figures;

public class King extends Figure{
    public King(){
        super.setEnumType(FiguresEnum.KING);
    }

    @Override
    public boolean move(byte xCoord, byte yCoord) {
        if(checkPossibilityOfMove(xCoord, yCoord)){
            this.setX(xCoord);
            this.setY(yCoord);
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkPossibilityOfMove(byte xCoord, byte yCoord){
        if(xCoord < 0 || xCoord > 8){
            System.out.println("Incorrect value!");
            return false;
        }
        if(yCoord < 0 || yCoord > 8){
            System.out.println("Incorrect value!");
            return false;
        }
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