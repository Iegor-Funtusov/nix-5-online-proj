package ua.com.exceptions.format;


import org.apache.commons.lang3.StringUtils;
import ua.com.exceptions.entity.Calendar;
import ua.com.exceptions.services.CalendarService;

import java.io.IOException;

public class InputUtil {
    private final static String FIRST_FORMAT = "\\d{0,2}/\\d{1,2}/\\d{0,2}";  // dd/mm/yy
    private final static String SECOND_FORMAT ="\\d{0,2}/\\d{0,2}/\\d{0,4}"; // m/d/yyyy
    private final static String THIRD_FORMAT = "\\D{0,8}-\\d{0,2}-\\d{0,2}"; // mmm-d-yy
    private final static String FOURTH_FORMAT = "\\d{0,2}-\\D{0,8}-\\d{0,4}";  // dd-mmm-yyyy
    private final static String YEAR = "\\d{0,4}";
    private final static int DEFAULT_DAY_MONTH_VALUE = 1;
    private final static int DEFAULT_YEAR = 2021;

    public static Calendar chooseFormatInput(String data, int choise) throws NumberFormatException, IOException, RuntimeException {
        data = data.trim();
        String date = StringUtils.substringBefore(data, " ");
        String time = StringUtils.substringAfter(data, " ");
        int [] Date = new int[3];
        int [] Time = new int[] {0,0,0};
        switch (choise){
            case 1 : {
                //  dd/mm/yy
                if(checkFIRST_FORMAT(date)){
                   Date = splitFIRST(date);
                }
                 Time = timeFunc(time);
                if(CalendarService.checkDate(Date[2], Date[1], Date[0],
                        Time[0], Time[1], Time[2])){
                return new Calendar(Date[2], Date[1], Date[0],
                        Time[0], Time[1], Time[2]);}
            }
            case 2 : {
                // m/d/yyyy
                if(checkSECOND_FORMAT(date)){
                    Date = splitSECOND(date);
                }
                Time = timeFunc(time);
                if(CalendarService.checkDate(Date[2], Date[0], Date[1],
                        Time[0], Time[1], Time[2])){
                return new Calendar(Date[2], Date[0], Date[1],
                        Time[0], Time[1], Time[2]);
                }
            }
            case 3 : {
                //mmm-d-yy
                if(checkTHIRD_FORMAT(date)){
                    Date = splitThird(date);
                }
                Time = timeFunc(time);
                if(CalendarService.checkDate(Date[2], Date[0], Date[1],
                        Time[0], Time[1], Time[2])){
                return new Calendar(Date[2], Date[0], Date[1],
                        Time[0], Time[1], Time[2]);
                }
            }
            case 4 : {
                //dd-mmm-yyyy
                if(checkFOURTH_FORMAT(date)){
                    Date = splitFOURTH(date);
                }
                Time = timeFunc(time);
                if(CalendarService.checkDate(Date[2], Date[1], Date[0],
                        Time[0], Time[1], Time[2])){
                return new Calendar(Date[2], Date[1], Date[0],
                        Time[0], Time[1], Time[2]);
                }
            }
            case 5: {
                // yyyy
                if(checkYEAR(date)){
                    Date[0] = splitYEAR(date);
                }
                Time = timeFunc(time);
                if(CalendarService.checkDate(Date[2], 1, 1,
                        Time[0], Time[1], Time[2]))
                return new Calendar(Date[2], 1, 1,
                        Time[0], Time[1], Time[2]);
            }
        }
      return null;
    }

    public static int[] timeFunc(String time){
        int []Time = new int [3];
        if(!time.equals("")){
                Time = splitTime(time);
        }
        return Time;
    }

    public static boolean checkFIRST_FORMAT(String s){
        try {
            return s.matches(FIRST_FORMAT);
        }
        catch (NumberFormatException e){
            throw new NumberFormatException("Doesn`t match format dd/mm/yy");
        }
    }

    public static boolean checkSECOND_FORMAT(String s) throws NumberFormatException{
        return s.matches(SECOND_FORMAT);
    }

    public static boolean checkTHIRD_FORMAT(String s) throws NumberFormatException{
        return s.matches(THIRD_FORMAT);
    }

    public static boolean checkFOURTH_FORMAT(String s) throws NumberFormatException{
        return s.matches(FOURTH_FORMAT);
    }

    public static boolean checkYEAR(String s) throws NumberFormatException{
        return s.matches(YEAR);
    }

    //  dd/mm/yy
    public static int[] splitFIRST(String s){
        String delimiter = "/";
        int [] Date = new int[3];
        String [] substring;
        substring = s.split(delimiter);
        if(substring.length!=0) {
            for (int i = 0; i < substring.length; i++) {
                if (!substring[i].equals("")) {
                    Date[i] = Integer.parseInt(substring[i]);
                }
                if (substring[0].equals("")) {
                    Date[0] = DEFAULT_DAY_MONTH_VALUE;
                }
                if (substring.length == 3) {
                    Date[2] += 1900;
                }
                if (substring.length == 2) {
                    Date[2] = DEFAULT_YEAR;
                }
            }
        }
        else{
            Date[0] = DEFAULT_DAY_MONTH_VALUE;
            Date[1] = DEFAULT_DAY_MONTH_VALUE;
            Date[2] = DEFAULT_YEAR;
        }
        return Date;
    }

    // m/d/yyyy
    public static int[] splitSECOND(String s){
        String delimiter = "/";
        int [] Date = new int[3];
        String [] substring;
        substring = s.split(delimiter);
        if(substring.length!=0) {
            for (int i = 0; i < substring.length; i++) {
                if (!substring[i].equals("")) {
                    Date[i] = Integer.parseInt(substring[i]);
                }
                if (substring[0].equals("")) {
                    Date[0] = DEFAULT_DAY_MONTH_VALUE;
                }
                if (substring[1].equals("")) {
                    Date[1] = DEFAULT_DAY_MONTH_VALUE;
                }
                if (substring.length == 2) {
                    Date[2] = DEFAULT_YEAR;
                }
            }
        }
        else{
            Date[0] = DEFAULT_DAY_MONTH_VALUE;
            Date[1] = DEFAULT_DAY_MONTH_VALUE;
            Date[2] = DEFAULT_YEAR;
        }
        return Date;
    }

    // mmm-d-yy
    public static int[] splitThird(String s) throws IOException {
        String delimiter = "-";
        int [] Date = new int[3];
        String [] substring;
        substring = s.split(delimiter);
        if(substring.length!=0) {
            for (int i = 0; i < substring.length; i++) {
                if (!substring[0].equals("")) {
                    Date[0] = getMonthName(substring[0]);
                }
                if (substring[1].equals("")) {
                    Date[1] = DEFAULT_DAY_MONTH_VALUE;
                }
                if (substring.length == 2) {
                    Date[2] = DEFAULT_YEAR;
                }
                if (!substring[i].equals("")) {
                    if(i==0){continue;}
                    Date[i] = Integer.parseInt(substring[i]);
                    if(i==2){Date[i] += 1900;}
                }
            }
        }
        else{
            Date[0] = DEFAULT_DAY_MONTH_VALUE;
            Date[1] = DEFAULT_DAY_MONTH_VALUE;
            Date[2] = DEFAULT_YEAR;
        }
        return Date;
    }

    // dd-mmm-yyyy
    public static int[] splitFOURTH(String s) throws IOException {
        String delimiter = "-";
        int [] Date = new int[3];
        String [] substring;
        substring = s.split(delimiter);
        if(substring.length!=0) {
            for (int i = 0; i < substring.length; i++) {
                if (substring[0].equals("")) {
                    Date[0] = DEFAULT_DAY_MONTH_VALUE;
                }
                else{
                    Date[0] =Integer.parseInt(substring[0]);
                }
                if(substring.length > 1) {
                    if (!substring[1].equals("")) {
                        Date[1] = getMonthName(substring[1]);
                    }
                    if(substring.length > 2){
                    if (!substring[2].equals("")) {
                        Date[2] = Integer.parseInt(substring[2]);
                        }
                    }
                }
                if (substring.length == 1) {
                    Date[1] = DEFAULT_DAY_MONTH_VALUE;
                    Date[2] = DEFAULT_YEAR;
                }
                if (substring.length == 2) {
                    Date[2] = DEFAULT_YEAR;
                }
            }
        }
        else{
            Date[0] = DEFAULT_DAY_MONTH_VALUE;
            Date[1] = DEFAULT_DAY_MONTH_VALUE;
            Date[2] = DEFAULT_YEAR;
        }
        return Date;
    }

    // yyyy
    public static int splitYEAR(String s) {
        if(!s.equals("")){
          return Integer.parseInt(s);
        }
        return DEFAULT_YEAR;
    }

    public static int[] splitTime(String s){
        String delimiter = ":";
        int [] Time = new int[3];
        String [] substring;
        substring = s.split(delimiter);
        for(int i = 0; i < substring.length; i++){
            if(substring.length == 1){
                if(!substring[0].equals("")){
                    int a = Integer.parseInt(substring[0]);
                    if(a < 100)
                    Time[2] = a;
                }
            }
            if(substring.length == 2){
                if(!substring[0].equals("")){
                    int a = Integer.parseInt(substring[0]);
                    if(a < 100){
                    Time[1] = a;
                    }
                }
                if(!substring[1].equals("")){
                    int b = Integer.parseInt(substring[1]);
                    if(b < 100){
                    Time[2] = b;
                    }
                }
            }
            if(substring.length == 3){
                if(!substring[0].equals("")){
                    int a = Integer.parseInt(substring[0]);
                    if(a < 100) {
                        Time[0] = a;
                    }
                }
                if(!substring[1].equals("")){
                    int b = Integer.parseInt(substring[1]);
                    if(b < 100){
                        Time[1] = b;
                    }
                }
                if(!substring[2].equals("")){
                    int c = Integer.parseInt(substring[2]);
                    if(c < 100){
                        Time[2] = c;
                    }
                }
            }
        }
        return Time;
    }

    private static int getMonthName(String month) throws IOException {
        if (month.equals("")){
            return DEFAULT_DAY_MONTH_VALUE;
        }
        switch (month){
            case "January": {return 1;}
            case "February": {return 2;}
            case "March": {return 3;}
            case "April": {return 4;}
            case "May": {return 5;}
            case "June": {return 6;}
            case "July": {return 7;}
            case "August": {return 8;}
            case "September": {return 9;}
            case "October": {return 10;}
            case "November": {return 11;}
            case "December": {return 12;}
        }
        throw new IOException("This month " + month + " doesn`t exist");
    }

}
