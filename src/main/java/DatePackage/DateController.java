package DatePackage;

import DateHelpers.CountDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DateController {
    private DateService dateService;
    private BufferedReader bf;

    public DateController(){
        dateService = new DateService();
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public void userInterface(){

        try{
            do{
                System.out.println("Choose the operation: \n" +
                        " 1-create new date \n" +
                        " 2-get info \n" +
                        " 3-find difference between the dates \n" +
                        " 4-add date \n" +
                        " 5-subtract date \n" +
                        " 6-change the date output template \n");

                switch (Integer.parseInt(bf.readLine())){
                    case 1 -> createDate();
                    case 2 -> getDate();
                    case 3 -> differenceBetweenDates();
                    case 4 -> addDate();
                    case 5 -> subtractDate();
                    case 6 -> changeOutputTemplate();
                    default -> System.out.println("Incorrect value entered");
                }

                System.out.println("Do you want to continue? 1-yes, else-no");
            } while (Integer.parseInt(bf.readLine()) == 1);
        }

        catch (IOException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                bf.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }


    private void createDate() throws IOException{
        System.out.println("Enter date with one of the various format:");
        //System.out.println("dd/mm/yyyy hh:mm:ss");
        //System.out.println("dd-mm-yyyy hh:mm:ss");
        //System.out.println("If you don't want to enter time, just press enter after writing your date");
        String dateToCreate = bf.readLine();
        dateService.createDate(dateToCreate);
        System.out.println("Successfully created. " + dateService.getDate() + "\n");

    }


    private void getDate(){
        System.out.println(dateService.getDate());
    }


    private void differenceBetweenDates(){
    }



    private void addDate() {
        try {
            System.out.println("Please choose what do you want to add: 1-seconds, 2-minutes, 3-hours, 4-days, 5-months, 6-years");
            switch (Integer.parseInt(bf.readLine())){
                case 1 -> addSeconds();
                case 2 -> addMinutes();
                case 3 -> addHours();
                case 4 -> addDays();
                case 5 -> addMonths();
                case 6 -> addYears();
                default -> System.out.println("Incorrect value entered");
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }



    private void addSeconds() throws IOException{
        System.out.println("Enter quantity of seconds which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addSeconds(value);
            System.out.println("Successful. " + dateService.getDate().toString());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void addMinutes() throws IOException{
        System.out.println("Enter quantity of minutes which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addMinutes(value);
            System.out.println("Successful. " + dateService.getDate().toString());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void addHours() throws IOException{
        System.out.println("Enter quantity of hours which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addHours(value);
            System.out.println("Successful. " + dateService.getDate().toString());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void addDays() throws IOException{
        System.out.println("Enter quantity of days which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addDays(value);
            System.out.println("Successful. " + dateService.getDate().toString());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void addMonths() throws IOException{
        System.out.println("Enter quantity of months which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addMonths(value);
            System.out.println("Successful. " + dateService.getDate().toString());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void addYears() throws IOException{
        System.out.println("Enter quantity of years which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addYears(value);
            System.out.println("Successful. " + dateService.getDate().toString());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }





    private void subtractDate(){


    }


    //сравнивать перечень дат по убыванию и возрастанию; ????


    private void changeOutputTemplate(){

    }


}
