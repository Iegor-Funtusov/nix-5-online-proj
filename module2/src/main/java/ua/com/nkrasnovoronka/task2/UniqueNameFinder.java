package ua.com.nkrasnovoronka.task2;

import ua.com.nkrasnovoronka.util.FileUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UniqueNameFinder {
    public void getFirstUniqueName(String filePath, String outputFile){
        List<String> namesFromFile = FileUtil.readFile(filePath);
        Map<String, Long> frequency = namesFromFile.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<String, Long> e : frequency.entrySet()){
            if(e.getValue() == 1){
                FileUtil.writeToFile(Collections.singletonList(e.getKey()), outputFile);
                break;
            }else {
                FileUtil.writeToFile(Collections.singletonList("Current file doesn't contains unique names"), outputFile);
            }
        }
    }
}
