package domain;

import java.util.Arrays;
import java.util.Objects;

public class Author {
    private String authorId;
    private String firstName;
    private String lastName;

    private Book[] books;

    public Author() {
        books = new Book[5];
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "domain.Author{" + '\'' +
            "authorId='" + authorId + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
          // ", books=" + Arrays.toString(books) +
            ", books=" + (int) Arrays.stream(books).filter(Objects::nonNull).count();
    }
}
