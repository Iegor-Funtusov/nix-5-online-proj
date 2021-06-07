package DatePackage;

import DateHelpers.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DateController {
    private final DateService dateService;
    private OutputTemplate outputTemplate;
    private final BufferedReader bf;

    public DateController(){
        dateService = new DateService();
        outputTemplate = OutputTemplate.DD_MM_YY;   //Шаблон вывода даты по умолчанию
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public void userInterface(){
        try{
            do{
                System.out.println("""
                        Choose the operation:\s
                         1-create new date\s
                         2-get all dates\s
                         3-find difference between the dates\s
                         4-add date\s
                         5-subtract date\s
                         6-sort dates\s
                         7-change the date output template\s
                         8-show input templates""");

                switch (Integer.parseInt(bf.readLine())){
                    case 1 -> createDate();
                    case 2 -> printAllDates();
                    case 3 -> differenceBetweenDates();
                    case 4 -> addDate();
                    case 5 -> subtractDate();
                    case 6 -> sortDates();
                    case 7 -> changeOutputTemplate();
                    case 8 -> showInputTemplates();
                    default -> System.out.println("Incorrect value entered");
                }

                System.out.println("Do you want to continue? 1-yes, else-no");
            } while (Integer.parseInt(bf.readLine()) == 1);
        }
        catch (NumberFormatException e){
            System.out.println("Incorrect value entered! Please, try once more");
        }  catch (IOException e){
            System.out.println("Incorrect value entered! Please, try once more");
        }  catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                bf.close();
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }


    private void createDate() {
        try {
            System.out.println("Enter date with one of the various format:");
            String dateToCreate = bf.readLine();
            Date addedDate = dateService.createDate(dateToCreate);
            System.out.println("Successfully created. " + formOutputDate(addedDate));
        } catch (RuntimeException e){
            System.out.println("Incorrect value entered");
        } catch (IOException e) {
            System.out.println("Incorrect value entered");
        }
    }


    private void addDate() {
        try {
            Date dateToModify = chooseDate();
            System.out.println("Please choose what do you want to add: 1-seconds, 2-minutes, 3-hours, 4-days, 5-months, 6-years");
            switch (Integer.parseInt(bf.readLine())){
                case 1 -> addSeconds(dateToModify);
                case 2 -> addMinutes(dateToModify);
                case 3 -> addHours(dateToModify);
                case 4 -> addDays(dateToModify);
                case 5 -> addMonths(dateToModify);
                case 6 -> addYears(dateToModify);
                default -> System.out.println("Incorrect value entered");
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void addSeconds(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of seconds which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addSeconds(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void addMinutes(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of minutes which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addMinutes(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void addHours(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of hours which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addHours(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void addDays(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of days which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addDays(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void addMonths(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of months which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addMonths(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void addYears(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of years which you want to add to the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.addYears(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }




    private void subtractDate(){
        try {
            Date dateToModify = chooseDate();
            System.out.println("Please choose what do you want to subtract: 1-seconds, 2-minutes, 3-hours, 4-days, 5-months, 6-years");
            switch (Integer.parseInt(bf.readLine())){
                case 1 -> subtractSeconds(dateToModify);
                case 2 -> subtractMinutes(dateToModify);
                case 3 -> subtractHours(dateToModify);
                case 4 -> subtractDays(dateToModify);
                case 5 -> subtractMonths(dateToModify);
                case 6 -> subtractYears(dateToModify);
                default -> System.out.println("Incorrect value entered");
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


    private void subtractSeconds(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of seconds which you want to subtract from the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.subtractSeconds(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void subtractMinutes(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of minutes which you want to subtract from the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.subtractMinutes(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void subtractHours(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of hours which you want to subtract from the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.subtractHours(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void subtractDays(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of days which you want to subtract from the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.subtractDays(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void subtractMonths(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of months which you want to subtract from the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.subtractMonths(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void subtractYears(Date dateToModify) throws IOException{
        System.out.println("Enter quantity of years which you want to subtract from the current date:");
        int value = Integer.parseInt(bf.readLine());

        try {
            dateService.subtractYears(dateToModify, value);
            System.out.println("Successful. " + formOutputDate(dateToModify));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }



    private void differenceBetweenDates(){
        try {
            System.out.println("Choose first date:");
            Date firstDate = chooseDate();
            System.out.println("Choose second date:");
            Date secondDate = chooseDate();
            System.out.println("What difference do you want to find?");
            System.out.println("1-seconds, 2-minutes, 3-hours, 4-days, 5-months, 6-years");
            switch (Integer.parseInt(bf.readLine())){
                case 1 -> System.out.println("Result for seconds: " + dateService.differenceSeconds(firstDate, secondDate));
                case 2 -> System.out.println("Result for minutes: " + dateService.differenceMinutes(firstDate, secondDate));
                case 3 -> System.out.println("Result for hours: " + dateService.differenceHours(firstDate, secondDate));
                case 4 -> System.out.println("Result for days: " + dateService.differenceDays(firstDate, secondDate));
                case 5 -> System.out.println("Result for months: " + dateService.differenceMonths(firstDate, secondDate));
                case 6 -> System.out.println("Result for years: " + dateService.differenceYears(firstDate, secondDate));
            }

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }



    private void sortDates() throws IOException {
        ArrayList<Date> sortedDates;
        System.out.println("Choose the order of sorting: 1-ascending sort, 2-descending sort");
        switch (Integer.parseInt(bf.readLine())){
            case 1 -> sortedDates = dateService.ascendingSort(dateService.getDate());
            case 2 -> sortedDates = dateService.descendingSort(dateService.getDate());
            default -> {
                System.out.println("Incorrect value entered");
                return;
            }
        }
        printAllDates();
    }



    private Date chooseDate() throws IOException {
        printAllDates();
        System.out.println("Choose the index of a date, or input 0 to create new date");
        int choose = Integer.parseInt(bf.readLine());
        if(choose == 0){
            createDate();
            return dateService.getDate(dateService.getDate().size() - 1);
        }
        if(choose < 0 || choose > dateService.getDate().size()){
            throw new RuntimeException("Incorrect index entered");
        }

        return dateService.getDate().get(choose-1);
    }



    private void printAllDates(){
        ArrayList<Date> dates = (ArrayList<Date>) dateService.getDate();
        if(dates.size() == 0){
            System.out.println("There are no dates");
            return;
        }
        for(int i = 0; i < dates.size(); i ++){
            System.out.println("Date #" + (i+1) + " " + formOutputDate(dates.get(i)));
        }
    }



    private StringBuilder formOutputDate(Date date){
        try {
            StringBuilder result = new StringBuilder("");
            switch (outputTemplate){
                case DD_MM_YY -> {
                    result.append(date.getDay()).append("/");
                    result.append(date.getMonth()).append("/");
                    result.append(date.getYear());
                }
                case M_D_YY -> {
                    result.append(date.getMonth()).append("/");
                    result.append(date.getDay()).append("/");
                    result.append(date.getYear());
                }
                case MMM_D_YY -> {
                    result.append(dateService.getFormattedMonth(date.getMonth())).append("-");
                    result.append(date.getDay()).append("-");
                    result.append(date.getYear());
                }
                case DD_MMM_YY -> {
                    result.append(date.getDay()).append("-");
                    result.append(dateService.getFormattedMonth(date.getMonth())).append("-");
                    result.append(date.getYear());
                }
            }
            result.append(" ").append(dateService.getFormattedTime(date));
            return result;
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



    private void changeOutputTemplate() throws IOException{
        System.out.println("""
                Choose the output template:\s
                1: dd/mm/yy hh:mm:ss\s
                2: m/d/yy hh:mm:ss\s
                3: mmm-dd-yy hh:mm:ss\s
                4: dd-mmm-yy hh:mm""");

        switch (Integer.parseInt(bf.readLine())){
            case 1 -> outputTemplate = OutputTemplate.DD_MM_YY;
            case 2 -> outputTemplate = OutputTemplate.M_D_YY;
            case 3 -> outputTemplate = OutputTemplate.MMM_D_YY;
            case 4 -> outputTemplate = OutputTemplate.DD_MMM_YY;
            default -> {
                System.out.println("Incorrect value entered");
                return;
            }
        }
        System.out.println("Successfully changed");
    }



    private void showInputTemplates(){
        System.out.println("dd/mm/yyyy");
        System.out.println("/mm/yyyy");
        System.out.println("/mm/");
        System.out.println("yyyy");
        System.out.println("Also you can add a time by the space with such template hh:mm:ss or mm:ss");
        System.out.println("If you don't want to enter time, just press enter after writing your date \n");
    }
}
