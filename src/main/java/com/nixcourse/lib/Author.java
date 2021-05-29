package com.nixcourse.lib;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class Author extends BaseEntity {

    private final String name;
    private final String surname;
    private int bookCounter = 0;
    private Book[] books = new Book[10];

    public Author(String name, String surname) {
        this.id = BaseEntity.generateId();
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Book[] getBooks() {
        return books;
    }

    public Book getBook(String bookId) {
        for (Book book : books) {
            if (book != null) {
                if (book.getId().equals(bookId)) {
                    return book;
                }
            }
        }
        return null;
    }

    public void writeBook(String title, String text) {
        if (bookCounter >= books.length) {
            books = Arrays.copyOf(books, books.length * 2);
        }
        books[bookCounter] = new Book(title, text, this);
        ++bookCounter;
    }

    public void deleteBook(String bookId) {
        for (int i = 0; i < books.length; ++i) {
            if (books[i] != null) {
                if (books[i].getId().equals(bookId)) {
                    this.books = ArrayUtils.remove(books, i);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
