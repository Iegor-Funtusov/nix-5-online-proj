package com.k4rnaj1k.entities;

public class Author {

    public Author(){
        booklist = "";
    }

    private int id;
    private String name;
    private String surname;
    private String booklist;
    private String visible;

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBooklist() {
        return booklist;
    }

    public Author(String name, String surname, String booklist){
        this.name = name;
        this.surname = surname;
        this.booklist = booklist;
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
