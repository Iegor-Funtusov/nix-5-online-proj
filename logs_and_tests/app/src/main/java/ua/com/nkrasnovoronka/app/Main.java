package ua.com.nkrasnovoronka.app;

import ua.com.nkrasnovoronka.app.dao.Author;
import ua.com.nkrasnovoronka.app.dao.Book;
import ua.com.nkrasnovoronka.app.service.AuthorService;
import ua.com.nkrasnovoronka.app.service.BookService;
import ua.com.nkrasnovoronka.app.service.impl.AuthorServiceImpl;
import ua.com.nkrasnovoronka.app.service.impl.BookServiceImpl;

public class Main {
    public static void main(String[] args) {
        AuthorService authorService = new AuthorServiceImpl();
        authorService.create(new Author("a"));
        Author author = new Author("b");
        author.setId("1");
        authorService.create(author);
        System.out.println(authorService.getAllAuthors());
        authorService.removeAuthorByName("a");
        System.out.println(authorService.getAllAuthors());
        BookService bookService = new BookServiceImpl();
        Book book = new Book("test");
        book.setAuthorId("1");
        bookService.create(book);
        System.out.println(authorService.getAllAuthorBooks("b"));
    }
}
