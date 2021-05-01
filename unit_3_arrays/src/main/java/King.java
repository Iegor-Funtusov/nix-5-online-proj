public class King extends Figure {
    public boolean isRightMove(int letCurrPos, int numCurrPos, int letNewPos, int numNewPos) {
        return (isRightPositionOnBoard(letCurrPos, numCurrPos) &&
            isRightPositionOnBoard(letNewPos, numNewPos) &&
            (Math.abs(letCurrPos - letNewPos) <= 1 && Math.abs(numCurrPos - numNewPos) <= 1));
    }
}
