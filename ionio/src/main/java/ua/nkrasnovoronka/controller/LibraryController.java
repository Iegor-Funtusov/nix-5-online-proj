package ua.nkrasnovoronka.controller;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.model.Genre;
import ua.nkrasnovoronka.service.AuthorService;
import ua.nkrasnovoronka.service.BookService;
import ua.nkrasnovoronka.service.impl.AuthorServiceImpl;
import ua.nkrasnovoronka.service.impl.BookServiceImpl;
import ua.nkrasnovoronka.util.UserInput;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryController {
    AuthorService authorService = new AuthorServiceImpl();
    BookService bookService = new BookServiceImpl();

    public void addAuthorToLibrary() {
        System.out.println("Adding author to library\n");
        String firstName = UserInput.userInputString("Enter first name");
        String secondName = UserInput.userInputString("Enter second name");
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(secondName);
        authorService.create(author);
    }

    public void printAllAuthors() {
        System.out.println(authorService.getAllAuthors());
    }

    public void removeAuthor() {
        Long authorId = Long.valueOf(UserInput.userInputNumber("Pleas enter author id"));
        authorService.removeAuthorById(authorId);
    }

    public void updateAuthor() {
        System.out.println();
        Long authorId = (long) UserInput.userInputNumber("Pleas enter author id to be updated");
        Author authorByName = authorService.getAuthorById(authorId);
        String updatedFirstName = UserInput.userInputString("Pleas enter updated author first name");
        String updatedSecondName = UserInput.userInputString("Pleas enter updated author second name");
        authorByName.setFirstName(updatedFirstName);
        authorByName.setLastName(updatedSecondName);
        authorService.updateAuthor(authorId, authorByName);
    }

    public void createBook() {
        System.out.println("Adding book to library\n");
        String bookName = UserInput.userInputString("Pleas enter book title");
        int rating = UserInput.userInputNumber("Pleas enter rating");
        List<Integer> integers = UserInput.userInputNumbers("Pleas enter authors id seperated by  space");
        Genre genre = getGenre();
        Book book = new Book();
        book.setBookTitle(bookName);
        book.setGenre(genre);
        book.setBookRating(rating);
        book.setBooksAuthors(integers.stream().map(Long::valueOf).collect(Collectors.toSet()));
        bookService.create(book);
    }

    private Genre getGenre() {
        printGenres();
        int bookGenre = UserInput.userInputNumber("Pleas enter book genre");
        Genre genre;
        if (bookGenre > Genre.values().length || bookGenre < 0) {
            genre = Genre.OTHER;
        } else {
            genre = Genre.values()[bookGenre];
        }
        return genre;
    }

    private void printGenres() {
        Genre[] values = Genre.values();
        for (Genre g : values) {
            System.out.println(g.name() + ":" + g.ordinal());
        }
    }

    public void updateBook() {
        Long bookId = (long) UserInput.userInputNumber("Pleas enter book id to be updated");
        Book bookById = bookService.getBookById(bookId);
        boolean isRunning = true;
        while (isRunning) {
            int choose = UserInput.userInputNumber("Pleas enter 1 - change book name 2 - book rating 3 - book genre 0 - to exit");
            switch (choose) {
                case 1: {
                    String updatedBookName = UserInput.userInputString("Pleas enter new book name");
                    bookById.setBookTitle(updatedBookName);
                    break;
                }
                case 2: {
                    long updatedBookRating = UserInput.userInputNumber("Pleas enter new book rating");
                    bookById.setBookRating(updatedBookRating);
                    break;
                }
                case 3: {
                    bookById.setGenre(getGenre());
                    break;
                }
                case 0: {
                    isRunning = false;
                    break;
                }
                default: {
                    System.out.println("Wrong action pleas repeat");
                }
            }
        }
        bookService.updateBook(bookId, bookById);
    }

    public void printAllBooks() {
        System.out.println(bookService.getAllBooks());
    }

    public void printAllBooksByAuthor() {
        System.out.println();
        long authorId = UserInput.userInputNumber("Pleas enter author id");
        System.out.println(authorService.getAllAuthorBooks(authorId));
    }

    public void getAuthorById() {
        long authorId = UserInput.userInputNumber("Pleas enter author id");
        System.out.println(authorService.getAuthorById(authorId));
    }

    public void getBookById() {
        long bookId = UserInput.userInputNumber("Pleas enter book id");
        System.out.println(bookService.getBookById(bookId));
    }

    public void removeBook() {
        Long bookId = (long) UserInput.userInputNumber("Pleas enter book id to be removed");
        bookService.removeBookById(bookId);
    }

    public void addBooksToAuthor() {
        long authorId = UserInput.userInputNumber("Pleas enter author id");
        long bookId = UserInput.userInputNumber("Pleas enter book id to be added to author books");
        authorService.addBookToAuthor(authorId, bookId);
    }

    public void addAuthorsToBook() {
        long bookId = UserInput.userInputNumber("Pleas enter book id");
        long authorId = UserInput.userInputNumber("Pleas enter author id to be added to book");
        bookService.addAuthorToBook(bookId, authorId);
    }


}

