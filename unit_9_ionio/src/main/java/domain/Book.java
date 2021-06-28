package domain;

import java.util.List;

public class Book {
    private String bookId;
    private String title;
 //   private String year;
    private List<String> authors;
    private boolean visible;

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

  /*  public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }*/

    @Override
    public String toString() {
        return "Book{" +
            "bookId='" + bookId + '\'' +
            ", title='" + title + '\'' +
            ", authors=" + authors +
            ", visible=" + visible +
            '}';
    }
}
