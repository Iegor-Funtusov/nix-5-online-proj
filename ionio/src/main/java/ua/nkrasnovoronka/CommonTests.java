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
        System.out.println("<Creating 5 authors>");
        for (int i = 1; i <= 5; i++) {
            Author author = new Author();
            author.setFirstName("FirstName " + i);
            author.setLastName("LastName " + i);
            authorService.create(author);
        }
        System.out.println("<Created 5 authors>");

        System.out.println("<Creating 5 books>");
        for (int i = 1; i <= 5; i++) {
            Book book = new Book();
            book.setBookTitle("Title " + i);
            book.setBookRating(i);
            book.setGenre(Genre.OTHER);
            book.setBooksAuthors(Collections.singleton((long) i));
            bookService.create(book);
        }
        System.out.println("<Created  5 books>");
        System.out.println("Deleting author and book with id 2");

        authorService.removeAuthorById(2L);
        bookService.removeBookById(2L);

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

        System.out.println("<Getting author with id 1");
        System.out.println(authorService.getAuthorById(1L));

        System.out.println("Adding book with id 1 to author with id 5");
        authorService.addBookToAuthor(1L, 5L);

        System.out.println("Printing all author books");
        System.out.println(authorService.getAllAuthorBooks(1L));
        System.out.println("Removing book with id 5 from author with id 1");

        bookService.removeAuthorFromBook(5L, 1L);
        System.out.println(authorService.getAllAuthorBooks(1L));


    }
}
