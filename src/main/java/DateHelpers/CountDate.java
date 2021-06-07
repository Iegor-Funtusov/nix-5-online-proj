package DateHelpers;
import DatePackage.*;
import java.util.Map;

//Класс для сложения и вычитания даты/времени
public class CountDate {

    public static Date addSeconds(Date date, int seconds){
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        int totalTime = result.getTime() + seconds;

        if(totalTime > DateConstants.SECONDS_PER_DAY){
            result = addDays(result, totalTime / DateConstants.SECONDS_PER_DAY);
            result.setTime(totalTime % DateConstants.SECONDS_PER_DAY);
        }
        else{
            result.setTime(totalTime);
        }
        return result;
    }


    public static Date addMinutes(Date date, int minutes){
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        int totalTime = result.getTime() + (minutes * DateConstants.SECONDS_IN_MINUTE);
        if(totalTime > DateConstants.SECONDS_PER_DAY){
            result = addDays(result, totalTime / DateConstants.SECONDS_PER_DAY);
            result.setTime(totalTime % DateConstants.SECONDS_PER_DAY);
        }
        else{
            result.setTime(totalTime);
        }
        return result;
    }


    public static Date addHours(Date date, int hours){
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        int totalTime = result.getTime() + (hours * DateConstants.SECONDS_IN_HOUR);
        if(totalTime > DateConstants.SECONDS_PER_DAY){
            result = addDays(result, totalTime / DateConstants.SECONDS_PER_DAY);
            result.setTime(totalTime % DateConstants.SECONDS_PER_DAY);
        }
        else{
            result.setTime(totalTime);
        }
        return result;
    }


    public static Date addDays(Date date, int days){
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        Map<Integer, Integer> monthDay = DateConstants.getMonthDay();
        int daysInCurMonth = monthDay.get((int)result.getMonth());

        //Если февраль високосного года, то в нём 29 дней
        if(result.getMonth() == 2 && checkViscousYear(result)){
            daysInCurMonth++;
        }

        int totalDays = result.getDay() + days;
        if(totalDays > daysInCurMonth){
            result = addMonths(result, totalDays / daysInCurMonth);
            result.setDay((byte) (totalDays % daysInCurMonth));
        }
        else {
            result.setDay((byte) (totalDays % (daysInCurMonth+1)));
        }
        return result;
    }


    public static Date addMonths(Date date, int months){
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        int totalMonths = result.getMonth() + months;
        if(totalMonths > DateConstants.QUANTITY_OF_MONTHS){
            result = addYears(result, totalMonths / DateConstants.QUANTITY_OF_MONTHS);
            result.setMonth((byte) (totalMonths % DateConstants.QUANTITY_OF_MONTHS));
        }
        else {
            result.setMonth((byte) (totalMonths % DateConstants.QUANTITY_OF_MONTHS));
        }
        return result;
    }


    public static Date addYears(Date date, int years){
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        int totalYears = result.getYear() + years;
        result.setYear(totalYears);
        return result;
    }


    public static Date subtractSeconds(Date date, int seconds) throws RuntimeException {
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        int totalTime = result.getTime() - seconds;

        if(totalTime < 0){
            int daysToSubtract = 1 + (Math.abs(totalTime) / DateConstants.SECONDS_PER_DAY);
            result = subtractDays(result, daysToSubtract);
            result.setTime(Math.abs(totalTime) % DateConstants.SECONDS_PER_DAY);
        }
        else{
            result.setTime(totalTime);
        }

        return checkValidityOfYear(result);
    }


    public static Date subtractMinutes(Date date, int minutes) throws RuntimeException {
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        int totalTime = result.getTime() - (minutes * DateConstants.SECONDS_IN_MINUTE);

        if(totalTime < 0){
            int daysToSubtract = 1 + (Math.abs(totalTime) / DateConstants.SECONDS_PER_DAY);
            result = subtractDays(result, daysToSubtract);
            result.setTime(Math.abs(totalTime) % DateConstants.SECONDS_PER_DAY);
        }
        else{
            result.setTime(totalTime);
        }

        return checkValidityOfYear(result);
    }


    public static Date subtractHours(Date date, int hours) throws RuntimeException {
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        int totalTime = result.getTime() - (hours * DateConstants.SECONDS_IN_HOUR);

        if(totalTime < 0){
            int daysToSubtract = 1 + (Math.abs(totalTime) / DateConstants.SECONDS_PER_DAY);
            result = subtractDays(result, daysToSubtract);
            result.setTime(Math.abs(totalTime) % DateConstants.SECONDS_PER_DAY);
        }
        else{
            result.setTime(totalTime);
        }

        return checkValidityOfYear(result);
    }


    public static Date subtractDays(Date date, int days) throws RuntimeException {
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        while (days != 0){
            if(days < result.getDay()){
                result.setDay((byte) (result.getDay() - days));
                days = 0;
            }
            else {
                days = days - result.getDay();
                result = subtractMonths(result, 1);
                Integer currentMonth = Integer.valueOf(result.getMonth());
                int newMonthQuantityDays = DateConstants.getMonthDay().get(currentMonth);
                result.setDay((byte) newMonthQuantityDays);
            }
        }

        return checkValidityOfYear(result);
    }


    public static Date subtractMonths(Date date, int months) throws RuntimeException {
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        if(DateConstants.QUANTITY_OF_MONTHS < months || result.getMonth() <= months){
            int yearsToSubtract = 1 + (months / DateConstants.QUANTITY_OF_MONTHS);
            result = subtractYears(result, yearsToSubtract);

            months = months - result.getMonth();
            byte newMonth = (byte) (DateConstants.QUANTITY_OF_MONTHS - (months % DateConstants.QUANTITY_OF_MONTHS));
            result.setMonth(newMonth);
        }
        else{
            result.setMonth((byte) (result.getMonth() - months));
        }

        return checkValidityOfYear(result);
    }


    public static Date subtractYears(Date date, int years) throws RuntimeException {
        if(isEmpty(date)){
            throw new RuntimeException("Date does not exist");
        }

        Date result = date;
        int totalYears = result.getYear() - years;
        result.setYear(totalYears);

        return checkValidityOfYear(result);
    }



    //Служебные методы
    public static boolean checkViscousYear(Date date){
        return date.getYear() % 4 == 0;
    }


    private static Date checkValidityOfYear(Date date){
        if(date.getYear() >= 0){
            return date;
        }
        throw new RuntimeException("Year will be negative");
    }


    private static boolean isEmpty(Date dateToCheck){
        return dateToCheck == null;
    }

}