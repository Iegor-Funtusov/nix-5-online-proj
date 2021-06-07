package DateHelpers;
import DatePackage.Date;

//Нахождение разности дат
public class DifferenceDate {

    public static int findDifferenceSeconds(Date first, Date second){
        if(isEmpty(first) || isEmpty(second)){
            throw new RuntimeException("Date does not exist");
        }

        int firstSeconds = getSecondsFromTime(first);
        int secondSeconds = getSecondsFromTime(second);
        return Math.abs(firstSeconds - secondSeconds);
    }


    public static int findDifferenceMinutes(Date first, Date second){
        if(isEmpty(first) || isEmpty(second)){
            throw new RuntimeException("Date does not exist");
        }

        int firstMinutes = getMinutesFromTime(first);
        int secondMinutes = getMinutesFromTime(second);
        return Math.abs(firstMinutes - secondMinutes);
    }


    public static int findDifferenceHours(Date first, Date second){
        if(isEmpty(first) || isEmpty(second)){
            throw new RuntimeException("Date does not exist");
        }

        int firstHours = getHoursFromTime(first);
        int secondHours = getHoursFromTime(second);
        return Math.abs(firstHours - secondHours);
    }


    public static int findDifferenceDays(Date first, Date second){
        if(isEmpty(first) || isEmpty(second)){
            throw new RuntimeException("Date does not exist");
        }

        return Math.abs(first.getDay() - second.getDay());
    }


    public static int findDifferenceMonths(Date first, Date second){
        if(isEmpty(first) || isEmpty(second)){
            throw new RuntimeException("Date does not exist");
        }

        return Math.abs(first.getMonth() - second.getMonth());
    }


    public static int findDifferenceYears(Date first, Date second){
        if(isEmpty(first) || isEmpty(second)){
            throw new RuntimeException("Date does not exist");
        }
        return Math.abs(first.getYear() - second.getYear());
    }


    //Служебные методы
    private static boolean isEmpty(Date dateToCheck){
        return dateToCheck == null;
    }


    private static int getSecondsFromTime(Date date){
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }
        return date.getTime() % 60;
    }


    private static int getMinutesFromTime(Date date){
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }
        return (date.getTime() / 60) % 60;
    }


    private static int getHoursFromTime(Date date){
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }
        return date.getTime() / 3600;
    }
}
