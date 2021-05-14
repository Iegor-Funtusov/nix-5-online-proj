package ua.com.nix.utils;

import lombok.SneakyThrows;
import ua.com.nix.board.Position;

import java.io.BufferedReader;

public class PositionUtil {

    @SneakyThrows
    public static Position createPosition(BufferedReader reader){
        String coordinates = reader.readLine();
        Position position = null;
        if(coordinates.length() != 2) {
            System.out.println("Invalid number of symbols!");
            return position;
        }
        int x, y;
        if ((x = getColumn(coordinates.charAt(0))) == -1 ) {
            System.out.println("Invalid symbol -> \"" + coordinates.charAt(0) + "\"");
            return position;
        }
        if ((y = getRow(coordinates.charAt(1))) == -1 ) {
            System.out.println("Invalid symbol -> \"" + coordinates.charAt(1) + "\"");
            return position;
        }
        position = new Position(y, x);
        return position;
    }

    private static int getRow(char yCoord){
        int row;
        switch (yCoord){
            case '1':{
                row = 7;
                break;
            }
            case '2':{
                row = 6;
                break;
            }
            case '3':{
                row = 5;
                break;
            }
            case '4':{
                row = 4;
                break;
            }
            case '5':{
                row = 3;
                break;
            }
            case '6':{
                row = 2;
                break;
            }
            case '7':{
                row = 1;
                break;
            }
            case '8':{
                row = 0;
                break;
            }
            default: {
                row = -1;
                break;
            }

        }
        return row;
    }

    private static int getColumn(char xCoord){
        int column;
        switch (xCoord){
            case 'a':{
                column = 0;
                break;
            }
            case 'b':{
                column = 1;
                break;
            }
            case 'c':{
                column = 2;
                break;
            }
            case 'd':{
                column = 3;
                break;
            }
            case 'e':{
                column = 4;
                break;
            }
            case 'f':{
                column = 5;
                break;
            }
            case 'g':{
                column = 6;
                break;
            }
            case 'h':{
                column = 7;
                break;
            }
            default: {
                column = -1;
                break;
            }

        }
        return column;
    }
}
