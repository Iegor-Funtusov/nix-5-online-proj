package task2;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FindUniqueName {

    public String find(List<String> names){

        Map<String, Integer> namesCount = new LinkedHashMap<String, Integer>();
        for(int i = 0; i < names.size(); i++) {
            if (namesCount.get(names.get(i)) != null) {
                namesCount.put(names.get(i), namesCount.get(names.get(i)) + 1);
            } else {
                namesCount.put(names.get(i), 1);
            }
        }
        for (Map.Entry<String, Integer> entry : namesCount.entrySet()){
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return null;
    }
}
