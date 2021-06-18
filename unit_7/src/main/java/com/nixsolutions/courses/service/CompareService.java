package com.nixsolutions.courses.service;

import java.util.Collections;
import java.util.List;

public class CompareService {

    public <T extends Comparable<T>> List<T> compareAscending (List<T> list) {
        Collections.sort(list);
        return list;
    }

    public <T extends Comparable<T>> List<T> compareDescending (List<T> list) {
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
}
