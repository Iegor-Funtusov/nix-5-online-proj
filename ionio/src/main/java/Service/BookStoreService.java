package Service;

import java.util.Scanner;

public interface BookStoreService {

    void createBook(Scanner s);

    void createAuthor(Scanner s);

    void getBooksAuthors(Scanner s);

    void getAuthorsBooks(Scanner s);

    void getBooks();

    void getAuthors();

    void updateAuthor(Scanner s);

    void updateBook(Scanner s);
}
