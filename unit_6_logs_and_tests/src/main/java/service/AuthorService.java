package service;

import dao.AuthorDAO;
import domain.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class AuthorService {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    private AuthorDAO authorDAO = new AuthorDAO();

    public void createAuthor(Author author) {
        loggerInfo.info("Начало создания автора: " + author.getFirstName() + " " + author.getLastName());
        if (!author.getLastName().isEmpty()) {
            boolean exist = authorDAO.ifExistLastName(author.getLastName());
            if (exist) {
                loggerError.error("Такой автор уже есть! " + author.getFirstName() + " " + author.getLastName());
                System.out.println("Такой автор уже есть! " + author.getFirstName() + " " + author.getLastName());
            } else {
                authorDAO.createAuthor(author);
                System.out.println("Автор создан: " + author.getFirstName() + " " + author.getLastName());
                loggerInfo.info("Автор создан: " + author.getFirstName() + " " + author.getLastName());
            }
        } else {
            loggerError.error("Фамилия автора не может быть пустой!");
            System.out.println("Фамилия автора не может быть пустой!");
       }
    }

    public void deleteAuthor(String lastName) {
        Collection<Author> list = authorDAO.findAuthors();
        Author author = checkAuthor(list, lastName);
        if (author != null) {
            loggerWarn.warn("Начало удаления автора: " + lastName);
            authorDAO.deleteAuthor(author.getAuthorId());
            System.out.println("Автор удалён: " + author.getFirstName() + " " + author.getLastName());
            loggerWarn.warn("Автор удалён: "  + author.getFirstName() + " " + author.getLastName());
        } else {
            loggerError.error("Такого автора нет! " + lastName);
            System.out.println("Такого автора нет! " + lastName);
        }
    }

    public void updateAuthor(Author author) {
        loggerWarn.warn("Начало обновления автора: " + author.getFirstName() + " " + author.getLastName());
        authorDAO.updateAuthor(author);
        loggerWarn.warn("Автор обновлён: " + author.getFirstName() + " " + author.getLastName());
    }

    public Collection<Author> findAuthors() {
        loggerInfo.info("Список авторов: ");
        loggerInfo.info(authorDAO.findAuthors().toString());
        return authorDAO.findAuthors();
    }

    public Author checkAuthor(Collection<Author> list, String name) {
        Author bookAuthor = null;
        for (Author author : list) {
            if (author.getLastName().equalsIgnoreCase(name)) {
                bookAuthor = author;
                break;
            }
        }
        return bookAuthor;
    }

    public void errorMessage() {
        loggerError.error("Такого автора нет!");
        System.out.println("Такого автора нет!");
    }
}
