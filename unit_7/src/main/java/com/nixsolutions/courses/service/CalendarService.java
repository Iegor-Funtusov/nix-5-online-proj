package com.nixsolutions.courses.service;

import com.nixsolutions.courses.data.Date;
import com.nixsolutions.courses.util.CalendarUtils;

import java.time.DateTimeException;
import java.util.List;

public class CalendarService {

    private final CompareService compareService = new CompareService();
    private final SubtractService subtractService = new SubtractService();
    private final AddService addService = new AddService();
    private final DifferenceService differenceService = new DifferenceService();

    public List<Date> compareDates(List<Date> list, String order) {
        switch (order) {
            case "1":
                return compareService.compareAscending(list);
            case "2":
                return compareService.compareDescending(list);
        }
        return list;
    }

    public Date subtractFromDate(Date date, int value, String scope) {
        switch (scope) {
            case "seconds":
                return subtractService.subtractSeconds(date, value);
            case "minutes":
                return subtractService.subtractMinutes(date, value);
            case "hours":
                return subtractService.subtractHours(date, value);
            case "days":
                return subtractService.subtractDays(date, value);
            case "months":
                return subtractService.subtractMonths(date, value);
            case "years":
                return subtractService.subtractYears(date, value);
        }
        return date;
    }

    public Date addToDate(Date date, int value, String scope) {
        switch (scope) {
            case "seconds":
                return addService.addSeconds(date, value);
            case "minutes":
                return addService.addMinutes(date, value);
            case "hours":
                return addService.addHours(date, value);
            case "days":
                return addService.addDays(date, value);
            case "months":
                return addService.addMonths(date, value);
            case "years":
                return addService.addYears(date, value);
        }
        return date;
    }

    public double findDifference(Date from, Date to, String scope) throws DateTimeException {
        switch (scope) {
            case "seconds":
                return differenceService.differenceInSeconds(from, to);
            case "minutes":
                return differenceService.differenceInMinutes(from, to);
            case "hours":
                return differenceService.differenceInHours(from, to);
            case "days":
                return differenceService.differenceInDays(from, to);
            case "months":
                return differenceService.differenceInMonths(from, to);
            case "years":
                return differenceService.differenceInYears(from, to);
        }
        return -1;
    }

}
