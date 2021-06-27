package com.nixsolutions.model;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String id;
    private String title;
    private boolean isVisible = true;
    private List<String> authorList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean IsVisible) {
        this.isVisible = IsVisible;
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Id = '" + id + '\'' +
                ", Title = '" + title + '\'' +
                ", Author List = " + authorList +
                '}';
    }
}