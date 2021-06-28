import csvfile.CSVFile;
import domain.Author;
import domain.Book;
import service.AuthorService;
import service.BookService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LibraryController {

    AuthorService authorService = new AuthorService();
    BookService bookService = new BookService();
    CSVFile CSVFile = new CSVFile();

    public void readConsole() {
        try {
            CSVFile.createFiles();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Добро пожаловать в библиотеку!");
            System.out.println("Выберите действие или нажмите '0' для выхода: ");
            System.out.println("1 - занесение информации в библиотеку");
            System.out.println("2 - просмотр библиотеки");
            System.out.println("3 - обновление информации");
            System.out.println("4 - удаление информации");
          //  System.out.println("5 - СommonTest");
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        createInfo(reader);
                        break;
                    case "2":
                        readInfo(reader);
                        break;
                    case "3":
                        updateInfo(reader);
                        break;
                    case "4":
                        deleteInfo(reader);
                        break;
                 /*   case "5":
                        commonTest(reader);
                        break;*/
                    default:
                        // throw new IllegalArgumentException("Неизвестный выбор!");
                        System.out.println("Неизвестный выбор!");
                }
                System.out.println("Выберите действие или нажмите '0' для выхода: ");
                System.out.println("1 - занесение информации в библиотеку");
                System.out.println("2 - просмотр библиотеки");
                System.out.println("3 - обновление информации");
                System.out.println("4 - удаление информации");
              //  System.out.println("5 - commonTest");
                input = reader.readLine();
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void createInfo(BufferedReader reader) {
        try {
            System.out.println("Выберите действие или нажмите '0' для выхода: ");
            System.out.println("1 - создание автора");
            System.out.println("2 - создание книги");
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        createAuthor(reader);
                        break;
                    case "2":
                        createBook(reader);
                        break;
                    default:
                        // throw new IllegalArgumentException("Неизвестный выбор!");
                        System.out.println("Неизвестный выбор!");
                }
                System.out.println("Выберите действие или нажмите '0' для выхода: ");
                System.out.println("1 - создание автора");
                System.out.println("2 - создание книги");
                input = reader.readLine();
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void readInfo(BufferedReader reader) {
        try {
            System.out.println("Выберите действие или нажмите '0' для выхода: ");
            System.out.println("1 - просмотр всех авторов");
            System.out.println("2 - просмотр книг автора");
            System.out.println("3 - просмотр авторов книги");
            System.out.println("4 - просмотр всех книг");
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        readAllAuthors();
                        break;
                    case "2":
                        findBooksByAuthor(reader);
                        break;
                    case "3":
                        findAuthorsByBook(reader);
                        break;
                    case "4":
                        readAllBooks();
                        break;
                    default:
                        //  throw new IllegalArgumentException("Неизвестный выбор!");
                        System.out.println("Неизвестный выбор!");
                }
                System.out.println("Выберите действие или нажмите '0' для выхода: ");
                System.out.println("1 - просмотр всех авторов");
                System.out.println("2 - просмотр книг автора");
                System.out.println("3 - просмотр авторов книги");
                System.out.println("4 - просмотр всех книг");
                input = reader.readLine();
                //  break;
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void updateInfo(BufferedReader reader) {
        try {
            System.out.println("Выберите действие или нажмите '0' для выхода: ");
            System.out.println("1 - обновление автора");
            System.out.println("2 - обновление книги");
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        updateAuthor(reader);
                        break;
                    case "2":
                        updateBook(reader);
                        break;
                    default:
                        //  throw new IllegalArgumentException("Неизвестный выбор!");
                        System.out.println("Неизвестный выбор!");
                }
                System.out.println("Выберите действие или нажмите '0' для выхода: ");
                System.out.println("1 - обновление автора");
                System.out.println("2 - обновление книги");
                input = reader.readLine();
                //   break;
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void deleteInfo(BufferedReader reader) {
        try {
            System.out.println("Выберите действие или нажмите '0' для выхода: ");
            System.out.println("1 - удаление автора");
            System.out.println("2 - удаление книги");
            String input = reader.readLine();
            while (!"0".equals(input)) {
                switch (input) {
                    case "1":
                        deleteAuthor(reader);
                        break;
                    case "2":
                        deleteBook(reader);
                        break;
                    default:
                        //  throw new IllegalArgumentException("Неизвестный выбор!");
                        System.out.println("Неизвестный выбор!");
                }
                //  break;
                System.out.println("Выберите действие или нажмите '0' для выхода: ");
                System.out.println("1 - удаление автора");
                System.out.println("2 - удаление книги");
                input = reader.readLine();
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void createAuthor(BufferedReader reader) {
        try {
            Author author = new Author();
            System.out.println("Введите имя автора:");
            String name = reader.readLine();
            author.setFirstName(name);
            System.out.println("Введите фамилию:");
            String surname = reader.readLine();
            author.setLastName(surname);
            author.setVisible(true);
            authorService.createAuthor(author);
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void createBook(BufferedReader reader) {
        try {
            Book book = new Book();
            System.out.println("Введите название книги:");
            book.setTitle(reader.readLine());
            book.setVisible(true);
            List<String> authorsIds = new ArrayList<>();
            System.out.println("Введите количество авторов:");
            int n = Integer.parseInt(reader.readLine());
            for (int j = 0; j < n; j++) {
                System.out.println("Введите id автора");
                String id = reader.readLine();
                authorsIds.add(id);
            }
            book.setAuthors(authorsIds);
            bookService.createBook(book, authorsIds);
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void readAllAuthors() {
        List<Author> authors = authorService.readAuthors();
        authors.forEach(System.out::println);
    }

    private void readAllBooks() {
        List<Book> books = bookService.readBooks();
        System.out.println(books);
    }

  /*  private void findAuthor(BufferedReader reader) {
        System.out.println("Введите Id автора:");
        String id = null;
        try {
            id = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Author author = authorService.findAuthor(id);
        System.out.println(author);

    }*/

  /*  private void findBook(BufferedReader reader) {
        System.out.println("Введите Id книги: ");
        String id = null;
        try {
            id = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Book book = bookService.findBook(id);
        System.out.println(book);
    }*/

    private void updateAuthor(BufferedReader reader) {
        try {
            System.out.println("Enter id");
            String id = reader.readLine();
      /*  if (authorService.readAuthor(id) == null) {
            System.out.println("Автора с таким id нет!");
        }*/
            Author bookAuthor = authorService.findAuthor(id);
            if (bookAuthor != null) {
                System.out.println("Выберите действие или нажмите '0' для выхода: ");
                System.out.println("1 - изменение имени автора");
                System.out.println("2 - изменение фамилии автора");
                String input = reader.readLine();
                while (!"0".equals(input)) {
                    switch (input) {
                        case "1":
                            System.out.println("Введите новое имя автора:");
                            bookAuthor.setFirstName(reader.readLine());
                            authorService.updateAuthor(bookAuthor);
                            break;
                        case "2":
                            System.out.println("Введите новую фамилию автора:");
                            bookAuthor.setLastName(reader.readLine());
                            authorService.updateAuthor(bookAuthor);
                            break;
                        default:
                          //  throw new IllegalArgumentException("Неизвестный выбор!");
                            System.out.println("Неизвестный выбор!");
                    }
                    System.out.println("Выберите действие или нажмите '0' для выхода: ");
                    System.out.println("1 - изменение имени автора");
                    System.out.println("2 - изменение фамилии автора");
                    input = reader.readLine();
                }
                System.out.println("Автор обновлён: " + bookAuthor.getLastName() + " " + bookAuthor.getFirstName());
            } else System.out.println("Такого автора нет!");
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void updateBook(BufferedReader reader) {
        try {
            System.out.println("Введите id книги");
            String id = reader.readLine();
     /*   if (bookService.readBook(id) == null) {
            System.out.println("Неверный id!");
        }*/
            Book book = bookService.findBook(id);
            if (book != null) {
                System.out.println("Введите новое название книги:");
                book.setTitle(reader.readLine());
                bookService.updateBook(book);
                System.out.println("Книга " + "\"" + book.getTitle() + "\"" + " изменена");
            } else System.out.println("Такой книги нет!");
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void deleteAuthor(BufferedReader reader) {
        try {
            System.out.println("Введите id автора для удаления:");
            String authorId = reader.readLine();
            if (authorId == null) {
                System.out.println("Неверный ввод!");
                return;
            }
            try {
                authorService.deleteAuthor(authorService.findAuthor(authorId));
            } catch (Exception e) {
                System.out.println("Такого автора нет!");
            }
            } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void deleteBook(BufferedReader reader) {
        try {
            System.out.println("Введите Id книги для удаления:");
            String bookId = reader.readLine();
            if (bookId == null) {
                System.out.println("Неверный ввод!");
                return;
            }
            try {
                bookService.deleteBook(bookService.findBook(bookId));
            } catch (Exception e) {
                System.out.println("Книги с таким id нет!");
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void findBooksByAuthor(BufferedReader reader) {
        try {
            System.out.println("Введите id автора: ");
            String id = reader.readLine();
            if (authorService.findAuthor(id) == null) {
                System.out.println("Неверный id!");
            }
            List<Book> books = bookService.readBooksByAuthor(id);
            System.out.println("Список книг автора с id: " + authorService.findAuthor(id).getLastName());
            books.forEach(System.out::println);
            if (books.get(0) == null) {
                System.out.println("У автора нет книг!");
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void findAuthorsByBook(BufferedReader reader) {
        try {
            System.out.println("Введите id книги: ");
            String id = reader.readLine();
            if (bookService.findBook(id) == null) {
                System.out.println("Неверный id!");
            }
            List<Author> authors = authorService.readAuthorsByBook(id);
            System.out.println("Список авторов для книги с id: " + bookService.findBook(id).getTitle());
            authors.forEach(System.out::println);
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }

    private void commonTest(BufferedReader reader) {
        try {
            File authors = new File("authors.csv");
            File books = new File("books.csv");
          //  new CommonTest().test();
            System.exit(0);
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println(e + "Ошибка ввода/выводв!");
        }
    }
}
