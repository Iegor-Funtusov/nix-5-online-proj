package ua.com.courses.services;

import ua.com.courses.daolevel.DAOStudent;
import ua.com.courses.entity.Student;

public class StudentService {
    private DAOStudent daoStudent = new DAOStudent();

    public void create(Student student){
        daoStudent.create(student);
    }

    public void update(Student student) {
        daoStudent.update(student);
    }

    public void delete(String id) {
        daoStudent.delete(id);
    }

    public Student read(String id){
        return daoStudent.read(id);
    }

    public Student readSurnameName(String surname, String name){
        return daoStudent.readSurnameName(surname, name);
    }

    public Student[] readAll(){
        return daoStudent.readAll();
    }

    public boolean CheckExist(String Surname, String Name){
        return daoStudent.checkIfExistStudentSurname(Surname, Name);
    }
}
