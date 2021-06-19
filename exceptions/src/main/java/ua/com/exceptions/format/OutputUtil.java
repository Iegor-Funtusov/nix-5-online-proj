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
                System.out.println(calendar.getDay() + "/" +
                        calendar.getMonth() + "/" +
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
        return Integer.toString(number);
    }

    private static String shortYear(int year){
        String yearS = Integer.toString(year);
        return yearS.substring(yearS.length()-2);
    }

    private static String getMonthName(int month){
        switch (month){
            case 1: {return   "Январь";}
            case 2: {return   "Февраль";}
            case 3: {return   "Март";}
            case 4: {return   "Апрель";}
            case 5: {return   "Май" ;}
            case 6: {return   "Июнь" ;}
            case 7: {return   "Июль";}
            case 8: {return   "Август"  ;}
            case 9: {return   "Сентябрь";}
            case 10: {return  "Октябрь" ;}
            case 11: {return  "Ноябрь"  ;}
            case 12: {return  "Декабрь" ;}
        }
        return null;
    }

}
