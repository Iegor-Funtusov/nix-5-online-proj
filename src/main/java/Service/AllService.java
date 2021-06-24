package Service;

import dao.AllDao;
import data.Author;
import data.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class AllService {

    AllDao allDao = new AllDao();

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");


    public void initFiles(){
        loggerInfo.info("Start init txt files");
        allDao.initFiles();
        loggerInfo.info("End of init txt files");
    }



    public void createAuthor(Author author){
        loggerInfo.info("Start create author " + author.getFirstName() +" "+ author.getLastName());
        allDao.createAuthor(author);
        loggerInfo.info("End create author");
    }
    public List<Author> readAllAuthor(){
        loggerInfo.info("Read all author");
        return allDao.reedAllA();
    }
    public Author findAuthorById(String id){
        loggerInfo.info("Read author by id : " + id);
        return allDao.findAuthorById(id);
    }
    public void updateAuthor(Author author){
        loggerWarn.info("Start updating author : "+author.getFirstName() +" "+ author.getLastName());
        allDao.updateAuthor(author);
        loggerWarn.info("End updating author");
    }
    public void deleteAuthor(String id){
        loggerWarn.info("Start deleting author by id : " + id);
        allDao.deleteAuthor(id);
        loggerWarn.info("End deleting author by id ");
    }

    public List<Author> findAutByBook(String id){
        loggerInfo.info("Find authors by book with id : " + id);
        return allDao.findAutByBook(id);
    }




    public void createBook(Book book, List<String> authors){
        loggerInfo.info("Start create book  " + book.getTitle() );
        allDao.createBook(book,authors);
        loggerInfo.info("End create book  " );
    }
    public List<Book> readAllBook(){
        loggerInfo.info("Read all book");
        return allDao.reedAllB();
    }
    public Book findBookById(String id){
        loggerInfo.info("Read book by id : " + id);
        return allDao.findBookById(id);
    }
    public void updateBook(Book book){
        loggerWarn.info("Start updating book : "+ book.getTitle());
        allDao.updateBook(book);
        loggerWarn.info("End updating book ");
    }
    public void deleteBook(String id){
        loggerWarn.info("Start deleting book by id : " + id);
        allDao.deleteBook(id);
        loggerWarn.info("End deleting book by id ");
    }

    public List<Book> findBookByAut(String id){
        loggerInfo.info("Find books by author with id : " + id);
        return allDao.findBookByAut(id);
    }

}
