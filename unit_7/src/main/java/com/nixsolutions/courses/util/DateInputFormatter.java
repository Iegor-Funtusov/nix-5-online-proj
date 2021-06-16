package com.nixsolutions.courses.util;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.data.Time;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

public class DateInputFormatter {

    private static final String FIRST_REGEX = "\\d{0,2}/\\d{2}/\\d{0,2}"; // dd/mm/yy
    private static final String SECOND_REGEX = "\\d{0,2}/\\d{1,2}/\\d{0,4}"; // m/d/yyyy
    private static final String ONLY_YEAR_REGEX = "\\d{4}";
    private static final String THIRD_REGEX = "\\D{3,}-\\d{1,2}-\\d{2}"; // mmm-d-yy
    private static final String FOURTH_REGEX = "\\d{2}-\\D{3,}-\\d{4}"; // dd-mmm-yyyy

    static Date formatedDate;

    public static Date formatDate(String input, String format) throws DataFormatException {
        formatedDate = new Date();
        String data = input.trim();
        String date = StringUtils.substringBefore(data, " ");
        System.out.println(date); //
        String time = StringUtils.substringAfter(data, " ");
        System.out.println(time); //

        switch (format) {
            case "1":
                formatedDate = firstFormat(date); // dd/mm/yy hh:mm:ss
                break;
            case "2":
                formatedDate = secondFormat(date); // m/d/yyyy hh:mm:ss
                break;
            case "3":
                formatedDate = thirdFormat(date); // mmm-d-yy hh:mm:ss
                break;
            case "4":
                formatedDate = fourthFormat(date); // dd-mmm-yyyy hh:mm:ss
                break;
        }

        formatedDate.setTime(formatTime(time));
        System.out.println(formatedDate);
        return formatedDate;
    }

    private static Time formatTime(String input) {
        Time time = new Time();
        time.setHours(0);
        time.setMinutes(0);
        time.setSeconds(0);
        if (input.length() != 0) {
            String[] data = input.split(":");
            if (data.length == 2) {
                time.setMinutes(Integer.parseInt(data[0]));
                time.setSeconds(Integer.parseInt(data[1]));
            } else {
                time.setHours(Integer.parseInt(data[0]));
                time.setMinutes(Integer.parseInt(data[1]));
                time.setSeconds(Integer.parseInt(data[2]));
            }
        }
        return time;
    }

    private static Date firstFormat(String input) throws DataFormatException {
        if (Pattern.matches(FIRST_REGEX, input)) {
            Date date = new Date();
            String[] data = input.split("/");
            if (data[0].equals("")) {
                date.setDay(1);
            } else {
                date.setDay(Integer.parseInt(data[0]));
            }
            if (data[1].equals("")) {
                date.setMonth(1);
            } else {
                date.setMonth(Integer.parseInt(data[1]));
            }
            if (data.length > 2) {
                if (!data[2].equals("")) {
                    date.setYear(Integer.parseInt(data[2]));
                } else {
                    date.setYear(21);
                }
            } else {
                date.setYear(21);
            }
            return date;
        } else {
            throw new DataFormatException("Mismatch with chosen format");
        }
    }

    private static Date secondFormat(String input) throws DataFormatException {
        if (Pattern.matches(SECOND_REGEX, input) || Pattern.matches(ONLY_YEAR_REGEX, input)) {
            Date date = new Date();
            if (input.contains("/")) {
                String[] data = input.split("/");
                if (data[0].equals("")) {
                    date.setMonth(1);
                } else {
                    date.setMonth(Integer.parseInt(data[0]));
                }
                if (data[1].equals("")) {
                    date.setDay(1);
                } else {
                    date.setDay(Integer.parseInt(data[1]));
                }
                if (data.length > 2) {
                    if (!data[2].equals("")) {
                        date.setYear(Integer.parseInt(data[2]));
                    } else {
                        date.setYear(2021);
                    }
                } else {
                    date.setYear(2021);
                }
            } else {
                date.setYear(Integer.parseInt(input));
            }
            return date;
        } else {
            throw new DataFormatException("Mismatch with chosen format");
        }
    }

    private static Date thirdFormat(String input) throws DataFormatException {
        if (Pattern.matches(THIRD_REGEX, input)) {
            Date date = new Date();
            String[] data = input.split("-");
            date.setMonth(parseMonth(data[0]));
            if (data[1].equals("")) {
                date.setDay(1);
            } else {
                date.setDay(Integer.parseInt(data[1]));
            }
            if (data.length > 2) {
                if (!data[2].equals("")) {
                    date.setYear(Integer.parseInt(data[2]));
                } else {
                    date.setYear(21);
                }
            } else {
                date.setYear(21);
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

    private static Date fourthFormat(String input) throws DataFormatException {
        if (Pattern.matches(FOURTH_REGEX, input)) {
            Date date = new Date();
            String[] data = input.split("-");
            if (data[0].equals("")) {
                date.setDay(0);
            } else {
                date.setDay(Integer.parseInt(data[0]));
            }
            date.setMonth(parseMonth(data[1]));
            if (data.length > 2) {
                if (!data[2].equals("")) {
                    date.setYear(Integer.parseInt(data[2]));
                } else {
                    date.setYear(2021);
                }
            } else {
                date.setYear(2021);
            }
            return date;
        } else {
            throw new DataFormatException("Mismatch with chosen format");
        }
    }

}
