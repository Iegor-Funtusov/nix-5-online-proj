package book;

public class BookService {

    private final BookDao bookDao = new BookDao();

    public void create(Book book) {
        if (!(isParamNull(book))) {
            bookDao.create(book);
        }
    }

    public void update(Book book) {
        if (!(isBookExistById(book.getId()))) {
            bookDao.update(book);
        }
    }

    public Book[] readAll() {
        return bookDao.readAll();
    }

    public Book findById(String id) {
        if (!isBookExistById(id)) {
            return bookDao.findById(id);
        }
        return null;
    }

    public void delete(String id) {
        if (!isBookExistById(id)) {
            bookDao.delete(id);
        }
    }

    private boolean isBookExistById(String id) {
        Book CurrentBook = bookDao.findById(id);
        if (CurrentBook.getId() != null) {
            if (CurrentBook.getId().equals(id)) {
                return false;
            } else {
            }
        } else {
        }
        return true;
    }

    public boolean isParamNull(Book book) {
        return book.getTitle() == null;
    }
}
