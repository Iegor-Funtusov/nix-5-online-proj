package ua.nkrasnovoronka.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Book extends AbstractEntity {
    private String bookTitle;
    private Genre genre;
    private double bookRating;
    private Set<Long> booksAuthors;

    public Book() {
        booksAuthors = new HashSet<>();
        super.setVisible(true);
    }

    public void setBooksAuthors(Long authorId) {
        booksAuthors.add(authorId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + super.getId() + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", genre=" + genre +
                ", bookRating=" + bookRating +
                ", booksAuthors=" + booksAuthors +
                '}';
    }
}
