package com.nixsolutions.courses.service;

import com.nixsolutions.courses.data.Author;
import com.nixsolutions.courses.data.Book;
import com.nixsolutions.courses.service.impl.AuthorService;
import com.nixsolutions.courses.service.impl.BookService;
import com.nixsolutions.courses.util.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LibraryService {

    private static final BookService bookService = new BookService();
    private static final AuthorService authorService = new AuthorService();

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public LibraryService() {
        initFiles();
    }

    private void initFiles() {
        bookService.init();
        authorService.init();
    }

    public void createBook(Book book) {
        List<String> authors = checkAuthors(book);
        if (authors.size() == 0) {
            loggerError.error("No valid authors of book:" + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
            throw new RuntimeException("No valid authors of book" + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
        }
        book.setAuthors(authors);
        bookService.create(book);
    }

    private List<String> checkAuthors(Book book) {
        loggerInfo.info("Checking authors of book: " + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
        List<String> data = book.getAuthors();
        List<String[]> authors = CSVParser.readAllAuthors();
        List<String> authorsIds = CSVParser.getIds(authors);
        for (String id : data) {
            if (!authorsIds.contains(id)) {
                loggerWarn.warn("Author does not exist: " + "id=" + id);
                data.remove(id);
            } else {
                Author author = authorService.findById(id);
                List<String> bookList = author.getBooks();
                if (!bookList.contains(book.getId())) {
                    bookList.add(book.getId());
                    author.setBooks(bookList);
                    authorService.update(author);
                }
            }
        }
        return data;
    }

    public void createAuthor(Author author) {
        List<String> books = checkBooks(author);
        if (books.size() == 0) {
            loggerWarn.warn("No valid books of author:" + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
        }
        author.setBooks(books);
        authorService.create(author);
    }

    private List<String> checkBooks(Author author) {
        loggerInfo.info("Checking books of author:" + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
        List<String> data = author.getBooks();
        List<String[]> books = CSVParser.readAllBooks();
        List<String> booksIds = CSVParser.getIds(books);
        for (String[] book : books) {
            booksIds.add(book[0]);
        }
        for (String id : data) {
            if (!booksIds.contains(id)) {
                loggerWarn.warn("Book does not exist: " + "id=" + id);
                data.remove(id);
            } else {
                Book book = bookService.findById(id);
                List<String> authorList = book.getAuthors();
                if (!authorList.contains(book.getId())) {
                    authorList.add(book.getId());
                    author.setBooks(authorList);
                    bookService.update(book);
                }
            }
        }
        return data;
    }

    public void updateBook(Book book) {
        bookService.update(book);
    }

    public void updateAuthor(Author author) {
        authorService.update(author);
    }

    public void deleteBook(String id) {
        bookService.delete(id);
    }

    public void deleteAuthor(String id) {
        authorService.delete(id);
    }

    public List<Book> readAllBooks() {
        return bookService.readAll();
    }

    public List<Author> readAllAuthors() {
        return authorService.readAll();
    }

    public Book getBookById(String id) {
        return bookService.findById(id);
    }

    public Author getAuthorById(String id) {
        return authorService.findById(id);
    }

}
