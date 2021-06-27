package com.k4rnaj1k.Service.Impl;

import com.k4rnaj1k.Dao.AuthorDao;
import com.k4rnaj1k.Dao.BookDao;
import com.k4rnaj1k.Dao.Impl.AuthorDaoImpl;
import com.k4rnaj1k.Dao.Impl.BookDaoImpl;
import com.k4rnaj1k.Service.BookStoreService;
import com.k4rnaj1k.entities.Author;
import com.k4rnaj1k.entities.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;

public class BookStoreImpl implements BookStoreService {

    AuthorDao authorDao = new AuthorDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    private static Logger loggerInfo = LoggerFactory.getLogger("info");
    private static Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static Logger loggerError = LoggerFactory.getLogger("error");

    @Override
    public void createBook(Scanner s) {
        Book book = new Book();
        System.out.println("Please input the book's name");
        book.setName(s.nextLine());
        loggerInfo.info("Creating a book with a name" + book.getName()+ ".");
        if (bookDao.find(book.getName()) == null) {
            System.out.println("Please input the amount of book's authors");
            boolean flag = true;
            while (flag) {
                try {
                    loggerWarn.warn("About to parse the integer amount of book's authors.");
                    int count = Integer.parseInt(s.nextLine());
                    flag = false;
                    for (int i = 0; i < count; i++) {
                        Author author = new Author();
                        System.out.println("Please input author's name");
                        author.setName(s.nextLine());
                        System.out.println("Please input author's surname");
                        author.setSurname(s.nextLine());
                        if (authorDao.find(author) == null) {
                            author.setBooklist(book.getName());
                            author.setVisible("true");
                            authorDao.create(author);
                        } else {
                            author = authorDao.find(author);
                            author.setBooklist(author.getBooklist() + "; " + book.getName());
                            author.setVisible("true");
                            authorDao.update(authorDao.find(author), author);
                        }
                        book.setAuthors(book.getAuthors().replace(author.getName() + " " + author.getSurname() + "; ", "").concat(author.getName() + " " + author.getSurname() + "; "));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Wrong input.Try again.");
                    loggerError.error("Couldn't parse the input integer.");
                }
            }
            bookDao.create(book);
            loggerInfo.info("Successfully created a book " + book.getName() + ".");
        } else if (bookDao.find(book.getName()).getVisible().equals("false")) {
            loggerInfo.info("Found the book, it had its visibility set to false.");
            book = bookDao.find(book.getName());
            String[] authors = book.getAuthors().split("; ");
            for (int i = 0; i < authors.length; i++) {
                Author author = new Author();
                author.setName(authors[i].split(" ")[0]);
                author.setSurname(authors[i].split(" ")[1]);
                if (authorDao.find(author) == null) {
                    author.setBooklist(book.getName());
                    author.setVisible("true");
                    authorDao.create(author);
                } else {
                    author = authorDao.find(author);
                    author.setBooklist(author.getBooklist() + book.getName() + "; ");
                    authorDao.update(authorDao.find(author), author);
                }
                loggerInfo.info("Updated all the book's authors.");
            }
            book.setVisible("true");
            bookDao.update(bookDao.find(book.getName()), book);
            loggerInfo.info("Updated the book.");
        }
    }

    @Override
    public void createAuthor(Scanner s) {
        loggerInfo.info("Starting the process of author creation.");
        Author author = new Author();
        System.out.println("Please input author's name");
        author.setName(s.nextLine());
        System.out.println("Please input author's surname");
        author.setSurname(s.nextLine());
        boolean flag = true;
        if (authorDao.find(author) == null) {
            loggerInfo.info("Creating an author " + author.getName() + " " + author.getSurname());
            System.out.println("Please input amount of author's books.");
            while (flag) {
                try {
                    loggerWarn.warn("About to parse an integer amount of the author's books.");
                    int count = Integer.parseInt(s.nextLine());
                    flag = false;
                    for (int i = 0; i < count; i++) {
                        Book book = new Book();
                        System.out.println("Please input the name of the book.");
                        book.setName(s.nextLine());
                        if (bookDao.find(book.getName()) == null) {
                            book.setAuthors(author.getName() + " " + author.getSurname() + "; ");
                            bookDao.create(book);
                        } else {
                            book = bookDao.find(book.getName());
                            book.setAuthors(book.getAuthors().replace(author.getName() + " " + author.getSurname() + "; ", "") + author.getName() + " " + author.getSurname() + "; ");
                            bookDao.update(bookDao.find(book.getName()), book);
                        }
                        author.setBooklist(author.getBooklist() + book.getName() + "; ");
                    }
                    authorDao.create(author);
                } catch (NumberFormatException e) {
                    System.out.println("wrong input, please try again.");
                    loggerError.error("Couldn't parse the input integer.");
                }
            }
        } else {
            System.out.println("Author with this name and surname already exists." +
                    " If u'd like to update an author, use the corresponding option.");
        }
    }

    @Override
    public void getBooksAuthors(Scanner s) {
        loggerWarn.warn("Started the process of searching of authors by book's name.");
        System.out.println("Please input the name of the book.");
        Book found = bookDao.find(s.nextLine());
        if (found != null) {
            System.out.println(found.getName());
            Arrays.stream(found.getAuthors().split("; ")).forEach(string -> System.out.println("\t" + string));
        } else {
            System.out.println("Couldn't find the book.");
            loggerError.error("Couldn't find the book.");
        }
    }

    @Override
    public void getAuthorsBooks(Scanner s) {
        loggerWarn.warn("About to get books by author's name.");
        System.out.println("Please input the name of the author u'd like to find the books of.");
        Author author = new Author();
        author.setName(s.nextLine());
        System.out.println("Please input the surname.");
        author.setSurname(s.nextLine());
        author = authorDao.find(author);
        if (author != null) {
            System.out.println(author.getName() + " " + author.getSurname());
            for (String book :
                    author.getBooklist().split("; ")) {
                System.out.println("\t" + book);
            }
        }else{
            System.out.println("Couldn't find the author.");
            loggerError.error("Couldn't find the author.");
        }
    }

    @Override
    public void getBooks() {
        loggerWarn.warn("About to get the complete list of books.");
        int count = 0;
        for (Book book :
                bookDao.findAll()) {
            if (book.getVisible().equals("true")) {
                System.out.print(book.getName());
                Arrays.stream(book.getAuthors().split("; ")).forEach(author->System.out.println("\t"+author));
                System.out.println();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No books to show");
            loggerError.error("No books to show.");
        }
    }

    @Override
    public void getAuthors() {
        loggerWarn.warn("About to get the complete list of authors.");
        int count = 0;
        for (Author author :
                authorDao.findAll()) {
            if (author.getVisible().equals("true")) {
                System.out.println(author.getName() + " " + author.getSurname());
                for (String bookName :
                        author.getBooklist().split("; ")) {
                    System.out.println("\t" + bookName);
                }
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No authors to show.");
            loggerError.error("No authors to show.");
        }
    }

    @Override
    public void updateAuthor(Scanner s) {
        loggerWarn.warn("Starting the process of updating the author's data.");
        System.out.println("Please input the name of the author u'd like to update data of.");
        boolean flag = true;

        while (flag) {
            Author current = new Author();
            current.setName(s.nextLine());
            System.out.println("Please input the surname.");
            current.setSurname(s.nextLine());


            current = authorDao.find(current);

            if (current != null) {
                loggerInfo.info("Successfully found an author " + current.getName() +" " + current.getSurname());
                Author updated = new Author();
                System.out.println("Editing " + current.getName() + " " + current.getSurname() + "'s data.");
                System.out.println("Please input the new name.");
                updated.setName(s.nextLine());
                System.out.println("Please input the new surname.");
                updated.setSurname(s.nextLine());
                System.out.println("Would u like to update the bookslist?(y/n)");
                if (s.nextLine().toLowerCase().startsWith("y")) {
                    System.out.println("Input the names of the books with a semicolon and a space after each.");
                    for (String bookString :
                            s.nextLine().split("; ")) {
                        if (bookDao.find(bookString) == null) {
                            Book book = new Book();
                            book.setName(bookString);
                            book.setAuthors(updated.getName() + " " + updated.getSurname());
                            bookDao.create(book);
                        } else {
                            Book updatedBook = bookDao.find(bookString);
                            updatedBook.setAuthors(updatedBook.getAuthors().replace(current.getName() + " " + current.getSurname(), updated.getName() + " " + updated.getSurname()));
                            bookDao.update(bookDao.find(bookString), updatedBook);
                        }
                    }
                }
                for (String bookName :
                        current.getBooklist().split("; ")) {
                    Book updatedBook = bookDao.find(bookName);
                    updatedBook.setAuthors(updatedBook.getAuthors().replaceAll(current.getName() + " " + current.getSurname(), updated.getName() + " " + updated.getSurname()));
                    bookDao.update(bookDao.find(updatedBook.getName()), updatedBook);
                }
                authorDao.update(current, updated);
                flag = false;
            } else {
                System.out.println("Author not found. Would u like to try again?(y/n)");
                loggerError.error("Couldn't find the given author.("+ current.getName()+" "+ current.getSurname()+")");
                flag = s.nextLine().toLowerCase().startsWith("y");
            }
        }
    }

    @Override
    public void updateBook(Scanner s) {
        System.out.println("Please input the name of the book u'd like to update.");
        Book current = bookDao.find(s.nextLine());
        if (current != null) {
            for (String author :
                    bookDao.find(current.getName()).getAuthors().split("; ")) {
                Author authorR = new Author();
                authorR.setName(author.split(" ")[0]);
                authorR.setSurname(author.split(" ")[1]);
                authorR = authorDao.find(authorR);
                authorR.setBooklist(authorR.getBooklist().replace(current.getName() + "; ", ""));
            }
            Book updated = new Book();
            System.out.println("Please input the new name of the book.");
            updated.setName(s.nextLine());
            System.out.println("Input the amount of authors.");
            boolean flag = true;
            while (flag) {
                try {
                    loggerWarn.warn("About to parse the inputted integer of updated book's authors.");
                    int count = Integer.parseInt(s.nextLine());
                    for (int i = 0; i < count; i++) {
                        Author author = new Author();
                        System.out.println("Please input the author's name.");
                        author.setName(s.nextLine());
                        System.out.println("Please input the author's surname");
                        author.setSurname(s.nextLine());
                        if (authorDao.find(author) == null) {
                            author.setBooklist(updated.getName());
                            authorDao.create(author);
                        } else {
                            Author updatedAuthor = authorDao.find(author);
                            updatedAuthor.setBooklist(updatedAuthor.getBooklist().concat(updated.getName()));
                            authorDao.update(author, updatedAuthor);
                        }
                        updated.setAuthors(updated.getAuthors() + author.getName() + " " + author.getSurname() + "; ");
                    }
                    flag = false;
                } catch (NumberFormatException e) {
                    loggerError.error("Couldn't parse the given int.");
                    System.out.println("Wrong input, please try again.");
                }
            }
            updated.setVisible("true");
            bookDao.update(current, updated);
        } else {
            System.out.println("Couldn't find the book");
            loggerError.error("Couldn't find the given book.("+current.getName()+")");
        }
    }

    public void removeAuthor(Scanner s) {
        loggerWarn.warn("Starting the process of author's removal.");
        System.out.println("Please input the name of the author u'd like to remove.");
        Author removed = new Author();
        removed.setName(s.nextLine());
        System.out.println("Please input the surname.");
        removed.setSurname(s.nextLine());
        if (authorDao.find(removed) != null) {
            removed = authorDao.find(removed);
            removed.setVisible("false");
            authorDao.update(authorDao.find(removed), removed);
            loggerInfo.info("Successfully found and removed the given author.("+removed.getName()+" "+ removed.getSurname()+")");
        } else {
            loggerError.error("Couldn't find the given author.("+removed.getName()+ " "+ removed.getSurname()+")");
            System.out.println("Author not found");
        }
    }

    @Override
    public void removeBook(Scanner s) {
        loggerWarn.warn("Starting the proccess of book removal.");
        System.out.println("Please input the name of the book u'd like to remove.");
        String name = s.nextLine();
        if (bookDao.find(name) != null) {
            loggerInfo.info("Successfully found the given book in the database.("+name+")");
            Book removed = bookDao.find(name);
            for (String author :
                    bookDao.find(name).getAuthors().split("; ")) {
                Author authorOfBook = new Author();
                authorOfBook.setName(author.split(" ")[0]);
                authorOfBook.setSurname(author.split(" ")[1]);
                authorOfBook = authorDao.find(authorOfBook);
                authorOfBook.setBooklist(authorOfBook.getBooklist().replaceAll(removed.getName() + "; ", ""));
                authorDao.update(authorDao.find(authorOfBook), authorOfBook);
            }
            removed.setVisible("false");
            bookDao.update(bookDao.find(removed.getName()), removed);
            loggerInfo.info("Successfully \"removed\" the book from the database.");
        } else {
            loggerError.error("Couldn't find the given book.("+name+")");
            System.out.println("Couldn't find the book.");
        }
    }

    @Override
    public void autotest(BookStoreService service) {
        System.out.println("Trying to create an author with a book \"Sherlock\".");
        createAuthor(new Scanner("Some\nAuthor\n1\nSherlock"));
        System.out.println("Result: ");
        getBooks();
        System.out.println("Success.");
        System.out.println("Trying to create a duplicate author.");
        createAuthor(new Scanner("Some\nAuthor"));
        System.out.println("Failed.(As intended.)");
        System.out.println("Trying to create a book with a name \"Sherlock\" in bookDao.");
        createBook(new Scanner("Sherlock"));
        System.out.println("Failed.(As intended)");
        System.out.println("Trying to rename \"Some Author\" to \"Conan Doyle\"");
        updateAuthor(new Scanner("Some\nAuthor\nConan\nDoyle\nn"));
        getAuthors();
        getBooks();
        System.out.println("Removing Conan Doyle.");
        removeAuthor(new Scanner("Conan\nDoyle"));
        System.out.println("Current author's list:");
        getAuthors();
        System.out.println("Creating a book \"The Adventures of Sherlock Holmes\" for Conan Doyle.");
        createBook(new Scanner("The Adventures of Sherlock Holmes\n1\nConan\nDoyle"));
        getAuthors();
        getBooks();
    }
}
