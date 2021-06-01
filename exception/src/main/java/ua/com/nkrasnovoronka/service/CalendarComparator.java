package ua.com.nkrasnovoronka.service;

import ua.com.nkrasnovoronka.model.Calendar;

import java.util.Comparator;

public class CalendarComparator implements Comparator<Calendar> {
    @Override
    public int compare(Calendar o1, Calendar o2) {
        if(o1.getDate().getYear() > o2.getDate().getYear()){
            return 1;
        }
        return 0;
    }
}
