package ua.com.ionio.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.ionio.dao.DaoAuthor;
import ua.com.ionio.dao.DaoBook;
import ua.com.ionio.entity.Author;
import ua.com.ionio.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class AccessService {
     private DaoBook daoBooK;
     private DaoAuthor daoAuthor;

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

     public AccessService(){
         daoAuthor = new DaoAuthor();
         daoBooK = new DaoBook();
     }

     public void createAuthor(Author author){
         if(author != null){
         loggerInfo.info("Start create Author");
         if(beforeCreateAuthor(author)){
             daoAuthor.create(author);
             loggerInfo.info("End create author");
         }
            else{
             loggerError.error("Author already exist - can not create");
             throw new IllegalArgumentException("This Author already exist");
            }
         }
         else {
             loggerError.error("Author is null - can not create");
             throw new NullPointerException("Author is null");
         }
     }

     public void updateAuthor(Author author){
         loggerInfo.info("Start update Author");
         if(author!= null){
             daoAuthor.update(author);
             loggerInfo.info("End update author: " + author.getId());
         }
         else{
             loggerError.error("Author is null - can not update");
             throw new NullPointerException("Author is null");
         }
     }

     public void deleteAuthor(String id){
         if(id != null) {
             loggerWarn.warn("Start delete Author" + id);
             if (daoAuthor.findAuthorById(id) != null) {
                 daoAuthor.delete(id);
                 loggerWarn.warn("End delete Author" + id);
             } else {
                 loggerError.error("Author doesn`t exist - can not delete");
                 throw new IllegalArgumentException("Author doesn`t exist");
             }
         }
         else{
             loggerError.error("Author is null - can not delete");
             throw new NullPointerException("Author is null");
         }
     }

     public Author getAuthorById(String id) throws IllegalArgumentException{
               loggerInfo.info("Start get Author: " + id);
               Author author = daoAuthor.findAuthorById(id);
               loggerInfo.info("End get Author:" + id);
               return author;
     }

     public List<Author> getAllAuthors(){
         return daoAuthor.findAllAuthors();
     }

    public void createBook(Book book) {
        if (book != null) {
            loggerInfo.info("Start create Book");
            daoBooK.create(book);
            loggerInfo.info("End create author");
        } else {
            loggerError.error("Book is null - can not create");
            throw new NullPointerException("Book is null");
        }
    }

    public void updateBook(Book book){
        loggerInfo.info("Start update Book");
        if(book!= null){
            daoBooK.update(book);
            loggerInfo.info("End update author: " + book.getId());
        }
        else{
            loggerError.error("Book is null - can not update");
            throw new NullPointerException("Book is null");
        }
    }

    public void deleteBook(String id){
        daoBooK.delete(id);
        if(id != null) {
            loggerWarn.warn("Start delete Book: " + id);
            if (daoBooK.findBookById(id) != null) {
                daoBooK.delete(id);
                loggerWarn.warn("End delete Book: " + id);
            } else {
                loggerError.error("Book doesn`t exist - can not delete");
                throw new IllegalArgumentException("Book doesn`t exist");
            }
        }
        else{
            loggerError.error("Book is null - can not delete");
            throw new NullPointerException("Book is null");
        }
    }

    public Book getBookById(String id){

        loggerInfo.info("Start get Book: " + id);
        Book book = daoBooK.findBookById(id);
        loggerInfo.info("End get Book:" + id);
        return book;
    }

    public List<Book> getAllBooks(){
        return daoBooK.findAllBooks();
    }

    public List<Author> getAuthorbyBook(String book_id){
         if(daoBooK.findBookById(book_id)!= null && daoBooK.findBookById(book_id).isIsvisableBook()) {
             Book book = daoBooK.findBookById(book_id);
                 if (book.getListAuthors() != null) {
                     List<Author> authors = new ArrayList<Author>();
                     for (int i = 0; i < book.getListAuthors().size(); i++) {
                         Author author = daoAuthor.findAuthorById(book.getListAuthors().get(i));
                         if(author.isIsvisableAuthor()) {
                             authors.add(author);
                         }
                     }
                     return authors;
                 }
//                 else {
//                     return null;
//                 }
         }
             throw new IllegalArgumentException("Book doesn`t exist");
    }

    public List<Book> getBookbyAuthor(String author_id){
        if(daoAuthor.findAuthorById(author_id)!= null && daoAuthor.findAuthorById(author_id).isIsvisableAuthor()){
            Author author = daoAuthor.findAuthorById(author_id);
            if(author.getListBooks()!=null) {
                List<Book> books = new ArrayList<Book>();
                for (int i = 0; i < author.getListBooks().size(); i++){
                    Book book = daoBooK.findBookById(author.getListBooks().get(i));
                    if(book.isIsvisableBook()) {
                        books.add(book);
                    }
                }
                return books;
                }
            }
//            else{
//                return null;
//            }
        throw new IllegalArgumentException("Book doesn`t exist");
    }

    private boolean beforeCreateAuthor(Author author){
        List<Author> authors = daoAuthor.findAllAuthors();
        if(authors.size() != 0){
        for (Author a: authors) {
            if(a.getFirstname().equals(author.getFirstname())
            && a.getLastname().equals(author.getLastname())){
                return false;
                }
            }
        }
         return true;
    }

    public boolean ifExistBook(String id){
        boolean b = daoBooK.findBookById(id) == null;
        return b;
    }

    public boolean ifExistAuthor(String id){
        boolean b = daoAuthor.findAuthorById(id) == null;
        return b;
    }
}
