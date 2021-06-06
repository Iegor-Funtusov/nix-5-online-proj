package ua.com.threadedcode.entity;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Objects;

public class Book extends BaseEntity {
    private String title;
    private Object[] authorId;


    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object[] getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Object authorId) {
        this.authorId = ArrayUtils.addAll(this.authorId, authorId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return  super.clone();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id" + super.getId() + '\'' +
                ", title='" + title + '\'' +
                ", author=" + Arrays.toString(authorId) +
                '}';
    }
}
