package com.nixsolutions.courses;

import com.nixsolutions.courses.data.Author;
import com.nixsolutions.courses.service.impl.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AuthorCrudTest {

    private static final AuthorService authorService = new AuthorService();
    private static final String TEST_STRING = "test";

    @BeforeAll
    static void init() {
        authorService.init();
    }

    @Test
    void create() {
        Author author = new Author();
        author.setName(TEST_STRING);
        author.setSurname(TEST_STRING);
        authorService.create(author);
        Author createdAuthor = authorService.findById(author.getId());

        Assertions.assertEquals(author, createdAuthor);
    }
}
