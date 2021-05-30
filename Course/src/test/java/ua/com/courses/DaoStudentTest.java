package ua.com.courses;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import ua.com.courses.daolevel.DAOStudent;
import ua.com.courses.entity.Student;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class DaoStudentTest {
    private static DAOStudent daoStudent = new DAOStudent();

    @BeforeAll
    public static void setUpDaoStudent(){
        for(int i=0; i < 10; i++){
            Student student = new Student("Surname" +i, "Name"+i);
            daoStudent.create(student);
        }
        Assert.assertTrue(daoStudent.readAll().length != 0);
    }

    @Test
    @Order(1)
    public void createStudent(){
        Student student = new Student("SurnameCREATED" , "NameCREATED");
        daoStudent.create(student);
        Student courseFromArray = daoStudent.readSurnameName("SurnameCREATED" , "NameCREATED");
        Assert.assertEquals(student, courseFromArray);
    }

    @Test
    @Order(2)
    public void readStudent(){
        Student studentFromArray = daoStudent.readSurnameName("SurnameCREATED" , "NameCREATED");
        Assert.assertNotNull(studentFromArray);
    }

    @Test
    @Order(3)
    public void readAllStudent() {
        Assert.assertEquals(daoStudent.readAll().length, 11);
    }

    @Test
    @Order(4)
    public void checkExistStudentTest(){
        Assert.assertFalse(daoStudent.checkIfExistStudentSurname("notExistSurname", "notExistName"));
    }

    @Test
    @Order(5)
    public void updateStudent(){
        String surnameBeforeUpdate = daoStudent.readSurnameName("SurnameCREATED" ,
                "NameCREATED").getName();
        Student student = daoStudent.readSurnameName("SurnameCREATED" ,
                "NameCREATED");
        student.setName("NameUPDATED");
        Assert.assertNotEquals(student.getName(), surnameBeforeUpdate);
    }

    @Test
    @Order(6)
    public void deleteStudent(){
        Student [] students = daoStudent.readAll();
        daoStudent.delete(students[10].getId());
        Assert.assertEquals(daoStudent.readAll().length, 10);
    }

    @Test
    @Order(7)
    public void readById(){
        Student [] students = daoStudent.readAll();
        Student s = students[9];
        Student read = daoStudent.read(s.getId());
        Assert.assertEquals(s, read);
    }
}
