package ua.com.ionio.entity;

import java.util.List;

public class Author {
    private String id;
    private String firstname;
    private String lastname;
    private List<String> listBooks;
    private boolean isvisableAuthor;

    public Author(String id, String firstname,
                  String lastname, List<String> listBooks) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.listBooks = listBooks;
        this.isvisableAuthor = true;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<String> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<String> listBooks) {
        this.listBooks = listBooks;
    }

    public boolean isIsvisableAuthor() {
        return isvisableAuthor;
    }

    public void setIsvisableAuthor(boolean isvisableAuthor) {
        this.isvisableAuthor = isvisableAuthor;
    }
}
