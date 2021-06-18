package calendar.formatter;

import calendar.enums.MonthFormats;
import calendar.service.DateTimeCalculatorImpl;

public class DateTimeValidator {

    public static String format(DateTimeCalculatorImpl dt, MyFormatter.DateFormats format) {
        String day;
        String month;
        String year;

        switch (format) {
            case DD_MM_YY: {
                day = (dt.getDay() < 10 ? "0" : "") + dt.getDay();
                month = (dt.getMonth() < 10 ? "0" : "") + dt.getMonth();
                char[] temp = String.valueOf(dt.getYear()).toCharArray();
                year = "" + (temp.length == 1 ? "" : temp[temp.length - 2]);
                year += "" + temp[temp.length - 1];
                return "" + day + "/"
                        + month + "/"
                        + year + " "
                        + formatTime(dt);
            }
            case M_D_YYYY: {
                day = "" + dt.getDay();
                month = "" + dt.getMonth();
                year = "" + dt.getYear();
                return month + "/" + day + "/" + year + " " + formatTime(dt);
            }
            case MMM_D_YY: {
                day = "" + dt.getDay();
                month = "" + MonthFormats.getMonth(dt.getMonth());
                char[] temp = String.valueOf(dt.getYear()).toCharArray();
                year = "" + (temp.length == 1 ? "" : temp[temp.length - 2]);
                year += "" + temp[temp.length - 1];
                return month + "-" + day + "-" + year + " " + formatTime(dt);
            }
            case DD_MMM_YYYY: {
                day = (dt.getDay() < 10 ? "0" : "") + dt.getDay();
                month = "" + MonthFormats.getMonth(dt.getMonth());
                year = "" + dt.getYear();
                return day + "-" + month + "-" + year + " " + formatTime(dt);
            }
            default:
                return "Change format according to existing.";
        }
    }

    private static String formatTime(DateTimeCalculatorImpl dateTimeCalculator) {
        String hour;
        String minute;
        String second;
        hour = (dateTimeCalculator.getHour() < 10 ? "0" : "") + dateTimeCalculator.getHour();
        minute = (dateTimeCalculator.getMinute() < 10 ? "0" : "") + dateTimeCalculator.getMinute();
        second = (dateTimeCalculator.getSecond() < 10 ? "0" : "") + dateTimeCalculator.getSecond();

        return hour + ":" + minute + ":" + second;
    }
}
