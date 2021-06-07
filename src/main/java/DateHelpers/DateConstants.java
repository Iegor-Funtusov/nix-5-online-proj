package DateHelpers;

import java.util.HashMap;
import java.util.Map;

public class DateConstants {
    public static  int DEFAULT_YEAR = 2021;
    public static final int DEFAULT_MONTH = 1;
    public static final int DEFAULT_DAY = 1;
    public static final int DEFAULT_TIME = 0;

    public static int SECONDS_IN_MINUTE = 60;
    public static int SECONDS_IN_HOUR = 3600;
    public static int SECONDS_PER_DAY = 86400;

    public static int QUANTITY_OF_MONTHS = 12;


    //Для того чтобы получить номер месяца и кол-во дней в нём
    public static Map<Integer, Integer> getMonthDay(){
        Map<Integer, Integer> monthDay = new HashMap<>();
        //Соответсвие месяца и кол-ва дней в нём
        monthDay.put(1, 31);
        monthDay.put(2, 28);
        monthDay.put(3, 31);
        monthDay.put(4, 30);
        monthDay.put(5, 31);
        monthDay.put(6, 30);
        monthDay.put(7, 31);
        monthDay.put(8, 31);
        monthDay.put(9, 30);
        monthDay.put(10, 31);
        monthDay.put(11, 30);
        monthDay.put(12, 31);
        return monthDay;
    }


}
