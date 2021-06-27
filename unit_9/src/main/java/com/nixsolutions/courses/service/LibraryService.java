package com.nixsolutions.courses.service;

import com.nixsolutions.courses.service.impl.AuthorService;
import com.nixsolutions.courses.service.impl.BookService;

public class LibraryService {

    private static final BookService bookService = new BookService();
    private static final AuthorService authorService = new AuthorService();


}
