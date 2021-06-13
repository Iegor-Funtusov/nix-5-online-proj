package ua.com.alevel.app.service;

import ua.com.alevel.app.entity.Calendar;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingService {

    private final List<Calendar> dates;

    public SortingService(List<Calendar> dates){
        this.dates = dates;
    }

    public List<Calendar> sortASC(){
        return dates.stream().sorted().collect(Collectors.toList());
    }

    public List<Calendar> sortDESC(){
        return dates.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }
}
