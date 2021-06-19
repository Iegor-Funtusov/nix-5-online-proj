package ua.com.alevel.app.entity;

import ua.com.alevel.lib.BaseEntity;

import java.util.Objects;

public class University extends BaseEntity {

    long studId;
    long courseId;

    public University(long studId, long courseId) {
        this.studId = studId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return studId == that.studId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studId, courseId);
    }

    public long getStudId() {
        return studId;
    }

    public long getCourseId() {
        return courseId;
    }
}