package tasks;

import java.util.HashMap;
import java.util.List;

public class UniqueName {
    public String uniqueName(List<String> names){
        HashMap<String , Integer> map = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            if (map.containsKey(names.get(i))) {
                map.put(names.get(i), map.get(names.get(i)) + 1);
            }
            else {
                map.put(names.get(i), 1);
            }
        }
        for (int i = 0; i < names.size(); i++)
            if (map.get(names.get(i)) == 1)
                return names.get(i);
        return "Unique names don't exist";
    }
}
