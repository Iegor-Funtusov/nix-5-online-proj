package com.nixsolutions.courses.app;

import com.nixsolutions.courses.lib.BaseEntity;

public class Book extends BaseEntity {

    private int year;
    private String title;

    public Book(String title, int year) {
        this.year = year;
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + super.getId() +
                ", year=" + year +
                ", title='" + title + '\'' +
                '}';
    }
}
