package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.entity.Author;
import ua.com.alevel.lib.CrudService;
import ua.com.alevel.lib.CrudServiceFactory;

public class AuthorService {

    private final CrudService<Author> authorCrudService = CrudServiceFactory.getInstance().getCrudService();

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public void create(Author author){
        loggerInfo.info("Start creating the author: " + author.getName());
        authorCrudService.create(author);
        loggerInfo.info("The author was created");
    }

    public void update(Author author){
        loggerInfo.info("Start updating the author with id: " + author.getId());
        try{
            authorCrudService.update(author);
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
        loggerInfo.info("The author was updated.");
    }

    public void delete(Author author){
        loggerWarn.warn("Start deleting author by id: " + author.getId());
        try{
            authorCrudService.delete(author.getId());
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
        loggerWarn.warn("The author was deleted.");
    }

    public Author read(String id){
        loggerInfo.info("Start reading the author by id: " + id);
        try{
            Author author = authorCrudService.read(id);
            loggerInfo.info("The author was read");
            return author;
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
    }

    public Object[] read(){
        try{
            loggerInfo.info("Start reading all authors");
            Object[] authors = authorCrudService.read();
            loggerInfo.info("Authors were read");
            return authors;
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
    }
}
