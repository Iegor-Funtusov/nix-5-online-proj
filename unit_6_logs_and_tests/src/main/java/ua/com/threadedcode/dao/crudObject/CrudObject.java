package ua.com.threadedcode.dao.crudObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.threadedcode.dao.ICrudProcess;
import ua.com.threadedcode.entity.Author;
import ua.com.threadedcode.entity.BaseEntity;
import ua.com.threadedcode.entity.Book;

import java.util.*;
import java.util.stream.Collectors;

public class CrudObject<E extends BaseEntity> implements ICrudProcess<E> {
    private static CrudObject instance;
    private final int STORAGE_SIZE = 10;

    private Object[] authorsStorage = new Object[STORAGE_SIZE];
    private Object[] bookStorage = new Object[STORAGE_SIZE];

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public static CrudObject getInstance() {
        if (instance == null) {
            instance = new CrudObject();
        }
        return instance;
    }

    @Override
    public void createAuthor(Author author) {
        if (!Arrays.stream(authorsStorage)
                .filter(e -> Objects.nonNull(e))
                .anyMatch(e -> (((Author) e).equals(author)))
        ) {
            author.setId(generateId(UUID.randomUUID().toString(), authorsStorage));
            for (int i = 0; i < authorsStorage.length; i++) {
                if (authorsStorage[i] == null) {
                    try {
                        authorsStorage[i] = (Author) author.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            loggerInfo.info("author " + author.getFirstName() + " " + author.getLastName() + " is created");
        } else {
            loggerInfo.info("author " + author.getFirstName() + " " + author.getLastName() + " is already exist");
        }
    }


    @Override
    public List findBooksByAuthorFistName(String firstname) {
        Object[] a = new Object[STORAGE_SIZE];
        Author author = (Author) getAuthorByName(firstname);
        Object[] bookIDs = author.getBookId();

        if (Objects.nonNull(bookIDs)) {
            for (Object book : bookStorage) {
                if (Objects.nonNull(book)) {
                    for (int i = 0; i < bookIDs.length; i++) {
                        if (Objects.nonNull(bookIDs[i]) && ((Book) book).getId().equals(bookIDs[i])) {
                            a[i] = ((Book) book).getTitle();
                        }
                    }
                }
            }
        } else {
            a[0] = String.format("Author %s %s hasn't any book yet", author.getFirstName(), author.getLastName());
        }
        return Arrays.stream(a)
                .filter(o -> Objects.nonNull(o))
                .collect(Collectors.toList());
    }

    @Override
    public void updateAuthor(Author author) {
        int index = getEntityIndexById((E) author);
        try {
            authorsStorage[index] = (Author) author.clone();
            loggerInfo.info("author " + author.getFirstName() + " " + author.getLastName() + " is updated");
        } catch (CloneNotSupportedException e) {
            loggerError.error(String.valueOf(e));
        }

//        System.out.printf("Author %s %s %s is updated %n",
//                author.getFirstName(),
//                author.getLastName(),
//                Arrays.toString(author.getBookId()));
    }

    @Override
    public void deleteAuthor(String firstName) {
        Author author = (Author) getAuthorByName(firstName);
        int index = getAuthorIndexByFirstname(authorsStorage, firstName);
        authorsStorage[index] = null;
        loggerWarn.warn("author " + author.getFirstName() + " " + author.getLastName() + " is deleted");
//        System.out.printf("Author %s %s %s is deleted %n",
//                a.getFirstName(),
//                a.getLastName(),
//                Arrays.toString(a.getBookId()));
    }

    @Override
    public List getAllAuthors() {
        return Arrays.stream(authorsStorage)
                .filter(o -> Objects.nonNull(o))
                .collect(Collectors.toList());
    }


    public Author getAuthorByName(String firstName) {
        return (Author) Arrays.stream(authorsStorage)
                .filter(author -> ((Author) author).getFirstName().equals(firstName))
                .findFirst().orElse(null);
    }

    @Override
    public void createBook(Book book) {
        if (!Arrays.stream(bookStorage)
                .filter(e -> Objects.nonNull(e))
                .anyMatch(e -> (((Book) e).equals(book)))
        ) {
            book.setId(generateId(UUID.randomUUID().toString(), bookStorage));

            for (int i = 0; i < bookStorage.length; i++) {
                if (bookStorage[i] == null) {
                    try {
                        bookStorage[i] = (Book) book.clone();
                        loggerInfo.info("book " + book.getTitle() + " is created");
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
//            System.out.printf("Book %s is created %n", book.getTitle());
        }
    }

    @Override
    public void updateBook(Book book) {
        int index = getEntityIndexById((E) book);
        try {
            bookStorage[index] = (Book) book.clone();
            loggerInfo.info("book " + book.getTitle() + " is updated");
        } catch (CloneNotSupportedException e) {
            loggerError.error(String.valueOf(e));
        }
    }

    @Override
    public void deleteBook(String title) {
        Book book = (Book) findBookByTitle(title);
        int index = getEntityIndexById((E) book);
        Object[] bookIDs = book.getAuthorId();

        if (!Objects.nonNull(bookIDs) || Arrays.stream(bookIDs).allMatch(Objects::isNull)) {
            bookStorage[index] = null;
            loggerWarn.warn("book " + title + " is deleted");
        } else {
            loggerWarn.warn("can not delete a book with a few authors");
        }
    }

    @Override
    public void deleteBookWithAuthor(String title, String firstname) {
        Book book = (Book) findBookByTitle(title);
        Object[] authorsId = book.getAuthorId();

        Author author = getAuthorByName(firstname);
        String authorId = author.getId();

        for (int i = 0; i < authorsId.length; i++) {
            if (authorsId[i].equals(authorId)) {
                authorsId[i] = null;
                loggerWarn.warn(author.getFirstName() + "," + author.getLastName() + " is removed from book author's");
                break;
            }
        }
        deleteBook(title);
    }

    @Override
    public List getAllBooks() {
        return Arrays.stream(bookStorage)
                .filter(o -> Objects.nonNull(o))
                .collect(Collectors.toList());
    }

    @Override
    public Book findBookByTitle(String title) {
        return (Book) Arrays.stream(bookStorage)
                .filter(book -> ((Book) book).getTitle().equals(title))
                .findFirst().orElse(null);
    }

    private String generateId(String id, Object[] storage) {
        if (Arrays.stream(storage)
                .filter(e -> Objects.nonNull(e))
                .anyMatch(e -> ((E) e).getId().equals(id))
        ) {
            return generateId(UUID.randomUUID().toString(), storage);
        }
        return id;
    }

    private Object[] getStorage(E e) {
        if (e.getClass().getSimpleName().equals("Author")) {
            return authorsStorage;
        }
        return bookStorage;
    }

    public int getAuthorIndexByFirstname(Object[] storage, String firsName) {
        int index = 555;

        for (int i = 0; i < storage.length; i++) {
            if (((Author) storage[i]).getFirstName().equals(firsName)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int getEntityIndexById(E entity) {
        int index = 555;
        Object[] storage = getStorage(entity);

        for (int i = 0; i < storage.length; i++) {
            if (((E) storage[i]).getId().equals(entity.getId())) {
                index = i;
                break;
            }
        }
        return index;
    }
}
