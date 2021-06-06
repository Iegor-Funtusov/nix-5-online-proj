package ua.com.alevel.services;

import ua.com.alevel.data_classes.Author;
import ua.com.alevel.data_classes.Book;
import ua.com.alevel.lib.CrudService;
import ua.com.alevel.lib.CrudServiceFactory;

import java.util.Collection;

public class AuthorService {

    private final CrudService<Author> bookCrudService = CrudServiceFactory.getInstance().getCrudService();

    public void create(Author author){
        bookCrudService.create(author);
    }

    public void update(Author author){
        bookCrudService.update(author);
    }

    public void delete(Author author){
        bookCrudService.delete(author.getId());
    }

    public Author read(String id){
       return bookCrudService.read(id);
    }

    public Collection<Author> read(){
        return bookCrudService.read();
    }

    public void addBook(Author author, Book book){
        author.getBooks().add(book);
    }

    public void removeBook(Author author, Book book){
        author.getBooks().remove(book);
    }

}
