package ua.com.alevel;

import ua.com.alevel.controller.ConsoleController;
import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AuthorService;

public class TestsAndLogsMain {
    public static void main(String[] args) {
        ConsoleController controller = new ConsoleController();
        controller.run();

//        Author author = new Author("qqq", 6);
//        Author author2 = new Author("www", 6);
//        Author author3 = new Author("eee", 6);
//
//        AuthorService authorService = new AuthorService();
//
//        authorService.create(author);
//        authorService.create(author2);
//        authorService.create(author3);
    }
}
