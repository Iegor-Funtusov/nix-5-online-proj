package DataClasses;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String bookName;
    private List<Author> authors;

    public Book(){
        authors = new ArrayList<>();
    }

    public String getBookName() {
        return bookName;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
