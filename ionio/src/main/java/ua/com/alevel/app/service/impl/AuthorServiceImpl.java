package ua.com.alevel.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.app.dao.LibraryDao;
import ua.com.alevel.app.dao.impl.LibraryDaoImpl;
import ua.com.alevel.app.entity.Author;
import ua.com.alevel.app.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    LibraryDao libraryDao = new LibraryDaoImpl();

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");

    public void create(Author author){
        loggerInfo.info("Start create author " + author.getFirstName() +" "+ author.getLastName());
        libraryDao.createAuthor(author);
        loggerInfo.info("End create author");
    }

    public List<Author> read(){
        loggerInfo.info("Read all author");
        return libraryDao.readAuthor();
    }

    public Author read(String id){
        loggerInfo.info("Read author by id : " + id);
        return libraryDao.readAuthor(id);
    }

    public void update(Author author){
        loggerWarn.info("Start updating author : "+author.getFirstName() +" "+ author.getLastName());
        libraryDao.updateAuthor(author);
        loggerWarn.info("End updating author");
    }

    public void delete(String id){
        loggerWarn.info("Start deleting author by id : " + id);
        libraryDao.deleteAuthor(id);
        loggerWarn.info("End deleting author by id ");
    }

    public List<Author> readByBook(String id){
        loggerInfo.info("Find authors by book with id : " + id);
        return libraryDao.readByBookAuthor(id);
    }
}
