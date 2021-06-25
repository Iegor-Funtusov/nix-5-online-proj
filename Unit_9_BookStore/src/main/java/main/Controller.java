package main;

import data.Author;
import data.Book;
import services.LibraryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Controller {
    public void start(){
        chooseEntity();
    }
    private void chooseEntity(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("With which entity would you like to work?\n" +
                    "1 >> Books\n" +
                    "2 >> Authors\n" +
                    "3 >> exit from program");
            String entity = sc.next();
            switch (entity) {
                case "1": {
                    booksCRUD();
                }
                break;
                case "2": {
                    authorsCRUD();
                }
                break;
                case "3": {
                    System.exit(0);
                }
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private void booksCRUD(){
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Choose CRUD operation:\n" +
                    "1 >> create\n" +
                    "2 >> read all books\n" +
                    "3 >> read all Authors of a certain Book\n" +
                    "4 >> update\n" +
                    "5 >> delete\n" +
                    "6 >> return to choice of entities");
            String choice = scanner.next();
            switch (choice) {
                case "1": {
                    System.out.println("Input name of book:");
                    String name = name();
                    System.out.println("Input Author(s) of book separated by comma:");
                    String list = list();
                    Book book = new Book();
                    book.setName(name);
                    book.setListOfAuthors(list);
                    LibraryService.createBook(book);
                }break;
                case "2": {
                    LibraryService.readAllBooks();
                }
                break;
                case "3": {
                    System.out.println("Input name of book by the authors which you want to know:");
                    String name;
                    name = name();
                    LibraryService.readAllBooks(name);
                }
                break;
                case "4": {
                    boolean flag = true;
                    while (flag) {
                        String bookName, authorName, newInput;
                        try {
                            System.out.println("Input name of book that you want to change");
                            bookName = name();
                            System.out.println("Input at least one of the authors (if some authors exists)");
                            authorName = reader.readLine();

                            System.out.println("Input new name of book");
                            newInput = name();
                            LibraryService.updateBook(bookName, authorName, newInput, 1);
                            flag = false;

                        } catch (IOException e) {
                            System.out.println("Incorrect input. Try again");
                        }
                    }
                }
                break;
                case "5": {
                    System.out.println("Input name of book that you want to delete");
                    String bookName, authorName;
                    try {
                        bookName = name();
                        System.out.println("Input at list one of the authors of book (if such exists) that you want to delete");
                        authorName = reader.readLine();
                        LibraryService.deleteBook(bookName, authorName);
                    } catch (IOException e) {
                        System.out.println("Incorrect input. Try again");
                    }
                }break;
                case "6":{
                    return;
                }
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private String name(){
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if(name.length() > 40){
            System.out.println("Name is too long. Input again");
            return name();
        }
        if(name.isEmpty()){
            System.out.println("Your input is empty. Input again");
            return name();
        }
        return name;
    }

    private String list(){
        Scanner scanner = new Scanner(System.in);
        String authors = scanner.nextLine();
        if(authors.isEmpty()){
            System.out.println("Your input is empty. Input again");
            return list();
        }
        for (int i = 0; i < authors.length(); i++) {
            if(!(Character.isLetter(authors.charAt(i)) || authors.charAt(i) == ' ' || authors.charAt(i) == ',')){
                System.out.println("Input only name and last name of authors and separate them via comma");
                return list();
            }
        }
        String[] listOfAuthors = authors.split(",");
        for (int i = 0; i < listOfAuthors.length; i++) {
            listOfAuthors[i] = listOfAuthors[i].trim();
            String[] fullName = listOfAuthors[i].split(" ");
            if(fullName.length != 2){
                System.out.println("Incorrect input. Input should contain first and last names. Input again");
                return list();
            }
        }
        String list = "";
        for (int i = 0; i < listOfAuthors.length; i++) {
            if(i == listOfAuthors.length - 1)
                list += listOfAuthors[i];
            else
                list += listOfAuthors[i] + ", ";
        }
        return list;
    }

    private void authorsCRUD(){
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Choose CRUD operation:\n" +
                    "1 >> create\n" +
                    "2 >> read all Authors\n" +
                    "3 >> read all Books of a certain Author\n" +
                    "4 >> update\n" +
                    "5 >> delete\n" +
                    "6 >> return to choice of entities");
            String choice = scanner.next();
            switch (choice) {
                case "1": {
                    Author author = new Author();
                    System.out.println("Input first name of author");
                    author.setFirstName(FirstLastName());
                    System.out.println("Input last name of author");
                    author.setLastName(FirstLastName());
                    try {
                        String YesNo;
                        while (true) {
                            System.out.println("Would you like to add books?\n" +
                                    "1 >> yes\n" +
                                    "2 >> no");
                            YesNo = reader.readLine();
                            if(!(YesNo.equals("1") || YesNo.equals("2"))){
                                System.out.println("Wrong input. Input again 1 or 2");
                            }
                            else
                                break;
                        }
                        if(YesNo.equals("1")) {
                            System.out.println("Input books of author (separated by comma)");
                            author.setListOfBooks(listOfBooks());
                        }
                        else
                            author.setListOfBooks("");
                        LibraryService.createAuthor(author);
                    } catch (IOException e) {
                        System.out.println("Incorrect input. Try again");
                    }
                }
                break;
                case "2": {
                    LibraryService.readAllAuthors();
                }
                break;
                case "3": {
                    System.out.println("Input name of author which you want to find:");
                    String name;
                    try {
                        name = reader.readLine();
                        LibraryService.readAllAuthors(name);
                    } catch (IOException e) {
                        System.out.println("Incorrect input. Try again");
                    }
                }
                break;
                case "4": {
                    boolean flag = true;
                    while (flag) {
                        String oldBook, authorName, authorLastName, fullName, newInput, decision;
                        try {
                            System.out.println("Input author's first name:");
                            authorName = FirstLastName();
                            System.out.println("Input author's last name");
                            authorLastName = FirstLastName();
                            fullName = authorName + " " + authorLastName;
                            System.out.println("What do you want to change:\n" +
                                    "1 >> name of book\n" +
                                    "2 >> author's name");
                            decision = reader.readLine();
                            switch (decision) {
                                case "1": {
                                    System.out.println("Input name of book that you want to change:");
                                    oldBook = name();
                                    System.out.println("Input new name of book:");
                                    newInput = name();
                                    LibraryService.updateAuthor(oldBook, fullName, newInput, 1);
                                    flag = false;
                                }
                                break;
                                case "2": {
                                    System.out.println("Input new name of author:");
                                    newInput = FirstLastName();
                                    System.out.println("Input new last name of author:");
                                    newInput += " " + FirstLastName();
                                    LibraryService.updateAuthor("", fullName, newInput, 2);
                                    flag = false;
                                }
                                break;
                                default:
                                    System.out.println("Wrong input. Input again");
                            }
                        } catch (IOException e) {
                            System.out.println("Incorrect input. Try again");
                        }
                    }
                }
                break;
                case "5": {
                    System.out.println("Input author that you want to delete: ");
                    String name;
                    try {
                        name = reader.readLine();
                        LibraryService.deleteAuthor(name);
                    } catch (IOException e) {
                        System.out.println("Incorrect input. Try again");
                    }
                }
                break;
                case "6":{
                    return;
                }
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private String FirstLastName(){
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if(name.length() > 40){
            System.out.println("Input is too long. Input again");
            return FirstLastName();
        }
        if(name.isEmpty()){
            System.out.println("Your input is empty. Input again");
            return FirstLastName();
        }
        for (int i = 0; i < name.length(); i++) {
            if(!Character.isLetter(name.charAt(i))){
                System.out.println("Name and last name can contain only letters. Input again");
                return FirstLastName();
            }
        }
        return name;
    }

    private String listOfBooks(){
        Scanner scanner = new Scanner(System.in);
        String listOfBooks = scanner.nextLine();
        if(listOfBooks.length() > 40){
            System.out.println("Input is too long. Input again");
            return listOfBooks();
        }
        if(listOfBooks.isEmpty()){
            System.out.println("Your input is empty. Input again");
            return listOfBooks();
        }
        for (int i = 0; i < listOfBooks.length(); i++) {
            if(!Character.isLetter(listOfBooks.charAt(i))){
                System.out.println("Input list of book separated by comma without any excess symbols. Input again");
                return listOfBooks();
            }
        }

        String[] books = listOfBooks.split(",");
        String list = "";
        for (int i = 0; i < books.length; i++) {
            if(i == books.length - 1)
                list += books[i];
            else
                list += books[i] + ", ";
        }
        return list;
    }
}
