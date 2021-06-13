package ua.com.alevel.entity;

import ua.com.alevel.lib.BaseEntity;

public class Relation extends BaseEntity {

    private Book book;
    private Author author;

    public Relation(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Relation{ " +
                "book = " + book +
                ",\n\t  author = " + author +
                '}';
    }
}
