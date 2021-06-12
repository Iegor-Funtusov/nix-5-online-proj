package ua.com.alevel.entity;

import ua.com.alevel.lib.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class Book extends BaseEntity {

    private String title;
    private List<Author> authors;

    public Book(String title) {
        this.title = title;
        this.authors = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    private String getAuthorsString(){
        String authorsString = "";
        for (Author author : authors){
            authorsString+= "\n" + "ID= " + author.getId() + ", name= " + author.getName() + ", age= " + author.getAge();
        }
        return authorsString;
    }

    @Override
    public String toString() {
        return "Book: " + "ID = " + this.getId() +
                ", title = " + title +
                ", authors = " + getAuthorsString();
    }
}
