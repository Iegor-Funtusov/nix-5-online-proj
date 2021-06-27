package Service.Impl;

import Dao.AuthorDao;
import Dao.Impl.AuthorDaoImpl;
import Dao.BookDao;
import Dao.Impl.BookDaoImpl;
import Service.BookStoreService;
import entities.Author;
import entities.Book;

import java.util.Scanner;

public class BookStoreImpl implements BookStoreService {

    public BookStoreImpl() {
        try{
        this.authorDao = new AuthorDaoImpl();
        }catch (Exception e){
            System.out.println("Sorry, author dao couldn't be initialized, shutting down...");
        }
    }

    AuthorDao authorDao;
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void createBook(Scanner s) {
        Book book = new Book();
        System.out.println("Please input the book's name");
        book.setName(s.nextLine());
        System.out.println("Please input the book's authors with a coma and space after each");
        String[] authorsNames = s.nextLine().split(", ");
        for (String authorName :
                authorsNames) {
            Author author = new Author();
            author.setName(authorName.split(" ")[0]);
            author.setSurname(authorName.split(" ")[1]);
            authorDao.create(author);
        }
        bookDao.create(book);
    }

    @Override
    public void createAuthor(Scanner s) {
        Author author = new Author();
        System.out.println("Please input author's name");
        author.setName(s.nextLine());
        System.out.println("Please input author's surname");
        author.setSurname(s.nextLine());
        System.out.println("Please input author's books with a coma and a space after each.");
        String input = s.nextLine();
        String[] inputSplit = input.split("; ");
        for (int i = 0; i < inputSplit.length; i++) {
            Book book = new Book();
            book.setName(inputSplit[i]);
            book.setAuthors(author.getName() + " " + author.getSurname());
            bookDao.create(book);
        }
        author.setBooklist(input);
        authorDao.create(author);
    }

    @Override
    public void getBooksAuthors(Scanner s) {
        System.out.println("Please input the name of the book.");
        Book found = bookDao.find(s.nextLine());
        if (found != null) {
            System.out.println(bookDao.find(s.nextLine()).getAuthors());
        } else {
            System.out.println("Couldn't find the book.");
        }
    }

    @Override
    public void getAuthorsBooks(Scanner s) {
        System.out.println("Please input the name of the author u'd like to find the books of.");
        Author author = new Author();
        author.setName(s.nextLine());
        System.out.println("Please input the surname.");
        author.setSurname(s.nextLine());
        author = authorDao.find(author);
        if(author!=null){
            for (String book :
                    author.getBooklist().split("; ")) {
                System.out.println("\t" + book);
            }
        }
    }

    @Override
    public void getBooks() {
        for (Book book :
                bookDao.findAll()) {
            System.out.print(book.getName() + " authors " + book.getAuthors());
            System.out.println();
        }
    }

    @Override
    public void getAuthors() {
        for (Author author :
                authorDao.findAll()) {
            System.out.println(author.getName() + " " + author.getSurname());
            for (String bookName :
                    author.getBooklist().split("; ")) {
                System.out.println("\t" + bookName);
            }
        }
    }

    @Override
    public void updateAuthor(Scanner s) {
        System.out.println("Please input the name of the author u'd like to update data of.");
        Author current = new Author();
        current.setName(s.nextLine());
        System.out.println("Please input the surname.");
        current.setSurname(s.nextLine());
        boolean flag = true;
        while (flag) {
            current = authorDao.find(current);
            if (current != null) {
                Author updated = new Author();
                System.out.println("Editing " + current.getName() + " " + current.getSurname() + "'s data.");
                System.out.println("Please input the new name.");
                updated.setName(s.nextLine());
                System.out.println("Please input the new surname.");
                updated.setSurname(s.nextLine());
                authorDao.update(current, updated);
                flag = false;
            } else {
                System.out.println("Author not found. Would u like to try again?(y/n)");
                flag = s.nextLine().toLowerCase().startsWith("y");
            }
        }
    }

    @Override
    public void updateBook(Scanner s) {
        System.out.println("Please input the name of the book u'd like to update.");
        Book current = bookDao.find(s.nextLine());
        if(current!=null){
            Book updated = new Book();
            System.out.println("Please input the new name of the book.");
            updated.setName(s.nextLine());
            bookDao.update(current, updated);
        }else{
            System.out.println("Couldn't find the book");
        }
    }
}
