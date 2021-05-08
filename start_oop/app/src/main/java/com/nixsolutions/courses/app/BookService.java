package com.nixsolutions.courses.app;

import com.nixsolutions.courses.lib.CrudProcess;
import com.nixsolutions.courses.lib.CrudProcessFactory;

import java.util.Collection;

public class BookService {

    CrudProcess<Book> bookCrudProcess = CrudProcessFactory.getInstance().getCrudProcess();

    public void create(Book book) {
        bookCrudProcess.create(book);
    }

    public void update(Book book) {
        bookCrudProcess.update(book);
    }

    public void delete(String id) {
        bookCrudProcess.delete(id);
    }

    public Book read(String id) {
        return bookCrudProcess.read(id);
    }

    public Collection<Book> readAll() {
        return bookCrudProcess.readAll();
    }
}
