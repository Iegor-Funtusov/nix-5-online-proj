package com.k4rnaj1k;

public class DateService {

    static String[] engmonths = new String[]{
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
    static String[] rumonths = new String[]{
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

    public static int compare(MyDate date1, MyDate date2) {
        int res = -1;
        if (date1.getYears() == date2.getYears()) {
            if (date1.getMonths() == date2.getMonths()) {
                if (date1.getDays() == date2.getDays()) {
                    if (date1.getHours() == date2.getHours()) {
                        if (date1.getMinutes() == date2.getMinutes()) {
                            if (date1.getSeconds() == date2.getSeconds()) {
                                res = 0;
                            } else if (date1.getSeconds() > date2.getSeconds()) {
                                res = 1;
                            }
                        } else if (date1.getMinutes() > date2.getMinutes()) {
                            res = 1;
                        }
                    } else if (date1.getHours() > date2.getHours()) {
                        res = 1;
                    }
                } else if (date1.getDays() > date2.getMonths()) {
                    res = 1;
                }
            } else if (date1.getMonths() > date2.getMonths()) {
                res = 1;
            }
        } else if (date1.getYears() > date2.getYears()) {
            res = 1;
        }
        return res;
    }

    public static int parseDays(String days, MyDate date) throws NumberFormatException {
        if (days.length() == 0) {
            return 1;
        }
        int dayscount = Integer.parseInt(days);
        if(dayscount>=1 && dayscount<=getMaxDaysInMonth(date)){
            return dayscount;
        }
        /*if (months == 2 && dayscount <= 28 && dayscount >= 1) {
            return dayscount;
        } else if (dayscount>=1 && dayscount<=getMaxDaysInMonth(months))
            return dayscount;
        else if (months % 2 == 0 && dayscount <= 30 && dayscount >= 1) {
            return dayscount;
        }*/
        throw new NumberFormatException();
    }

    public static int parseMonthsfromString(String months) {

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

    public static void findDiff(MyDate currDate, MyDate dateDiff, MyDate resDate) {
//        resDate.setSeconds((currDate.getSeconds() - dateDiff.getSeconds()) + ((currDate.getMinutes() - dateDiff.getMinutes()) * 60) + ((currDate.getHours() - dateDiff.getHours()) * 60 * 60));
//        diff += currDate.getSeconds() - dateDiff.getSeconds();
       /* diff += (currDate.getMinutes() - dateDiff.getMinutes()) * 60;
        diff += (currDate.getHours() - dateDiff.getHours()) * 60 * 60;*/
//        resDate.setMonths(12 - currDate.getMonths() + dateDiff.getMonths())
        resDate.setDays(findDiffDays(currDate, dateDiff, resDate));
        resDate.setHours(resDate.getDays() * 24 + Math.abs(currDate.getHours() - dateDiff.getHours()));
        resDate.setMinutes(resDate.getHours() * 60 + Math.abs(currDate.getMinutes() - dateDiff.getMinutes()));
        resDate.setSeconds(resDate.getMinutes() * 60 + Math.abs(currDate.getSeconds() - dateDiff.getSeconds()));
        resDate.setYears(resDate.getMonths() / 12);
//        return diff;
    }

    private static boolean isLeap(int year) {
        // Если год не делится на 4, значит он обычный.
        // Иначе надо проверить не делится ли год на 100.
        // Если не делится, значит это не столетие и можно сделать вывод, что год високосный.
        // Если делится на 100, значит это столетие и его следует проверить его делимость на 400.
        // Если год делится на 400, то он високосный.
        // Иначе год обычный.
        boolean res = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    res = true;
                }
            } else {
                res = true;
            }
        }
        return res;
    }

    private static int findDiffDays(MyDate currDate, MyDate dateDiff, MyDate resDate) {
        int days = 0;
        if (currDate.getYears().equals(dateDiff.getYears())) {
            for (int j = currDate.getMonths(); j <= dateDiff.getMonths(); j++) {
                if (j == dateDiff.getMonths()) {
                    days += dateDiff.getDays() - currDate.getDays();
                    break;
                } else if (j == 2) {
                    if (isLeap(currDate.getYears())) {
                        days += 29;
                    } else {
                        days += 28;
                    }
                } else if (j % 2 == 1 || j == 8 || j == 12) {
                    days += 31;
                } else {
                    days += 30;
                }
                resDate.setMonths(resDate.getMonths() + 1);
            }
        }
        for (int i = currDate.getYears() + 1; i <= dateDiff.getYears(); i++) {
            if (dateDiff.getYears() == i) {
                for (int j = 1; j <= dateDiff.getMonths(); j++) {
                    if (j == dateDiff.getMonths()) {
                        days += dateDiff.getDays();
                    }
                    if (j == 2) {
                        if (isLeap(i)) {
                            days += 29;
                        } else {
                            days += 28;
                        }
                    } else if (j % 2 == 1 || j == 8) {
                        days += 31;
                    } else {
                        days += 30;
                    }
                    resDate.setMonths(resDate.getMonths() + 1);
                }
            } else {
                if (isLeap(i)) {
                    days += 366;
                } else {
                    days += 365;
                }
                resDate.setMonths(resDate.getMonths() + 12);
            }
        }
        return days;
    }

    public static void addDays(int days, MyDate currDate) {
        int prevdays = currDate.getDays();
        if(currDate.getDays() + days > getMaxDaysInMonth(currDate)){
            addMonths(1, currDate);
            currDate.setDays(0);
            addDays(prevdays+days - getMaxDaysInMonth(currDate), currDate);
        }else{
            currDate.setDays(currDate.getDays() + days);
        }
        /*
        if (currDate.getMonths() == 2 && isLeap(currDate.getYears()) && currDate.getDays() + days > 29) {
            addMonths(1, currDate);
            currDate.setDays(0);
            addDays((prevdays + days) - 29, currDate);
        } else if (currDate.getMonths() == 2 && currDate.getDays() + days > 28) {
            addMonths(1, currDate);
            currDate.setDays(0);
            addDays((prevdays + days) - 28, currDate);
        } else if ((currDate.getMonths() % 2 == 1 || currDate.getMonths() == 8 || currDate.getMonths() == 12) && currDate.getDays() + days > 31) {
            addMonths(1, currDate);
            currDate.setDays(0);
            addDays((prevdays + days) - 31, currDate);
        } else if (currDate.getMonths() % 2 == 0 && currDate.getDays() + days > 30) {
            addMonths(1, currDate);
            currDate.setDays(0);
            addDays((prevdays + days) - 30, currDate);
        } else {
            currDate.setDays(currDate.getDays() + days);
        }*/
    }

    private static int getMaxDaysInMonth(MyDate date) {
        /*Январь - 31 день 1
        Февраль - 28 дней (29 в високосном)
        Март - 31 день 3
        Апрель - 30 дней 4
        Май - 31 день 5
        Июнь - 30 дней 6
        Июль - 31 день 7
        Август - 31 день 8
        Сентябрь - 30 дней 9
        Октябрь - 31 день 10
        Ноябрь - 30 дней 11
        Декабрь - 31 день 12*/
        switch (date.getMonths()) {
            case 1:
            case 7:
            case 8:
            case 3:
            case 5:
            case 10:
            case 12:
                return 31;
            case 2:
                return isLeap(date.getYears()) ? 28 : 29;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
        return 0;
    }

    public static void addMonths(int months, MyDate currDate) {
        if (currDate.getMonths() + months > 12) {
            addYears((currDate.getMonths() + months) / 12, currDate);
            currDate.setMonths(months % 12);
        } else {
            currDate.setMonths(currDate.getMonths() + months);
        }
    }

    public static void addYears(int years, MyDate currDate) {
        currDate.setYears(currDate.getYears() + years);
    }

    public static void printDate(MyDate date, DateType dateType, boolean english) {
        switch (dateType) {
            case ddmmyy:
                System.out.print(date.getDays() + "/" + date.getMonths() + "/" + String.valueOf(date.getYears()).substring(2));
                break;
            case mdyyyy:
                System.out.print(date.getMonths() + "/" + date.getDays() + "/" + date.getYears());
                break;
            case mmmdyy:
                System.out.print(english ? engmonths[date.getMonths() - 1] : rumonths[date.getMonths() - 1] + "-" + date.getDays() + "-" + String.valueOf(date.getYears()).substring(2));
                break;
            case ddmmmyyyy:
                System.out.print(date.getDays() + "-" + (english ? engmonths[date.getMonths() - 1] : rumonths[date.getMonths() - 1]) + "-" + date.getYears());
        }
        System.out.print(" " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
    }

    public static void subDays(int days, MyDate currDate){
        if(currDate.getDays() - days< 0){
            subMonths(1, currDate);
            currDate.setDays(getMaxDaysInMonth(currDate));
            subDays(days - getMaxDaysInMonth(currDate), currDate);
        }
        currDate.setDays(currDate.getDays() - days);
    }

    public static void subMonths(int months, MyDate currDate){
        if(currDate.getMonths() - months < 0){
            subYears(1, currDate);
            currDate.setMonths(12);
            subMonths(months-12, currDate);
        }
        currDate.setMonths(currDate.getMonths() - months);
    }

    public static void subYears(int years, MyDate currDate){
        currDate.setYears(currDate.getYears() - years);
    }

    public enum DateType {
        ddmmyy,
        mdyyyy,
        mmmdyy,
        ddmmmyyyy;
    }
}
