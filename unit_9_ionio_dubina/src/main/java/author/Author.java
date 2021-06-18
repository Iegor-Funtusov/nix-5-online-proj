package author;

import book.Book;

public class Author {

    private String id;
    private String Surname;
    private String Name;
    private Book[] books;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Author(Book[] books) {
        this.books = books;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    @Override
    public String toString() {
        return "Author{" +
                "Surname='" + Surname + '\'' +
                ", name='" + Name + '\'' +
                ", books=" + books +
                '}';
    }
}