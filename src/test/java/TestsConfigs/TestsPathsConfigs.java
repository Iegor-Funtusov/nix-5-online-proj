package TestsConfigs;

public enum TestsPathsConfigs {
    BOOKS_TEST_FILE("booksTest.csv"),
    AUTHORS_TEST_FILE("authorsTest.csv");

    private final String path;

    TestsPathsConfigs(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
