package DatePackage;
import java.util.ArrayList;
import java.util.List;

public class DateDB {
    private List<Date> dates;

    DateDB(){
        dates = new ArrayList<>();
    }

    public void add(Date dateToAdd){
        if(isExistDate(dateToAdd)){
            throw new RuntimeException("Date is already exists");
        }
        dates.add(dateToAdd);
    }


    public Date get(int indexOfElement){
        isNotEmpty();
        if(indexOfElement > dates.size()){
            throw new RuntimeException("Index is out of bounds");
        }
        return dates.get(indexOfElement);
    }


    public List<Date> get(){
        isNotEmpty();
        return dates;
    }


    private boolean isExistDate(Date dateToCheck){
        return dates.stream().anyMatch(date -> date.equals(dateToCheck));
    }

    private void isNotEmpty(){
        if(dates.size() == 0){
            throw new RuntimeException("Dates is empty");
        }
    }
}
