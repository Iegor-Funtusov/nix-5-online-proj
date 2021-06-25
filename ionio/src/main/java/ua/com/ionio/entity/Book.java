package ua.com.ionio.entity;

import java.util.List;

public class Book {
    private String id;
    private String title;
    private List<String> listAuthors;
    private boolean isvisableBook;

    public Book(String id, String title, List<String> listAuthors) {
        this.id = id;
        this.title = title;
        this.listAuthors = listAuthors;
        this.isvisableBook = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getListAuthors() {
        return listAuthors;
    }

    public void setListAuthors(List<String> listAuthors) {
        this.listAuthors = listAuthors;
    }

    public boolean isIsvisableBook() {
        return isvisableBook;
    }

    public void setIsvisableBook(boolean isvisableBook) {
        this.isvisableBook = isvisableBook;
    }
}
