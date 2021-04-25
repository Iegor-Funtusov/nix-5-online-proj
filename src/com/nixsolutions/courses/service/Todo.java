package com.nixsolutions.courses.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Todo {
    private int id;
    private String title;
    private String deadline;
}
