public class PawnB extends Figure {
    public boolean isRightMove(int letCurrPos, int numCurrPos, int letNewPos, int numNewPos) {
        return (isRightPositionOnBoard(letCurrPos, numCurrPos) &&
            isRightPositionOnBoard(letNewPos, numNewPos) &&
            (forward(numCurrPos, numNewPos) && (numCurrPos != 8) &&
                ((Math.abs(letCurrPos - letNewPos) == 1 && Math.abs(numCurrPos - numNewPos) == 1) ||
                    ((letCurrPos == letNewPos) &&
                        (Math.abs(numCurrPos - numNewPos) == 1 ||
                            // Если это первый ход пешки
                            (firstMove(numCurrPos) &&
                                // можно ходить на две позиции вперёд
                                Math.abs(numNewPos - numCurrPos) == 2))))));
    }

    // Чёрные пешки ходят со второй строчки
    public static boolean firstMove(int yFrom) {
            return (yFrom == 7);
    }

    // Пешки ходят только прямо
    public static boolean forward(int yFrom, int yTo) {
            return (yFrom >= yTo);
    }
}
