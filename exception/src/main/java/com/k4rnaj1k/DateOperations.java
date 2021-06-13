package com.k4rnaj1k;

import java.util.Date;

public class DateOperations {
    public static int parseDays(String days, Integer months) throws NumberFormatException {
        if (days.length() == 0) {
            return 1;
        }
        int dayscount = Integer.parseInt(days);
        if (months == 2 && dayscount <= 28 && dayscount >= 1) {
            return dayscount;
        } else if (months % 2 == 1 && dayscount <= 31 && dayscount >= 1)
            return dayscount;
        else if (months % 2 == 0 && dayscount <= 30 && dayscount >= 1) {
            return dayscount;
        }
        throw new NumberFormatException();
    }

    public static int parseMonthsfromString(String months) {
        String[] engmonths = new String[]{
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };
        String[] rumonths = new String[]{
                "Январь",
                "Февраль",
                "Март",
                "Апрель",
                "Май",
                "Июнь",
                "Июль",
                "Август",
                "Сентябрь",
                "Октябрь",
                "Ноябрь",
                "Декабрь"
        };
        for (int i = 0; i < engmonths.length; i++) {
            if (engmonths[i].equals(months))
                return i + 1;
        }
        for (int i = 0; i < rumonths.length; i++) {
            if (rumonths[i].equals(months))
                return i + 1;
        }
        throw new NumberFormatException();
    }

    public static int parseMonths(String months) throws NumberFormatException {
        if (months.length() == 0) {
            return 1;
        }
        int monthscount = Integer.parseInt(months);
        if (monthscount < 1 || monthscount > 12) {
            throw new NumberFormatException();
        }
        return monthscount;
    }

    public static int parseYears(String years) throws NumberFormatException {
        if (years.length() == 0) {
            return 2021;
        }
        int yearscount;
        if (years.length() == 2) {
            yearscount = Integer.parseInt(years) + 2000;
            return yearscount;
        }
        return Integer.parseInt(years);
    }

    public static int findDiff(MyDate currDate, MyDate dateDiff, MyDate resDate) {
        int diff = 0;
        resDate.setSeconds((currDate.getSeconds() - dateDiff.getSeconds()) + ((currDate.getMinutes() - dateDiff.getMinutes()) * 60) + ((currDate.getHours() - dateDiff.getHours()) * 60 * 60));
//        diff += currDate.getSeconds() - dateDiff.getSeconds();
       /* diff += (currDate.getMinutes() - dateDiff.getMinutes()) * 60;
        diff += (currDate.getHours() - dateDiff.getHours()) * 60 * 60;*/
        resDate.setMonths(12 - currDate.getMonths() + dateDiff.getMonths());
        resDate.setMonths(findDiffMonths(currDate.getYears(), dateDiff.getYears()) + (resDate.getMonths() - currDate.getMonths()));
        resDate.setDays(findDiffDays(currDate, dateDiff));
        resDate.setHours(resDate.getDays() * 24 + Math.abs(currDate.getHours() - dateDiff.getHours()));
        resDate.setMinutes(resDate.getHours() * 60 + Math.abs(currDate.getMinutes() - dateDiff.getMinutes()));
        resDate.setSeconds(resDate.getMinutes() * 60 + Math.abs(currDate.getSeconds() - dateDiff.getSeconds()));
        resDate.setYears(resDate.getMonths() / 12);
        return diff;
    }

    private static int findDiffMonths(int years, int diffyears) {
        int res = 0;
        for (int i = years; i < diffyears; i++) {
            res += 12;
        }
        return res;
    }

    private static int findDiffDays(MyDate currDate, MyDate dateDiff) {
        // Если год не делится на 4, значит он обычный.
        // Иначе надо проверить не делится ли год на 100.
        // Если не делится, значит это не столетие и можно сделать вывод, что год високосный.
        // Если делится на 100, значит это столетие и его следует проверить его делимость на 400.
        // Если год делится на 400, то он високосный.
        // Иначе год обычный.
        int days = 0;
        for (int i = currDate.getYears(); i <= dateDiff.getYears(); i++) {
            if (i == dateDiff.getYears() - 1)
                if (i % 4 == 0) {
                    if (i % 100 == 0) {
                        if (i % 400 == 0) {
                            days += 366;
                        }
                    }
                    days += 366;
                } else {
                    days += 365;
                }
        }
        return days;
    }

    public static void addDays(int days, MyDate currDate) {
        if (currDate.getMonths() == 2 && currDate.getDays() + days > 28) {
            addMonths(1, currDate);
            currDate.setDays(0);
            addDays(days - 28, currDate);
        } else if (currDate.getMonths() % 2 == 1 && currDate.getDays() + days > 31) {
            addMonths(1, currDate);
            currDate.setDays(0);
            addDays(days - 31, currDate);
        } else if (currDate.getMonths() % 2 == 0 && currDate.getDays() > 30) {
            addMonths(1, currDate);
            currDate.setDays(0);
            addDays(days - 30, currDate);
        }
        currDate.setDays(currDate.getDays() + days);
    }

    public static void addMonths(int months, MyDate currDate){
        if(currDate.getMonths() + months > 12){
            addYears(1, currDate);
            currDate.setMonths(1);
            addMonths(months-12, currDate);
        }
        currDate.setMonths(currDate.getMonths()+ months);
    }

    public static void addYears(int years, MyDate currDate){
        currDate.setYears(currDate.getYears() + years);
    }

}
