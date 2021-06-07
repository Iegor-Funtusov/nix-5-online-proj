package DataBase;

import DataClasses.Course;
import DataClasses.Student;
import java.util.ArrayList;
import java.util.UUID;

public class Db {
    private final Student []students;
    private final Course []courses;

    public Db(){
        students = new Student[Constants.MAX_STUDENTS];
        courses = new Course[Constants.MAX_COURSES];
    }


    public boolean addNewStudent(Student studentToAdd){
        if(!isHaveFreePlace(students)){
            return false;
        }

        studentToAdd.setId(generateID(UUID.randomUUID().toString()));
        for(int i = 0; i < students.length; i ++){
            if(students[i] == null){
                students[i] = studentToAdd;
                return true;
            }
        }
        return false;
    }



    public boolean addNewCourse(Course courseToAdd){
        if(!isHaveFreePlace(courses)){
            return false;
        }

        for(int i = 0; i < courses.length; i++){
            if(courses[i] == null){
                courses[i] = courseToAdd;
                return true;
            }
        }
        return false;
    }



    public boolean addStudentToCourse(Student studentToAdd, Course course){
        if(isEmptyCourses()){
            return false;
        }
        if(studentToAdd == null || course == null){
            return false;
        }
        if(!isHaveFreePlace(course.getStudents())){
            return false;
        }

        //Если студент уже был записан на какой-то курс, выписываю его со старого курса и записываю на новый
        if(studentToAdd.getCourse() != null){
            deleteStudentFromCourse(studentToAdd, studentToAdd.getCourse());
        }

        Student []studentsAtCourse = course.getStudents();
        for(int i = 0; i < studentsAtCourse.length; i++){
            if(studentsAtCourse[i] == null){
                studentsAtCourse[i] = studentToAdd;
                break;
            }

        }
        for(Course item : courses){
            if(item != null && item.equals(course)){
                item.setStudents(studentsAtCourse);
                item.setFreePlaces(item.getFreePlaces() - 1);
                break;
            }
        }
        return true;
    }





    public ArrayList<Student> getAllStudents(){
        if(isEmptyStudents(students)){
            return null;
        }

        ArrayList<Student> items = new ArrayList<Student>();
        for (Student student : students) {
            if (student != null) {
                items.add(student);
            }
        }
        return items;
    }


    public ArrayList<Course> getAllCourses(){
        if(isEmptyCourses()){
            return null;
        }

        ArrayList<Course> items = new ArrayList<Course>();
        for(Course course : courses){
            if(course != null){
                items.add(course);
            }
        }
        return items;
    }


    public ArrayList<Student> getStudentsAtCourse(Course course){
        if(isEmptyStudents(course.getStudents())){
            return null;
        }

        ArrayList<Student> items = new ArrayList<Student>();
        for (Student student : students) {
            if (student != null && student.getCourse().equals(course)) {
                items.add(student);
            }
        }
        return items;
    }

    public Course getCourseByName(String courseName){
        for(Course course : courses) {
            if (course != null){
                if (course.getNameOfCourse().equals(courseName)) {
                    return course;
                }
            }
        }
        return null;
    }

    public Course getCourseByTeacher(String teacherName){
        for(Course course : courses){
            if(course != null & course.getNameOfTeacher().equals(teacherName)){
                return course;
            }
        }
        return null;
    }

    public Student getStudentById(String id){
        for(Student student : students){
            if(student != null & student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }

    public Student getStudentByName(String name){
        for(Student student : students){
            if(student != null){
                if(student.getName().equals(name)){
                    return student;
                }
            }
        }
        return null;
    }


    public boolean deleteCourse(Course course){
        if(isEmptyCourses() || course == null){
            return false;
        }
        for(int i = 0; i < courses.length; i++){
            if(courses[i] != null){
                if(courses[i].equals(course)){
                    courses[i] = null;
                }
            }
        }


        Student []delCourseStudents = course.getStudents();
        for (Student item : delCourseStudents) {
            for (int j = 0; j < students.length; j++) {
                if(item != null && students[j] != null){
                    if (students[j].getId().equals(item.getId())) {
                        students[j].setCourse(null);
                        break;
                    }
                }
            }
        }

        return true;
    }


    public boolean deleteStudentFromCourse(Student studentToDelete, Course course){
        if(isEmptyCourses()){
            return false;
        }
        if(studentToDelete == null || course == null){
            return false;
        }

        Student []studentsAtCourse = course.getStudents();
        for(int i = 0; i < studentsAtCourse.length; i++){
            if(studentsAtCourse[i] != null){
                if(studentsAtCourse[i].getId().equals(studentToDelete.getId())){
                    studentsAtCourse[i] = null;
                    break;
                }
            }
        }

        for(Course item : courses){
            if(item != null && item.equals(course)){
                item.setStudents(studentsAtCourse);
                item.setFreePlaces(item.getFreePlaces() + 1);
            }
        }

        return true;
    }


    public boolean deleteStudent(Student studentToDelete){
        for(int i = 0; i < students.length; i++){
            if(students[i] != null){
                if(students[i].getId().equals(studentToDelete.getId())){
                    students[i] = null;
                    deleteStudentFromCourse(studentToDelete, studentToDelete.getCourse());
                    return true;
                }
            }
        }
        return false;
    }


    public boolean updStudent(Student student){
        for(int i = 0; i < students.length; i++){
            if(students[i] != null){
                if(students[i].getId().equals(student.getId())){
                    students[i] = student;
                    return true;
                }
            }
        }
        return false;
    }

    private String generateID(String id){
        for (Student student : students) {
            if (student != null) {
                if (student.getId().equals(id)) {
                    return generateID(UUID.randomUUID().toString());
                }
            }
        }
        return id;
    }

    private boolean checkUniquenessStudent(String idToCheck){
        for(Student student : students){
            if(student != null){
                if(student.getId().equals(idToCheck)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkUniquenessCourse(Course courseToCheck){
        for(Course course : courses){
            if(course != null){
                if(course.equals(courseToCheck)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isEmptyStudents(Student studentsToCheck[]){
        for(Student student : studentsToCheck){
            if(student != null){
                return false;
            }
        }
        return true;
    }

    private boolean isEmptyCourses(){
        for(Course course : courses){
            if(course != null){
                return false;
            }
        }
        return true;
    }


    private boolean isHaveFreePlace(Object []objects){
        for(Object item : objects){
            if(item == null){
                return true;
            }
        }
        return false;
    }

}