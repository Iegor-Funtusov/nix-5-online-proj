package Decorators;

import CrudCSV.BaseEntity;
import java.util.List;

public class FormatList{

    public static List<? extends BaseEntity> formatToOutput(List<? extends BaseEntity> listToFormat){
       if(listToFormat.isEmpty() || listToFormat.size() == 1){
           throw new RuntimeException("There are no elements");
       }

       listToFormat.remove(listToFormat.get(0));
       return listToFormat;
    }
}
