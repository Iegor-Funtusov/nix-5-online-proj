package com.nixcourse.lib;

import java.util.UUID;

public class BaseEntity {

    private String id;

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
