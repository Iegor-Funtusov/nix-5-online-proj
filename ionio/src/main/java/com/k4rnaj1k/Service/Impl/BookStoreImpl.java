package com.k4rnaj1k.Service.Impl;

import com.k4rnaj1k.Dao.AuthorDao;
import com.k4rnaj1k.Dao.Impl.AuthorDaoImpl;
import com.k4rnaj1k.Dao.BookDao;
import com.k4rnaj1k.Dao.Impl.BookDaoImpl;
import com.k4rnaj1k.Service.BookStoreService;
import com.k4rnaj1k.entities.Author;
import com.k4rnaj1k.entities.Book;

import java.util.Arrays;
import java.util.Scanner;

public class BookStoreImpl implements BookStoreService {

    AuthorDao authorDao = new AuthorDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void createBook(Scanner s) {
        Book book = new Book();
        System.out.println("Please input the book's name");
        book.setName(s.nextLine());
        if (bookDao.find(book.getName()) == null) {
            System.out.println("Please input the amount of book's authors");
            boolean flag = true;
            while (flag) {
                try {
                    int count = Integer.parseInt(s.nextLine());
                    flag = false;
                    for (int i = 0; i < count ; i++){
                        Author author = new Author();
                        System.out.println("Please input author's name");
                        author.setName(s.nextLine());
                        System.out.println("Please input author's surname");
                        author.setSurname(s.nextLine());
                        if (authorDao.find(author) == null) {
                            author.setBooklist(book.getName());
                            authorDao.create(author);
                        } else {
                            author = authorDao.find(author);
                            author.setBooklist(author.getBooklist() + "; " + book.getName());
                            authorDao.update(authorDao.find(author), author);
                        }
                        book.setAuthors(book.getAuthors().concat(author.getName() + " " + author.getSurname() + "; "));
                    }
                }
            catch(NumberFormatException e){
                System.out.println("Wrong input.Try again.");
            }
            }
            bookDao.create(book);
        } else {
            System.out.println("Trying to create a duplicate book.");
        }
    }

    @Override
    public void createAuthor(Scanner s) {
        Author author = new Author();
        System.out.println("Please input author's name");
        author.setName(s.nextLine());
        System.out.println("Please input author's surname");
        author.setSurname(s.nextLine());
        boolean flag = true;
        if (authorDao.find(author) == null) {

            System.out.println("Please input amount of author's books.");

            while (flag) {
                try {
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
                            book.setAuthors(book.getAuthors() + author.getName() + " " + author.getSurname() + "; ");
                            bookDao.update(bookDao.find(book.getName()), book);
                        }
                        author.setBooklist(author.getBooklist() + book.getName() + "; ");
                    }
                    authorDao.create(author);
                } catch (NumberFormatException e) {
                    System.out.println("wrong input, please try again.");
                }
            }
        } else {
            System.out.println("Author with this name and surname already exists." +
                    " If u'd like to update an author, use the corresponding option.");
        }
    }

    @Override
    public void getBooksAuthors(Scanner s) {
        System.out.println("Please input the name of the book.");
        Book found = bookDao.find(s.nextLine());
        if (found != null) {
            System.out.println(found.getName());
            Arrays.stream(found.getAuthors().split("; ")).forEach(string-> System.out.println("\t" + string));
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
        if (author != null) {
            for (String book :
                    author.getBooklist().split("; ")) {
                System.out.println("\t" + book);
            }
        }
    }

    @Override
    public void getBooks() {
        if (bookDao.findAll().size() == 0) {
            System.out.println("No books to show");
        }
        for (Book book :
                bookDao.findAll()) {
            if (book.getVisible().equals("true")) {
                System.out.print(book.getName() + " authors " + book.getAuthors());
                System.out.println();
            }
        }
    }

    @Override
    public void getAuthors() {
        if (authorDao.findAll().size() == 0) {
            System.out.println("No authors to show.");
        }
        for (Author author :
                authorDao.findAll()) {
            if (author.getVisible().equals("true")) {
                System.out.println(author.getName() + " " + author.getSurname());
                for (String bookName :
                        author.getBooklist().split("; ")) {
                    System.out.println("\t" + bookName);
                }
            }
        }
    }

    @Override
    public void updateAuthor(Scanner s) {
        System.out.println("Please input the name of the author u'd like to update data of.");
        boolean flag = true;

        while (flag) {
            Author current = new Author();
            current.setName(s.nextLine());
            System.out.println("Please input the surname.");
            current.setSurname(s.nextLine());


            current = authorDao.find(current);

            if (current != null) {
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
                flag = s.nextLine().toLowerCase().startsWith("y");
            }
        }
    }

    @Override
    public void updateBook(Scanner s) {
        System.out.println("Please input the name of the book u'd like to update.");
        Book current = bookDao.find(s.nextLine());
        if (current != null) {
            Book updated = new Book();
            System.out.println("Please input the new name of the book.");
            updated.setName(s.nextLine());
            System.out.println("Input the amount of authors.");
            boolean flag = true;
            while (flag) {
                try {
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
                    System.out.println("Wrong input, please try again.");
                }
            }
            updated.setVisible("true");
            bookDao.update(current, updated);
        } else {
            System.out.println("Couldn't find the book");
        }
    }

    public void removeAuthor(Scanner s) {
        System.out.println("Please input the name of the author u'd like to remove.");
        Author removed = new Author();
        removed.setName(s.nextLine());
        System.out.println("Please input the surname.");
        removed.setSurname(s.nextLine());
        if (authorDao.find(removed) != null) {
            removed = authorDao.find(removed);
            removed.setVisible("false");
            authorDao.update(authorDao.find(removed), removed);
        } else {
            System.out.println("Author not found");
        }
    }

    @Override
    public void removeBook(Scanner s) {
        System.out.println("Please input the name of the book u'd like to remove.");
        String name = s.nextLine();
        if (bookDao.find(name) != null) {
            Book removed = new Book();
            removed = bookDao.find(removed.getName());
            removed.setVisible("false");
            bookDao.update(bookDao.find(removed.getName()), removed);
        } else {
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
        System.out.println("Failed.");
    }
}
