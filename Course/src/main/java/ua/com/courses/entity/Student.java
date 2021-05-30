package ua.com.courses.entity;

import java.util.Arrays;

public class Student{
    private String id;
    private String surname;
    private String name;
    private String [] courses;

    public Student(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", courses=" + Arrays.toString(courses) +
                '}';
    }
}
