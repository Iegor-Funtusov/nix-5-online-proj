package ua.com.alevel.entities;

import ua.com.alevel.lib.BaseEntity;

import java.util.List;

public class Author extends BaseEntity {

    private String name;
    private int age;
    private List<Book> books;

    public Author(String name, int age, List<Book> books) {
        this.name = name;
        this.age = age;
        this.books = books;
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
}
