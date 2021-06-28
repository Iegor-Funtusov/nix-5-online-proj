package service;

import dao.AuthorDAO;
import domain.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AuthorService {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    private final AuthorDAO authorDAO = new AuthorDAO();

    public void createAuthor(Author author) {
        if (author != null) {
            loggerInfo.info("Начало создания автора: " + author.getFirstName() + " " + author.getLastName());
            if (!author.getLastName().isEmpty()) {
                authorDAO.createAuthor(author);
                System.out.println("Автор создан: " + author.getFirstName() + " " + author.getLastName());
                loggerInfo.info("Автор создан: " + author.getFirstName() + " " + author.getLastName());
            } else {
                loggerError.error("Фамилия автора не может быть пустой!");
                System.out.println("Фамилия автора не может быть пустой!");
            }
        } else loggerError.error("Автор пуст!");
    }

    public void deleteAuthor(Author author) {
        if (author != null && authorDAO.readAuthor(author.getAuthorId()) != null) {
            loggerWarn.warn("Начало удаления автора: " + author.getLastName());
            authorDAO.deleteAuthor(author.getAuthorId());
            System.out.println("Автор удалён: " + author.getFirstName() + " " + author.getLastName());
            loggerWarn.warn("Автор удалён: "  + author.getFirstName() + " " + author.getLastName());
        } else {
            loggerError.error("Такого автора нет!");
            System.out.println("Такого автора нет!");
        }
    }

    public void updateAuthor(Author author) {
        if (author != null && authorDAO.readAuthor(author.getAuthorId()) != null) {
            loggerWarn.warn("Начало обновления автора: " + author.getFirstName() + " " + author.getLastName());
            authorDAO.updateAuthor(author);
            loggerWarn.warn("Автор обновлён: " + author.getFirstName() + " " + author.getLastName());
        } else loggerError.error("Такого автора нет!");
    }

    public List<Author> readAuthors() {
        loggerInfo.info("Список авторов: ");
        loggerInfo.info(authorDAO.readAuthors().toString());
        return authorDAO.readAuthors();
    }

    public Author findAuthor(String id) {
        if (authorDAO.readAuthor(id) != null) {
            loggerInfo.info("Поиск автора по id: " + id);
            return authorDAO.readAuthor(id);
        }
        else {
            loggerError.error("Такого автора нет!");
            return null;
        }
    }

    public List<Author> readAuthorsByBook(String id) {
        loggerInfo.info("Поиск авторов по книге с id:" + id);
        return authorDAO.readAuthors(id);
    }
}
