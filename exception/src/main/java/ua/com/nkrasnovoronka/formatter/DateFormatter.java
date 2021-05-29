package ua.com.nkrasnovoronka.formatter;

import ua.com.nkrasnovoronka.model.Date;
import ua.com.nkrasnovoronka.model.Month;
import ua.com.nkrasnovoronka.exception.DataFormatException;
import ua.com.nkrasnovoronka.util.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatter {
    private static final int DEFAULT_YEAR = 2021;
    private static final int DEFAULT_DAY = 1;

    public Date formatStringToDate(int parser, String input) throws DataFormatException{
        String regexp = Constants.dateParserMap.get(parser);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input.toLowerCase());
        if(!input.matches(regexp)){
            throw new DataFormatException(String.format("Date input doesn't match %s format",Constants.datePatterns.get(parser - 1)));
        }
        Date date = null;
        while (matcher.find()){
            int day = getDayOrSetDefault(matcher);
            Month month = getMonthOrSetDefault(matcher);
            int year = getYearOrSetDefault(matcher);
            date = new Date(day, month, year);
        }
        return date;
    }

    private int getYearOrSetDefault(Matcher matcher) {
        int year;
        try {
            year = Integer.parseInt(matcher.group("year"));
        }catch (NumberFormatException e){
            year = DEFAULT_YEAR;
        }
        return year;
    }

    private int getDayOrSetDefault(Matcher matcher) {
        int day;
        try{
            day = Integer.parseInt(matcher.group("day"));
        }catch (NumberFormatException e){
            day = DEFAULT_DAY;
        }
        return day;
    }

    private Month getMonthOrSetDefault(Matcher matcher) {
        Month month;
        try {
            month = Month.getMountByParameter(Integer.parseInt(matcher.group("month")));
        }catch (NumberFormatException e){
            month = Month.getMountByParameter(matcher.group("month"));
        }
        return month;
    }


}
