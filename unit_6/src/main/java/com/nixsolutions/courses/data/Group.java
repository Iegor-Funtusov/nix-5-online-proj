package com.nixsolutions.courses.data;

import java.util.Arrays;

public class Group {

    private final int SIZE;
    private String name;
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
                "SIZE=" + SIZE +
                ", name='" + name + '\'' +
                ", list=" + Arrays.toString(list) +
                '}';
    }
}
