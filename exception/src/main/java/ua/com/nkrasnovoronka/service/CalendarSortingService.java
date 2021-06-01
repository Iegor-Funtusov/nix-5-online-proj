package ua.com.nkrasnovoronka.service;

import ua.com.nkrasnovoronka.model.Calendar;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarSortingService {
    private List<Calendar> elements;

    public CalendarSortingService(List<Calendar> elements){
        this.elements = elements;
    }

    public List<Calendar> sortASC(){
        return elements.stream().sorted().collect(Collectors.toList());

    }

    public List<Calendar> sortDESC(){
        return elements.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

}
