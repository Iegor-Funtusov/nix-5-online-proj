package ua.com.alevel.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.lib.BaseEntityContainer;

public class University {
    private final BaseEntityContainer<StudentAndCourse> studentAndCourseList = new BaseEntityContainer<>();
    private final StudentDAO studentDAO = new StudentDAO();
    private final CourseDAO courseDAO = new CourseDAO();
    private static final Logger log_info = LoggerFactory.getLogger("info");
    private static final Logger log_warn = LoggerFactory.getLogger("warn");
    private static final Logger log_error = LoggerFactory.getLogger("error");

    public void addStudent(String name, int age) {
        log_info.info("Add student method started " + "Class: " + this.getClass().getSimpleName());
        if (age < 0 || age > 150)
            throw new IllegalArgumentException("Incorrect age of student");
        Student student = new Student(name, age);
        studentDAO.create(student);
        log_info.info("Add student end");
    }

    public void addCourse(String name, int duration) {
        log_info.info("Add course method started " + "Class: " + this.getClass().getSimpleName());
        if (duration < 0 || duration > 600)
            throw new IllegalArgumentException("Incorrect course duration");
        Course course = new Course(name, duration);
        courseDAO.create(course);
        log_info.info("Method addCourse end");
    }

    public void printAllStudents() {
        log_info.info("Printing students" + "Class: " +  this.getClass().getSimpleName());
        BaseEntityContainer<Student> temp = studentDAO.readAll();
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(i + 1 + ": " + temp.get(i));
        }
    }

    public void printAllCourses() {
        log_info.info("Printing courses", this.getClass().getSimpleName());
        BaseEntityContainer<Course> temp = courseDAO.readAll();
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(i + 1 + ": " + temp.get(i));
        }
    }

    public void printAllCoursesTakenByStudent(long studentId) {
        StringBuilder coursesTakenByStudent = new StringBuilder(studentDAO.read(studentId).getName() + ": ");
        for (int i = 0; i < studentAndCourseList.size(); i++) {
            if (studentAndCourseList.get(i).getStudId() == studentId) {
                coursesTakenByStudent.append(courseDAO.read(studentAndCourseList.get(i).getCourseId()));
            }
        }
        System.out.println(coursesTakenByStudent);
    }

    public void printAllStudentsOnCourse(long courseId) {
        StringBuilder studentsOnCourse = new StringBuilder(courseDAO.read(courseId).getName() + ": ");
        for (int i = 0; i < studentAndCourseList.size(); i++) {
            if (studentAndCourseList.get(i).getCourseId() == courseId) {
                studentsOnCourse.append(studentDAO.read(studentAndCourseList.get(i).getStudId())).append(" ");
            }
        }
        System.out.println(studentsOnCourse);
    }

    public void printAllStudentsOnCourses() {
        BaseEntityContainer<Course> temp = courseDAO.readAll();
        for (int i = 0; i < courseDAO.readAll().size(); i++) {
            printAllStudentsOnCourse(temp.get(i).getId());
        }
    }

    public void addStudentToCourse(long studentId, long courseId) {
        if (studentDAO.read(studentId) != null && courseDAO.read(courseId) != null) {
            StudentAndCourse temp = new StudentAndCourse(studentId, courseId);
            for (int i = 0; i < studentAndCourseList.size(); i++) {
                if (temp.equals(studentAndCourseList.get(i))) {
                    System.out.println("This student already attends this course");
                    return;
                }
            }
            studentAndCourseList.add(temp);
        }
        System.out.println("There's no student or course with this ID");
    }

    public void removeStudentFromCourse(long studentId, long courseId) {
        log_warn.warn("Removing student from course started " + "Class: " + this.getClass().getSimpleName());
        StudentAndCourse temp = new StudentAndCourse(studentId, courseId);
        for (int i = 0; i < studentAndCourseList.size(); i++) {
            if (temp.equals(studentAndCourseList.get(i))) {
                studentAndCourseList.remove(i);
            }
        }
        log_warn.warn("Removing student from course end");
    }

    public void removeStudent(long studentId) {
        log_warn.warn("Cascade removing student from course started " + "Class: " + this.getClass().getSimpleName());
        for (int i = 0; i < studentAndCourseList.size(); i++) {
            if (studentAndCourseList.get(i).getStudId() == studentId) {
                studentAndCourseList.remove(i);
            }
            studentDAO.delete(studentId);
        }
        log_warn.warn("Cascade student from course end");

    }

    public void removeCourse(long courseId) {
        log_warn.warn("Cascade removing course started" + "Class: " +  this.getClass().getSimpleName());

        for (int i = 0; i < studentAndCourseList.size(); i++) {
            if (studentAndCourseList.get(i).getCourseId() == courseId) {
                studentAndCourseList.remove(i);
            }
        }
        courseDAO.delete(courseId);
        log_warn.warn("Cascade removing course started");

    }

    public void updateStudentInfo(long studentId, Student student) {
        log_warn.warn("Updating student started " + "Class: " + this.getClass().getSimpleName());
        student.setId(studentId);
        studentDAO.update(studentId, student);
        log_warn.warn("Updating student end");
    }

    public void updateCourseInfo(long courseId, Course course) {
        log_warn.warn("Updating course started " + "Class: " + this.getClass().getSimpleName());
        course.setId(courseId);
        courseDAO.update(courseId, course);
        log_warn.warn("Updating course end");
    }

    public Student getStudent(long id) {
        return studentDAO.read(id);
    }

    public Course getCourse(long id) {
        return courseDAO.read(id);
    }

    public int size() {
        return studentAndCourseList.size();
    }

    public BaseEntityContainer<Student> getStudentList() {
        return studentDAO.readAll();
    }

    public BaseEntityContainer<Course> getCourseList() {
        return courseDAO.readAll();
    }
}
