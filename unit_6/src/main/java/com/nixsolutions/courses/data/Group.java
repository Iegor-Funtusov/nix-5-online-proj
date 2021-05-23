package com.nixsolutions.courses.data;

import java.util.Arrays;
import java.util.Objects;

public class Group {

    private String name;
    private final int SIZE;
    private Student[] list;

    public Group(int SIZE) {
        this.SIZE = SIZE;
        list = new Student[SIZE];
    }

    public int getSIZE() {
        return SIZE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student[] getList() {
        return list;
    }

    public void setList(Student[] list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", SIZE=" + SIZE +
                ", list=" + Arrays.toString(Arrays.stream(list).filter(Objects::nonNull).toArray(Student[]::new)) +
                '}';
    }
}
