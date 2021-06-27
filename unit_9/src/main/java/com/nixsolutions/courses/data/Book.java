package com.nixsolutions.courses.data;

import java.util.List;
import java.util.UUID;

public class Book {

    private String id;
    private String title;
    private List<String> authors;
    private boolean isVisible;

    public Book() {
        id = UUID.randomUUID().toString();
        isVisible = true;
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
