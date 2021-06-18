package com.nixsolutions.courses.util;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.data.Time;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class DateInputFormatter {

    private static final String FIRST_REGEX = "\\d{0,2}/\\d{2}/\\d{0,2}"; // dd/mm/yy
    private static final String SECOND_REGEX = "\\d{0,2}/\\d{1,2}/\\d{0,4}"; // m/d/yyyy
    private static final String ONLY_YEAR_REGEX = "\\d{4}";
    private static final String THIRD_REGEX = "\\D*-\\d{1,2}-\\d{0,2}"; // mmm-d-yy
    private static final String FOURTH_REGEX = "\\d{0,2}-\\D{3,}-\\d{0,4}"; // dd-mmm-yyyy
    private static final String LIST_REGEX = ",\\s*";
    private static final Integer CURRENT_YEAR = 2021;

    private static Date formattedDate;

    public static List<Date> formatDateList(String input, String format) throws DataFormatException {
        String[] data = input.split(LIST_REGEX);
        List<Date> dates = new ArrayList<>();
        for (String date : data) {
            dates.add(formatDate(date, format));
        }
        return dates;
    }

    public static Date formatDate(String input, String format) throws DataFormatException {
        String data = input.trim();
        String date = StringUtils.substringBefore(data, " ");
        String time = StringUtils.substringAfter(data, " ");
        switch (format) {
            case "1":
                formattedDate = firstFormat(date); // dd/mm/yy hh:mm:ss
                break;
            case "2":
                formattedDate = secondFormat(date); // m/d/yyyy hh:mm:ss
                break;
            case "3":
                formattedDate = thirdFormat(date); // mmm-d-yy hh:mm:ss
                break;
            case "4":
                formattedDate = fourthFormat(date); // dd-mmm-yyyy hh:mm:ss
                break;
        }
        if (time.length() != 0) formattedDate.setTime(formatTime(time));
        System.out.println(formattedDate);
        return formattedDate;
    }

    private static Time formatTime(String input) throws DataFormatException {
        if (input.contains(":")) {
            Time time = new Time();
            if (StringUtils.substringBetween(input, ":") != null) {
                time.setHours(Integer.parseInt(StringUtils.substringBefore(input, ":")));
                time.setMinutes(Integer.parseInt(StringUtils.substringBetween(input, ":")));
            } else {
                time.setMinutes(Integer.parseInt(StringUtils.substringBefore(input, ":")));
            }
            time.setSeconds(Integer.parseInt(StringUtils.substringAfterLast(input, ":")));
            return time;
        } else {
            throw new DataFormatException("Wrong time format");
        }
    }

    private static Date firstFormat(String input) throws DataFormatException {
        if (input.matches(FIRST_REGEX)) {
            Date date = new Date();
            if (StringUtils.isNotBlank(StringUtils.substringBefore(input, "/"))) {
                date.setDay(Integer.parseInt(StringUtils.substringBefore(input, "/")));
            } else {
                date.setDay(1);
            }
            date.setMonth(Integer.parseInt(StringUtils.substringBetween(input, "/")));
            if (StringUtils.isNotBlank(StringUtils.substringAfterLast(input, "/"))) {
                date.setYear(Integer.parseInt(StringUtils.substringAfterLast(input, "/")));
            } else {
                date.setYear(CURRENT_YEAR);
            }
            return date;
        } else {
            throw new DataFormatException("Mismatch with chosen format");
        }
    }

    private static Date secondFormat(String input) throws DataFormatException {
        if (input.matches(SECOND_REGEX) || input.matches(ONLY_YEAR_REGEX)) {
            Date date = new Date();
            if (input.contains("/")) {
                if (StringUtils.isNotBlank(StringUtils.substringBefore(input, "/"))) {
                    date.setMonth(Integer.parseInt(StringUtils.substringBefore(input, "/")));
                } else {
                    date.setMonth(1);
                }
                date.setDay(Integer.parseInt(StringUtils.substringBetween(input, "/")));
                if (StringUtils.isNotBlank(StringUtils.substringAfterLast(input, "/"))) {
                    date.setYear(Integer.parseInt(StringUtils.substringAfterLast(input, "/")));
                } else {
                    date.setYear(CURRENT_YEAR);
                }
            } else {
                date.setYear(CURRENT_YEAR);
            }
            return date;
        } else {
            throw new DataFormatException("Mismatch with chosen format");
        }
    }

    private static Date thirdFormat(String input) throws DataFormatException {
        if (input.matches(THIRD_REGEX)) {
            Date date = new Date();
            if (StringUtils.isNotBlank(StringUtils.substringBefore(input, "-"))) {
                date.setMonth(parseMonth(StringUtils.substringBefore(input, "-")));
            } else {
                date.setMonth(1);
            }
            date.setDay(Integer.parseInt(StringUtils.substringBetween(input, "-")));
            if (StringUtils.isNotBlank(StringUtils.substringAfterLast(input, "-"))) {
                date.setYear(Integer.parseInt(StringUtils.substringAfterLast(input, "-")));
            } else {
                date.setYear(CURRENT_YEAR);
            }
            return date;
        } else {
            throw new DataFormatException("Mismatch with chosen format");
        }
    }

    private static Date fourthFormat(String input) throws DataFormatException {
        if (input.matches(FOURTH_REGEX) || input.matches(ONLY_YEAR_REGEX)) {
            Date date = new Date();
            if (input.contains("-")) {
                if (StringUtils.isNotBlank(StringUtils.substringBefore(input, "-"))) {
                    date.setDay(Integer.parseInt(StringUtils.substringBefore(input, "-")));
                } else {
                    date.setDay(1);
                }
                date.setMonth(parseMonth(StringUtils.substringBetween(input, "-")));
                if (StringUtils.isNotBlank(StringUtils.substringAfterLast(input, "-"))) {
                    date.setYear(Integer.parseInt(StringUtils.substringAfterLast(input, "-")));
                } else {
                    date.setYear(CURRENT_YEAR);
                }
            } else {
                date.setYear(CURRENT_YEAR);
            }
            return date;
        } else {
            throw new DataFormatException("Mismatch with chosen format");
        }
    }

    private static int parseMonth(String month) {
        switch (month) {
            case "Январь":
                return 1;
            case "Февраль":
                return 2;
            case "Март":
                return 3;
            case "Апрель":
                return 4;
            case "Май":
                return 5;
            case "Июнь":
                return 6;
            case "Июль":
                return 7;
            case "Август":
                return 8;
            case "Сентябрь":
                return 9;
            case "Октябрь":
                return 10;
            case "Ноябрь":
                return 11;
            case "Декабрь":
                return 12;
        }
        return 1;
    }
}
