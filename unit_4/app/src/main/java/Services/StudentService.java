package Services;

import libs.CrudProcess;
import libs.Factory;

import java.util.Collection;

public class StudentService {
    CrudProcess<Student> StudentCrudProcess = Factory.getInstance().getCrudProcess();

    public void create(Student student){
        StudentCrudProcess.create(student);
    }
    public void update (Student student){
        StudentCrudProcess.update(student);
    }
    public void delete (String id){
        StudentCrudProcess.delete(id);
    }
    public Collection<Student> list (){
        return StudentCrudProcess.list();
    }
    public Student read(String id){
        return StudentCrudProcess.read(id);
    }
}
