package ua.com.courses;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import ua.com.courses.daolevel.DAOCourse;
import ua.com.courses.entity.Course;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DaoCourseTest {
    private static DAOCourse daoCourse = new DAOCourse();

    @BeforeAll
    public static void setUpDaoCourse(){
            for(int i=0; i < 10; i++){
                Course course = new Course("test"+ i);
                daoCourse.create(course);
            }
         Assert.assertTrue(daoCourse.readAll().length != 0);
    }

    @Test
    @Order(1)
    public void createCourse(){
        Course course = new Course("TestCourse");
        daoCourse.create(course);
        Course courseFromArray = daoCourse.read("TestCourse");
        Assert.assertEquals(course, courseFromArray);
    }

    @Test
    @Order(2)
    public void readCourse(){
        Course courseFromArray = daoCourse.read("TestCourse");
        Assert.assertNotNull(courseFromArray);
    }

    @Test
    @Order(3)
    public void readAllCourse() {
        Assert.assertEquals(daoCourse.readAll().length, 11);
    }

    @Test
    @Order(4)
    public void CheckExistCourseTest(){
        Assert.assertFalse(daoCourse.checkIfExistCourse("notExistCourse"));
    }

    @Test
    @Order(5)
    public void UpdateCourse(){
        String nameBeforeUpdate = daoCourse.read("test1").getName();
        Course course = daoCourse.read("test1");
        course.setName("UPDATED");
        Assert.assertNotEquals(course.getName(), nameBeforeUpdate);
    }

    @Test
    @Order(6)
    public void deleteCourse(){
        daoCourse.delete("UPDATED");
        Assert.assertEquals(daoCourse.readAll().length, 10);
    }
}

