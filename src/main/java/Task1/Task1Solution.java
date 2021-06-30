package Task1;
import java.util.ArrayList;
import java.util.List;

public class Task1Solution{

    public static List<String> getSolution(List<String> dates){
        if(dates.isEmpty()){
            throw new RuntimeException("There are no dates");
        }

        List<String> resultDates = new ArrayList<>();

        for(String item : dates){
            String []components = validateDateBySlash(item);
            if(components == null){
                components = validateDateByDash(item);
                if(components == null){
                    continue;
                }
                String result = splitConcatDate(components);
                resultDates.add(result);
                continue;
            }
            String result = splitConcatDate(components);
            resultDates.add(result);
        }
        return resultDates;
    }


    private static String[] validateDateBySlash(String date){
        try {
            String []components = date.split("/",3);
            int day, month, year;
            if(components.length != 3){
                return null;
            }
            //Если в первом разряде больше 31, то проверяю его как год
            if(Integer.parseInt(components[0]) > 31){
                day = Integer.parseInt(components[2]);
                year = Integer.parseInt(components[0]);
            } else {
                day = Integer.parseInt(components[0]);
                year = Integer.parseInt(components[2]);
            }
            month = Integer.parseInt(components[1]);

            if(day < 0 || month < 0 || month > 12 || year < 0){
                return null;
            }
            //Проверка что кол-во дней не больше чем всего дней в месяце
            int daysInMonth = getDaysInMonth(month, checkViscousYear(year));
            if(day > daysInMonth){
                return null;
            }
            return components;

        } catch (NumberFormatException e){
            return null;
        }
    }


    private static String[] validateDateByDash(String date){
        try {
            String []components = date.split("-",3);
            int day, month, year;
            if(components.length != 3){
                return null;
            }
            month = Integer.parseInt(components[0]);
            day = Integer.parseInt(components[1]);
            year = Integer.parseInt(components[2]);

            if(day < 0 || month < 0 || month > 12 || year < 0){
                return null;
            }
            //Проверка что кол-во дней не больше чем всего дней в месяце
            int daysInMonth = getDaysInMonth(month, checkViscousYear(year));
            if(day > daysInMonth){
                return null;
            }
            return components;

        } catch (NumberFormatException e){
            return null;
        }
    }


    private static String splitConcatDate(String []components){
        return components[0] + components[1] + components[2];
    }


    private static int getDaysInMonth(int month, boolean viscousYear){
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> viscousYear ? 29 : 28;
            case 4, 6, 9, 11 -> 30;
            default -> -1;
        };
    }


    private static boolean checkViscousYear(int year){
        return (year % 4 == 0 || year % 400 == 0) && year % 100 != 0;
    }
}
