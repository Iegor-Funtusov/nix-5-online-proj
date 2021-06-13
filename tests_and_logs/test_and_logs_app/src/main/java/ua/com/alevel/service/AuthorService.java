package ua.com.alevel.service;

import ua.com.alevel.entity.Author;
import ua.com.alevel.lib.CrudService;
import ua.com.alevel.lib.CrudServiceFactory;

public class AuthorService {

    private final CrudService<Author> authorCrudService = CrudServiceFactory.getInstance().getCrudService();

    public void create(Author author){
        authorCrudService.create(author);
    }

    public void update(Author author){
        authorCrudService.update(author);
    }

    public void delete(Author author){
        authorCrudService.delete(author.getId());
    }

    public Author read(String id){
       return authorCrudService.read(id);
    }

    public Object[] read(){
        return authorCrudService.read();
    }
}
