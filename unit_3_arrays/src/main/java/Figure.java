abstract public class Figure {
    public boolean isRightPositionOnBoard(int letter, int num) {
        return ((letter >= 'a' && letter <= 'h') && (num >= 1 && num <= 8));
    }

    abstract public boolean isRightMove(int letCurrPos, int numCurrPos, int letNewPos, int numNewPos);
}
