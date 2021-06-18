package ua.com.alevel.test.app;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.alevel.app.*;

public class UniversityTest {
    private University university = new University();
    private StudentDAO studentDAO = new StudentDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private final int n = 10;

    @BeforeEach
    public void init() {
        for (int i = 0; i < n; i++) {
            studentDAO.create(new Student("" + i, i));
            courseDAO.create(new Course("" + i, i));
        }
    }

    @Test
    public void testAddStudent() {
        for (int i = 0; i < studentDAO.readAll().size(); i++) {
            university.addStudent(studentDAO.readAll().get(i).getName(),
                    studentDAO.readAll().get(i).getAge());
        }
        for (int i = 0; i < studentDAO.readAll().size(); i++) {
            Assert.assertNotNull(university.getStudentList().get(i));

        }
    }

    @Test
    public void testAddCourse() {
        for (int i = 0; i < courseDAO.readAll().size(); i++) {
            university.addCourse(courseDAO.readAll().get(i).getName(),
                    courseDAO.readAll().get(i).getDuration());
        }
        for (int i = 0; i < courseDAO.readAll().size(); i++) {
            Assert.assertNotNull(university.getCourseList().get(i));
        }
    }

    @Test
    public void testRemoveStudent() {
        for (int i = 0; i < studentDAO.readAll().size(); i++) {
            university.removeStudent(studentDAO.readAll().get(i).getId());
            Assert.assertNull(university.getStudent(studentDAO.readAll().get(i).getId()));
        }
    }

    @Test
    public void testRemoveCourse() {
        for (int i = 0; i < courseDAO.readAll().size(); i++) {
            university.removeCourse(courseDAO.readAll().get(i).getId());
            Assert.assertNull(university.getCourse(courseDAO.readAll().get(i).getId()));
        }
    }
}