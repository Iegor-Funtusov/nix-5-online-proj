package Task2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task2Solution {

    public static List<String> getSolution(List<String> allNames){
        List<String> uniqueNames = new ArrayList<>();
        Map<String, Long> nameQuantity = allNames.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for(Map.Entry<String, Long> item : nameQuantity.entrySet()){
            if(item.getValue() == 1){
                uniqueNames.add(item.getKey());
            }
        }
        return uniqueNames;
    }

}
