package com.nixsolutions.courses.service.impl;

import com.nixsolutions.courses.data.Author;
import com.nixsolutions.courses.data.Book;
import com.nixsolutions.courses.service.CrudService;
import com.nixsolutions.courses.util.CSVParser;
import com.nixsolutions.courses.util.FilePaths;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BookService implements CrudService<Book> {

    private static final String[] BOOKS_HEADER = {"ID", "TITLE", "AUTHORS' IDS", "VISIBLE"};

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public void init() {
        try (CSVWriter booksWriter = new CSVWriter(new FileWriter(FilePaths.BOOKS.getPath()))) {
            loggerInfo.info("Creating file for books");
            booksWriter.writeNext(BOOKS_HEADER);
            loggerInfo.info("File for books created");
        } catch (IOException e) {
            loggerError.error("Error while init file for books");
            e.printStackTrace();
        }
    }


    @Override
    public void create(Book book) {
        try (CSVWriter booksWriter = new CSVWriter(new FileWriter(FilePaths.BOOKS.getPath(), true))) {
            loggerInfo.info("Creating book: " + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
            List<String[]> data = new ArrayList<>();
            StringBuilder authors = new StringBuilder();
            for (String id : book.getAuthors()) {
                authors.append(id);
            }
            String[] line = {book.getId(), book.getTitle(), String.valueOf(authors), String.valueOf(book.isVisible())};
            data.add(line);
            booksWriter.writeAll(data);
            loggerInfo.info("Book created: " + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
        } catch (IOException e) {
            loggerError.error("Error while creating book: " + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        loggerInfo.info("Updating book: " + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
        List<String[]> data = CSVParser.readAllBooks();
        int index = -1;
        for (String[] element : data) {
            if (element[0].equals(book.getId())) index = data.indexOf(element);
        }
        StringBuilder authors = new StringBuilder();
        for (String id : book.getAuthors()) {
            authors.append(id);
        }
        String[] line = {book.getId(), book.getTitle(), String.valueOf(authors), String.valueOf(book.isVisible())};
        data.set(index, line);
        try (CSVWriter writer = new CSVWriter(new FileWriter(FilePaths.BOOKS.getPath()))) {
            writer.writeAll(data);
            loggerInfo.info("Updated book: " + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
        } catch (IOException e) {
            loggerError.error("Error while updating book: " + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
            e.printStackTrace();
        }
    }

    @Override
    public Book findById(String id) throws NoSuchElementException {
        loggerInfo.info("Reading book by id:" + id);
        return CSVParser.readBook(id);
    }

    @Override
    public void delete(String id) {
        Book book = findById(id);
        loggerWarn.warn("Deleting book: " + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
        book.setVisible(false);
        update(book);
        loggerWarn.warn("Book deleted: " + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
    }

    @Override
    public List<Book> readAll() {
        try {
            loggerInfo.info("Reading all books");
            return CSVParser.parseAllBooks();
        } catch (IOException e) {
            loggerError.error("Error while reading all books");
            e.printStackTrace();
        }
        return null;
    }

//    public List<Author> readAllAuthors(String bookId) {
//        Book book = findById(bookId);
//        loggerInfo.info("Reading all authors of book:");
//        return null;
//    }
}
