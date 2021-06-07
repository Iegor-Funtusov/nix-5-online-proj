import DataBase.Db;
import DataClasses.Course;
import DataClasses.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class Service {
    private final Db dataBase;
    private static final Logger infoLog = LoggerFactory.getLogger("info");
    private static final Logger warningLog = LoggerFactory.getLogger("warn");
    private static final Logger errorLog = LoggerFactory.getLogger("error");

    Service(){
        dataBase = new Db();
    }


    public boolean addStudent(Student studentToAdd){
        if(isEmpty(studentToAdd)){
            errorLog.error("In adding student. Student is empty");
            return false;
        }
        if(dataBase.addNewStudent(studentToAdd)){
            infoLog.info("Successfully created student " + studentToAdd.toString());
            return true;
        }
        errorLog.error("Error in creating student " + studentToAdd.toString());
        return false;
    }


    public boolean addCourse(Course courseToAdd){
        if(isEmpty(courseToAdd)){
            errorLog.error("In adding course. Course is empty");
            return false;
        }
        if(dataBase.addNewCourse(courseToAdd)){
            infoLog.info("Successfully created course " + courseToAdd.toString());
            return true;
        }
        errorLog.error("Error in creating course " + courseToAdd.toString());
        return false;
    }


    public boolean addStudentToCourse(Student studentToAdd, Course courseToAdd){
        warningLog.warn("Try to enroll student to course");
        if(isEmpty(studentToAdd)){
            errorLog.error("In adding student to course. Student is empty");
            return false;
        }
        if(isEmpty(courseToAdd)){
            errorLog.error("In adding student to course. Course is empty");
            return false;
        }
        if(dataBase.addStudentToCourse(studentToAdd, courseToAdd)){
            infoLog.info("Student " + studentToAdd.toString() + " successfully added to a course " + courseToAdd.toString());
            return true;
        }
        errorLog.error("Error in adding student to course");
        return false;
    }


    public boolean deleteStudent(Student studentToDelete){
        warningLog.warn("Try to delete student");
        if(isEmpty(studentToDelete)){
            errorLog.error("In deleting student. Student is empty");
            return false;
        }
        if(dataBase.deleteStudent(studentToDelete)){
            infoLog.info("Successfully deleted student " + studentToDelete.toString());
            return true;
        }
        errorLog.error("Error in deleting student. " + studentToDelete.toString());
        return false;
    }


    public boolean deleteCourse(Course courseToDelete){
        warningLog.warn("Try to delete the course");
        if(isEmpty(courseToDelete)){
            errorLog.error("In deleting course, course is empty");
            return false;
        }
        if(dataBase.deleteCourse(courseToDelete)){
            infoLog.info("Successfully deleted course " + courseToDelete.toString());
            return true;
        }
        errorLog.error("Error in deleting course. " + courseToDelete.toString());
        return false;
    }


    public boolean deleteStudentFromCourse(Student studentToDelete, Course course){
        warningLog.warn("Try to delete student from course");
        if(isEmpty(studentToDelete)){
            errorLog.error("In deleting course, student is empty");
            return false;
        }
        if(isEmpty(course)){
            errorLog.error("In deleting course, course is empty");
            return false;
        }
        if(dataBase.deleteStudentFromCourse(studentToDelete, course)){
            infoLog.info("Successfully deleted student " + studentToDelete.toString() + " from course " + course.getNameOfCourse());
            return true;
        }
        errorLog.error("Error in deleting student " + studentToDelete.toString() + " from course " + course.getNameOfCourse());
        return false;
    }

    public boolean updStudent(Student student){
        warningLog.warn("Try to upd student");
        if(isEmpty(student)){
            errorLog.error("In upd student, student is empty");
            return false;
        }
        if(dataBase.updStudent(student)){
            infoLog.info("Successfully updated student + " + student.toString());
            return true;
        }
        errorLog.error("Error in updating student " + student.toString());
        return false;
    }



    public ArrayList<Student> getAllStudents(){
        infoLog.info("Try to get all students");
        ArrayList<Student> allStudents = dataBase.getAllStudents();
        if(!isEmpty(allStudents)){
            infoLog.info("Successfully got all students");
        }
        else{
            warningLog.warn("All students is empty");
        }
        return allStudents;
    }


    public ArrayList<Course> getAllCourses(){
        infoLog.info("Try to get all courses");
        ArrayList<Course> allCourses =  dataBase.getAllCourses();
        if(!isEmpty(allCourses)){
            infoLog.info("Successfully got all courses");
        }
        else {
            warningLog.warn("All students is empty");
        }
        return allCourses;
    }


    public Course getCourseByName(String courseName){
        infoLog.info("Try to get course by name");
        Course course = dataBase.getCourseByName(courseName);
        if(!isEmpty(course)){
            infoLog.info("Successfully got course by name " + course.toString());
        }
        else {
            warningLog.warn("There is no such course with name " + courseName);
        }
        return course;
    }


    public Course getCourseByTeacher(String teacherName){
        infoLog.info("Try to get course by teacher");
        Course course = dataBase.getCourseByTeacher(teacherName);
        if(!isEmpty(course)){
            infoLog.info("Successfully got course by teacher name " + course.toString());
        }
        else {
            warningLog.warn("There is no such course with teacher " + teacherName);
        }
        return course;
    }

    public ArrayList<Student> getAllStudentsAtCourse(Course course){
        infoLog.info("Try to get all students at course");
        if(isEmpty(course)){
            errorLog.error("At getAllStudentsAtCourse course is empty");
            return null;
        }
        ArrayList<Student> studentsAtCourse = dataBase.getStudentsAtCourse(course);
        if(isEmpty(studentsAtCourse)){
            errorLog.error("There are no students at course " + course.getNameOfCourse());
        }
        return studentsAtCourse;
    }


    public Student getStudentById(String id){
        infoLog.info("Try to get student by id");
        Student student = dataBase.getStudentById(id);
        if(!isEmpty(student)){
            infoLog.info("Successfully got student by id " + student.toString());
        }
        else {
            warningLog.warn("There is no such student  " + id);
        }
        return student;
    }


    public Student getStudentByName(String name) {
        infoLog.info("Try to get student by name");
        Student student = dataBase.getStudentByName(name);
        if(!isEmpty(student)){
            infoLog.info("Successfully got student by name " + student.toString());
        }
        else {
            warningLog.warn("There is no such student " + name);
        }
        return student;
    }


    private boolean isEmpty(Object objectToCheck){
        return objectToCheck == null;
    }
}
