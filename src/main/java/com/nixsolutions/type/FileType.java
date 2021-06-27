package com.nixsolutions.type;

public enum FileType {
    FILE_BOOKS("books.txt"),
    FILE_AUTHORS("authors.txt");

    private final String path;

    FileType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}