package DateHelpers;
import DatePackage.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Сортировка дат
public class SortDate {

    public static ArrayList<Date> ascendingSort(List<Date> datesToSort) {
        isEmptyDates(datesToSort);
        Collections.sort(datesToSort);
        return (ArrayList<Date>) datesToSort;
    }


    public static ArrayList<Date> descendingSort(List<Date> datesToSort){
        isEmptyDates(datesToSort);
        Collections.sort(datesToSort);
        Collections.reverse(datesToSort);
        return (ArrayList<Date>) datesToSort;
    }


    private static void isEmptyDates(List<Date> dates){
        if(dates.size() == 0){
            throw new RuntimeException("List is empty");
        }
    }

}
