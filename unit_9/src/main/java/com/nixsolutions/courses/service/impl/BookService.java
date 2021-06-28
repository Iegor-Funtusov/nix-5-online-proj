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
            e.printStackTrace();
            loggerError.error("Error while init file for books");
        }
    }


    @Override
    public void create(Book book) {
        try (CSVWriter booksWriter = new CSVWriter(new FileWriter(FilePaths.BOOKS.getPath(), true))) {
            loggerInfo.info("Creating book item:" + "(id=" + book.getId() + ",title=" + book.getTitle() + ")");
            List<String[]> data = new ArrayList<>();
            StringBuilder authors = new StringBuilder();
            for (String id : book.getAuthors()) {
                authors.append(id);
            }
            String[] line = {book.getId(), book.getTitle(), String.valueOf(authors), String.valueOf(book.isVisible())};
            data.add(line);
            booksWriter.writeAll(data);
            loggerInfo.info("File for books created");
        } catch (IOException e) {
            e.printStackTrace();
            loggerError.error("Error while init file for books");
        }
    }

    @Override
    public void update(Book book) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book findById(String id) throws NoSuchElementException {
        return CSVParser.readBook(id);
    }

    @Override
    public void delete(String id) {
        Book book = findById(id);
        book.setVisible(false);
        update(book);
    }

    @Override
    public List<Book> readAll() {
        try {
            return CSVParser.parseAllBooks();
        } catch (IOException e) {
            loggerError.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Author> readAllAuthors(String bookId) {
        return null;
    }
}
