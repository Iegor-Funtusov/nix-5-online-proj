package data;

public class Author {
    private int authorID;
    private String firstName;
    private String lastName;
    private String listOfBooks;

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
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

    public String getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(String listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

}
