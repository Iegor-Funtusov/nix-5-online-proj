package Controllers;

import Configs.PathConfigs;
import DataClasses.Author;
import DataClasses.Book;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    private final MainController mainController;
    private final BufferedReader bf;
    private final String AGREE_INPUT = "1";

    public UserInterface(){
        mainController = new MainController();
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public void userUI(){
        while(true){
            try{
                System.out.println("""
                        Choose the option:\s
                        1-create book, 2-update book, 3-get info, 4-get all books, 5-delete book, Q-exit program\s
                        6-create author, 7-update author, 8-get info, 9-get all authors, 0-delete author, Q-exit program\s
                        To run the common test press T""");

                String choose = bf.readLine();
                switch (choose.toUpperCase()){
                    case "1" -> createBook();
                    case "2" -> updateBook();
                    case "3" -> readBook();
                    case "4" -> readAllBooks();
                    case "5" -> deleteBook();
                    case "6" -> createAuthor();
                    case "7" -> updateAuthor();
                    case "8" -> readAuthor();
                    case "9" -> readAllAuthors();
                    case "0" -> deleteAuthor();
                    case "T" -> commonTestRun();
                    case "Q" -> System.exit(1);
                    default -> System.out.println("Incorrect value entered");
                }

            } catch (IOException e){
                System.out.println("Incorrect value entered");
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }


    private void createBook(){
        try{
            Book newBook = new Book();
            System.out.println("Enter name of the book:");
            String bookName = bf.readLine();
            newBook.setBookName(bookName);
            System.out.println("Choose the author");
            Author author = mainController.chooseAuthor();
            mainController.createBook(newBook, author);
            System.out.println("Book was successfully created. " + newBook.toString() + "\n");

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }


    private void readBook(){
        try{
            System.out.println("Enter name of book info about which do you want to get:");
            String bookName = bf.readLine();
            Book foundBook = mainController.readBook(bookName);
            System.out.println("Successfully found. " + foundBook.toString());

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        }  catch (CsvException e){
            System.out.println(e.getMessage());
        }  catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void readAllBooks(){
        try {
            List<Book> allBooks = mainController.readAllBooks();
            allBooks.forEach(System.out::println);
            System.out.println();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void updateBook(){
        try {
            System.out.println("Choose the book which you want to update:");
            Book bookToUpd = mainController.chooseBook();
            System.out.println("You chose " + bookToUpd.toString());
            System.out.println("What do you want to update? 1-name of book, 2-add author, 3-delete author, 0-exit");
            String choose = bf.readLine();
            switch (choose){
                case "1" -> {
                    System.out.println("Enter new book's name:");
                    String newBookName = bf.readLine();
                    mainController.updateBookName(bookToUpd, newBookName);
                }

                case "2" -> {
                    System.out.println("Choose the author which you want to add:");
                    Author authorToAdd = mainController.chooseAuthor();
                    mainController.updateBookAddAuthor(bookToUpd, authorToAdd);
                }
                case "3" -> {
                    if(bookToUpd.getAuthors().size() == 1){
                        System.out.println("You can not delete author from this book, because this book has only one author");
                        return;
                    }
                    System.out.println("Choose the author which you want to delete:");
                    Author authorToDel = mainController.chooseAuthor();
                    mainController.updateBookDeleteAuthor(bookToUpd, authorToDel);
                }
                case "0" -> System.exit(1);
                default -> System.out.println("Incorrect value entered");
            }
            System.out.println("Successfully updated. Do you want to see the result? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                System.out.println(bookToUpd.toString());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void deleteBook(){
        try{
            System.out.println("Choose the book which you want to delete:");
            Book bookToDel = mainController.chooseBook();
            mainController.deleteBook(bookToDel);
            System.out.println("Successfully deleted");

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void createAuthor(){
        try{
            Author newAuthor = new Author();

            System.out.println("Enter firstname of the author:");
            String firstname = bf.readLine();
            newAuthor.setFirstName(firstname);

            System.out.println("Enter lastname of the author:");
            String lastname = bf.readLine();
            newAuthor.setLastName(lastname);

            System.out.println("Do you want to add book to the author? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                Book bookToAdd = mainController.chooseBook();
                mainController.createAuthor(newAuthor, bookToAdd);
            } else{
                System.out.println("Author will be added without books");
                mainController.createAuthor(newAuthor);
            }

            System.out.println("Author was successfully created. " + newAuthor.toString() + "\n");

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }


    private void readAuthor(){
        try{
            System.out.println("Enter full name of the author information about which you want to get:");
            String fullName = bf.readLine();
            Author author = mainController.readAuthor(fullName);
            System.out.println("Successfully found. " + author.toString());

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }


    private void readAllAuthors(){
        try {
            List<Author> allAuthors = mainController.readAllAuthors();
            allAuthors.forEach(System.out::println);
            System.out.println();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void updateAuthor(){
        try {
            System.out.println("Choose thr author which you want to update:");
            Author authorToUpd = mainController.chooseAuthor();
            System.out.println("You chose: " + authorToUpd.toString());
            System.out.println("1-update firstname, 2-update lastname, 3-add book, 4-delete book, 0-exit");
            String choose = bf.readLine();
            switch (choose){
                case "1" -> {
                    System.out.println("Enter new author's firstname");
                    String newFirstName = bf.readLine();
                    mainController.updateAuthorFirstName(authorToUpd, newFirstName);
                }
                case "2" -> {
                    System.out.println("Enter new author's lastname");
                    String newLastName = bf.readLine();
                    mainController.updateAuthorLastName(authorToUpd, newLastName);
                }
                case "3" -> {
                    System.out.println("Choose the book which you want to add to the author:");
                    Book bookToAdd = mainController.chooseBook();
                    mainController.updateAuthorAddBook(authorToUpd, bookToAdd);
                }
                case "4" -> {
                    if(authorToUpd.getBooks().size() == 0){
                        System.out.println("Author does not contain any book");
                        return;
                    }
                    System.out.println("Choose the book which you want to delete from the author:");
                    Book bookToDel = mainController.chooseBook();
                    mainController.updateAuthorDeleteBook(authorToUpd, bookToDel);
                }
                case "0" -> System.exit(1);
                default -> System.out.println("Incorrect value entered");
            }
            System.out.println("Successfully updated. Do you want to see the result? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                System.out.println(authorToUpd.toString());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void deleteAuthor(){
        try{
            System.out.println("Choose the author which you want to delete:");
            Author authorToDelete = mainController.chooseAuthor();
            mainController.deleteAuthor(authorToDelete);
            System.out.println("Successfully deleted");

        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void commonTestRun() {
        try {
            List<Author> allAuthors;
            List<Book> allBooks;

            System.out.println("Common test:");
            System.out.println("___________________________________________________________________");


            System.out.println("Generating authors WITHOUT books");
            for (int i = 0; i < 10; i++) {
                Author newAuthor = new Author();
                newAuthor.setFirstName("Firstname " + (i + 1));
                newAuthor.setLastName("Lastname " + (i + 1));
                mainController.createAuthor(newAuthor);
            }
            System.out.println("Successfully created authors");

            System.out.println("___________________________________________________________________");
            System.out.println("All created authors:");
            allAuthors = mainController.readAllAuthors();
            allAuthors.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            System.out.println("Generating books:");
            for(int i = 0; i < 10; i++){
                Book newBook = new Book();
                newBook.setBookName("BookName " + (i+1));
                List<String> authors = new ArrayList<>();
                Author authorOfBook = mainController.readAllAuthors().get(i);
                mainController.createBook(newBook, authorOfBook);
            }
            System.out.println("Successfully created books");

            System.out.println("___________________________________________________________________");
            System.out.println("All created books:");
            allBooks = mainController.readAllBooks();
            allBooks.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            System.out.println("All created authors WITH books:");
            allAuthors = mainController.readAllAuthors();
            allAuthors.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            System.out.println("Updating authors:");
            System.out.println("Changing firstname of the author");
            allAuthors = mainController.readAllAuthors();
            for(int i = 0; i < 10; i++){
                Author authorToUpd = allAuthors.get(i);
                String newFirstName = "UpdatedFirstname" + (i+1);
                mainController.updateAuthorFirstName(authorToUpd, newFirstName);
            }
            System.out.println("Successfully updated authors");

            System.out.println("___________________________________________________________________");
            System.out.println("All updated authors:");
            allAuthors = mainController.readAllAuthors();
            allAuthors.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            System.out.println("Updating books:");
            System.out.println("Adding new author to book with +1 index");
            allBooks = mainController.readAllBooks();
            for(int i = 0; i < 10; i++){
                Book bookToUpd = allBooks.get(i);
                Author authorToAdd;

                if(i == 9){
                    authorToAdd = allAuthors.get(0);
                } else {
                    authorToAdd = allAuthors.get(i + 1);
                }
                mainController.updateBookAddAuthor(bookToUpd, authorToAdd);
            }
            System.out.println("Successfully updated books");

            System.out.println("___________________________________________________________________");
            System.out.println("All updated books:");
            allBooks = mainController.readAllBooks();
            allBooks.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            System.out.println("Deleting books:");
            System.out.println("Delete 5 first books");
            allBooks = mainController.readAllBooks();
            for(int i = 0; i < 5; i++){
                Book bookToDel = allBooks.get(i);
                mainController.deleteBook(bookToDel);
            }
            System.out.println("Successfully deleted");

            System.out.println("___________________________________________________________________");
            System.out.println("All remaining books:");
            allBooks = mainController.readAllBooks();
            allBooks.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            System.out.println("Deleting authors:");
            System.out.println("Delete 5 first authors");
            allAuthors = mainController.readAllAuthors();
            for(int i = 0; i < 5; i++){
                Author authorToDel = allAuthors.get(i);
                mainController.deleteAuthor(authorToDel);
            }
            System.out.println("Successfully deleted");

            System.out.println("___________________________________________________________________");
            System.out.println("All remaining authors:");
            allAuthors = mainController.readAllAuthors();
            allAuthors.forEach(System.out::println);
            System.out.println();



            System.out.println("___________________________________________________________________");
            System.out.println("End of common test");
            System.out.println("___________________________________________________________________");


            System.out.println("Do you want to delete common test files? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                Path pathToBooksCSV = Paths.get(PathConfigs.BOOKS_FILE.getPath());
                Path pathToAuthorsCSV = Paths.get(PathConfigs.AUTHORS_FILE.getPath());

                Files.deleteIfExists(pathToBooksCSV);
                Files.deleteIfExists(pathToAuthorsCSV);

                System.out.println("Successfully deleted");
                System.out.println("___________________________________________________________________");
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

}