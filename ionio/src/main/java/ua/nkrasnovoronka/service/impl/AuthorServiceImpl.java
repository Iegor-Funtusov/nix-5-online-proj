package ua.nkrasnovoronka.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nkrasnovoronka.dao.AuthorDAO;
import ua.nkrasnovoronka.dao.impl.AuthorDAOImpl;
import ua.nkrasnovoronka.model.Author;
import ua.nkrasnovoronka.model.Book;
import ua.nkrasnovoronka.service.AuthorService;

import java.util.Collection;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDAO authorDAO = new AuthorDAOImpl();

    @Override
    public void create(Author author) {
        authorDAO.create(author);
    }

    @Override
    public Collection<Author> getAllAuthors() {
        return authorDAO.findAll();
    }

    @Override
    public void updateAuthor(Long authorId, Author author) {
        authorDAO.update(authorId, author);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorDAO.findByID(id);
    }


    @Override
    public Collection<Book> getAllAuthorBooks(Long authorId) {
        return authorDAO.findAllAuthorsBooks(authorId);
    }

    @Override
    public void addBookToAuthor(Long authorId, Long bookId) {
        authorDAO.addBookToAuthor(authorId, bookId);
    }

    @Override
    public void removeBookFromAuthor(Long authorId, Long bookId) {
        authorDAO.removeBookFromAuthor(authorId, bookId);
    }
}
