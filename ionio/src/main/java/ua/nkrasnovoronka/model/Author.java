package ua.nkrasnovoronka.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Author extends AbstractEntity {
    private String firstName;
    private String lastName;
    private Set<Long> booksList;

    public Author() {
        booksList = new HashSet<>();
        super.setVisible(true);
    }

    public void addBookToAuthor(Long bookId) {
        booksList.add(bookId);
    }


    @Override
    public String toString() {
        return "Author{" +
                "id='" + super.getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", booksList=" + booksList +
                '}';
    }
}
