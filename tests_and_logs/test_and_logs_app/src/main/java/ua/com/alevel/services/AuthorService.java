package ua.com.alevel.services;

import ua.com.alevel.entities.Author;
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

    public void read(Author author){
        bookCrudService.read(author.getId());
    }

    public Collection<Author> read(){
        return bookCrudService.read();
    }

}
