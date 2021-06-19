package ua.com.alevel.app.dao;

import ua.com.alevel.app.entity.Course;
import ua.com.alevel.lib.ArrayListCrudService;
import ua.com.alevel.lib.BaseEntityContainer;
import ua.com.alevel.lib.CRUDService;

public class CourseDAO implements CRUDService<Course> {

    private final ArrayListCrudService<Course> courseList = new ArrayListCrudService<>();

    @Override
    public void update(long id, Course entity) {
        courseList.update(id, entity);
    }

    @Override
    public void delete(long id) {
        courseList.delete(id);
    }

    @Override
    public void create(Course entity) {
        courseList.create(entity);
    }

    @Override
    public Course read(long id) {
        return courseList.read(id);
    }

    @Override
    public BaseEntityContainer<Course> readAll() {
        return courseList.readAll();
    }

}
