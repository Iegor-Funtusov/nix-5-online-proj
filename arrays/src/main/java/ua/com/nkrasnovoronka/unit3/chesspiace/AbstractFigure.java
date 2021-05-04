package ua.com.nkrasnovoronka.unit3.chesspiace;

/**
 * Figure abstract class
 */
public abstract class AbstractFigure {
    private Color color;

    public abstract boolean isMoveValid(Square start, Square end);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.getName() + " ";
    }
}
