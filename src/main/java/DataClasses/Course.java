package DataClasses;

import DataBase.Constants;

public class Course {
    private String nameOfCourse;
    private String nameOfTeacher;
    private Student[] students;
    private int freePlaces;

    public Course(){
        students = new Student[Constants.MAX_STUDENTS_ON_COURSE];
        freePlaces = Constants.MAX_STUDENTS_ON_COURSE;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public String getNameOfTeacher() {
        return nameOfTeacher;
    }

    public Student[] getStudents() {
        return students;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public void setNameOfTeacher(String nameOfTeacher) {
        this.nameOfTeacher = nameOfTeacher;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;

        return nameOfCourse.equals(course.nameOfCourse) &&
                nameOfTeacher.equals(course.nameOfTeacher) &&
                freePlaces == course.getFreePlaces();
    }

    @Override
    public String toString() {
        return "Name of course: " + nameOfCourse +
                " teacher: " + nameOfTeacher +
                " free places: " + freePlaces;
    }
}
