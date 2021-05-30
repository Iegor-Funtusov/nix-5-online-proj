package ua.com.courses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.courses.entity.Course;
import ua.com.courses.entity.Student;
import ua.com.courses.services.CoursesService;
import ua.com.courses.services.StudentService;

public class CourseController {
    public static CoursesService coursesService = new CoursesService();
    public static StudentService studentService = new StudentService();

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public Course getCourse(String name){
        return coursesService.read(name);
    }

    public Student getStudent(String surname, String name) {
        return studentService.readSurnameName(surname, name);
    }

    public void printCourses(){
       Course [] courses = coursesService.readAll();
       for(Course course : courses){
           System.out.println(course);
       }
    }

    public void printStudents(){
        Student [] students = studentService.readAll();
        for(Student student : students){
            System.out.println(student);
        }
    }

    public void createCourse(String name){
        loggerInfo.info("Start create course: " + name);
        if(!coursesService.checkIfExistCourse(name)) {
            Course course = new Course(name);
            coursesService.create(course);
            loggerInfo.info("End create course " + name);
        }
        else{
            loggerWarn.warn("Course " + name + " is already exist");
            System.out.println("Course with this name "  + name + " exists");
        }
    }

    public void updateCourseAddStudent(Course course, Student student){
        loggerInfo.info("Start update course " + course.getName() + " add student "  +student.getId());
        if(CurseCheck(student,course) && course != null && student!= null){
        Course course1 = coursesService.read(course.getName());
        String [] studentsBefore = course1.getStudents();
        String [] students;
        if(studentsBefore != null){
            students = new String[studentsBefore.length + 1];
            for(int i = 0; i < students.length; i++){
                if(i == students.length-1){
                    loggerInfo.info("Start update student: " + student.getId() + " add new course " + course.getName());
                    students[i] = student.getId();
                    course.setStudents(students);
                    loggerInfo.info("End update student: " + student.getId() + " add new course " + course.getName());
                }
                else{
                students[i] = studentsBefore[i];
                }
            }
        }
        else {
            loggerInfo.info("Start update student: " + student.getId() + " add new course " + course.getName());
            students = new String[] {student.getId()};
            course.setStudents(students);
            loggerInfo.info("End update student: " + student.getId() + " add new course " + course.getName());
        }
        coursesService.update(course);
        loggerInfo.info("End update course" + course.getName() + " add student "  +student.getId());
        }
        else {
            loggerError.error("Student " + student.getId() + " already attend course " + course.getName());
            //System.out.println("This student has already attend this course!");
        }
    }

    public void UpdateCourseRemoveStudent(Course course, Student student){
        loggerInfo.info("Start update course" + course.getName() + " remove student " + student.getId());
        if(!CurseCheck(student,course) && course != null && student!=null && course.getStudents()!= null){
            Course course1 = coursesService.read(course.getName());
            Student [] students = studentService.readAll();
            for(Student s : students) {
                if (s.getCourses() != null) {
                    String[] courses = s.getCourses();
                    for (int i = 0; i < courses.length; i++) {
                        if (courses[i].equals(course.getName()) && s.equals(student)) {
                            loggerInfo.info("Start update courses of :" + student.getId() + "remove course" + course.getName());
                            s.setCourses(changeArray(courses, i));
                            studentService.update(s);
                            loggerInfo.info("End update courses of :" + student.getId() + "remove course" + course.getName());
                        }
                    }
                }
            }
            String [] courseStudent = course1.getStudents();
            for (int j = 0; j < courseStudent.length; j++){
                if(courseStudent[j].equals(student.getId())){
                    loggerInfo.info("Start update " + course.getName() + " remove student  "+  student.getId());
                    String [] newCourseStudent = changeArray(courseStudent,j);
                    course1.setStudents(newCourseStudent);
                    coursesService.update(course1);
                    loggerInfo.info("End update " + course.getName() + " remove student  "+  student.getId());
                }
            }
        }
        else{
            loggerError.error("Student " + student.getId() + " doesn`t attend course" + course.getName());
            System.out.println("This course doesn`t have this student!");
        }
    }

    public void deleteCourse(String name){
        String [] students = coursesService.read(name).getStudents();
            if(students!= null){
            for (String s : students) {
                Student student = studentService.read(s);
                String[] courses = student.getCourses();
                if(courses != null){
                for(int j = 0; j < courses.length; j++){
                                if(courses[j].equals(name)){
                                loggerWarn.warn("Start delete course " + coursesService.read(name) + " from students");
                                student.setCourses(changeArray(courses,j));
                                studentService.update(student);
                                loggerWarn.warn("End delete course " + coursesService.read(name) + " from students");
                            }
                        }
                    }
                }
            }
        loggerWarn.warn("Start delete course " + name);
        coursesService.delete(name);
        loggerWarn.warn("End delete course " + name);
    }

    public void createStudent(String surname, String name){
        loggerInfo.info("Start create student " + surname + " " + name);
        if(!studentService.CheckExist(surname, name)) {
            Student student = new Student(surname, name);
            studentService.create(student);
            loggerInfo.info("End create student " + surname + " " + name);
        }
        else{
            loggerWarn.warn("Student"  + surname + " " + name + " exists");
            System.out.println("Student"  + surname + " " + name + " exists");
        }
    }

    public void deleteStudent(String id){
        String [] courses = studentService.read(id).getCourses();
        if(courses != null){
        for(int i = 0; i < courses.length; i++){
            Course course = coursesService.read(courses[i]);
            String [] students = course.getStudents();
            for(int j = 0; j < students.length; j++){
                loggerWarn.warn("Start delete student " + id + " from courses");
                if(students[j].equals(id)){
                    course.setStudents(changeArray(students, j));
                    coursesService.update(course);
                    loggerWarn.warn("End delete student " + id + " from courses");
                }
            }
            }
        }
        loggerWarn.warn("Start delete student " + id);
        studentService.delete(id);
        loggerWarn.warn("End delete student " + id);
    }

    private static String [] changeArray(String[] s, int index){
        if(s.length == 1){
            return null;
        }
        String [] newArray = new String[s.length-1];
        int indexNewArray = 0;
        for (int i = 0; i < s.length; i++){
            if(i != index){
                newArray[indexNewArray++] = s[i];
            }
        }
       return newArray;
    }

    public void updateStudentAddCourses(String id, Course course){
            loggerInfo.info("Start update student " + id  + " add course " + course.getName());
            if(studentService.read(id) != null && course != null){
            Student student = studentService.read(id);
            String [] courses = student.getCourses();
            if(courses == null || courses.length == 0){
                String [] coursesUpdated = new String[] {course.getName()};
                student.setCourses(coursesUpdated);
                studentService.update(student);
            }
            else if(CheckIfAlreadyAttendCourse(studentService.read(id), course.getName())){
                String[] coursesUpdated = new String[courses.length + 1];
                for (int i = 0; i < coursesUpdated.length; i++) {
                    if (i != coursesUpdated.length - 1) {
                     coursesUpdated[i] = courses[i];
                    } else {
                        loggerInfo.info("Start update course: " + course.getName() + " add new student " + student.getId());
                        coursesUpdated[i] = course.getName();
                        student.setCourses(coursesUpdated);
                        loggerInfo.info("End update course: " + course.getName() + " add new student " + student.getId());
                }
            }
            studentService.update(student);
            loggerInfo.info("End update student " + id + " add course " + course.getName());
            }
            else {
                System.out.println("Student already attend this course! ");
                loggerWarn.warn("Student " + id + " attend " + course.getName());
               }
            }
    }

    private boolean CheckIfAlreadyAttendCourse(Student student, String name) {
        String [] courses = student.getCourses();
        for(int i = 0; i < courses.length; i++) {
            if (courses[i].equals(name)) {
                return false;
            }
        }
        return true;
    }

    private boolean CurseCheck(Student student, Course course){
        String [] students = course.getStudents();
        if(students == null || students.length == 0){
            return true;
        }
        for (String s : students) {
            if (s.equals(student.getId())) {
                return false;
            }
        }
        return true;
    }

}