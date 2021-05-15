package lev1.chess;

public class StepValid {
    public static boolean isStepValid(int start_x,int start_y,int new_x,int new_y){
        return ((start_x - 2 == new_x) && (start_y - 1 == new_y || start_y + 1 == new_y)) ||
                ((start_x - 1 == new_x || start_x + 1 == new_x) && (start_y + 2 == new_y)) ||
                ((start_x + 2 == new_x) && (start_y + 1 == new_y || start_y - 1 == new_y)) ||
                ((start_x - 1 == new_x || start_x + 1 == new_x) && (start_y - 2 == new_y));

    }
    public static boolean isStepInBoard(int new_x,int new_y){
        return new_x < Integer.MAX_VALUE && new_y < Integer.MAX_VALUE;
    }
}
