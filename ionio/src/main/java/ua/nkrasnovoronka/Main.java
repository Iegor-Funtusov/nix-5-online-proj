package ua.nkrasnovoronka;

import ua.nkrasnovoronka.controller.LibraryController;
import ua.nkrasnovoronka.util.UserInput;

public class Main {
    private static LibraryController libraryController = new LibraryController();

    public static void main(String[] args) {
        crudAction();
    }

    private static void crudAction() {
        int action = 1;
        while (action != 0) {
            action = UserInput.userInputNumber("Pleas chose action with:\nbook - 1\nauthor - 2\n0 - to exit");
            switch (action) {
                case 1: {
                    bookCrud();
                    break;
                }
                case 2: {
                    authorCrud();
                    break;
                }
                default: {
                    System.out.println("Sorry wrong action. Pleas repeat");
                }
            }
        }
    }

    public static void bookCrud() {
        int bookAction = 1;
        while (bookAction != 0) {
            System.out.println("Pleas choose action with books");
            bookAction = UserInput.userInputNumber("1 - create book\n2 - read book by id\n" +
                    "3 - read all books\n4 - read all books by author id\n5 - update book\n" +
                    "6 - delete book\n7 - add author to book\n8 - return\n0 - to exit");
            switch (bookAction) {
                case 1: {
                    libraryController.createBook();
                    break;
                }
                case 2: {
                    libraryController.getBookById();
                    break;
                }
                case 3: {
                    libraryController.printAllBooks();
                    break;
                }
                case 4: {
                    libraryController.printAllBooksByAuthor();
                    break;
                }
                case 5: {
                    libraryController.updateBook();
                    break;
                }
                case 6: {
                    libraryController.removeBook();
                    break;
                }
                case 7: {
                    libraryController.addAuthorsToBook();
                    break;
                }
                case 8: {
                    crudAction();
                    break;
                }
                default: {
                    System.out.println("Sorry wrong action. Pleas repeat");
                }
            }
        }
    }

    public static void authorCrud() {
        int bookAction = 1;
        while (bookAction != 0) {
            System.out.println("Pleas choose action with authors");
            bookAction = UserInput.userInputNumber("1 - create author\n2 - read author by id\n" +
                    "3 - read all authors\n4 - read all books by author id\n5 - update author\n" +
                    "6 - delete author\n7 - add books to author\n8 - return\n0 - to exit");
            switch (bookAction) {
                case 1: {
                    libraryController.addAuthorToLibrary();
                    break;
                }
                case 2: {
                    libraryController.getAuthorById();
                    break;
                }
                case 3: {
                    libraryController.printAllAuthors();
                    break;
                }
                case 4: {
                    libraryController.printAllBooksByAuthor();
                    break;
                }
                case 5: {
                    libraryController.updateAuthor();
                    break;
                }
                case 6: {
                    libraryController.removeAuthor();
                    break;
                }
                case 7: {
                    libraryController.addBooksToAuthor();
                    break;
                }
                case 8: {
                    crudAction();
                    break;
                }
                default: {
                    System.out.println("Sorry wrong action. Pleas repeat");
                }
            }
        }
    }
}
