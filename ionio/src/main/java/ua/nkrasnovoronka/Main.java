package ua.nkrasnovoronka;

import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.service.AuthorService;
import ua.nkrasnovoronka.service.impl.AuthorServiceImpl;

public class Main {
    public static void main(String[] args) {
        AuthorService authorService = new AuthorServiceImpl();
        Author author = new Author();
        author.setFirstName("Test");
        authorService.create(author);
        author.setFirstName("Kall");
        authorService.create(author);

        System.out.println(authorService.getAllAuthors());
    }
}
