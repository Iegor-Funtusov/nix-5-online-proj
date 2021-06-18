package calendar.formatter;

import calendar.service.DateTimeCalculatorImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFormatter {

    public enum DateFormats {
        DD_MM_YY("dd/mm/yy"),
        M_D_YYYY("m/d/yyyy"),
        MMM_D_YY("mmm-d-yy"),
        DD_MMM_YYYY("dd-mmm-yyyy");

        public final String format;

        DateFormats(String format) {
            this.format = format;
        }
    }

    public static DateTimeCalculatorImpl parserDateTime(String dateString, String timeString) {
        int year = 2021;
        int month = 1;
        int day = 1;

        if (dateString == null) {
            dateString = "";
        }
        String regex = "([\\d]*)/([\\d]*)/([\\d]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateString);
        if (matcher.find()) {
            if (!matcher.group(1).isEmpty()) {
                day = Integer.parseInt(matcher.group(1));
            }
            if (!matcher.group(2).isEmpty()) {
                month = Integer.parseInt(matcher.group(2));
            }
            if (!matcher.group(3).isEmpty()) {
                if (matcher.group(3).length() == 2) {
                    year = +Integer.parseInt("19" + matcher.group(3));
                } else {
                    year = Integer.parseInt(matcher.group(3));
                }
            }
        } else {
            year = Integer.parseInt(dateString);
        }

        int hour = 0;
        int minute = 0;
        int second = 0;
        if (timeString != null) {
            regex = "(\\d+):(\\d+):?(\\d*)";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(timeString);
            if (matcher.find()) {
                hour = Integer.parseInt(matcher.group(1));
                minute = Integer.parseInt(matcher.group(2));
                if (!matcher.group(3).isEmpty()) {
                    second = Integer.parseInt(matcher.group(3));
                }
            }
        }
        return new DateTimeCalculatorImpl(year, month, day, hour, minute, second);
    }

    public static String format(DateTimeCalculatorImpl calculator, DateFormats format) {
        String day;
        String month;
        String year;

        switch (format) {
            case DD_MM_YY: {
                day = (calculator.getDay() < 10 ? "0" : "") + calculator.getDay();
                month = (calculator.getMonth() < 10 ? "0" : "") + calculator.getMonth();
                char[] temp = String.valueOf(calculator.getYear()).toCharArray();
                year = "" + (temp.length == 1 ? "" : temp[temp.length - 2]);
                year += "" + temp[temp.length - 1];
                return "" + day + "/"
                        + month + "/"
                        + year + " "
                        + formatTime(calculator);
            }
            case M_D_YYYY: {
                day = "" + calculator.getDay();
                month = "" + calculator.getMonth();
                year = "" + calculator.getYear();
                return month + "/" + day + "/" + year + " " + formatTime(calculator);
            }
            case MMM_D_YY: {
                day = "" + calculator.getDay();
                month = "" + calendar.enums.MonthFormats.getMonth(calculator.getMonth() - 1);
                char[] temp = String.valueOf(calculator.getYear()).toCharArray();
                year = "" + (temp.length == 1 ? "" : temp[temp.length - 2]);
                year += "" + temp[temp.length - 1];
                return month + "-" + day + "-" + year + " " + formatTime(calculator);
            }
            case DD_MMM_YYYY: {
                day = (calculator.getDay() < 10 ? "0" : "") + calculator.getDay();
                month = "" + calendar.enums.MonthFormats.getMonth(calculator.getMonth() - 1);
                year = "" + calculator.getYear();
                return day + "-" + month + "-" + year + " " + formatTime(calculator);
            }
            default:
                return "Unrecognized format";
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
