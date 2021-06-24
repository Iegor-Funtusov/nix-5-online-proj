package ua.nkrasnovoronka.data;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

import java.util.Collection;

public class CSVLibraryDB implements LibraryDB{

    private static CSVLibraryDB instance;

    private CSVLibraryDB() {

    }

    public static CSVLibraryDB getInstance(){
        if(instance != null){
            return instance;
        }
        return new CSVLibraryDB();
    }

    @Override
    public Book getBookByName(String name) {
        return null;
    }

    @Override
    public Collection<Book> readAllBooks() {
        return null;
    }

    @Override
    public Collection<Book> getAllBooksByAuthorsName(String name) {
        return null;
    }

    @Override
    public Author removeAuthor(String name) {
        return null;
    }

    @Override
    public Book removeBook(String name) {
        return null;
    }

    @Override
    public Collection<Author> readAllAuthors() {
        return null;
    }

    @Override
    public Author getAuthorByName(String name) {
        return null;
    }
}
