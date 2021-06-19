package ua.com.alevel.app.entity;

import ua.com.alevel.lib.BaseEntity;

public class Student extends BaseEntity {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "id = " + getId() +  " name = " + name + " age = " + age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}