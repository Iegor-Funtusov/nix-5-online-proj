package Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task2UserInterface {
    private final BufferedReader bf;

    public Task2UserInterface(){
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public void task2UserUI(){
        String AGREE_INPUT = "1";
        String choose;
        try {
            printTask2Condition();
            do{
                System.out.println("1-Enter names from the keyboard, 2-Use inline data, 'Q'-exit");
                choose = bf.readLine();
                switch (choose.toUpperCase()){
                    case "1" -> keyboardDataInput();
                    case "2" -> inlineDataFillIn();
                    case "Q" -> System.exit(1);
                    default -> System.out.println("Incorrect value entered");
                }

                System.out.println("Do you want to continue working with this task? 1-yes, else-no");
                choose = bf.readLine();

            } while (choose.equals(AGREE_INPUT));

        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void keyboardDataInput(){
        try {
            List<String> allNames = new ArrayList<>();
            String enteredName;

            while (true){
                System.out.println("Enter new name. If you want to stop enter 'S'");
                enteredName = bf.readLine();

                if(enteredName.equalsIgnoreCase("s")){
                    break;
                }
                allNames.add(enteredName);
            }
            System.out.println("\nAll entered names:");
            printNames(allNames);

            List<String> result = Task2Solution.getSolution(allNames);
            if(result.size() == 0){
                System.out.println("There are no unique names\n");
                return;
            }
            System.out.println("Unique names are:");
            printNames(result);
            FileWork.writeToFile(result);

        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void inlineDataFillIn(){
        try{
            List<String> allNames = FileWork.readFromFile();
            System.out.println("\nAll entered names:");
            printNames(allNames);

            List<String> result = Task2Solution.getSolution(allNames);
            if(result.size() == 0){
                System.out.println("There are no unique names\n");
                return;
            }
            System.out.println("Unique names are:");
            printNames(result);
            FileWork.writeToFile(result);

        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void printNames(List<String> allNames){
        for (String item : allNames) {
            System.out.println(item);
        }
        System.out.println();
    }


    private void printTask2Condition(){
        System.out.println("""
                Task 2:
                A list of names is given. Find unique names. Permissible time complexity 
                is O (n), provided that access to the list item by index is O (1).
                """);
    }
}