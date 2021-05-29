package ua.com.alevel.services;

import ua.com.alevel.entities.Book;
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

    public void read(Book book){
        bookCrudService.read(book.getId());
    }

    public Collection<Book> read(){
        return bookCrudService.read();
    }

}
