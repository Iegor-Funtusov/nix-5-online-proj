package entities;

import java.util.Arrays;

public class Author {

    private int id;
    private String name;
    private String surname;
    private String booklist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBooklist() {
        return booklist;
    }

    public void setBooklist(String booklist) {
        this.booklist = booklist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
