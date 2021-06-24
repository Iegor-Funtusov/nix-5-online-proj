package ua.nkrasnovoronka.data;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;

public interface LibraryDB {


    void createAuthor(Author author);

    void createBook(Book book);
}
