package com.nixsolutions.courses.service.impl;

import com.nixsolutions.courses.data.Author;
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
    private static final String IDS_SEPARATOR = ", ";

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public void init() {
        try (CSVWriter authorsWriter = new CSVWriter(new FileWriter(FilePaths.AUTHORS.getPath()))) {
            loggerInfo.info("Creating file for authors");
            authorsWriter.writeNext(AUTHORS_HEADER);
            loggerInfo.info("File for authors created");
        } catch (IOException e) {
            loggerError.error("Error while init file for authors");
            e.printStackTrace();
        }
    }

    @Override
    public void create(Author author) {
        try (CSVWriter booksWriter = new CSVWriter(new FileWriter(FilePaths.AUTHORS.getPath(), true))) {
            loggerInfo.info("Creating author: " + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
            List<String[]> data = new ArrayList<>();
            StringBuilder books = new StringBuilder();
            for (String id : author.getBooks()) {
                books.append(id).append(IDS_SEPARATOR);
            }
            String[] line = {author.getId(), author.getName(), author.getSurname(), String.valueOf(books), String.valueOf(author.isVisible())};
            data.add(line);
            booksWriter.writeAll(data);
            loggerInfo.info("Author created: " + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
        } catch (IOException e) {
            loggerError.error("Error while creating author: " + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Author author) {
        loggerInfo.info("Updating author: " + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
        List<String[]> data = CSVParser.readAllAuthors();
        int index = -1;
        for (String[] element : data) {
            if (element[0].equals(author.getId())) index = data.indexOf(element);
        }
        StringBuilder books = new StringBuilder();
        for (String id : author.getBooks()) {
            books.append(id).append(IDS_SEPARATOR);
        }
        String[] line = {author.getId(), author.getName(), author.getSurname(), String.valueOf(books), String.valueOf(author.isVisible())};
        data.set(index, line);
        try (CSVWriter writer = new CSVWriter(new FileWriter(FilePaths.AUTHORS.getPath()))) {
            writer.writeAll(data);
            loggerInfo.info("Updated author: " + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
        } catch (IOException e) {
            loggerInfo.info("Error while updating author: " + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
            e.printStackTrace();
        }
    }

    @Override
    public Author findById(String id) {
        loggerInfo.info("Reading author by id:" + id);
        return CSVParser.readAuthor(id);
    }

    @Override
    public void delete(String id) {
        Author author = findById(id);
        loggerWarn.warn("Deleting author: " + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
        author.setVisible(false);
        update(author);
        loggerWarn.warn("Author deleted: " + "(id=" + author.getId() + ",name=" + author.getName() + ",surname=" + author.getSurname() + ")");
    }

    @Override
    public List<Author> readAll() {
        try {
            loggerInfo.info("Reading all authors");
            return CSVParser.parseAllAuthors();
        } catch (IOException e) {
            loggerError.error("Error while reading all authors");
            e.printStackTrace();
        }
        return null;
    }
}
