package com.nixsolutions.courses.service;

import com.nixsolutions.courses.data.Date;

import java.util.Collections;
import java.util.List;

public class CompareService {

    protected List<Date> compareAscending (List<Date> list) {
        Collections.sort(list);
        return list;
    }

    protected List<Date> compareDescending (List<Date> list) {
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
}
