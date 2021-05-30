package ua.com.courses.entity;

import java.util.Arrays;

public class Course{
    private String name;
    private String [] students;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getStudents() {
        return students;
    }

    public void setStudents(String[] students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", students=" + Arrays.toString(students) +
                '}';
    }
}
