import DataClasses.Course;
import DataClasses.Student;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceStudentTest {
    private static final Service service = new Service();

    @BeforeAll
    public static void setUpCourses(){
        for(int i = 0; i < TestConstants.DEFAULT_QUANTITY_COURSES; i++){
            Course course = new Course();
            course.setNameOfCourse(TestConstants.TEST_COURSE_NAME + (i + 1));
            course.setNameOfTeacher(TestConstants.TEST_TEACHER_NAME + (i + 1));
            service.addCourse(course);
        }
        Assertions.assertEquals(TestConstants.DEFAULT_QUANTITY_COURSES, service.getAllCourses().size());
    }


    @BeforeAll
    public static void setUpStudents(){
        for (int i = 0; i < TestConstants.DEFAULT_QUANTITY_STUDENTS; i++){
            Student student = new Student();
            student.setName(TestConstants.TEST_STUDENT_NAME + (i + 1));
            service.addStudent(student);
        }
        Assertions.assertEquals(TestConstants.DEFAULT_QUANTITY_STUDENTS, service.getAllStudents().size());
    }


    @Test
    @Order(1)
    public void addStudentTest(){
        Student student = new Student();
        student.setName(TestConstants.TEST_STUDENT_NAME);
        service.addStudent(student);

        Student check = service.getStudentByName(student.getName());
        Assertions.assertNotNull(check);
    }

    @Test
    @Order(2)
    public void getAllStudentsTest(){
        ArrayList<Student> allStudents = service.getAllStudents();
        Assertions.assertTrue(allStudents.size() != 0);
    }

    @Test
    @Order(3)
    public void getStudentByTestName(){
        Student studentToCheck = service.getStudentByName(TestConstants.TEST_STUDENT_NAME);
        Assertions.assertNotNull(studentToCheck);
    }

    @Test
    @Order(4)
    public void getStudentByIdTest(){
        Student first = service.getStudentByName(TestConstants.TEST_STUDENT_NAME);
        Student second = service.getStudentById(first.getId());
        Assertions.assertEquals(first, second);
    }

    @Test
    @Order(5)
    public void deleteStudentTest(){
        Student studentToDelete = service.getStudentByName(TestConstants.TEST_STUDENT_NAME);
        service.deleteStudent(studentToDelete);
        Student check = service.getStudentByName(TestConstants.TEST_STUDENT_NAME);
        Assertions.assertNull(check);
    }

    @Test
    @Order(6)
    public void enrollToCourseTest(){
        Student student = service.getAllStudents().get(TestConstants.DEFAULT_INDEX);
        Course course = service.getAllCourses().get(TestConstants.DEFAULT_INDEX);
        if(service.addStudentToCourse(student, course)){
            student.setCourse(course);
        }

        Assertions.assertEquals(course, service.getAllCourses().get(TestConstants.DEFAULT_INDEX));
        Assertions.assertEquals(course, student.getCourse());
    }
}













