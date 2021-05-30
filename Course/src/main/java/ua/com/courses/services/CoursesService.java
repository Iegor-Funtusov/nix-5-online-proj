package ua.com.courses.services;

import ua.com.courses.daolevel.DAOCourse;
import ua.com.courses.entity.Course;

public class CoursesService {
    private DAOCourse daoCourse = new DAOCourse();

    public void create(Course course){
        daoCourse.create(course);
    }

    public void update(Course course) {
        daoCourse.update(course);
    }

    public void delete(String name) {
        daoCourse.delete(name);
    }

    public Course read(String name){
        return daoCourse.read(name);
    }

    public Course [] readAll(){
        return daoCourse.readAll();
    }

    public boolean checkIfExistCourse (String name){
        return daoCourse.checkIfExistCourse(name);
    }
}
