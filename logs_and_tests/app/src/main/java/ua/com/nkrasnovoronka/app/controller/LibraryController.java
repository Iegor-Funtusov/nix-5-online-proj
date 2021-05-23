package ua.com.nkrasnovoronka.app.controller;

import ua.com.nkrasnovoronka.app.dao.Author;
import ua.com.nkrasnovoronka.app.dao.Book;
import ua.com.nkrasnovoronka.app.service.AuthorService;
import ua.com.nkrasnovoronka.app.service.BookService;
import ua.com.nkrasnovoronka.app.service.impl.AuthorServiceImpl;
import ua.com.nkrasnovoronka.app.service.impl.BookServiceImpl;
import ua.com.nkrasnovoronka.app.util.UserInput;

public class LibraryController {
    AuthorService authorService = new AuthorServiceImpl();
    BookService bookService = new BookServiceImpl();


    public void addAuthorToLibrary(){
        System.out.println("Adding author to library\nPleas write author name");
        String authorName = UserInput.userInputString();
        authorService.create(new Author(authorName));
    }
    public void printAllAuthors(){
        System.out.println(authorService.getAllAuthors());
    }

    public void removeAuthor(){
        System.out.println("Pleas enter author name to be removed");
        String authorName = UserInput.userInputString();
        authorService.removeAuthorByName(authorName);
    }

    public void updateAuthor(){
        System.out.println("Pleas enter author name to be updated");
        String authorName = UserInput.userInputString();
        Author authorByName = authorService.getAuthorByName(authorName);
        System.out.println("Pleas enter updated author name");
        String updatedAuthorName = UserInput.userInputString();
        authorByName.setName(updatedAuthorName);
        authorService.updateAuthor(authorByName);
    }

    public void createBook(){
        System.out.println("Adding book to library\nPleas enter book name");
        String bookName = UserInput.userInputString();
        System.out.println("Pleas enter book author name");
        String authorName = UserInput.userInputString();
        Book book = new Book(bookName);
        Author authorByName = authorService.getAuthorByName(authorName);
        book.setAuthorId(authorByName.getId());
        bookService.create(book);
    }

    public void updateBook(){
        System.out.println("Pleas enter book name to be updated");
        String bookName = UserInput.userInputString();
        Book bookByName = bookService.getBookByName(bookName);
        boolean isRunning = true;
        while (isRunning){
            System.out.println("Pleas enter 1 - change book name 2 - book author 0 - to exit");
            int choose = UserInput.userInputNumber();
            switch (choose){
                case 1 :{
                    System.out.println("Pleas enter new book name");
                    String updatedBookName = UserInput.userInputString();
                    bookByName.setName(updatedBookName);
                    break;
                }
                case 2:{
                    System.out.println("Pleas enter new book author name");
                    String updatedBookAuthor = UserInput.userInputString();
                    Author authorByName = authorService.getAuthorByName(updatedBookAuthor);
                    bookByName.setAuthorId(authorByName.getId());
                    break;
                }
                case 0:{
                    isRunning = false;
                    break;
                }
                default:{
                    System.out.println("Wrong action pleas repeat");
                }
            }
        }
        bookService.updateBook(bookByName);
    }

    public void printAllBooks(){
        System.out.println(bookService.getAllBooks());
    }

    public void printAllBooksByAuthor(){
        System.out.println("Pleas enter author name ");
        String authorName = UserInput.userInputString();
        System.out.println(authorService.getAllAuthorBooks(authorName));
    }

    public void getAuthorByName(){
        System.out.println("Pleas enter author name");
        String authorName = UserInput.userInputString();
        System.out.println(authorService.getAuthorByName(authorName));
    }

    public void getBookByName(){
        System.out.println("Pleas enter book name");
        String bookName = UserInput.userInputString();
        System.out.println(bookService.getBookByName(bookName));
    }

    public void removeBook(){
        System.out.println("Pleas enter book name to be removed");
        String bookName = UserInput.userInputString();
        bookService.removeBookByName(bookName);
    }



}
