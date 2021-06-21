package ua.com.exceptions.services;

import ua.com.exceptions.entity.Calendar;
import ua.com.exceptions.format.InputUtil;
import ua.com.exceptions.format.OutputUtil;
import ua.com.exceptions.operations.AddToCalendar;
import ua.com.exceptions.operations.DifferenceCalendar;
import ua.com.exceptions.operations.SubtractFromCalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class GeneralService {

    public final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final static String[] FORMATS = {"dd/mm/yy hh:mm:ss", "m/d/yyyy hh:mm:ss",
    "mmm-d-yy hh:mm:ss", "dd-mmm-yyyy hh:mm:ss"};
    private final static String[] ITEMS= {"seconds", "minutes",
            "hours", "days", "months", "years"};
    private int format;
    boolean check = true;
    private static ArrayList<Calendar> list = new ArrayList<Calendar>();

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public void run(){
        while(check){
        if(getFormat() == 0) {
            setFormat();
        }
        System.out.println("Would you like to exchange format? 0/1");
        try {
            String res = bufferedReader.readLine();
            int res_ = Integer.parseInt(res);
            if(res_ == 0) {exchangeFormat();}
        }catch (IOException | NumberFormatException e){
            System.out.println("Error! Please enter format in a range 1-2");
            continue;
        }
        System.out.println("Please, choose operation");
        System.out.println("1 -> Add to date");
        System.out.println("2 -> Subtract to date");
        System.out.println("3 -> Find difference between two dates");
        System.out.println("4 -> Sort dates");
        System.out.println("5 -> Exit");
        System.out.println("");
        try {
            String res = bufferedReader.readLine();
            int res_ = Integer.parseInt(res);
            if(res_ > 0 && res_ < 6){
                System.out.println(res_);
                chooseOperation(res_);
            }
            else{
                throw new IOException("Error! Please enter operation in a range 1-5");
            }
        }catch (IOException | NumberFormatException e){
            System.out.println("Error! Please enter operation in a range 1-5");
        }
    }
}

    private void chooseOperation(int operation) throws IOException {
        switch (operation){
            case 1: {
                System.out.println("Choose what do you want to add: ");
                listOptions();
                chooseAdd(readFromConsoleOption());
                break;
            }
            case 2: {
                System.out.println("Choose what do you want to subtract");
                listOptions();
                chooseSub(readFromConsoleOption());
                break;
            }
            case 3: {
                System.out.println("Please enter two dates");
                String date1 = enterStringData();
                Calendar calendar1 = CreateCalendar(date1);
                Calendar calendar2 = null;
                if(calendar1!=null) {
                    list.add(calendar1);
                    String date2 = enterStringData();
                    calendar2 = CreateCalendar(date2);
                }
                if(calendar1!=null && calendar2!=null){
                    list.add(calendar2);
                    System.out.println("Choose in what do you want to get difference?");
                    listOptions();
                    chooseDiff(readFromConsoleOption(), calendar1, calendar2);
                }
                break;
            }
            case 4: {
                while(true) {
                System.out.println("System saved all entered dates and have default list." +
                        "Would you like to add? 0/1");
                    try {
                        String res = bufferedReader.readLine();
                        int res_ = Integer.parseInt(res);
                        if (res_ == 0) {
                            String date = enterStringData();
                            Calendar calendar = CreateCalendar(date);
                            list.add(calendar);
                        }
                        else{
                            break;
                        }
                    } catch (IOException e) {
                        System.out.println("Error! Please enter format in a range 1-2");
                    }
                }
                System.out.println("1 -> sort");
                System.out.println("2 -> reverse");
                readFromConsoleOptionBool();
                break;
            }
            case 5: {
                check = false;
                break;
            }
        }
    }

    private void listOptions(){
        System.out.println("1 -> seconds");
        System.out.println("2 -> minutes");
        System.out.println("3 -> hours");
        System.out.println("4 -> days");
        System.out.println("5 -> months");
        System.out.println("6 -> years");
    }

    private int readFromConsoleOption() throws IOException {
        try {
            String res = bufferedReader.readLine();
            int res_ = Integer.parseInt(res);
            if(res_ > 0 && res_< 7){
                return res_;
            }
        }catch (IOException e){
            System.out.println("Error! Please enter operation in a range 1-6");
        }
        throw new IOException("Error! Please enter operation in a range 1-6");
    }

    private void readFromConsoleOptionBool() throws IOException {
        System.out.println("Enter your choice: ");
        try {
            String res = bufferedReader.readLine();
            int res_ = Integer.parseInt(res);
            switch (res_){
                case 1:{
                chooseSort(1);
                break;
            }
                case 2: {
                chooseSort(2);
                break;
            }
            default: {System.out.println("Please, enter 1 or 2");
                readFromConsoleOptionBool();}
            }
        }catch (IOException e){
            System.out.println("Error! Please enter 1/2");
        }
    }

    private void chooseSort(int choise){
        TreeSet<Calendar> calendarsList;
        Calendar calendar = new Calendar(1200, 12, 2, 12, 12 ,12);
        Calendar calendar1 = new Calendar(1234, 11, 2, 12, 12 ,12);
        Calendar calendar2 = new Calendar(1234, 1, 2, 12, 12 ,12);
        list.add(calendar);
        list.add(calendar1);
        list.add(calendar2);
        if(choise == 1){
        calendarsList = (TreeSet<Calendar>) new TreeSet<>(list).descendingSet();
        }
        else{
            calendarsList = new TreeSet<>(list);
        }
        for (Calendar c: calendarsList) {
            System.out.println(c);
        }
    }

    private void chooseAdd(int choise){
        System.out.println("Enter " + ITEMS[choise-1]);
        int variable = 0;
        try{
            variable = Integer.parseInt(bufferedReader.readLine());
        }
        catch (IOException | NumberFormatException e){
            System.out.println("Sorry, you entered empty");
            //chooseAdd(choise);
        }
        String date = enterStringData();
        Calendar calendar = CreateCalendar(date);
        if(calendar!=null)
        list.add(calendar);
        if(variable > 0 && calendar != null) {
                switch (choise) {
                case 1: { AddToCalendar.addSeconds(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
                case 2: { AddToCalendar.addMinutes(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
                case 3: { AddToCalendar.addHourse(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
                case 4: { AddToCalendar.addDay(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
                case 5: { AddToCalendar.addMonth(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
                case 6: { AddToCalendar.addYear(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
            }
        }
        else{
            System.out.println("Sorry, you made a mistake: date is empty or number is negative. Try once more");
        }
    }

    private void chooseDiff(int choise, Calendar a, Calendar b){
            switch (choise) {
                case 1: {
                    System.out.println("Difference in seconds: " +
                            DifferenceCalendar.getDiffInSeconds(a, b));
                    break;}
                case 2: {
                    System.out.println("Difference in minutes: " +
                            DifferenceCalendar.getDiffInMinutes(a, b));
                    break;}
                case 3: {
                    System.out.println("Difference in hours: " +
                            DifferenceCalendar.getDiffInHours(a, b));
                    break;}
                case 4: {
                    System.out.println("Difference in days: " +
                            DifferenceCalendar.getDiffInDays(a, b));
                    break;}
                case 5: {
                    System.out.println("Difference in months: " +
                            DifferenceCalendar.getDiffInMonths(a, b));
                    break;}
                case 6: {
                    System.out.println("Difference in years: " +
                            DifferenceCalendar.getDiffInYears(a, b));
                    break;}
        }
    }

    private void chooseSub(int choise){
        System.out.println("Enter " + ITEMS[choise-1]);
        int variable = 0;
        try{
            variable = Integer.parseInt(bufferedReader.readLine());
        }
        catch (IOException | NumberFormatException e){
            System.out.println("Sorry, entered empty");
           // chooseSub(choise);
        }
        String date = enterStringData();
        Calendar calendar = CreateCalendar(date);
        if(calendar!=null)
        list.add(calendar);
        if(variable > 0 && calendar != null) {
            switch (choise) {
                case 1: { SubtractFromCalendar.subtractSeconds(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
                case 2: { SubtractFromCalendar.subtractMinutes(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
                case 3: { SubtractFromCalendar.subtractHourse(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
                case 4: { SubtractFromCalendar.subtractDay(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
                case 5: { SubtractFromCalendar.subtractMonth(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
                case 6: { SubtractFromCalendar.subtractYear(calendar, variable);
                    OutputUtil.chooseFormatOutput(calendar,format);
                    break;}
            }
        }
        else{
            System.out.println("Sorry, you made a mistake : date is empty or number is negative. Try once more");
        }
    }

    public String enterStringData(){
        System.out.println("Please, enter the Date in format -> " + FORMATS[format-1]);
        try{
            String s = bufferedReader.readLine();
            return s;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
            return null;
    }

    private int chooseFormat() throws IOException {
        System.out.println("Please, choose format ");
        System.out.println("1 -> dd/mm/yy hh:mm:ss");
        System.out.println("2 -> m/d/yyyy hh:mm:ss");
        System.out.println("3 -> mmm-d-yy hh:mm:ss");
        System.out.println("4 -> dd-mmm-yyyy hh:mm:ss");
        try{
        int res = Integer.parseInt(bufferedReader.readLine());
        if(res > 0 && res < 5){
            return res;
            }
        }
        catch (IOException | NumberFormatException e){
           // System.out.println(e.getMessage());
        }
        System.out.println("Sorry! You`ve entered invalid format");
        return 0;
    }

    public void setFormat(){
        try {
            while (true) {
                if (getFormat() == 0) {
                    setFormat(chooseFormat());
                }
                if (getFormat() != 0) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error! Please enter format in a range 1-4");
        }
    }

    public void exchangeFormat(){
        try {
            setFormat(chooseFormat());
            if(getFormat()==0){
                exchangeFormat();
            }
        }
        catch (IOException e) {
            System.out.println("Error! Please enter format in a range 1-4");
        }
    }

    public Calendar CreateCalendar(String data){
        Calendar calendar1 = null;
        try {
            calendar1 = InputUtil.chooseFormatInput(data, format);
        }
        catch (NumberFormatException e){
            System.out.println("Sorry, you have format error");
        }
        catch (IOException e){
            System.out.println("Sorry, you wrote month with typo");
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return calendar1;
    }
}
