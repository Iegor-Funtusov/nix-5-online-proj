package ua.com.alevel.test.app;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.alevel.app.dao.CourseDAO;
import ua.com.alevel.app.dao.StudentDAO;
import ua.com.alevel.app.entity.Course;
import ua.com.alevel.app.entity.Student;
import ua.com.alevel.app.service.UniversityService;

public class UniversityServiceTest {

    private final UniversityService universityService = new UniversityService();
    private final StudentDAO studentDAO = new StudentDAO();
    private final CourseDAO courseDAO = new CourseDAO();

    @BeforeEach
    public void init() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            studentDAO.create(new Student("" + i, i));
            courseDAO.create(new Course("" + i, i));
        }
    }

    @Test
    public void testAddStudent() {
        for (int i = 0; i < studentDAO.readAll().size(); i++) {
            universityService.addStudent(studentDAO.readAll().get(i).getName(),
                    studentDAO.readAll().get(i).getAge());
        }
        for (int i = 0; i < studentDAO.readAll().size(); i++) {
            Assert.assertNotNull(universityService.getStudentList().get(i));

        }
    }

    @Test
    public void testAddCourse() {
        for (int i = 0; i < courseDAO.readAll().size(); i++) {
            universityService.addCourse(courseDAO.readAll().get(i).getName(),
                    courseDAO.readAll().get(i).getDuration());
        }
        for (int i = 0; i < courseDAO.readAll().size(); i++) {
            Assert.assertNotNull(universityService.getCourseList().get(i));
        }
    }

    @Test
    public void testRemoveStudent() {
        for (int i = 0; i < studentDAO.readAll().size(); i++) {
            universityService.removeStudent(studentDAO.readAll().get(i).getId());
            Assert.assertNull(universityService.getStudent(studentDAO.readAll().get(i).getId()));
        }
    }

    @Test
    public void testRemoveCourse() {
        for (int i = 0; i < courseDAO.readAll().size(); i++) {
            universityService.removeCourse(courseDAO.readAll().get(i).getId());
            Assert.assertNull(universityService.getCourse(courseDAO.readAll().get(i).getId()));
        }
    }
}