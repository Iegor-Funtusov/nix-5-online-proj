package ua.com.threadedcode.entity;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Objects;

public class Author extends BaseEntity {
    private String firstName;
    private String lastName;
    private Object[] bookId;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object[] getBookId() {
        return bookId;
    }

    public void setBookId(Object bookId) {
        this.bookId = ArrayUtils.add(this.bookId, bookId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id" + super.getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + Arrays.toString(bookId) +
                '}';
    }
}
