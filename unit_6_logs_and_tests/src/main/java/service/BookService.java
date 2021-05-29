package service;

import dao.BookDAO;
import domain.Author;
import domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class BookService {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    private BookDAO bookDAO = new BookDAO();
    private AuthorService authorService = new AuthorService();


    public void createBook(Author author, Book book) {
        loggerInfo.info("Начало создания книги: " + "\"" + book.getTitle() + "\"");
        if (!book.getTitle().isEmpty()) {
            boolean exist = bookDAO.findByTitle(author, book.getTitle());
            if (exist) {
                loggerError.error("Книга " + "\"" + book.getTitle() + "\"" + " уже есть!");
                System.out.println("Книга " + "\"" + book.getTitle() + "\"" + " уже есть!");
            } else {
                bookDAO.createBook(author, book);
                System.out.println("Книга " + "\"" + book.getTitle() + "\"" + " создана");
                loggerInfo.info("Книга " + "\"" + book.getTitle() + "\"" + " создана");
            }
        } else {
            loggerError.error("Название книги не может быть пустым!");
            System.out.println("Название книги не может быть пустым!");
        }
    }

    public void deleteBook(Author author, String bookId) {
        loggerWarn.warn("Начало удаления книги по bookId: " + bookId);
        bookDAO.deleteBook(author, bookId);
        loggerWarn.warn("Книга удалена");
    }

    public void updateBook(Author author, Book book) {
        loggerWarn.warn("Начало обновления книги: " + "\"" + book.getTitle() + "\"");
        bookDAO.updateBook(author, book);
        loggerWarn.warn("Книга " + "\"" + book.getTitle() + "\"" + " обновлена");
    }

    public Collection<Book> findBooks(Collection<Author> list) {
        Collection<Book> books = null;
        for (Author author : list) {
            if (author != null) {
                if ((int) Arrays.stream(author.getBooks()).filter(Objects::nonNull).count() != 0) {
                    System.out.println("Список книг автора " + author.getFirstName() + " "
                        + author.getLastName() + ":");
                    books = bookDAO.findBooks(author);
                //    System.out.println(books);
                    loggerInfo.info("Вывод списка книг: ");
                    loggerInfo.info(String.valueOf(books));
                }
                return books;
            }
        }
        return null;
    }

    public Collection<Book> findBooks(Collection<Author> list, String lastName) {
        Author author = authorService.checkAuthor(list, lastName);
        if (author != null) {
            if ((int) Arrays.stream(author.getBooks()).filter(Objects::nonNull).count() != 0) {
                System.out.println("Список книг автора " + author.getFirstName() + " "
                    + author.getLastName() + ":");
                Collection<Book> books = bookDAO.findBooks(author);
                loggerInfo.info("Вывод списка книг: ");
                loggerInfo.info(String.valueOf(books));
                System.out.println(books);
                return books;
            } else {
                    System.out.println("У автора нет книг! " + author.getFirstName() + " " +
                        author.getLastName());
                    return null;
            }
        } else {
                System.out.println("Автор не существует! " + " " + lastName);
                return null;
        }
    }

   /* public domain.Book findBooks(domain.Author author, domain.Book book) {
        if (book != null) {
            System.out.println(bookDAO.findBooks(author, book.getBookId()));
            loggerInfo.info("Вывод книг по bookId: " + book.getBookId());
            return bookDAO.findBooks(author, book.getBookId());
        }
        else errorMessage();
        return null;
    }*/

    public Book bookCheck(Author bookAuthor, String title) {
        Book bookBook = null;
        for (Book book : bookAuthor.getBooks()) {
            if (book != null) {
                if (book.getTitle().equals(title)) {
                    bookBook = book;
                }
            }
        }
        return bookBook;
    }

    public void errorMessage() {
        loggerError.error("Такой книги нет!");
        System.out.println("Такой книги нет!");
    }

    public void errorCreateBookMessage() {
        loggerError.error("Такого автора нет! Преждем чем создать книгу создайте автора!");
        System.out.println("Такого автора нет! Преждем чем создать книгу создайте автора!");
    }
}
