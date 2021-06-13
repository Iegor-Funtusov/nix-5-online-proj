package utils;

import java.util.HashMap;

public class CONSTANTS {

   public static HashMap<Integer, Integer> monthsMap = new HashMap<Integer, Integer>();

    static {
        monthsMap.put(1, 31);
        monthsMap.put(2, 28);
        monthsMap.put(3, 31);
        monthsMap.put(4, 30);
        monthsMap.put(5, 31);
        monthsMap.put(6, 30);
        monthsMap.put(7, 31);
        monthsMap.put(8, 31);
        monthsMap.put(9, 30);
        monthsMap.put(10, 31);
        monthsMap.put(11, 30);
        monthsMap.put(12, 31);
    }

   public static HashMap<Integer, String> monthsNames = new HashMap<Integer, String>();

    static {
        monthsNames.put(1, "Январь");
        monthsNames.put(2, "Февраль");
        monthsNames.put(3, "Март");
        monthsNames.put(4, "Апрель");
        monthsNames.put(5, "Май");
        monthsNames.put(6, "Июнь");
        monthsNames.put(7, "Июль");
        monthsNames.put(8, "Август");
        monthsNames.put(9, "Сентябрь");
        monthsNames.put(10, "Октябрь");
        monthsNames.put(11, "Ноябрь");
        monthsNames.put(12, "Декабрь");
    }

   public static HashMap<String, Integer> monthsMapNames = new HashMap<String, Integer>();

    static {
        monthsMapNames.put("Январь", 1);
        monthsMapNames.put("Февраль", 2);
        monthsMapNames.put("Март", 3);
        monthsMapNames.put("Апрель", 4);
        monthsMapNames.put("Май", 5);
        monthsMapNames.put("Июнь", 6);
        monthsMapNames.put("Июль", 7);
        monthsMapNames.put("Август", 8);
        monthsMapNames.put("Сентябрь", 9);
        monthsMapNames.put("Октябрь", 10);
        monthsMapNames.put("Ноябрь", 11);
        monthsMapNames.put("Декабрь", 12);
    }
}
