package Services;

import CrudCSV.AuthorCrudProcess;
import CrudCSV.CrudProcess;
import DataClasses.Author;
import DataClasses.Book;
import DataClassesHelpers.AuthorHelper;
import Validation.Validator;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;

public class AuthorService{
    private CrudProcess<Author> authorCrudProcess;

    public AuthorService(String pathToCSV){
        authorCrudProcess = new AuthorCrudProcess<Author>(pathToCSV);
    }

    public void createAuthor(Author authorToAdd) throws IOException, CsvException {
        Validator.isNotNullEntity(authorToAdd);
        authorCrudProcess.create(authorToAdd);
    }


    public void updateAuthor(Author authorToUpd) throws IOException, CsvException {
        Validator.isNotNullEntity(authorToUpd);
        authorCrudProcess.update(authorToUpd);
    }


    public void deleteAuthor(Author authorToDel) throws IOException, CsvException {
        Validator.isNotNullEntity(authorToDel);
        authorCrudProcess.delete(authorToDel.getId());
    }


    public List<Author> read() throws IOException, CsvException {
        List<Author> allAuthors = authorCrudProcess.read();
        return (List<Author>) Decorators.FormatList.formatToOutput(allAuthors);
    }


    public Author read(String id) throws IOException, CsvException {
        return authorCrudProcess.read(id);
    }


    public Author addBookToAuthor(Author author, Book bookToAdd){
        return AuthorHelper.addBookToAuthor(author, bookToAdd);
    }

    //Аналогично с удалением

    public Author findAuthorByName(String fullName) throws IOException, CsvException {
        List<Author> allAuthors = read();
        for (Author item : allAuthors) {
            if(item.getFullName().equals(fullName)){
                return item;
            }
        }
        throw new RuntimeException("Such author does not exists");
    }
}








