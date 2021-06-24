package ua.nkrasnovoronka.util;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.model.Genre;

public class Util {

    private Util() {
    }

    public static String[] authorToStringArray(Author author, Long id) {
        String[] authorString = new String[4];
        authorString[0] = String.valueOf(id);
        authorString[1] = author.getFirstName();
        authorString[2] = author.getLastName();
        authorString[3] = String.valueOf(author.isVisible());
        return authorString;
    }

    public static String[] bookToStringArray(Book book, Long id) {
        String[] bookString = new String[5];
        bookString[0] = String.valueOf(id);
        bookString[1] = book.getBookTitle();
        bookString[2] = String.valueOf(book.getGenre());
        bookString[3] = String.valueOf(book.getBookRating());
        bookString[4] = String.valueOf(book.isVisible());
        return bookString;
    }

    public static Author authorFromStringArray(String[] authorString){
        Author author = new Author();
        author.setId(Long.valueOf(authorString[0]));
        author.setFirstName(authorString[1]);
        author.setLastName(authorString[2]);
        author.setVisible(Boolean.parseBoolean(authorString[3]));
        return author;
    }

    public static Book bookFromString(String[] bookString){
        Book book = new Book();
        book.setId(Long.valueOf(bookString[0]));
        book.setBookTitle(bookString[1]);
        book.setGenre(Genre.valueOf(bookString[2]));
        book.setBookRating(Double.parseDouble(bookString[3]));
        book.setVisible(Boolean.parseBoolean(bookString[4]));
        return book;
    }
}
