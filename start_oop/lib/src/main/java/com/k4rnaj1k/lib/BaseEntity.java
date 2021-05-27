package com.k4rnaj1k.lib;

public abstract class BaseEntity {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "com.k4rnaj1k.lib.BaseEntity{" +
                "id='" + id + '\'' +
                '}';
    }
}
