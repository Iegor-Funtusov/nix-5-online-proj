package ua.nkrasnovoronka;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.model.Genre;
import ua.nkrasnovoronka.service.AuthorService;
import ua.nkrasnovoronka.service.BookService;
import ua.nkrasnovoronka.service.impl.AuthorServiceImpl;
import ua.nkrasnovoronka.service.impl.BookServiceImpl;

import java.util.Collections;

public class CommonTests {

    private static AuthorService authorService = new AuthorServiceImpl();
    private static BookService bookService = new BookServiceImpl();

    public static void runSimulation() {
        System.out.println("<Creating 10 authors>");
        for (int i = 1; i <= 10; i++) {
            Author author = new Author();
            author.setFirstName("FirstName " + i);
            author.setLastName("LastName " + i);
            authorService.create(author);
        }
        System.out.println("<Created 10 authors>");

        System.out.println("<Creating 10 books>");
        for (int i = 1; i <= 10; i++) {
            Book book = new Book();
            book.setBookTitle("Title " + i);
            book.setBookRating(i);
            book.setGenre(Genre.OTHER);
            book.setBooksAuthors(Collections.singleton((long) i));
            bookService.create(book);
        }
        System.out.println("<Created  10 books>");
        System.out.println("Deleting author and book with id 5");

        authorService.removeAuthorById(5L);
        bookService.removeBookById(5L);

        System.out.println("<Print all authors and books>");

        System.out.println(authorService.getAllAuthors());
        System.out.println(bookService.getAllBooks());

        System.out.println("<Updating author and book with id 4");

        Author updatedAuthor = authorService.getAuthorById(4L);
        updatedAuthor.setFirstName("Updated");
        updatedAuthor.setLastName("Updated");
        authorService.updateAuthor(4L, updatedAuthor);

        Book updatedBook = bookService.getBookById(4L);
        updatedBook.setBookTitle("UpdatedTitle");
        updatedBook.setGenre(Genre.CLASSIC);
        updatedBook.setBookRating(1000);
        bookService.updateBook(4L, updatedBook);

        System.out.println("<Print all authors and books>");

        System.out.println(authorService.getAllAuthors());
        System.out.println(bookService.getAllBooks());

        System.out.println("<Getting author with id 6");
        System.out.println(authorService.getAuthorById(6L));

        System.out.println("Adding book with id 6 to author with id 2");
        authorService.addBookToAuthor(2L, 6L);

        System.out.println("Printing all author books");
        System.out.println(authorService.getAllAuthorBooks(2L));
        System.out.println("Removing book with id 6 from author with id 6");

        bookService.removeAuthorFromBook(6L, 6L);
        System.out.println(authorService.getAllAuthorBooks(6L));


    }
}
