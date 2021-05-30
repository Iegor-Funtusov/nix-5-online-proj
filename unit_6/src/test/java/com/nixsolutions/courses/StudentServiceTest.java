package com.nixsolutions.courses;

import com.nixsolutions.courses.data.Group;
import com.nixsolutions.courses.data.Student;
import com.nixsolutions.courses.services.GroupService;
import com.nixsolutions.courses.services.StudentService;
import org.junit.jupiter.api.*;

import javax.management.InstanceAlreadyExistsException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {

    private final static StudentService studentService = new StudentService();
    private final static Group group = new Group(5);
    private static String testId;

    @BeforeAll
    public static void init() {
        try {
            GroupService service = new GroupService();
            group.setName("TEST");
            service.create(group);
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void create() {
        Student student = new Student();
        student.setName("test");
        student.setAge(21);
        studentService.create(student,group);
        testId = student.getId();

        Assertions.assertNotNull(studentService.read(testId,group));
    }

    @Test
    @Order(2)
    public void readAll() {
        Student[] students = studentService.readAll(group);

        Assertions.assertTrue(students.length != 0);
    }

    @Test
    @Order(3)
    public void update() {
        Student newStudent = studentService.read(testId, group);
        newStudent.setName("UPDATE");

        Assertions.assertNotEquals(studentService.read(testId, group).getName(), "test");
    }

    @Test
    @Order(4)
    public void delete() {
        studentService.delete(testId, group);

        Assertions.assertThrows(RuntimeException.class, () ->{
            studentService.read(testId,group);
        });
    }
}
