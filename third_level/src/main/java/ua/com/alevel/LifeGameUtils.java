package ua.com.alevel;

public class LifeGameUtils {

    public static void calculateNextLifeGameState(LifeGame game){
        int[][] currentState = game.getGameBoard();
        int[][] nextState = new int[currentState.length][];

        for (int i = 0; i < nextState.length; i++) {
            nextState[i] = new  int[currentState[i].length];
        }

        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState[i].length; j++) {
                if(isAliveInNextGeneration(currentState, i, j))
                    nextState[i][j] = 1;
                else
                    nextState[i][j] = 0;
            }
        }
        game.setGameBoard(nextState);
    }

    private static boolean isAliveInNextGeneration(int[][] currentState, int i, int j) {
        int aliveNeighborCounter = 0;
        int m, n, p, s;

        if(i == 0)
            m = 0;
        else
            m = i-1;

        if(i == currentState.length - 1)
            n = i;
        else
            n = i + 1;

        if(j == 0)
            p = j;
        else
            p = j - 1 ;

        if(j == currentState[i].length - 1)
            s = j;
        else
            s = j + 1;

        for (int k = m; k <= n; k++) {
            for (int l = p; l <= s ; l++) {
                if(k == i && l == j)
                    continue;
                if (currentState[k][l] == 1)
                    aliveNeighborCounter++;
            }
        }
        if(currentState[i][j] == 1){
            if(aliveNeighborCounter == 2 || aliveNeighborCounter == 3 )
                return true;
        }else if(aliveNeighborCounter == 3)
            return true;
        return false;
    }

}
