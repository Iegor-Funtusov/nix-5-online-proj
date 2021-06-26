package DataClasses;

import CrudCSV.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class Author extends BaseEntity {
    private String firstName;
    private String lastName;
    private List<String> books;

    public Author(){
        books = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        if(books.isEmpty()){
            return super.toString() + " full name: " + getFullName();
        }

        StringBuilder booksInString = new StringBuilder("");
        for(String item : books){
            booksInString.append(item).append("; ");
        }
        return super.toString() + " full name: " + getFullName() + " books:" + booksInString;
    }
}
