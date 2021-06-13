package utils;

import domain.Date;

public class DateOutput {
        public static String formatDateToString(String format, Date date) {
            return generateOutput(format, date).toString();
    }
        public static StringBuilder generateOutput(String format, Date date) {
        StringBuilder sb = new StringBuilder();
        if (format.equals("mmm-d-yy")) {
            sb.append(CONSTANTS.monthsNames.get(date.getMonth()));
            sb.append("-");
            sb.append(String.format("%d", date.getDate()));
            sb.append("-");
            sb.append(String.format("%d", date.getYear()));
            return sb;
        }
        if (format.equals("dd-mmm-yyyy hh:mm")) {
            sb.append(String.format("%02d", date.getDate()));
            sb.append("-");
            sb.append(CONSTANTS.monthsNames.get(date.getMonth()));
            sb.append("-");
            sb.append(String.format("%02d", date.getYear()));
            sb.append(" ");
            sb.append(String.format("%02d", date.getTime() / 3600));
            sb.append(":");
            sb.append(String.format("%02d", (date.getTime() % 3600 / 60)));
            return sb;
        }
        if (format.equals("dd-mmm-yyyy hh:mm:ss")) {
            sb.append(String.format("%02d", date.getDate()));
            sb.append("-");
            sb.append(CONSTANTS.monthsNames.get(date.getMonth()));
            sb.append("-");
            sb.append(String.format("%02d", date.getYear()));
            sb.append(" ");
            sb.append(String.format("%02d", date.getTime() / 3600));
            sb.append(":");
            sb.append(String.format("%02d", (date.getTime() % 3600 / 60)));
            sb.append(":");
            sb.append(String.format("%02d", (date.getTime() % 60)));
            return sb;
        }
        if (format.equals("m/d/yyyy")) {
            sb.append(String.format("%d", date.getMonth()));
            sb.append("/");
            sb.append(String.format("%d", date.getDate()));
            sb.append("/");
            sb.append(String.format("%02d", date.getYear()));
            return sb;
        }
        sb.append(String.format("%02d", date.getDate()));
        sb.append("/");
        sb.append(String.format("%02d", date.getMonth()));
        sb.append("/");
        sb.append(String.format("%02d", date.getYear()));
        return sb;
    }
}
