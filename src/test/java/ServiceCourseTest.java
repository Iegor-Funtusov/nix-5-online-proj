import DataClasses.Course;
import DataClasses.Student;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceCourseTest {
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

    @Test
    @Order(1)
    public void addCourse(){
        Course course = new Course();
        course.setNameOfCourse(TestConstants.TEST_COURSE_NAME);
        course.setNameOfTeacher(TestConstants.TEST_TEACHER_NAME);
        service.addCourse(course);

        Course check = service.getCourseByName(course.getNameOfCourse());
        Assertions.assertNotNull(check);
    }


    @Test
    @Order(2)
    public void enrollStudentsToCourseTest(){
        Course course = service.getCourseByName(TestConstants.TEST_COURSE_NAME);

        for (int i = 0; i < TestConstants.DEFAULT_QUANTITY_STUDENTS; i++){
            Student student = new Student();
            student.setName(TestConstants.TEST_STUDENT_NAME + (i + 1));
            service.addStudent(student);
            service.addStudentToCourse(student, course);
            student.setCourse(service.getCourseByName(TestConstants.TEST_COURSE_NAME));
        }

        Assertions.assertEquals(TestConstants.DEFAULT_QUANTITY_STUDENTS, service.getAllStudents().size());
    }


    @Test
    @Order(3)
    public void getAllCoursesTest(){
        ArrayList<Course> allCourses = service.getAllCourses();
        Assertions.assertTrue(allCourses.size() != 0);
    }

    @Test
    @Order(4)
    public void getCourseByNameTest(){
        Course course = service.getCourseByName(TestConstants.TEST_COURSE_NAME);
        Assertions.assertNotNull(course);
    }

    @Test
    @Order(5)
    public void getCourseByTeacherTest(){
        Course course = service.getCourseByTeacher(TestConstants.TEST_TEACHER_NAME);
        Assertions.assertNotNull(course);
    }

    @Test
    @Order(6)
    public void getAllStudentsAtCourse(){
        ArrayList<Student> check = new ArrayList<>();
        Student []studentsAtCourse = service.getCourseByName(TestConstants.TEST_COURSE_NAME).getStudents();
        for(int i = 0; i < TestConstants.DEFAULT_QUANTITY_STUDENTS; i++){
            if(studentsAtCourse[i] != null){
                check.add(studentsAtCourse[i]);
            }
        }
        Assertions.assertEquals(TestConstants.DEFAULT_QUANTITY_STUDENTS, check.size());
    }

    @Test
    @Order(7)
    public void deleteStudentFromCourseTest(){
        Course course = service.getCourseByName(TestConstants.TEST_COURSE_NAME);
        int currentFreePlaces = course.getFreePlaces();
        Student studentToDelete = course.getStudents()[TestConstants.DEFAULT_INDEX];

        if(service.deleteStudentFromCourse(studentToDelete, course)){
            studentToDelete.setCourse(null);
        }

        Assertions.assertEquals(course.getFreePlaces(), currentFreePlaces + 1);
        Assertions.assertNull(studentToDelete.getCourse());
    }

    @Test
    @Order(8)
    public void deleteCourseTest(){
        Course courseToDelete = service.getCourseByName(TestConstants.TEST_COURSE_NAME);
        Student []studentsAtCourse = courseToDelete.getStudents();
        boolean flag = true;
        int quantityOfCourses = service.getAllCourses().size();

        service.deleteCourse(courseToDelete);
        for(int i = 0; i < studentsAtCourse.length; i++){
            if(studentsAtCourse[i] != null){
                if(studentsAtCourse[i].getCourse() != null){
                    flag = false;
                    break;
                }
            }
        }

        Assertions.assertEquals(quantityOfCourses - 1, service.getAllCourses().size());
        Assertions.assertTrue(flag);
    }
}
