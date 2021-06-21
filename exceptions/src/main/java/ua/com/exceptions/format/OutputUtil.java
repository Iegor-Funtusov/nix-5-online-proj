package ua.com.exceptions.format;

import ua.com.exceptions.entity.Calendar;

public class OutputUtil {
    public static void chooseFormatOutput(Calendar calendar, int choise){
        switch (choise){
            case 1: {
                //  dd/mm/yy
                System.out.println(addZero(calendar.getDay()) + "/" +
                        addZero(calendar.getMonth()) + "/" +
                        shortYear(calendar.getYear()) + " " +
                        printHMS(calendar));
                break;
            }
            case 2: {
                //  m/d/yyyy
                System.out.println(calendar.getMonth() + "/" +
                        calendar.getDay()+ "/" +
                        calendar.getYear() + " " +
                        printHMS(calendar));
                break;
            }
            case 3: {
                //  mmm-d-yy
                System.out.println(getMonthName(calendar.getMonth()) + "-" +
                        calendar.getDay() + "-" +
                        shortYear(calendar.getYear()) + " " +
                        printHMS(calendar));
                break;
            }
            case 4: {
                // dd-mmm-yyyy 00:00
                System.out.println(addZero(calendar.getDay()) + "-" +
                        getMonthName(calendar.getMonth()) + "-" +
                        calendar.getYear() + " " +
                        printHMS(calendar));
                break;
            }
        }
    }

    private static String printHMS(Calendar c){
        return addZero(c.getHour()) + ":" +
                addZero(c.getMinute()) + ":" +
                addZero(c.getSecond());
    }

    private static String addZero(int number){
        if(number >=1 && number<=9){
            return "0" + number;
        }
        if(number == 0){
            return "00";
        }
        return Integer.toString(number);
    }

    private static String shortYear(int year){
        String yearS = Integer.toString(year);
        return yearS.substring(yearS.length()-2);
    }

    private static String getMonthName(int month){
        switch (month){
            case 1: {return   "January";}
            case 2: {return   "February";}
            case 3: {return   "March";}
            case 4: {return   "April";}
            case 5: {return   "May" ;}
            case 6: {return   "June" ;}
            case 7: {return   "July";}
            case 8: {return   "August"  ;}
            case 9: {return   "September";}
            case 10: {return  "October" ;}
            case 11: {return  "November"  ;}
            case 12: {return  "December" ;}
        }
        return null;
    }

}
