package ua.com.nkrasnovoronka.formatter;

import ua.com.nkrasnovoronka.exception.DataFormatException;
import ua.com.nkrasnovoronka.model.Date;
import ua.com.nkrasnovoronka.model.Month;
import ua.com.nkrasnovoronka.util.Constants;
import ua.com.nkrasnovoronka.util.Util;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatter {

    private final int DEFAULT_YEAR = 2021;
    private final int DEFAULT_DAY = 1;

    private final String DELIMITER_1 = "/";
    private final String DELIMITER_2 = "-";

    public Date formatStringToDate(int parser, String input) throws DataFormatException {
        String regexp = Constants.dateParserMap.get(parser);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input.toLowerCase());
        if (!input.matches(regexp)) {
            throw new DataFormatException(String.format("Date input doesn't match %s format", Constants.datePatterns.get(parser - 1)));
        }
        Date date = null;
        while (matcher.find()) {
            int day = getDayOrSetDefault(matcher);
            Month month = getMonthOrSetDefault(matcher);
            int year = getYearOrSetDefault(matcher);
            date = new Date(day, month, year);
        }
        return date;
    }

    public String formatDateToString(int parse, Date date) {
        return generateOutput(parse, date).toString();
    }

    private StringJoiner generateOutput(int parse, Date date) {
        StringJoiner sj;
        if (parse == 1) {
            sj = new StringJoiner(DELIMITER_1);
            sj.add(String.format("%02d", date.getDay()));
            sj.add(String.format("%02d", date.getMonth().ordinal() + 1));
            sj.add(String.format("%02d", date.getYear()));
            return sj;
        }
        if (parse == 2) {
            sj = new StringJoiner(DELIMITER_1);
            sj.add(String.format("%d", date.getMonth().ordinal() + 1));
            sj.add(String.format("%d", date.getDay()));
            sj.add(String.format("%04d", date.getYear()));
            return sj;

        }
        if (parse == 3) {
            sj = new StringJoiner(DELIMITER_2);
            sj.add(Util.capitalize(date.getMonth().getShortName()));
            sj.add(String.format("%d", date.getDay()));
            sj.add(String.format("%02d", date.getYear()));
            return sj;

        }

        sj = new StringJoiner(DELIMITER_2);
        sj.add(String.format("%02d", date.getDay()));
        sj.add(Util.capitalize(date.getMonth().name()));
        sj.add(String.format("%04d", date.getYear()));
        return sj;
    }


    private int getYearOrSetDefault(Matcher matcher) {
        int year;
        try {
            year = Integer.parseInt(matcher.group("year"));
        } catch (NumberFormatException e) {
            year = DEFAULT_YEAR;
        }
        return year;
    }

    private int getDayOrSetDefault(Matcher matcher) {
        int day;
        try {
            day = Integer.parseInt(matcher.group("day"));
        } catch (NumberFormatException e) {
            day = DEFAULT_DAY;
        }
        return day;
    }

    private Month getMonthOrSetDefault(Matcher matcher) {
        Month month;
        try {
            month = Month.getMountByParameter(Integer.parseInt(matcher.group("month")));
        } catch (NumberFormatException e) {
            month = Month.getMountByParameter(matcher.group("month"));
        }
        return month;
    }


}
