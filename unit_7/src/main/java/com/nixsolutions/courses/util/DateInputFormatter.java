package com.nixsolutions.courses.util;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.data.Time;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

public class DateInputFormatter {

    private String format1 = "d{0}|d{2}/d{2}/d{0}|d{2}"; // dd/mm/yy
    private String format2 = "d{0,1}/d{1}/d{0,4}"; // m/d/yyyy
    private String format3 = "d{0,3}-d{1}-d{0,2}"; // mmm-d-yy
    private String format4 = "\\d{2}-\\d{2}-\\d{4}"; // dd-mmm-yyyy

    static Date formatedDate;

    public static Date formatDate(String input, String format) throws DataFormatException {
        formatedDate = new Date();
        String data = input.trim();
        String date = StringUtils.substringBefore(data, " ");
        System.out.println(date);
        String time = StringUtils.substringAfter(data, " ");
        System.out.println(time);

        switch (format) {
            case "1":
                formatedDate = firstFormat(date); // dd/mm/yy hh:mm:ss
                break;
        }
        if (time.length() != 0) {
            formatedDate.setTime(formatTime(time));
        }
        System.out.println(formatedDate);
        return formatedDate;
    }

    private static Time formatTime(String input) {
        Time time = new Time();
        String[] data = input.split(":");
        if (data.length == 2) {
            time.setMinutes(Integer.parseInt(data[0]));
            time.setSeconds(Integer.parseInt(data[1]));
        } else {
            time.setHours(Integer.parseInt(data[0]));
            time.setMinutes(Integer.parseInt(data[1]));
            time.setSeconds(Integer.parseInt(data[2]));
        }
        return time;
    }

    private static Date firstFormat(String input) throws DataFormatException {
        if (Pattern.matches("\\d{0,2}/\\d{1,2}/\\d{0,4}", input)) {
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
