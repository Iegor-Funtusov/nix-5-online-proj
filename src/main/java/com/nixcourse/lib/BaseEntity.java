package com.nixcourse.lib;

import java.util.UUID;

public class BaseEntity {

    protected String id;

    public String getId() {
        return id;
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}