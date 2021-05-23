package ua.com.nkrasnovoronka.app.controller;

import ua.com.nkrasnovoronka.app.dao.Author;
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



}
