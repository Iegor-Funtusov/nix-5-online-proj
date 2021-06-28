package service;

import dao.BookDAO;
import domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BookService {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    private final BookDAO bookDAO = new BookDAO();

    public void createBook(Book book, List<String> authors) {
        loggerInfo.info("Начало создания книги: " + "\"" + book.getTitle() + "\"");
        if (!book.getTitle().isEmpty()) {
            bookDAO.createBook(authors, book);
            System.out.println("Книга " + "\"" + book.getTitle() + "\"" + " создана");
            loggerInfo.info("Книга " + "\"" + book.getTitle() + "\"" + " создана");
        } else {
            loggerError.error("Название книги не может быть пустым!");
            System.out.println("Название книги не может быть пустым!");
        }
    }

    public void deleteBook(Book book) {
        if (book != null && bookDAO.readBook(book.getBookId()) != null) {
            loggerWarn.warn("Начало удаления книги " + "\"" +  book.getTitle() + "\"" +
                " по bookId: " + book.getBookId());
            bookDAO.deleteBook(book.getBookId());
            loggerWarn.warn("Книга удалена: " + "\"" + book.getTitle() + "\"" + " bookId: " + book.getBookId());
        } else loggerError.error("Такой книги нет!");
    }

    public void updateBook(Book book) {
        if (book != null && bookDAO.readBook(book.getBookId()) != null) {
            loggerWarn.warn("Начало обновления книги: " + book.getTitle() + " по bookId: " + book.getBookId());
            bookDAO.updateBook(book);
            loggerWarn.warn("Книга " + "\"" + book.getTitle() + "\"" + " обновлена");
        } else loggerError.error("Такой книги нет!");
    }

    public List<Book> readBooks() {
        loggerInfo.info("Список всех книг: ");
        loggerInfo.info(bookDAO.readBooks().toString());
        return bookDAO.readBooks();
    }

    public Book findBook(String id){
        loggerInfo.info("Поиск книги по id: " + id);
        return bookDAO.readBook(id);
    }

    public List<Book> readBooksByAuthor(String id){
        loggerInfo.info("Поиск книг по автору с id: " + id);
        return bookDAO.readBooksByAuthor(id);
    }
}
