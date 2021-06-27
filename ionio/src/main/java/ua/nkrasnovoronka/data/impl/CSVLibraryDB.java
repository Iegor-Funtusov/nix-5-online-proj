package ua.nkrasnovoronka.data.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nkrasnovoronka.data.LibraryDB;
import ua.nkrasnovoronka.model.AbstractEntity;
import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.util.Util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CSVLibraryDB implements LibraryDB {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public static final String CSV_DIRECTORY = "src/main/resources/csvdb";
    public static final String AUTHOR_CSV = "src/main/resources/csvdb/author.csv";
    public static final String BOOK_CSV = "src/main/resources/csvdb/book.csv";

    private final List<String[]> authorHeader = Collections.singletonList(new String[]{"id", "firstName", "lastName", "booksId", "visible"});
    private final List<String[]> bookHeader = Collections.singletonList(new String[]{"id", "title", "genre", "rating", "authorsId", "visible"});


    private long authorIdCounter = 1;
    private long bookIdCounter = 1;

    private static CSVLibraryDB instance;

    private CSVLibraryDB() {
        initFiles();
    }


    private void initFiles() {
        try {
            loggerInfo.info("Initialize csv files");
           Files.createDirectories(Path.of(CSV_DIRECTORY));
            writeHeaders();
        } catch (IOException e) {
            e.printStackTrace();
            loggerError.error("Initialization error");
        }
    }

    private void writeHeaders() {
        loggerInfo.info("Writing headers to csv files");
        try (CSVWriter authorWriter = new CSVWriter(new FileWriter(AUTHOR_CSV));
             CSVWriter bookWriter = new CSVWriter(new FileWriter(BOOK_CSV))) {
            authorWriter.writeAll(authorHeader);
            bookWriter.writeAll(bookHeader);
            loggerInfo.info("headers has been written to csv files");
        } catch (IOException e) {
            e.printStackTrace();
            loggerError.error("Cannot write header to files");
        }
    }

    public static CSVLibraryDB getInstance() {
        if (instance != null) {
            return instance;
        }
        return new CSVLibraryDB();
    }

    @Override
    public void createAuthor(Author author) {
        try (CSVWriter authorWriter = new CSVWriter(new FileWriter(AUTHOR_CSV, true))) {
            authorWriter.writeNext(Util.authorToStringArray(author, authorIdCounter++));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createBook(Book book) {
        try (CSVWriter bookWriter = new CSVWriter(new FileWriter(BOOK_CSV, true))) {
            if(!book.getBooksAuthors().isEmpty()){
                book.getBooksAuthors().forEach(aLong -> addBookToAuthor(aLong, bookIdCounter));
            }
            bookWriter.writeNext(Util.bookToStringArray(book, bookIdCounter++));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        loggerInfo.info("Getting all authors");
        List<Author> allAuthors = new ArrayList<>();
        try (CSVReader authorReader = new CSVReader(new FileReader(AUTHOR_CSV))) {
            allAuthors = authorReader.readAll().stream()
                    .skip(1)
                    .map(Util::authorFromStringArray)
                    .collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            loggerError.error("Cannot get all authors from file");
            e.printStackTrace();
        }
        return allAuthors;
    }

    @Override
    public List<Book> getAllBooks() {
        loggerInfo.info("Getting all books");
        List<Book> allBooks = new ArrayList<>();
        try (CSVReader bookReader = new CSVReader(new FileReader(BOOK_CSV))) {
            allBooks = bookReader.readAll().stream()
                    .skip(1)
                    .map(Util::bookFromString)
                    .collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            loggerError.error("Cannot get all books from file");
            e.printStackTrace();
        }
        return allBooks;
    }

    @Override
    public Author getAuthorById(Long authorId) {
        List<Author> allAuthors = getAllAuthors();
        if (isValidId(authorId, allAuthors)) {
            loggerError.error("Author with id {} doesn`t exists", authorId);
            return null;
        }
        loggerInfo.info("Getting author with id {}", authorId);
        return allAuthors.stream()
                .filter(author -> author.getId().equals(authorId) && author.isVisible())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book getBookById(Long bookId) {
        List<Book> allBooks = getAllBooks();
        if (isValidId(bookId, allBooks)) {
            loggerError.error("Book with id {} doesn`t exists", bookId);
            return null;
        }
        loggerInfo.info("Getting book with id {}", bookId);
        return allBooks.stream()
                .filter(book -> book.getId().equals(bookId) && book.isVisible())
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteBookById(Long id) {
        List<Book> allBooks = getAllBooks();
        if (isValidId(id, allBooks)) {
            loggerInfo.error("Book with id {} doesn`t exists", id);
            return;
        }
        List<String[]> collect = new ArrayList<>();
        for (Book b : allBooks) {
            if (b.getId().equals(id)) {
                b.setVisible(false);
            }
            collect.add(Util.bookToStringArray(b, b.getId()));
        }
        try (CSVWriter bookWriter = new CSVWriter(new FileWriter(BOOK_CSV))) {
            bookWriter.writeAll(bookHeader);
            bookWriter.writeAll(collect);
        } catch (IOException e) {
            loggerError.error("Cannot remove book with id {}", id);
            e.printStackTrace();
        }
        loggerInfo.info("Removing book with id {}", id);

    }

    private boolean isValidId(Long id, List<? extends AbstractEntity> list) {
        return list.size() < id - 1 || list.isEmpty() || !(list.get(id.intValue() - 1).isVisible());
    }

    @Override
    public void deleteAuthorById(Long id) {
        List<Author> allAuthors = getAllAuthors();
        if (isValidId(id, allAuthors)) {
            loggerInfo.error("Author with id {} doesn`t exists", id);
            return;
        }
        List<String[]> collect = new ArrayList<>();
        for (Author a : allAuthors) {
            if (a.getId().equals(id)) {
                a.setVisible(false);
            }
            collect.add(Util.authorToStringArray(a, a.getId()));
        }
        try (CSVWriter authorWriter = new CSVWriter(new FileWriter(AUTHOR_CSV))) {
            authorWriter.writeAll(authorHeader);
            authorWriter.writeAll(collect);
        } catch (IOException e) {
            loggerError.error("Cannot remove author with id {}", id);
            e.printStackTrace();
        }
        loggerInfo.info("Removing author with id {}", id);
    }

    @Override
    public void updateAuthor(Long authorId, Author author) {
        List<Author> authors = getAllAuthors();
        List<String[]> collect = new ArrayList<>();

        for (Author a : authors) {
            if (a.getId().equals(author.getId())) {
                a.setFirstName(author.getFirstName());
                a.setLastName(author.getLastName());
                a.setBooksList(author.getBooksList());
                a.setVisible(author.isVisible());
            }
            collect.add(Util.authorToStringArray(a, a.getId()));
        }
        try (CSVWriter authorWriter = new CSVWriter(new FileWriter(AUTHOR_CSV))) {
            authorWriter.writeAll(authorHeader);
            authorWriter.writeAll(collect);
        } catch (IOException e) {
            loggerError.error("Cannot update author with id {}", authorId);
            e.printStackTrace();
        }

        loggerInfo.info("Author with id {} was updated", author);
    }

    @Override
    public void updateBook(Long bookId, Book book) {
        List<Book> books = getAllBooks();
        List<String[]> collect = new ArrayList<>();

        for (Book b : books) {
            if (b.getId().equals(bookId)) {
                b.setBookTitle(book.getBookTitle());
                b.setGenre(book.getGenre());
                b.setBookRating(book.getBookRating());
                b.setBooksAuthors(book.getBooksAuthors());
                b.setVisible(book.isVisible());
            }
            collect.add(Util.bookToStringArray(b, b.getId()));
        }
        try (CSVWriter bookWriter = new CSVWriter(new FileWriter(BOOK_CSV))) {
            bookWriter.writeAll(bookHeader);
            bookWriter.writeAll(collect);
        } catch (IOException e) {
            loggerError.error("Cannot remove book with id {}", bookId);
            e.printStackTrace();
        }

        loggerInfo.info("Book with id {} was updated", bookId);
    }

    @Override
    public Collection<Book> getAllAuthorBooks(Long authorId) {
        Author authorById = getAuthorById(authorId);
        loggerInfo.info("Getting all books author with id {}", authorById);
        return authorById.getBooksList()
                .stream()
                .map(this::getBookById)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Author> getAllBooksAuthors(Long bookId) {
        Book bookById = getBookById(bookId);
        loggerInfo.info("Getting all authors with book id {}", bookById);
        return bookById.getBooksAuthors()
                .stream()
                .map(this::getAuthorById)
                .collect(Collectors.toList());
    }

    @Override
    public void addBookToAuthor(Long authorId, Long bookId) {
        Author authorById = getAuthorById(authorId);
        authorById.addBookToAuthor(bookId);
        updateAuthor(authorId, authorById);
    }

    @Override
    public void removeBookFromAuthor(Long authorId, Long bookId) {
        Author authorById = getAuthorById(authorId);
        authorById.setBooksList(authorById.getBooksList()
                .stream().filter(aLong -> !aLong.equals(bookId)).collect(Collectors.toSet()));
        updateAuthor(authorId, authorById);

    }

    @Override
    public void addAuthorToBook(Long bookId, Long authorId) {
        Book bookById = getBookById(bookId);
        bookById.addAuthorToBook(authorId);
        updateBook(bookId, bookById);
    }

    @Override
    public void removeAuthorFromBook(Long bookId, Long authorId) {
        Book bookById = getBookById(bookId);
        bookById.setBooksAuthors(bookById.getBooksAuthors().stream().filter(aLong -> !aLong.equals(authorId)).collect(Collectors.toSet()));
        updateBook(bookId, bookById);
    }
}
