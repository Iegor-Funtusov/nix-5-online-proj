package ua.com.alevel.data_classes;

import ua.com.alevel.lib.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book extends BaseEntity {

    private String title;
    private List<Author> authors;

    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public Book(String title) {
        this.title = title;
        this.authors = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return this.getId().equals(book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, this.getId());
    }

    @Override
    public String toString() {
        return "Book: " + "ID = " + this.getId() +
                "title = " + title +
                ", authors = " + authors;
    }
}
