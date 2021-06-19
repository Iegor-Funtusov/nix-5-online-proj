package ua.com.alevel.app.dao;

import ua.com.alevel.app.entity.Student;
import ua.com.alevel.lib.ArrayListCrudService;
import ua.com.alevel.lib.BaseEntityContainer;
import ua.com.alevel.lib.CRUDService;

public class StudentDAO implements CRUDService<Student> {

    private final ArrayListCrudService<Student> studentList = new ArrayListCrudService<>();

    @Override
    public void update(long id, Student entity) {
        studentList.update(id, entity);
    }

    @Override
    public void delete(long id) {
        studentList.delete(id);
    }

    @Override
    public void create(Student entity) {
        studentList.create(entity);
    }

    @Override
    public Student read(long id) {
        return studentList.read(id);
    }

    @Override
    public BaseEntityContainer<Student> readAll() {
        return studentList.readAll();
    }
}
