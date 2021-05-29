package com.k4rnaj1k.Service;

import com.k4rnaj1k.DataClasses.Author;
import com.k4rnaj1k.Dao.AuthorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorService {
    private final AuthorDao authorDao = new AuthorDao();

    private static Logger loggerInfo = LoggerFactory.getLogger("info");
    private static Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static Logger loggerError = LoggerFactory.getLogger("error");


    public void create(Author author) {
        try {
            loggerWarn.warn("Trying to add author " + author.getName()+ ".");
            authorDao.createAuthor(author);
            loggerInfo.info("Added author " + author.getName()+ ".");
        } catch (Exception ex) {
            loggerError.error(ex.getMessage());
        }
    }

    public Author[] findAll() {
        loggerInfo.info("Returning a full author's list.");
        return authorDao.findAll();
    }

    public void delete(Author author) {
        try {
            loggerWarn.info("Trying to remove author " + author.getName() + ".");
            authorDao.deleteAuthor(author);
            loggerInfo.info("Removed author " + author.getName() + ".");
        } catch (Exception ex) {
            loggerError.error(ex.getMessage());
        }
    }

    public AuthorDao getDao() {
        loggerInfo.info("Returning authorService's authorDao.");
        return this.authorDao;
    }
}
