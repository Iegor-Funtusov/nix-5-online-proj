package ua.com.nkrasnovoronka;

import ua.com.nkrasnovoronka.util.UserInput;

public class Module {
    public void start(){
        System.out.println("Pleas choose level");
        int level = UserInput.userInputNumber();
        System.out.println("Pleas enter task number");
        int task = UserInput.userInputNumber();
        System.out.println("User random data 1 - Yes 2 - No");
        boolean random = UserInput.userInputNumber() == 1;
        startTest(level, task, random);
    }

    private void startTest(int level, int task, boolean random) {

    }


}
