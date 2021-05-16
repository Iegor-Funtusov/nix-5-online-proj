package Level3;

public class LifeGame {
    private int COLS;
    private int ROWS;
    private boolean [][]gameField;

    public LifeGame(int dimension){
        COLS = dimension;
        ROWS = dimension;
        gameField = new boolean[COLS][ROWS];

        for(int i = 0; i < COLS; i++){
            for(int j = 0; j < ROWS; j++){
                if(Math.round(Math.random()) == 1)
                    gameField[i][j] = true;
                else
                    gameField[i][j] = false;
            }
        }
    }


    public LifeGame(boolean[][] field, int dimension){
        this.COLS = dimension;
        this.ROWS = dimension;
        this.gameField = field.clone();
    }


    public void nextStep(){

        //Если поле размером 1*1 то не нужно делать дополнительных действий
        if(COLS == 1 && ROWS == 1){
            return;
        }

        int neighbors = 0;
        for(int i = 0; i < COLS; i++){
            for(int j = 0; j < ROWS; j++){
                neighbors = calculateNeighbors(i, j);
                gameField[i][j] = getState(neighbors, i, j);
            }
        }
    }


    private int checkCell(int i, int j){
        if(gameField[i][j])
            return 1;
        return 0;
    }


    private boolean getState(int neighbors, int i , int j){
        if(neighbors < 2 && gameField[i][j])
            return false;
        if((neighbors == 2 || neighbors == 3) && gameField[i][j])
            return true;
        if(neighbors > 3 && gameField[i][j])
            return false;
        if (neighbors == 3 && !gameField[i][j])
            return true;

        return false;
    }


    private int calculateNeighbors(int i, int j){
        int neighbors = 0;

        //Верхняя левая клетка
        if(i == 0 && j == 0){
            neighbors += checkCell(i, j+1);      //Правая
            neighbors += checkCell(i+1, j+1); //Правая по диагонали внизу
            neighbors += checkCell(i+1, j);      //Нижняя

            return neighbors;
        }

        //Верхняя правая клетка
        if(i == 0 && j == ROWS-1){
            neighbors += checkCell(i+1, j);      //Нижняя
            neighbors += checkCell(i+1, j-1); //Левая по диагонали внизу
            neighbors += checkCell(i,j-1);       //Левая

            return neighbors;
        }

        //Нижняя левая клетка
        if(i == COLS-1 && j == 0){
            neighbors += checkCell(i-1, j);      //Верхняя
            neighbors += checkCell(i-1, j+1); //Правая по диагонали сверху
            neighbors += checkCell(i, j+1);      //Правая

            return neighbors;
        }

        //Правая нижняя клетка
        if(i == COLS-1 && j == ROWS-1){
            neighbors += checkCell(i,j-1);       //Левая
            neighbors += checkCell(i-1, j-1); //Левая по диагонали вверху
            neighbors += checkCell(i-1, j);      //Верхняя

            return neighbors;
        }

        //Верхний ряд
        if(i == 0 && j < ROWS-1){
            neighbors += checkCell(i, j+1);      //Правая
            neighbors += checkCell(i+1, j+1); //Правая по диагонали внизу
            neighbors += checkCell(i+1, j);      //Нижняя
            neighbors += checkCell(i+1,j-1);  //Левая по диагонали внизу
            neighbors += checkCell(i,j-1);       //Левая

            return neighbors;
        }

        //Левый столбец
        if(j == 0 && i != 0){
            neighbors += checkCell(i-1, j);      //Верхняя
            neighbors += checkCell(i-1, j+1); //Правая по диагонали сверху
            neighbors += checkCell(i, j+1);      //Правая
            neighbors += checkCell(i+1, j+1); //Правая по диагонали внизу
            neighbors += checkCell(i+1, j);      //Нижняя

            return neighbors;
        }

        //Нижний ряд
        if(i == COLS-1 && j != ROWS-1){
            neighbors += checkCell(i,j-1);       //Левая
            neighbors += checkCell(i-1, j-1); //Левая по диагонали вверху
            neighbors += checkCell(i-1, j);      //Верхняя
            neighbors += checkCell(i-1, j+1); //Правая по диагонали сверху
            neighbors += checkCell(i, j+1);      //Правая

            return neighbors;
        }

        //Правый столбец
        if(j == ROWS-1 && i != COLS-1){
            neighbors += checkCell(i+1, j);      //Нижняя
            neighbors += checkCell(i+1, j-1); //Левая по диагонали внизу
            neighbors += checkCell(i,j-1);       //Левая
            neighbors += checkCell(i-1, j-1); //Левая по диагонали вверху
            neighbors += checkCell(i-1, j);      //Верхняя

            return neighbors;
        }

        //Внутри квадрата
        else{
            neighbors += checkCell(i+1, j);      //Нижняя
            neighbors += checkCell(i+1, j-1); //Левая по диагонали внизу
            neighbors += checkCell(i,j-1);       //Левая
            neighbors += checkCell(i-1, j-1); //Левая по диагонали вверху
            neighbors += checkCell(i-1, j);      //Верхняя
            neighbors += checkCell(i-1, j+1); //Правая по диагонали сверху
            neighbors += checkCell(i, j+1);      //Правая
            neighbors += checkCell(i+1, j+1); //Правая по диагонали внизу

            return neighbors;
        }
    }




    public boolean[][] getGameField() {
        return gameField;
    }

    public int getCOLS() {
        return COLS;
    }

    public int getROWS() {
        return ROWS;
    }



}
