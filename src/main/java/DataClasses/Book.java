package DataClasses;

import CrudCSV.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class Book extends BaseEntity {
    private String bookName;
    private List<String> authors;

    public Book(){
        authors = new ArrayList<>();
    }

    public String getBookName() {
        return bookName;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        StringBuilder authorsInString = new StringBuilder("");
        for(String item : authors){
            authorsInString.append(item).append("; ");
        }

        return super.toString() + "\t name: " + bookName + "\tauthors: " + authorsInString;
    }
}