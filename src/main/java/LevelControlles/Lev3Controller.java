package LevelControlles;

import Level3.LifeGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lev3Controller {
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private LifeGame lifeGame;


    public void level3Interface() throws IOException {
        do {
            System.out.println("What do you want to do? 1-create new game field, 2-see the current game field, 3-do the next step of the game");

            switch (Integer.parseInt(bf.readLine())){
                case 1 -> createGameField();
                case 2 -> printGameField();
                case 3 -> nextStepOfGame();
                default -> System.out.println("Incorrect value entered");
            }

            System.out.println("Do you want to continue at this level? 1-yes, else-no");

        } while (Integer.parseInt(bf.readLine()) == 1);
    }



    private void createGameField() throws IOException{
        System.out.println("Enter the dimension of the game field");
        int dimension = Integer.parseInt(bf.readLine());

        if(dimension <= 0){
            System.out.println("Incorrect value entered");
            return;
        }

        if(chooseMethodOfInput()){
            lifeGame = new LifeGame(scanElements(dimension), dimension);
            printGameField();
        }
        else{
            lifeGame = new LifeGame(dimension);
            printGameField();
        }

    }



    private boolean[][] scanElements(int dimension) throws IOException{
        boolean [][] gameField = new boolean[dimension][dimension];
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                System.out.println("Press 1 if cell is live, else is cell is died. Cell [" + (i+1) + "]" + "[" + (j+1) + "]");
                int cell = Integer.parseInt(bf.readLine());
                if(cell == 1)
                    gameField[i][j] = true;
                else
                    gameField[i][j] = false;
            }
        }
        return gameField;
    }



    private void printGameField(){
        if(lifeGame == null){
            System.out.println("First you should to create gameField");
            return;
        }

        boolean [][]gameField = lifeGame.getGameField();

        System.out.println("x - the cell is live, o - the cell is dead");
        for(int i = 0; i < lifeGame.getCOLS(); i++){
            for(int j = 0; j < lifeGame.getROWS(); j++){
                if(gameField[i][j])
                    System.out.print("x ");
                else
                    System.out.print("o ");
            }
            System.out.println();
        }

    }


    private void nextStepOfGame(){
        if(lifeGame == null){
            System.out.println("First you should to create gameField");
            return;
        }

        lifeGame.nextStep();
        printGameField();
    }



    private boolean chooseMethodOfInput() throws IOException{
        System.out.println("Choose method of input: 1-from the keyboard, else-mechanically");
        if(Integer.parseInt(bf.readLine()) == 1){
            return true;
        }
        return false;
    }
}