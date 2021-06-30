package Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task1UserInterface {
    private final BufferedReader bf;

    public Task1UserInterface(){
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public void task1UserUI(){
        String AGREE_INPUT = "1";
        try {
            printTask1Condition();
            do{
                System.out.println("1-Enter dates from the keyboard, 2-Use inline data, 'Q'-exit");
                switch (bf.readLine().toUpperCase()){
                    case "1" -> keyboardDataInput();
                    case "2" -> inlineDataFillIn();
                    case "Q" -> System.exit(1);
                    default -> System.out.println("Incorrect value entered");
                }

                System.out.println("Do you want to continue working with this task? 1-yes, else-no");
            } while (bf.readLine().equals(AGREE_INPUT));

        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void keyboardDataInput(){
        try{
            List<String> allDates = new ArrayList<>();
            String enteredDate;
            while (true){
                System.out.println("Enter new date. If you want to stop enter 'S'");
                enteredDate = bf.readLine();

                if(enteredDate.equalsIgnoreCase("s")){
                    break;
                }
                allDates.add(enteredDate);
            }
            System.out.println("\nAll entered dates:");
            printDates(allDates);

            List<String> formattedDates = Task1Solution.getSolution(allDates);
            if(formattedDates.size() == 0){
                System.out.println("Dates is empty");
                return;
            }
            System.out.println("Formatted dates:");
            printDates(formattedDates);
            FileWork.writeToFile(formattedDates);

        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void inlineDataFillIn(){
        //Будут выведены только 1/1/2001, 2002/2/2, 3-3-2003 т.к. они подходят под все требования корректной даты

        try{
            List<String> listOfDates = FileWork.readFromFile();
            System.out.println("\nAll dates:");
            printDates(listOfDates);

            List<String> formattedDates = Task1Solution.getSolution(listOfDates);
            if(formattedDates.size() == 0){
                System.out.println("Dates is empty");
                return;
            }
            System.out.println("Formatted dates:");
            printDates(formattedDates);
            FileWork.writeToFile(formattedDates);

        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void printDates(List<String> allDates){
        for (String date : allDates) {
            System.out.println(date);
        }
        System.out.println();
    }


    private void printTask1Condition(){
        System.out.println("""
                Task 1:
                Given a list of dates (string) in formats like “2020/04/05”,\s
                “05/04/2020”, “04-05-2020” (all dates in the example are 5th April 2020)\s
                Return a list of dates (string) in the format “20200405”.\s
                Ignore dates with invalid format.
                """);
    }
}
