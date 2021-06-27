package com.nixsolutions.courses.util;

public enum FilePaths {

    AUTHORS("authors.csv"),
    BOOKS("books.csv");

    private final String path;

    FilePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
