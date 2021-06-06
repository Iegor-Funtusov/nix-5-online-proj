package ua.com.threadedcode.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.threadedcode.dao.CrudFactory;
import ua.com.threadedcode.dao.ICrudProcess;
import ua.com.threadedcode.dao.crudObject.CrudObject;
import ua.com.threadedcode.entity.Author;

import java.util.Collection;

public class AuthorService {
    //    private ICrudProcess<Author> authorCrud = CrudFactory.getInstance().getCrudProcess();
    private ICrudProcess<Author> authorCrud = CrudObject.getInstance();
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public void createAuthor(Author author) {
        loggerInfo.info("Start create author" + author.getFirstName() + " " + author.getLastName());
        authorCrud.createAuthor(author);

    }

    public void updateAuthor(Author author) {
        loggerInfo.info("Start update author " + author.getFirstName() + " " + author.getLastName());
        authorCrud.updateAuthor(author);
    }

    public void deleteAuthor(String firstname) {
        loggerWarn.warn("Start delete author " + firstname);

        try {
            authorCrud.deleteAuthor(firstname);
        }catch (NullPointerException e){
            loggerError.error(String.valueOf(e));
            throw new RuntimeException("can't find any books");
        }
    }

    public Collection getAllAuthors() {
        return authorCrud.getAllAuthors();
    }

    public Collection findBooksByAuthorsFirstName(String firstname) {
        loggerInfo.info("Try to find books by author " + firstname);
        try {
            return authorCrud.findBooksByAuthorFistName(firstname);
        } catch (NullPointerException e) {
            loggerError.error("can't find any books by author " + firstname);
            throw new RuntimeException("can't find any books");
        }
    }

    public Author getAuthorByName(String firstname) {
        loggerInfo.info("Try to find author " + firstname);
        try {
            return authorCrud.getAuthorByName(firstname);
        } catch (NullPointerException e) {
            loggerError.error("can't find any authors by name " + firstname);
            throw new RuntimeException("can't find any authors, try other author firstname");
        }
    }
}
