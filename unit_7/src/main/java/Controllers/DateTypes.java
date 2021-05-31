package Controllers;

import java.util.Scanner;

public class DateTypes {
    public static int[] dateControl(int format){
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            input = input.toLowerCase();
            boolean flagSlash = false, flagDash = false;
            if(format == 0 || format == 1 || format == 2) {
                if (withSlashes(input))
                    flagSlash = true;
            }
            if(format == 3 || format == 4) {
                if (withDashes(input))
                    flagDash = true;
            }
            if(flagSlash == true || flagDash == true){
                switch (input){
                    case "f":{
                        return InputOutput.format();
                    }
                    case "stop":{
                        int[] arr = new int[1];
                        arr[0] = -1;
                        return arr;
                    }
                    case "r":{
                        int[] arr = new int[1];
                        arr[0] = -2;
                        return arr;
                    }
                }
                int[] arr;
                if(flagSlash == true) {
                    arr = array(input, '/');
                    arr[6] = format;
                }
                else{
                    arr = array(input, '-');
                    arr[6] = format;
                }
                return arr;
            }
            else{
                System.out.println("Wrong input. Input again");
            }
        }
    }

    public static boolean withSlashes(String input){
        input = input.toLowerCase();
        switch (input){
            case "f": return true;
            case "stop": return true;
            case "r": return true;
        }
        int counter1, counter = counter1 = 0;
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '/')
                counter++;
            if(input.charAt(i) == ':')
                counter1++;
            if(!(Character.isDigit(input.charAt(i)) || input.charAt(i) == '/' || input.charAt(i) == ':' || input.charAt(i) == ' ')){
                System.out.println("Data can contain only digits, slashes, spaces and colons");
                return false;
            }
        }
        if(counter >= 3) {
            System.out.println("Excess slashes");
            return false;
        }
        if(counter1 >= 3) {
            System.out.println("Excess colons");
            return false;
        }
        if(input.isEmpty()){
            System.out.println("Your input is empty.");
            return false;
        }
        char sign = '/';
        if(input(input, sign) == true)
            return true;
        return false;
    }

    public static boolean withDashes(String input){
        input = input.toLowerCase();
        switch (input){
            case "f": return true;
            case "stop": return true;
            case "r": return true;
        }
        int counter1, counter = counter1 = 0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '-')
                counter++;
            if(input.charAt(i) == ':')
                counter1++;
        }
        if(counter >= 3) {
            System.out.println("Excess dashes");
            return false;
        }
        if(counter1 >= 3) {
            System.out.println("Excess colons");
            return false;
        }
        if(input.isEmpty()){
            System.out.println("Your input is empty.");
            return false;
        }
        char sign = '-';
        input(input, sign);
        if(input(input, sign) == true)
            return true;
        return false;
    }


    public static boolean input(String input, char sign){

        int[] arr = array(input, sign);
        if(arr[0] == -1){
            return false;
        }
        int day = arr[0];
        int month = arr[1];
        int year = arr[2];
        int hours = arr[3];
        int minutes = arr[4];
        int seconds = arr[5];

        if(hours < 0 || hours >= 24){
            System.out.println("Hours can be from 0 to 23");
            return false;
        }
        if(minutes < 0 || minutes >= 60){
            System.out.println("Minutes can be from 0 to 59");
            return false;
        }
        if(seconds < 0 || seconds >= 60){
            System.out.println("Seconds can be from 0 to 59");
            return false;
        }

        if(year >= 0) {
            if (month >= 1 && month <= 12) {
                if ((month <= 7 && month % 2 == 1) || (month >= 8 && month % 2 == 0)) {
                    if (day >= 1 && day <= 31) {
                        return true;
                    } else {
                        System.out.println("In this month day can be from 1 to 31");
                        return false;
                    }
                }
                else if (month == 2 && year % 4 == 0){
                    if (day >= 1 && day <= 29) {
                        return true;
                    } else {
                        System.out.println("In February in leap year day can be from 1 to 29");
                        return false;
                    }
                }
                else if(month == 2 && year % 4 != 0){
                    if (day >= 1 && day <= 28) {
                        return true;
                    } else {
                        System.out.println("In February in a common year day can be from 1 to 28");
                        return false;
                    }
                }
                else{
                    if (day >= 1 && day <= 30) {
                        return true;
                    } else {
                        System.out.println("In this month day can be from 1 to 30");
                        return false;
                    }
                }
            } else {
                System.out.println("Month can be from 1 to 12");
                return false;
            }
        }
        else{
            System.out.println("Year can be from 0");
            return false;
        }
    }

    public static int[] array (String string, char Sign){
        String sign = "" + Sign;
        String[] fullTime = string.split(" ");
        String[] date = fullTime[0].split(sign);
        int[] arr = new int[7];
        arr[6] = 0;
        if(sign.charAt(0) == '/' && fullTime[0].length() >= 12){
            System.out.println("Too long date");
            arr[0] = -1;
            return arr;
        }

        if(sign.charAt(0) == '-' && fullTime[0].length() >= 20){
            System.out.println("Too long date");
            arr[0] = -1;
            return arr;
        }

        if(fullTime.length > 2){
            System.out.println("Input only data with or without time (without any excess symbols)");
            arr[0] = -1;
            return arr;
        }
        try {
            if(fullTime.length == 1){
                arr[3] = 0;
                arr[4] = 0;
                arr[5] = 0;
            }
            else if(fullTime.length == 2){
                if(fullTime[1].length() > 8){
                    System.out.println("One of the components of time is too long");
                    arr[0] = -1;
                    return arr;
                }
                String[] time = fullTime[1].split(":");
                int minutes;
                int seconds;
                int hours;
                if(time.length == 3){
                    hours = Integer.parseInt(time[0]);
                    minutes = Integer.parseInt(time[1]);
                    seconds = Integer.parseInt(time[2]);
                }
                else if(time.length == 2){
                    hours = 0;
                    minutes = Integer.parseInt(time[0]);
                    seconds = Integer.parseInt(time[1]);
                }
                else
                {
                    arr[0] = -1;
                    return arr;
                }
                arr[3] = hours;
                arr[4] = minutes;
                arr[5] = seconds;
            }
            if(date.length == 1){
                int year = Integer.parseInt(date[0]);
                arr[1] = 1;
                arr[0] = 1;
                arr[2] = year;
            }
            else if (date.length == 2 && string.charAt(0) == '/') {
                int month = Integer.parseInt(date[1]);
                arr[1] = month;
                arr[0] = 1;
                arr[2] = 2021;
            }
            else if (date.length == 3 && string.charAt(0) == '/') {
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                arr[1] = month;
                arr[0] = 1;
                arr[2] = year;
            }
            else if (date.length == 3) {
                int day;
                int month;
                int year = Integer.parseInt(date[2]);
                if(sign.charAt(0) == '/') {
                    day = Integer.parseInt(date[0]);
                    month = Integer.parseInt(date[1]);
                    arr[1] = month;
                    arr[0] = day;
                    arr[2] = year;
                }
                else{
                    if(fullTime.length == 2) {
                        date[1] = date[1].toLowerCase();
                        month = month(date[1]);
                        if(month == -1) {
                            arr[0] = -1;
                            return arr;
                        }
                        day = Integer.parseInt(date[0]);
                    }
                    else {
                        date[0] = date[0].toLowerCase();
                        month = month(date[0]);
                        day = Integer.parseInt(date[1]);
                    }

                    if(month == -1) {
                        arr[0] = -1;
                        return arr;
                    }
                    arr[1] = month;
                    arr[0] = day;
                    arr[2] = year;
                }
            }
            else{
                arr[0] = -1;
            }
        }
        catch (NumberFormatException e){
            arr[0] = -1;
        }
        return arr;
    }

    private static int month(String string){
        int numOfMonth;
        switch (string){
            case "january": {
                numOfMonth = 1;
                return numOfMonth;
            }
            case "february": {
                numOfMonth = 2;
                return numOfMonth;
            }
            case "march": {
                numOfMonth = 3;
                return numOfMonth;
            }
            case "april": {
                numOfMonth = 4;
                return numOfMonth;
            }
            case "may": {
                numOfMonth = 5;
                return numOfMonth;
            }
            case "june": {
                numOfMonth = 6;
                return numOfMonth;
            }
            case "july": {
                numOfMonth = 7;
                return numOfMonth;
            }
            case "august": {
                numOfMonth = 8;
                return numOfMonth;
            }
            case "september": {
                numOfMonth = 9;
                return numOfMonth;
            }
            case "october": {
                numOfMonth = 10;
                return numOfMonth;
            }
            case "november": {
                numOfMonth = 11;
                return numOfMonth;
            }
            case "december": {
                numOfMonth = 12;
                return numOfMonth;
            }
        }
        return -1;
    }
}
