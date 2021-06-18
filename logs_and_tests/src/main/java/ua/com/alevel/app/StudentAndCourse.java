package ua.com.alevel.app;

import ua.com.alevel.lib.BaseEntity;

import java.util.Objects;

public class StudentAndCourse extends BaseEntity {
    long studId;
    long courseId;

    public StudentAndCourse(long studId, long courseId) {
        this.studId = studId;
        this.courseId = courseId;
    }

    public StudentAndCourse() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAndCourse that = (StudentAndCourse) o;
        return studId == that.studId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studId, courseId);
    }

    public long getStudId() {
        return studId;
    }

    public void setStudId(long studId) {
        this.studId = studId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}