package type;

public enum FileType {
    BOOKS("books.csv"),
    AUTHORS("authors.csv");

    private final String PATH;

    FileType(String path) {
        PATH = path;
    }

    public String getPATH() {
        return PATH;
    }
}
