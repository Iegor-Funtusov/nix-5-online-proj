package ua.com.alevel.app.entity;

import ua.com.alevel.lib.BaseEntity;

public class Course extends BaseEntity {
    private String name;
    private int duration;

    public Course() {}

    public Course(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String toString() {
        return "id = " + getId() + " Name = " + name + " Duration = " + duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
