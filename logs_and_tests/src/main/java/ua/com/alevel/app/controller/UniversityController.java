package ua.com.alevel.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.app.entity.Course;
import ua.com.alevel.app.entity.Student;
import ua.com.alevel.app.service.UniversityService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UniversityController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final UniversityService universityService = new UniversityService();
    private static final Logger log_error = LoggerFactory.getLogger("error");

    public void printUserMenu() {
        System.out.println(
                "Enter number to choose operation:" +
                        "\n1 -> Create Student" +
                        "\n2 -> Create Course" +
                        "\n3 -> Update Student" +
                        "\n4 -> Update Course" +
                        "\n5 -> Remove Student from course" +
                        "\n6 -> Remove Student" +
                        "\n7 -> Remove Course" +
                        "\n8 -> Print all students on courses" +
                        "\n9 -> Print all students" +
                        "\n10 -> Print all courses" +
                        "\n11 -> To add student to some course" +
                        "\n0 -> Exit"
        );
    }

    public void chooseMethod() throws IOException {
        try {
            while (true) {
                printUserMenu();
                int userInput = getUserInt();
                switch (userInput) {
                    case 1:
                        createStudent();
                        break;
                    case 2:
                        createCourse();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        updateCourse();
                        break;
                    case 5:
                        removeStudentFromCourse();
                        break;
                    case 6:
                        removeStudent();
                        break;
                    case 7:
                        removeCourse();
                        break;
                    case 8:
                        printAll();
                        break;
                    case 9:
                        printAllStudents();
                        break;
                    case 10:
                        printAllCourses();
                        break;
                    case 11:
                        addStudentOnCourse();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            log_error.error("User wrong input " + "Class: " + this.getClass().getSimpleName());
            System.out.println("Wrong input try again!");
            chooseMethod();
        }
    }

    private void createStudent() throws IOException {
        System.out.println("Enter student's name ");
        String name = getUserString();
        System.out.println("Enter student's age: ");
        int age = getUserInt();
        universityService.addStudent(name, age);
    }

    private void createCourse() throws IOException {
        System.out.println("Enter course name: ");
        String name = getUserString();
        System.out.println("Enter duration of course: ");
        int hours = getUserInt();
        universityService.addCourse(name, hours);
    }

    private void printAll() {
        universityService.printAllStudentsOnCourses();
    }

    private String getUserString() throws IOException {
        return reader.readLine();
    }

    private Integer getUserInt() throws IOException {
        String format = "\\d+";
        String userInput = reader.readLine();
        if (userInput.matches(format)) {
            return Integer.parseInt(userInput);
        } else {
            log_error.error("User wrong input " + "Class: " + this.getClass().getSimpleName());
            throw new IllegalArgumentException();
        }
    }

    private void printAllStudents() {
        universityService.printAllStudents();
    }

    private void printAllCourses() {
        universityService.printAllCourses();
    }

    private void addStudentOnCourse() throws IOException {
        try {
            System.out.println("Enter id of student who joins some course: ");
            int studId = getUserInt();
            System.out.println("Enter id of course where to add student: ");
            int courseId = getUserInt();
            universityService.addStudentToCourse(studId, courseId);
        } catch (IllegalArgumentException e) {
            log_error.error("User wrong input " + "Class: " + this.getClass().getSimpleName());
            System.out.println("Wrong input!\n" +
                    "1 - Try again\n" +
                    "Any key - Main menu");
            if (getUserInt() == 1) {
                addStudentOnCourse();
            }
        }
    }

    private void updateStudent() throws IOException {
        System.out.println("Enter id of student");
        int studentId = getUserInt();
        System.out.println("What do you want to update?\n" +
                "1 - Name\n" +
                "2 - Age");
        int userInput = getUserInt();
        try {
            if (userInput == 1) {
                System.out.println("Enter new name");
                String newName = getUserString();
                Student student = new Student(newName, universityService.getStudent(studentId).getAge());
                universityService.updateStudentInfo(studentId, student);
            }
            if (userInput == 2) {
                System.out.println("Entre new age");
                int age = getUserInt();
                Student student = new Student(universityService.getStudent(studentId).getName(), age);
                universityService.updateStudentInfo(studentId, student);
            }
        } catch (NullPointerException | IllegalArgumentException e1) {
            log_error.error("User wrong input " + "Class: " + this.getClass().getSimpleName());
            System.out.println("Wrong input\n" +
                    "1 - Try again\n" +
                    "Any key - Main menu");
            if (getUserInt() == 1) {
                updateStudent();
            }
        }
    }

    private void updateCourse() throws IOException {
        System.out.println("Enter id of course");
        int courseId = getUserInt();
        System.out.println("What do you want ot update?\n" +
                "1 - Name\n" +
                "2 - Duration"
        );
        try {
            int userInput = getUserInt();
            if (userInput == 1) {
                System.out.println("Enter new name");
                String newName = getUserString();
                Course course = new Course(newName, universityService.getCourse(courseId).getDuration());
                universityService.updateCourseInfo(courseId, course);
            }
            if (userInput == 2) {
                System.out.println("Entre new duration");
                int duration = getUserInt();
                Course course = new Course(universityService.getCourse(courseId).getName(), duration);
                universityService.updateCourseInfo(courseId, course);
            }
        } catch (NullPointerException | IllegalArgumentException e1) {
            log_error.error("User wrong input " + "Class: " + this.getClass().getSimpleName());
            System.out.println("Wrong input!\n" +
                    "1 - Try again\n" +
                    "Any key - Main menu");
            if (getUserInt() == 1) {
                updateCourse();
            }
        }
    }

    private void removeStudentFromCourse() throws IOException {
        try {
            System.out.println("Enter id of student");
            int studentId = getUserInt();
            System.out.println("Enter id of course");
            int courseId = getUserInt();
            universityService.removeStudentFromCourse(studentId, courseId);
        } catch (IllegalArgumentException e) {
            log_error.error("User wrong input " + "Class: " + this.getClass().getSimpleName());
            System.out.println("Wrong input!!!\n" +
                    "1 - Try again\n" +
                    "Any key - Main menu");
            if (getUserInt() == 1) {
                removeStudentFromCourse();
            }
        }
    }

    private void removeStudent() throws IOException {
        try {
            System.out.println("Enter student id");
            int studentId = getUserInt();
            universityService.removeStudent(studentId);
        } catch (IllegalArgumentException e) {
            log_error.error("User wrong input " + "Class: " + this.getClass().getSimpleName());
            System.out.println("Wrong input!\n" +
                    "1 - Try again\n" +
                    "Any key - Main menu");
            if (getUserInt() == 1) {
                removeStudent();
            }
        }
    }

    private void removeCourse() throws IOException {
        try {
            System.out.println("Enter course id");
            int courseId = getUserInt();
            universityService.removeCourse(courseId);
        } catch (IllegalArgumentException e) {
            log_error.error("User wrong input " + "Class: " + this.getClass().getSimpleName());
            System.out.println("Wrong input!\n" +
                    "1 - Try again\n" +
                    "Any key - Main menu");
            if (getUserInt() == 1) {
                removeCourse();
            }
        }
    }

}