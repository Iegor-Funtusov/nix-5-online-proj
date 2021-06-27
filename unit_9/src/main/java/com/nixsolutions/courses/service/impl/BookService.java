package com.nixsolutions.courses.service.impl;

import com.nixsolutions.courses.data.Author;
import com.nixsolutions.courses.data.Book;
import com.nixsolutions.courses.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BookService implements CrudService<Book> {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");


    @Override
    public void create(Book book) {

    }

    @Override
    public void update() {

    }

    @Override
    public Book findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Book> readAll() {
        return null;
    }

    public List<Author> readAllAuthors(String bookId) {
        return null;
    }
}
