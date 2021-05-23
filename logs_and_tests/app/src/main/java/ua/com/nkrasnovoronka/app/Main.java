package ua.com.nkrasnovoronka.app;

import ua.com.nkrasnovoronka.app.controller.LibraryController;
import ua.com.nkrasnovoronka.app.util.UserInput;

public class Main {
    private static LibraryController libraryController = new LibraryController();

    public static void main(String[] args) {
        crudAction();
    }

    private static void crudAction() {
        int action = 1;
        while (action != 0) {
            System.out.println("Pleas chose action with:\nbook - 1\nauthor - 2\n0 - to exit");
            action = UserInput.userInputNumber();
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
            System.out.println("1 - create book\n2 - read book by name\n" +
                    "3 - read all books\n4 - read all books by author name\n5 - update book\n" +
                    "6 - delete book\n7 - return\n0 - to exit");
            bookAction = UserInput.userInputNumber();
            switch (bookAction) {
                case 1: {
                    libraryController.createBook();
                    break;
                }
                case 2: {
                    libraryController.getBookByName();
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
            System.out.println("1 - create author\n2 - read author by name\n" +
                    "3 - read all authors\n4 - read all books by author name\n5 - update author\n" +
                    "6 - delete author\n7 - return\n0 - to exit");
            bookAction = UserInput.userInputNumber();
            switch (bookAction) {
                case 1: {
                    libraryController.addAuthorToLibrary();
                    break;
                }
                case 2: {
                    libraryController.getAuthorByName();
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
