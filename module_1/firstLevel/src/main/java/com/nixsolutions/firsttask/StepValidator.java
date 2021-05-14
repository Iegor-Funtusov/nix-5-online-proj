package com.nixsolutions.firsttask;

public class StepValidator {

    public static boolean isStepValid(int initial_x,int initial_y,int step_x,int step_y){
        return ((initial_x - 2 == step_x) && (initial_y - 1 == step_y || initial_y + 1 == step_y))
                || ((initial_x - 1 == step_x || initial_x + 1 == step_x) && (initial_y + 2 == step_y))
                || ((initial_x + 2 == step_x) && (initial_y + 1 == step_y || initial_y - 1 == step_y))
                || ((initial_x - 1 == step_x || initial_x + 1 == step_x) && (initial_y - 2 == step_y));
    }

    public static boolean isStepInBoard(int step_x,int step_y) {
        return step_x < Integer.MAX_VALUE && step_y < Integer.MAX_VALUE;
    }
}