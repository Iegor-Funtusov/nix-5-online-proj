package Controllers;

import Configs.PathConfigs;
import DataClasses.Author;
import DataClasses.Book;
import Services.AuthorService;
import Services.BookService;
import Validation.Validator;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bf;


    public MainController(){
        authorService = new AuthorService(PathConfigs.AUTHORS_FILE.getPath());
        bookService = new BookService(PathConfigs.BOOKS_FILE.getPath());
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public void createBook(Book newBook, Author author) throws IOException, CsvException {
            if(author == null){
                throw new RuntimeException("The book could not be created");
            }
            bookService.addAuthorToBook(newBook, author);
            bookService.createBook(newBook);

            authorService.addBookToAuthor(author, newBook);
            authorService.updateAuthor(author);
    }


    public Book readBook(String bookName) throws IOException, CsvException {
        if(bookName == null) {
            throw new RuntimeException("Book name is empty");
        }
       return bookService.findBookByName(bookName);
    }


    public List<Book> readAllBooks() throws IOException, CsvException {
        return bookService.read();
    }


    public void updateBookName(Book bookToUpd, String newBookName) throws IOException, CsvException {
        deleteBookFromAuthors(bookToUpd);       //Удаляю у авторов книги старое имя книги
        bookToUpd.setBookName(newBookName);
        bookService.updateBook(bookToUpd);      //Обновляю книгу
        addBooksToAuthors(bookToUpd);           //Добавляю авторам книгу с новым именем
    }


    public void updateBookAddAuthor(Book bookToUpd, Author authorToAdd) throws IOException, CsvException {
        if(authorToAdd == null){
            System.out.println("Incorrect value entered");
            return;
        }
        if(isBookContainsAuthor(bookToUpd, authorToAdd)){
            System.out.println("You can not add this author. Because book already contains such author");
            return;
        }
        bookToUpd = bookService.addAuthorToBook(bookToUpd, authorToAdd);
        bookService.updateBook(bookToUpd);
        authorToAdd = authorService.addBookToAuthor(authorToAdd, bookToUpd);
        authorService.updateAuthor(authorToAdd);
    }


    public void updateBookDeleteAuthor(Book bookToUpd, Author authorToDel) throws IOException, CsvException {
        if(authorToDel == null){
            System.out.println("Incorrect value entered");
            return;
        }
        if(!isBookContainsAuthor(bookToUpd, authorToDel)){
            System.out.println("You can not delete this author. Because book does not contain such author");
            return;
        }

        bookToUpd = bookService.deleteAuthorFromBook(bookToUpd, authorToDel);
        bookService.updateBook(bookToUpd);

        authorToDel = authorService.deleteBookFromAuthor(authorToDel, bookToUpd);
        authorService.updateAuthor(authorToDel);
    }


    public void deleteBook(Book bookToDel) throws IOException, CsvException {
        bookService.deleteBook(bookToDel);
        deleteBookFromAuthors(bookToDel);
    }




    public void createAuthor(Author newAuthor) throws IOException, CsvException {
        authorService.createAuthor(newAuthor);
    }


    public void createAuthor(Author newAuthor, Book bookToAdd) throws IOException, CsvException {
        if(bookToAdd != null){
            newAuthor = authorService.addBookToAuthor(newAuthor,bookToAdd);
            bookService.addAuthorToBook(bookToAdd, newAuthor);
            bookService.updateBook(bookToAdd);
            authorService.createAuthor(newAuthor);
            return;
        }
        throw new RuntimeException("Author will be added without books");
    }


    public Author readAuthor(String fullName) throws IOException, CsvException {
        if(fullName == null){
            throw new RuntimeException("Full name is empty");
        }
        return authorService.findAuthorByName(fullName);
    }


    public List<Author> readAllAuthors() throws IOException, CsvException {
        return authorService.read();
    }


    public void updateAuthorFirstName(Author authorToUpd, String newFirstName) throws IOException, CsvException {
        deleteAuthorFromBooks(authorToUpd);
        authorToUpd.setFirstName(newFirstName);
        authorService.updateAuthor(authorToUpd);
        addAuthorToBooks(authorToUpd);
    }

    public void updateAuthorLastName(Author authorToUpd, String newLastName) throws IOException, CsvException {
        deleteAuthorFromBooks(authorToUpd);
        authorToUpd.setLastName(newLastName);
        authorService.updateAuthor(authorToUpd);
        addAuthorToBooks(authorToUpd);
    }


    public void updateAuthorAddBook(Author authorToUpd, Book bookToAdd) throws IOException, CsvException {
        if(bookToAdd == null){
            throw new RuntimeException("Incorrect vale entered");
        }
        if(isAuthorContainsBook(authorToUpd, bookToAdd)){
            throw new RuntimeException("You can not add this book. Because such author already contains such book");
        }

        authorToUpd = authorService.addBookToAuthor(authorToUpd, bookToAdd);
        bookToAdd = bookService.addAuthorToBook(bookToAdd, authorToUpd);
        authorService.updateAuthor(authorToUpd);
        bookService.updateBook(bookToAdd);
    }


    public void updateAuthorDeleteBook(Author authorToUpd, Book bookToDel) throws IOException, CsvException {
        if(bookToDel == null){
            throw new RuntimeException("Incorrect vale entered");
        }
        if(!isAuthorContainsBook(authorToUpd, bookToDel)){
            throw new RuntimeException("You can not delete this book. Because such author does not contain such book");
        }
        authorToUpd = authorService.deleteBookFromAuthor(authorToUpd, bookToDel);
        bookToDel = bookService.deleteAuthorFromBook(bookToDel, authorToUpd);
        authorService.updateAuthor(authorToUpd);
        bookService.updateBook(bookToDel);
    }


    public void deleteAuthor(Author authorToDel) throws IOException, CsvException {
        authorService.deleteAuthor(authorToDel);        //Если автор удаляется, то книги онаписанные им остаются
    }



    public Author chooseAuthor() throws IOException, CsvException {
        try {
            List<Author> allAuthors = authorService.read();
            int index = 1;
            for (Author author : allAuthors) {
                System.out.println("#" + index + " " + author.toString());
                index++;
            }
            System.out.println("Choose index of the author:");
            index = Integer.parseInt(bf.readLine());
            index--;    //Т.к. для пользователя индексация начинается с единицы
            Validator.validateIndexOfElement(index, allAuthors);
            return allAuthors.get(index);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    public Book chooseBook() throws IOException, CsvException {
        try{
            List<Book> allBooks = bookService.read();
            int index = 1;
            for (Book book : allBooks) {
                System.out.println("#" + index + " " + book.toString());
                index++;
            }
            System.out.println("Choose index of the book:");
            index = Integer.parseInt(bf.readLine());
            index--;    //Т.к. для пользователя индексация начинается с единицы
            Validator.validateIndexOfElement(index, allBooks);
            return allBooks.get(index);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    private void deleteBookFromAuthors(Book bookToUpd) throws IOException, CsvException {
        List<String> allAuthors = new ArrayList<>(bookToUpd.getAuthors());
        for (String item : allAuthors){
            Author authorToUpd = authorService.findAuthorByName(item);
            authorToUpd = authorService.deleteBookFromAuthor(authorToUpd, bookToUpd);
            authorService.updateAuthor(authorToUpd);
        }
    }


    private void addBooksToAuthors(Book bookToUpd) throws IOException, CsvException {
        List<String> allAuthors = new ArrayList<>(bookToUpd.getAuthors());
        for (String item : allAuthors){
            Author authorToUpd = authorService.findAuthorByName(item);
            authorToUpd = authorService.addBookToAuthor(authorToUpd, bookToUpd);
            authorService.updateAuthor(authorToUpd);
        }
    }


    private boolean isBookContainsAuthor(Book book, Author author){
        List<String> allAuthors = book.getAuthors();
        if(allAuthors.stream().anyMatch(a -> a.equals(author.getFullName()))){
            return true;
        }
        return false;
    }


    public void deleteAuthorFromBooks(Author authorToUpd) throws IOException, CsvException {
        List<String> allBooks = new ArrayList<>(authorToUpd.getBooks());
        for(String item : allBooks){
            Book bookToUpd = bookService.findBookByName(item);
            bookToUpd = bookService.deleteAuthorFromBook(bookToUpd, authorToUpd);
            bookService.updateBook(bookToUpd);
        }
    }


    private void addAuthorToBooks(Author authorToUpd) throws IOException, CsvException {
        List<String> allBooks = new ArrayList<>(authorToUpd.getBooks());
        for(String item : allBooks){
            Book bookToAdd = bookService.findBookByName(item);
            bookToAdd = bookService.addAuthorToBook(bookToAdd, authorToUpd);
            bookService.updateBook(bookToAdd);
        }
    }


    private boolean isAuthorContainsBook(Author author, Book book){
        List<String> allBooks = author.getBooks();
        if(allBooks.stream().anyMatch(b -> b.equals(book.getBookName()))){
            return true;
        }
        return false;
    }
}
