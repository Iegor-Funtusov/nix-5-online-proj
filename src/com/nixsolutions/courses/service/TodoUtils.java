package com.nixsolutions.courses.service;

import java.util.List;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

public class TodoUtils {
    private static final Splitter SPLITTER = Splitter.on('/')
            .trimResults()
            .omitEmptyStrings();
    private static Joiner JOINER = Joiner.on(".");

    public static void changeDateFormat(Todo todo) {
        String newFormat = JOINER.join(SPLITTER.split(todo.getDeadline()));
        todo.setDeadline(newFormat);
    }

    public static void setUpperCase(Todo todo) {
        String s = StringUtils.upperCase(todo.getTitle());
        todo.setTitle(s);
    }

    public static void printList(List<Todo> list) {
        for(Todo item : list) {
            System.out.println(item.getId() + ". " + item.getTitle() + " until " + item.getDeadline());
        }
    }
}