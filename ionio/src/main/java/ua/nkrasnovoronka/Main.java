package ua.nkrasnovoronka;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.service.AuthorService;
import ua.nkrasnovoronka.service.BookService;
import ua.nkrasnovoronka.service.impl.AuthorServiceImpl;
import ua.nkrasnovoronka.service.impl.BookServiceImpl;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookServiceImpl();
        AuthorService authorService = new AuthorServiceImpl();
        Author author = new Author();
        author.setFirstName("Test");
        author.setLastName("Test");
        author.addBookToAuthor(1L);
        authorService.create(author);
        Book book = new Book();
        book.setBookTitle("Test");
        bookService.create(book);
        book.setBookTitle("Test2");
        bookService.create(book);
        bookService.removeBookById(1L);

        Collection<Book> allAuthorBooks = authorService.getAllAuthorBooks(1L);
        System.out.println(allAuthorBooks);

    }
}
