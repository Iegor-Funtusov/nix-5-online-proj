package domain;

public class Book {
    private String bookId;
    private String title;
    private int year;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "domain.Book{" + '\'' +
            "bookId=" + bookId +
            ", title='" + title + '\'' +
            ", year=" + year +
            '}';
    }
}
