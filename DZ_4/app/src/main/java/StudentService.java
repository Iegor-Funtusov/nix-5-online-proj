import java.util.Collection;

public class StudentService {
    private CrudInt<Student> studCrud = CrudProcFactory.getInstance().getCrudProcess();

    public void create(Student student) {
        studCrud.create(student);
    }

    public void delete(String id) {
        studCrud.delete(id);
    }

    public void update(Student student) {
        studCrud.update(student);
    }

    public Student read(String id) {
        return studCrud.read(id);
    }

    public Collection<Student> read() {
        return studCrud.read();
    }
}
