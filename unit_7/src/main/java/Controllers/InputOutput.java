package Controllers;

import java.util.Scanner;

import static Controllers.DateTypes.*;

public class InputOutput {
    public static void Output(int[] date){
        if(date[6] == 1){
            withoutTime(date);
            System.out.println();
            return;
        }
        if(date[6] == 2){
            withoutZeros(date);
            System.out.println();
            return;
        }
        if(date[6] == 3){
            firstMonth(date);
            System.out.println();
            return;
        }
        if(date[6] == 4){
            withTime(date);
            System.out.println();
            return;
        }
        for(int i = 0; i < 6; i++) {
            if(i == 3 || i == 4){
                if(date[i]/ 10 < 1){
                    System.out.print("0" + date[i] + ":");
                }
                else
                    System.out.print(date[i] + ":");
                continue;
            }
            if(i == 2){
                System.out.print(date[i] + " ");
                continue;
            }
            if(i == 0 || i == 1)
                if(date[i]/ 10 < 1)
                    System.out.print("0"+date[i] + "/");
                else
                    System.out.print(date[i] + "/");
            if(i == 5)
                if(date[i]/ 10 < 1){
                    System.out.println("0" + date[i]);
                }
                else
                    System.out.println(date[i]);
        }
    }

    private static void withoutTime(int[] date){
        for(int i = 0; i < 6; i++) {
            if (i == 2) {
                System.out.print(date[i] + " ");
                continue;
            }
            if (i == 0 || i == 1)
                if (date[i] / 10 < 1)
                    System.out.print("0" + date[i] + "/");
                else
                    System.out.print(date[i] + "/");
        }
    }

    private static void withoutZeros(int[] date){
        for(int i = 0; i < 6; i++) {
            if(i == 2){
                System.out.print(date[i] + " ");
                continue;
            }
            if(i == 0 || i == 1)
                if(date[i]/ 10 < 1)
                    System.out.print(date[i] + "/");
                else
                    System.out.print(date[i] + "/");
        }
    }

    private static void firstMonth(int[] date){
        String month = month(date[1]);
        System.out.print(month);
        System.out.print("-");
        System.out.print(date[0]);
        System.out.print("-");
        System.out.print(date[2]);
    }

    private static void withTime(int[] date){
        if(date[0] / 10 < 1)
            System.out.print("0"+date[0]);
        else
            System.out.print(date[0]);
        System.out.print("-");
        System.out.print(month(date[1]));
        System.out.print("-");
        System.out.print(date[2]+" ");
        if(date[3] / 10 < 1)
            System.out.print("0" + date[3] + ":");
        else
            System.out.print(date[3] + ":");
        if(date[4] / 10 < 1)
            System.out.print("0" + date[4] + ":");
        else
            System.out.print(date[4] + ":");
        if(date[5] / 10 < 1)
            System.out.print("0" + date[5]);
        else
            System.out.print(date[5]);
    }

    public static int[] format(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the way that you want to input and output date:\n" +
                "0 >> return to default input/output\n" +
                "1 >> dd/mm/yy - e.g. 01/12/21\n" +
                "2 >> m/d/yyyy - e.g. 3/4/2021\n" +
                "3 >> mmm-d-yy - e.g. March-4-21\n" +
                "4 >> dd-mmm-yyyy 00:00 - e.g. 09-April-1789 00:00");
        String input, type = scanner.nextLine();
        while (true) {
            switch (type) {
                case "0":{
                    System.out.println("Input date: ");
                    input = scanner.nextLine();
                    if (withSlashes(input) == true) {
                        if(correctness(input) == false) {
                            int[] arr = array(input, '/');
                            arr[6] = 0;
                            return arr;
                        }
                        continue;
                    }
                    System.out.println("Incorrect input. Input again (e.g 12/10/2021)");
                }break;
                case "1": {
                    System.out.println("Input date: ");
                    input = scanner.nextLine();
                    if (withSlashes(input) == true) {
                        if(correctness(input) == false) {
                            int[] arr = array(input, '/');
                            arr[6] = 1;
                            return arr;
                        }
                        continue;
                    }
                    System.out.println("Incorrect input. Input again (e.g 11/09/2020)");
                }
                break;
                case "2": {
                    System.out.println("Input date: ");
                    input = scanner.nextLine();
                    if (withSlashes(input) == true) {
                        if(correctness(input) == false) {
                            int[] arr = array(input, '/');
                            arr[6] = 2;
                            return arr;
                        }
                        continue;
                    }
                    System.out.println("Incorrect input. Input again (e.g 1/3/2019)");
                }
                break;
                case "3": {
                    System.out.println("Input date: ");
                    input = scanner.nextLine();
                    if (withDashes(input) == true) {
                        if(correctness(input) == false) {
                            int[] arr = array(input, '-');
                            arr[6] = 3;
                            return arr;
                        }
                        continue;
                    }
                    System.out.println("Incorrect input. Input again (e.g May-12-2021)");
                }
                break;
                case "4": {
                    System.out.println("Input date: ");
                    input = scanner.nextLine();
                    if (withDashes(input) == true) {
                        if(correctness(input) == false) {
                            int[] arr = array(input, '-');
                            arr[6] = 4;
                            return arr;
                        }
                        continue;
                    }
                    System.out.println("Incorrect input. Input again (e.g 09-April-2021 00:00)");
                }break;
                default: {
                    System.out.println("Incorrect input. Input again");
                    type = scanner.nextLine();
                }
            }
        }
    }

    private static boolean correctness(String string){
        switch (string){
            case "f":{
                System.out.println("Incorrect input. You have already chosen the format. Input again");
                return true;
            }
            case "stop":{
                System.out.println("Incorrect input. Input again");
                return true;
            }
            case "r":{
                System.out.println("Now you can't return. End your operations with date");
                return true;
            }
        }
        return false;
    }

    private static String month(int month){
        String Month = "";
        switch (month){
            case 1: {
                Month = "January";
                return Month;
            }
            case 2: {
                Month = "February";
                return Month;
            }
            case 3: {
                Month = "March";
                return Month;
            }
            case 4: {
                Month = "April";
                return Month;
            }
            case 5: {
                Month = "May";
                return Month;
            }
            case 6: {
                Month = "June";
                return Month;
            }
            case 7: {
                Month = "July";
                return Month;
            }
            case 8: {
                Month = "August";
                return Month;
            }
            case 9: {
                Month = "September";
                return Month;
            }
            case 10: {
                Month = "October";
                return Month;
            }
            case 11: {
                Month = "November";
                return Month;
            }
            case 12: {
                Month = "December";
                return Month;
            }
        }
        return Month;
    }
}
