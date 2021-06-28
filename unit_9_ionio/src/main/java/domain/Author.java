package domain;

import java.util.List;

public class Author {

    private String authorId;
    private String firstName;
    private String lastName;
    private List<String> books;// = new ArrayList<>();
    private boolean visible;


   // private Book[] books;

  /* public Author() {
       books = new Book[5];
   }*/

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public List<String> getBooks() {
        return books;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

  /*  public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
   }*/

  /*  @Override
    public String toString() {
        return "domain.Author{" + '\'' +
            "authorId='" + authorId + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
          // ", books=" + Arrays.toString(books) +
            ", books=" + (int) Arrays.stream(books).filter(Objects::nonNull).count();
    }*/

    @Override
    public String toString() {
        return "Author{" +
            "authorId='" + authorId + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", books=" + books + '\'' +
            ", visible=" + visible +
            '}';
    }
}
