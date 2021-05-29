package com.nixcourse.lib;

public class Book extends BaseEntity {

    private final Author author;
    private String title;
    private String text;

    public Book(String title, String text, Author author) {
        this.id = BaseEntity.generateId();
        this.title = title;
        this.text = text;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getAuthorName() {
        return author.getName();
    }

    public String getAuthorSurname() {
        return author.getSurname();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}
