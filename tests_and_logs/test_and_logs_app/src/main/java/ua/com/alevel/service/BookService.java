package ua.com.alevel.service;

import ua.com.alevel.entity.Book;
import ua.com.alevel.lib.CrudService;
import ua.com.alevel.lib.CrudServiceFactory;
import java.util.Collection;

public class BookService {

    private final CrudService<Book> bookCrudService = CrudServiceFactory.getInstance().getCrudService();

    public void create(Book book){
        bookCrudService.create(book);
    }

    public void update(Book book){
        bookCrudService.update(book);
    }

    public void delete(Book book){
        bookCrudService.delete(book.getId());
    }

    public Book read(String id){
        return bookCrudService.read(id);
    }

    public Collection<Book> read(){
        return bookCrudService.read();
    }
}
