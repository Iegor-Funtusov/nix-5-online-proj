package com.nixsolutions.courses.util;

import com.nixsolutions.courses.data.Date;
import java.util.zip.DataFormatException;

public class DateOutputFormatter {

    public static String formatDate(Date date, String format) throws DataFormatException {
        String outputDate = "";

        switch (format) {
            case "1":
                outputDate = firstFormat(date); // dd/mm/yy hh:mm:ss
                break;
            case "2":
                outputDate = secondFormat(date); // m/d/yyyy hh:mm:ss
                break;
//            case "3":
//                outputDate = thirdFormat(date); // mmm-d-yy hh:mm:ss
//                break;
//            case "4":
//                outputDate = fourthFormat(date); // dd-mmm-yyyy hh:mm:ss
//                break;
        }
        return outputDate;
    }

    private static String secondFormat(Date date) {
        String outputDate = String.format("%d/%d/%04d ", date.getDay(), date.getMonth(), date.getYear());
        outputDate += formatTime(date);

        return outputDate;
    }

    private static String formatTime(Date date) {
        return String.format("%02d:%02d:%02d", date.getTime().getHours(), date.getTime().getMinutes(), date.getTime().getSeconds());
    }

    private static String firstFormat(Date date) {
        String outputDate = String.format("%02d/%02d/%d ", date.getDay(), date.getMonth(), date.getYear());
        outputDate += formatTime(date);

        return outputDate;

    }
}
