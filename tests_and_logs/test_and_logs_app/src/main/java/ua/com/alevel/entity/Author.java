package ua.com.alevel.entity;

import ua.com.alevel.lib.BaseEntity;

import java.util.ArrayList;
import java.util.List;


public class Author extends BaseEntity {

    private String name;
    private int age;
    private List<Book> books;

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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
                ", age = " + age +
                ", books = " + books.toString();
    }
}
