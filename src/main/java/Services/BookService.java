package Services;

import CrudCSV.BookCrudProcess;
import CrudCSV.CrudProcess;
import DataClasses.Author;
import DataClasses.Book;
import DataClassesHelpers.BookHelper;
import Validation.Validator;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;

public class BookService {
    private CrudProcess<Book> bookCrudProcess;

    public BookService(String PathToCSV){
        bookCrudProcess = new BookCrudProcess<Book>(PathToCSV);
    }

    public void createBook(Book bookToAdd) throws IOException, CsvException {
        Validator.isNotNullEntity(bookToAdd);
        bookCrudProcess.create(bookToAdd);
    }


    public void updateBook(Book bookToUpd) throws IOException, CsvException {
        Validator.isNotNullEntity(bookToUpd);
        bookCrudProcess.update(bookToUpd);
    }


    public void deleteBook(Book bookToDel) throws IOException, CsvException {
        Validator.isNotNullEntity(bookToDel);
        bookCrudProcess.delete(bookToDel.getId());
    }


    public List<Book> read() throws IOException, CsvException  {
        List<Book> allBooks = bookCrudProcess.read();
        return (List<Book>) Decorators.FormatList.formatToOutput(allBooks);
    }


    public Book read(String id) throws IOException, CsvException {
        return bookCrudProcess.read(id);
    }

    public Book addAuthorToBook(Book book, Author authorToAdd){
        return BookHelper.addAuthorToBook(book, authorToAdd);
    }

    public Book deleteAuthorFromBook(Book book, Author authorToDelete){
        return BookHelper.deleteAuthorFromBook(book, authorToDelete);
    }

    public Book findBookByName(String bookName) throws IOException, CsvException {
        List<Book> allBooks = read();
        for (Book item : allBooks) {
            if(item.getBookName().equals(bookName)){
                return item;
            }
        }
        throw new RuntimeException("Book does not exist");
    }
}
