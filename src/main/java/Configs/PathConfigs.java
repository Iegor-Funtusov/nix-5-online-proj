package Configs;

public enum PathConfigs {
    //Хранятся пути к файлам
    BOOKS_FILE("Books.csv"),
    AUTHORS_FILE("Authors.csv");

    private final String path;

    PathConfigs(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
