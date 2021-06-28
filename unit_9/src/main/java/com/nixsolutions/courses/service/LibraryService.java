package com.nixsolutions.courses.service;

import com.nixsolutions.courses.data.Author;
import com.nixsolutions.courses.data.Book;
import com.nixsolutions.courses.service.impl.AuthorService;
import com.nixsolutions.courses.service.impl.BookService;
import com.nixsolutions.courses.util.FilePaths;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        List<String> authors = checkAuthors(book.getAuthors());
        if (authors.size() == 0) {
            loggerError.error("No valid authors of book:" + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
            throw new RuntimeException("No valid authors of book" + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
        }
        book.setAuthors(authors);
        bookService.create(book);
    }

    public void createAuthor(Author author) {
        List<String> books = checkBooks(author.getBooks());
        if (books.size() == 0) {
            loggerWarn.warn("No valid books of author:" + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
        }
        author.setBooks(books);
        authorService.create(author);
    }

    private List<String> checkAuthors(List<String> data) {
        try (CSVReader reader = new CSVReader(new FileReader(FilePaths.AUTHORS.getPath()))) {
            List<String[]> authors = reader.readAll();
            List<String> authorsIds = new ArrayList<>();
            for (String[] author : authors) {
                authorsIds.add(author[0]);
            }
            for (String id : data) {
                if (!authorsIds.contains(id)) {
                    loggerWarn.warn("Author does not exist: " + "id=" + id);
                    data.remove(id);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return data;
    }

    private List<String> checkBooks(List<String> data) {
        try (CSVReader reader = new CSVReader(new FileReader(FilePaths.BOOKS.getPath()))) {
            List<String[]> books = reader.readAll();
            List<String> booksIds = new ArrayList<>();
            for (String[] book : books) {
                booksIds.add(book[0]);
            }
            for (String id : data) {
                if (!booksIds.contains(id)) {
                    loggerWarn.warn("Book does not exist: " + "id=" + id);
                    data.remove(id);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
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

//    private boolean alreadyExist(Book book) throws FileNotFoundException {
//        try (CSVReader reader = new CSVReader(new FileReader(FilePaths.BOOKS.getPath()))) {
//            List<String[]> books = reader.readAll();
//            for (String[] fields : books) {
//                if (fields[1].equals(book.getTitle()) && CSVParser.parseIds(fields[2]).equals(book.getAuthors()))
//                    return true;
//            }
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

//    private boolean alreadyExist(Author author) {
//        try (CSVReader reader = new CSVReader(new FileReader(FilePaths.AUTHORS.getPath()))) {
//            List<String[]> authors = reader.readAll();
//            for (String[] fields : authors) {
//                if (fields[1].equals(author.getName()) && fields[2].equals(author.getSurname()) && CSVParser.parseIds(fields[3]).equals(author.getBooks()))
//                    return true;
//            }
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public Book getBookById(String id) {
        return bookService.findById(id);
    }

    public Author getAuthorById(String id) {
        return authorService.findById(id);
    }

}
