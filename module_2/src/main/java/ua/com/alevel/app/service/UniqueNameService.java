package ua.com.alevel.app.service;

import ua.com.alevel.app.util.FileUtils;

import java.util.HashMap;
import java.util.List;

public class UniqueNameService {

    public void findFirstUnique(String inFile, String outFile) {
        List<String> names = FileUtils.readFile(inFile);
        HashMap<String, Integer> map = new HashMap<>();
        for (String name : names) {
            if (map.containsKey(name)) {
                map.put(name, map.get(name) + 1);
            } else {
                map.put(name, 1);
            }
        }
        for (String name : names)
            if (map.get(name) == 1) {
                FileUtils.writeFile(List.of(name), outFile);
                return;
            }
        FileUtils.writeFile(List.of("This file has not unique names."), outFile);
    }
}
