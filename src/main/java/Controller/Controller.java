package Controller;

import Service.AllService;
import data.Author;
import data.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public static void runApp(){
        System.out.println("Добро пожаловать в Библиотеку");




        AllService allService = new AllService();
        Scanner scanner = new Scanner(System.in);
        allService.initFiles();


        while (true) {
            System.out.println("\nA - операции с Авторами(crud)" +
                    "\nB - опреации с Книгами(crud)" +
                    "\nC - общие операции(получить авторов по книге, получить книги по автору.)" +
                    "\n_____________" +
                    "\nX- выход");
            String a = scanner.next();
            char[] cur = a.toCharArray();

            switch (cur[0]) {
                case 'A': {

                    System.out.println("Работа с Авторами:" +
                            "\nA - добавить автора" +
                            "\nB - изменить даные автора" +
                            "\nC - удалить автора(visible = false)" +
                            "\nD - получить инфо про всех авторов" +
                            "\n_____________\nX- выход");
                    a = scanner.next();
                    cur = a.toCharArray();

                    switch (cur[0]) {
                        case 'A': {
                            Author author = new Author();
                            System.out.println("Введите Имя автора и нажмите Enter:");
                            author.setFirstName(scanner.next());
                            System.out.println("Введите Фамилию автора и нажмите Enter:");
                            author.setLastName(scanner.next());
                            author.setVisibleFlag(true);
                            allService.createAuthor(author);
                            System.out.println("Автор создан");
                            System.out.println("_____________________________________________");
                            break;
                        }
                        case 'B': {
                            System.out.println("Введите ID Автора");
                            String id = scanner.next();
                            if ((allService.findAuthorById(id)) == null) {
                                System.out.println("Неверный ID, попробуйте снова");
                                break;
                            }
                            Author author = allService.findAuthorById(id);
                            System.out.println("N-изменить Имя\nA-изменить Фамилию\nХ - выйти");
                            String b = scanner.next();
                            char[] cur_e = b.toCharArray();
                            switch (cur_e[0]) {
                                case 'N': {
                                    System.out.println("Введите новое Имя");
                                    author.setFirstName(scanner.next());
                                    allService.updateAuthor(author);
                                    System.out.println("Успешно изменено на " + author.getFirstName());
                                    System.out.println("_____________________________________________");
                                    break;
                                }
                                case 'A': {
                                    System.out.println("Введите новую Фамилию");
                                    author.setLastName(scanner.next());
                                    allService.updateAuthor(author);
                                    System.out.println("Успешно изменено на " + author.getLastName());
                                    System.out.println("_____________________________________________");
                                    break;
                                }
                                case 'X': {
                                    System.exit(0);
                                    break;
                                }
                                default:
                                    System.out.println("Неверно ввели");

                            }
                            break;

                        }
                        case 'C': {
                            System.out.println("Введите ID автора");
                            try {
                                allService.deleteAuthor(scanner.next());
                            } catch (RuntimeException ex) {
                                System.err.println("Неверно введен ID, попробуйте снова");
                            }
                            System.out.println("Успешно");
                            break;
                        }
                        case 'D': {
                            List<Author> authors = allService.readAllAuthor();
                            for (Author author : authors) {
                                System.out.println(author);
                            }
                            break;
                        }
                        case 'X': {
                            System.out.println("Переход в главное меню...");
                            break;
                        }
                        default: {
                            System.out.println("Ни один вариант не совпадает с предлагаемыми!!!");
                            break;
                        }
                    }
                    break;

                }


                case 'B': {
                    System.out.println("Работа с Книгами:" +
                            "\nA - добавить книгу" +
                            "\nB - изменить название книги" +
                            "\nC - удалить книгу (visible = false)" +
                            "\nD - получить инфо про все книги" +
                            "\n_____________\nX- выход");

                    a = scanner.next();
                    cur = a.toCharArray();

                    switch (cur[0]) {
                        case 'A': {
                            Book book = new Book();
                            System.out.println("Введите название книги и нажмите Enter:");
                            book.setTitle( scanner.next());
                            book.setVisibleFlag(true);

                            List<String> autId = new ArrayList<>();
                            System.out.println("Введите сколько авторов у книги(не меньше 1) и нажмите Enter:");
                            int i = scanner.nextInt();
                            for (int j = 0; j < i; j++) {
                                System.out.println("ведите ИД автора и нажмите Enter");
                                String s = scanner.next();
                                autId.add(s);
                            }
                            book.setAuthors(autId);
                            allService.createBook(book,autId);
                            System.out.println("Книга добавлена");
                            System.out.println("_____________________________________________");
                            break;
                        }
                        case 'B': {

                                System.out.println("Введите ID Книги");
                                String id = scanner.next();
                                if ((allService.findBookById(id)) == null) {
                                    System.out.println("Неверный ID, попробуйте снова");
                                    break;
                                }
                                Book book = allService.findBookById(id);
                                System.out.println("N-изменить Название\nХ - выйти");
                                String b = scanner.next();
                                char[] cur_e = b.toCharArray();
                                switch (cur_e[0]) {
                                    case 'N': {
                                        System.out.println("Введите новое Название");
                                        book.setTitle(scanner.next());
                                        allService.updateBook(book);
                                        System.out.println("Успешно изменено на " + book.getTitle());
                                        System.out.println("_____________________________________________");
                                        break;
                                    }
                                    case 'X': {
                                        System.exit(0);
                                        break;
                                    }
                                    default:
                                        System.out.println("Неверно ввели");

                                }
                                break;


                        }
                        case 'C': {
                            System.out.println("Введите ID книги");
                            try {
                                allService.deleteBook(scanner.next());
                            } catch (RuntimeException ex) {
                                System.err.println("Неверно введен ID, попробуйте снова");
                            }
                            System.out.println("Успешно");
                            break;
                        }
                        case 'D': {
                            List<Book> books = allService.readAllBook();
                            for (Book book : books) {
                                System.out.println(book);
                            }
                            break;
                        }
                        case 'X': {
                            System.out.println("Переход в главное меню...");
                            System.exit(0);
                            break;
                        }
                        default: {
                            System.out.println("Ни один вариант не совпадает с предлагаемыми!!!");
                        }
                    }
                    break;
                }


                case 'C': {
                    System.out.println("Работа с общими операциями:" +
                            "\nA - найти книги по автору" +
                            "\nB - найти авторов по книге" +
                            "\n_____________\nX- выход");
                    a = scanner.next();
                    cur = a.toCharArray();

                    switch (cur[0]) {
                        case 'A': {
                            System.out.println("Введите ИД автора и нажмите Enter:");
                            String na = scanner.next();
                            if(allService.findAuthorById(na)==null){
                                System.out.println("автора с таким ИД нет");
                                break;
                            }
                            List<Book> books = allService.findBookByAut(na);
                            for (Book book : books) {
                                System.out.println(book);
                            }
                            if (books.get(0) == null){
                                System.out.println("У этого автора нет книг");
                                break;
                            }
                            break;

                        }
                        case 'B': {
                            System.out.println("Введите ИД книги и нажмите Enter:");
                            String na = scanner.next();
                            if(allService.findBookById(na)==null){
                                System.out.println("книги с таким ИД нет");
                                break;
                            }
                            List<Author> authors = allService.findAutByBook(na);
                            for (Author author : authors) {
                                System.out.println(author);
                            }
                            break;
                        }
                        case 'X': {
                            System.out.println("Переход в главное меню...");
                            System.exit(0);
                            break;
                        }
                        default: {
                            System.out.println("Ни один вариант не совпадает с предлагаемыми!!!");
                        }
                    }
                    break;
                }


                case 'X': {
                    System.out.println("Goodbye");
                    System.exit(0);
                    break;
                }


                default: {
                    System.out.println("Неверно ввели");
                    break;
                }

            }
        }

    }

}
