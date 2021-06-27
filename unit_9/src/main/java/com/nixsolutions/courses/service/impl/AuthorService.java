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

public class AuthorService implements CrudService<Author> {

    private static final String[] AUTHORS_HEADER = {"ID", "NAME", "SURNAME", "BOOKS' IDS", "VISIBLE"};

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public void init() {
        try(CSVWriter authorsWriter = new CSVWriter(new FileWriter(FilePaths.AUTHORS.getPath()))) {
            loggerInfo.info("Creating file for authors");
            authorsWriter.writeNext(AUTHORS_HEADER);
            loggerInfo.info("File for authors created");
        } catch (IOException e) {
            e.printStackTrace();
            loggerError.error("Error while init csv file for authors");
        }
    }

    @Override
    public void create(Author author) {
        try (CSVWriter booksWriter = new CSVWriter(new FileWriter(FilePaths.AUTHORS.getPath()))) {
            List<String[]> data = new ArrayList<>();
            data.add(AUTHORS_HEADER);
            loggerInfo.info("Creating author item:" + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
            if (author.getBooks().size() != 0) {
                StringBuilder books = new StringBuilder();
                for (String id : author.getBooks()) {
                    books.append(id);
                }
                String[] line = {author.getId(), author.getName(), author.getSurname(), String.valueOf(books), String.valueOf(author.isVisible())};
                data.add(line);
            } else {
                String[] line = {author.getId(), author.getName(), author.getSurname(), String.valueOf(author.isVisible())};
                data.add(line);
            }
            booksWriter.writeAll(data);
            loggerInfo.info("Author created:" + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
        } catch (IOException e) {
            e.printStackTrace();
            loggerError.error("Error while init file for books");
        }
    }

    @Override
    public void update() {

    }

    @Override
    public Author findById(String id) {
        return CSVParser.readAuthor(id);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Author> readAll() {
        return null;
    }

    public List<Book> readAllBooks(String authorId) {
        return null;
    }
}
