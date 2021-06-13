package ua.com.alevel.app.util;

import ua.com.alevel.app.entity.Date;
import ua.com.alevel.app.entity.Month;
import ua.com.alevel.app.entity.Time;
import ua.com.alevel.app.exception.FormatException;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtils {

    private static final String REGEX_HOURS = "^(0\\d|1\\d|2[0-3]{1,2})$";
    private static final String REGEX_MIN_SEC = "^(0\\d|1\\d|[1-5]\\d{1,2})$";
    public static final String[] formats;
    public static final String[] patterns;

    static {
        formats = new String[]{"^(?<day>0[1-9]|[12]\\d|3[01]|)\\/(?<month>0[1-9]|1[0-2]|)\\/(?<year>\\d{2}|)$",
                "^(?<month>[1-9]|1[0-2]|)\\/(?<day>[1-9]|[12]\\d|3[01]|)\\/(?<year>\\d{4}|)$",
                "^(?<month>january|february|march|april|may|june|july|august|september|october|november|december|)\\-(?<day>[1-9]|[12]\\d|3[01]|)\\-(?<year>\\d{2}|)$",
                "^(?<day>0[1-9]|[12]\\d|3[01]|)\\-(?<month>january|february|march|april|may|june|july|august|september|october|november|december|)\\-(?<year>\\d{4}|)$"};
        patterns = new String[]{"dd/mm/yy", "m/d/yyyy", "mmm-d-yy", "dd-mmm-yyyy"};
    }

    private int hour = 0;
    private int minute = 0;
    private int seconds = 0;

    public Date stringToDate(int parser, String input) throws FormatException {
        String regex = formats[parser - 1];
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input.toLowerCase());
        if (!input.matches(regex)) {
            throw new FormatException(String.format("Input is not match %s", patterns[parser - 1]));
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

    public String dateToString(int parse, Date date) {
        return generateOutput(parse, date).toString();
    }

    public Time formatStringToTime(String input) throws FormatException {
        if (input.contains(":")) {
            String[] split = input.trim().split(":");
            if (split.length > 1 && split.length <= 3) {
                if (split.length == 3) {
                    if (split[0].matches(REGEX_HOURS) && split[1].matches(REGEX_MIN_SEC) && split[0].matches(REGEX_MIN_SEC)) {
                        hour = Integer.parseInt(split[0]);
                        minute = Integer.parseInt(split[1]);
                        seconds = Integer.parseInt(split[2]);
                    } else {
                        throw new FormatException("Invalid time format. Please try again");
                    }
                }
                if (split.length == 2) {
                    if (split[0].matches(REGEX_HOURS) && split[1].matches(REGEX_MIN_SEC)) {
                        hour = Integer.parseInt(split[0]);
                        minute = Integer.parseInt(split[1]);
                    } else if (split[0].matches(REGEX_MIN_SEC) && split[1].matches(REGEX_MIN_SEC)) {
                        minute = Integer.parseInt(split[0]);
                        seconds = Integer.parseInt(split[1]);
                    } else {
                        throw new FormatException("Invalid time format. Please try again");
                    }
                }
                return new Time(hour, minute, seconds);
            }
        }
        if (input.isBlank()) {
            return new Time(0, 0, 0);
        }
        throw new FormatException("Invalid time format. Please try again");
    }

    public String formatTimeToString(Time time) {
        StringJoiner sj = new StringJoiner(":");
        sj.add(String.format("%02d", time.getHours()));
        sj.add(String.format("%02d", time.getMinutes()));
        sj.add(String.format("%02d", time.getSeconds()));
        return sj.toString();
    }

    private int getYearOrSetDefault(Matcher matcher) {
        int year;
        try {
            year = Integer.parseInt(matcher.group("year"));
        } catch (NumberFormatException e) {
            year = 2021;
        }
        return year;
    }

    private int getDayOrSetDefault(Matcher matcher) {
        int day;
        try {
            day = Integer.parseInt(matcher.group("day"));
        } catch (NumberFormatException e) {
            day = 1;
        }
        return day;
    }

    private Month getMonthOrSetDefault(Matcher matcher) {
        Month month;
        try {
            month = Month.getMonthByParameter(Integer.parseInt(matcher.group("month")));
        } catch (NumberFormatException e) {
            month = Month.getMonthByParameter(matcher.group("month"));
        }
        return month;
    }

    private StringJoiner generateOutput(int parse, Date date) {
        StringJoiner sj;
        if (parse == 1) {
            sj = new StringJoiner("/");
            sj.add(String.format("%02d", date.getDay()));
            sj.add(String.format("%02d", date.getMonth().ordinal() + 1));
            sj.add(String.format("%02d", date.getYear()));
            return sj;
        }
        if (parse == 2) {
            sj = new StringJoiner("/");
            sj.add(String.format("%d", date.getMonth().ordinal() + 1));
            sj.add(String.format("%d", date.getDay()));
            sj.add(String.format("%04d", date.getYear()));
            return sj;

        }
        if (parse == 3) {
            sj = new StringJoiner("-");
            sj.add(Utils.firstLetterToCapital(date.getMonth().name()));
            sj.add(String.format("%d", date.getDay()));
            sj.add(String.format("%02d", date.getYear()));
            return sj;

        }
        sj = new StringJoiner("-");
        sj.add(String.format("%02d", date.getDay()));
        sj.add(Utils.firstLetterToCapital(date.getMonth().name()));
        sj.add(String.format("%04d", date.getYear()));
        return sj;
    }
}
