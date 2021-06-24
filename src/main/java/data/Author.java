package data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Author implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private List<String> books;
    private boolean visibleFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public boolean isVisibleFlag() {
        return visibleFlag;
    }

    public boolean getVisibleFlag() {
        return visibleFlag;
    }

    public void setVisibleFlag(boolean visibleFlag) {
        this.visibleFlag = visibleFlag;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                ", visibleFlag=" + visibleFlag +
                '}';
    }
}
