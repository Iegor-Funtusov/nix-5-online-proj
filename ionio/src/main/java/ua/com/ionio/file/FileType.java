package ua.com.ionio.file;

public enum FileType {

    CSV_AUTHOR("authors.csv"),
    CSV_BOOKS("books.csv");

    private final String path;

    FileType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
