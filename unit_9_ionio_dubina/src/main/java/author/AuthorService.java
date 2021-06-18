package author;

public class AuthorService {

private AuthorDao authorDao = new AuthorDao();

    public void create(Author author) {
        if (!isParamNull(author)) {
            authorDao.create(author);
        }
    }

    public void update(Author author) {
        if (!isAuthorExistById(author.getId())) {
            authorDao.update(author);
        }
    }

    public Author[] readAll() {
        return authorDao.readAll();
    }

    public Author findById(String id) {
        if (!isAuthorExistById(id)) {
            return authorDao.findById(id);
        }
        return null;
    }

    public void delete(String id) {
        if (!isAuthorExistById(id)) {
            authorDao.delete(id);
        }
    }

    private boolean isAuthorExistById(String id) {
        Author currentAuthor = authorDao.findById(id);
        if (currentAuthor.getId() != null) {
            if (currentAuthor.getId().equals(id)) {
                return false;
            } else {
            }
        } else {
        }
        return true;
    }

    public boolean isParamNull(Author author) {
        return author.getSurname() == null || author.getName() == null;
    }

}