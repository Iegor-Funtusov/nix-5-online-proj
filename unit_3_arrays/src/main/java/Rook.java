public class Rook extends Figure {
    public boolean isRightMove(int letCurrPos, int numCurrPos, int letNewPos, int numNewPos) {
        return (isRightPositionOnBoard(letCurrPos, numCurrPos) &&
            !isRightPositionOnBoard(letNewPos, numNewPos) &&
         ((letCurrPos == letNewPos && numCurrPos != numNewPos) ||
            (letCurrPos != letNewPos && numCurrPos == numNewPos)));
    }
}
