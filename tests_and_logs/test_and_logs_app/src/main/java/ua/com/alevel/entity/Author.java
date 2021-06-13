package ua.com.alevel.entity;

import ua.com.alevel.lib.BaseEntity;

public class Author extends BaseEntity {

    private String name;
    private int age;

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author: ID = " + this.getId() +
                ", name = " + name +
                ", age = " + age;
    }
}
