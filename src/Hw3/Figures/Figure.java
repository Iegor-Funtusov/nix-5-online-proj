package Figures;

public abstract class Figure {
    //Position of the figure on the board
    private byte x;
    private byte y;
    private ColorEnum color;
    private FiguresEnum enumType;

    protected abstract boolean checkPossibilityOfMove(byte xCoord, byte yCoord);

    public boolean move(byte xCoord, byte yCoord){
        if(checkPossibilityOfMove(xCoord, yCoord)){
            this.setX(xCoord);
            this.setY(yCoord);
            return true;
        }
        return false;
    }

    public byte getX() { return x; }
    public byte getY() { return y; }
    public ColorEnum getColor() { return color; }
    public FiguresEnum getEnumType() { return this.enumType; }

    public void setX(byte x) { this.x = x; }
    public void setY(byte y) { this.y = y; }
    public void setColor(ColorEnum color) { this.color = color; }

    protected void setEnumType(FiguresEnum type) { this.enumType = type; }   //Для определения енама фигуры при перемещении пр доске
}