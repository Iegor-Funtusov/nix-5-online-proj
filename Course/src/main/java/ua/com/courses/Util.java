package ua.com.courses;

import java.util.Scanner;

public class Util {

    public static void runApp(){
        CourseController courseController = new CourseController();
        Scanner scanner = new Scanner(System.in);
        boolean checker = true;
        while(checker){
            System.out.println("Choose operation:");
            System.out.println("1 -> create a course");
            System.out.println("2 -> create a student");
            System.out.println("3 -> read info about the course");
            System.out.println("4 -> read info about the student");
            System.out.println("5 -> get all courses");
            System.out.println("6 -> get all students");
            System.out.println("7 -> add student on the course");
            System.out.println("8 -> delete student from the course");
            System.out.println("9 -> delete student from the system");
            System.out.println("10 -> delete course from the system");
            System.out.println("11 -> exit");
            int chosenOperation;
            int getConsoleinfo = scanner.nextInt();
            scanner.nextLine();
                if(getConsoleinfo > 0 && getConsoleinfo < 12){
                    chosenOperation = getConsoleinfo;
                }
                else {
                    System.out.println("Please enter correct number of operation!!!");
                    continue;
                 }

            switch(chosenOperation){
                case 1: {
                    System.out.println("Enter course name: ");
                    String courseName = scanner.nextLine();
                    courseController.createCourse(courseName);
                    break;
                }
                case 2: {
                    System.out.println("Enter surname: ");
                    String surname = scanner.nextLine();
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    courseController.createStudent(surname, name);
                    break;
                }
                case 3: {
                    System.out.println("Enter course name to get info: ");
                    String name = scanner.nextLine();
                    courseController.getCourse(name);
                    break;
                }
                case 4: {
                    System.out.println("Enter surname and name to get info about current student");
                    System.out.println("Enter surname: ");
                    String surname = scanner.nextLine();
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    courseController.getStudent(surname, name);
                    break;
                }
                case 5: {
                    courseController.printCourses();
                    break;
                }
                case 6: {
                    courseController.printStudents();
                    break;
                }
                case 7: {
                    System.out.println("Fill the next info to ADD current student to the current course");
                    System.out.println("Enter surname: ");
                    String surname = scanner.nextLine();
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter course name: ");
                    String courseName = scanner.nextLine();
                    courseController.updateCourseAddStudent(courseController.getCourse(courseName),
                            courseController.getStudent(surname,name));
                    courseController.updateStudentAddCourses(courseController.getStudent(surname,name).getId(),
                            courseController.getCourse(courseName));
                    break;
                }
                case 8: {
                    System.out.println("Fill the next info to REMOVE current student to the current course");
                    System.out.println("Enter surname: ");
                    String surname = scanner.nextLine();
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter course name: ");
                    String courseName = scanner.nextLine();
                    courseController.UpdateCourseRemoveStudent(courseController.getCourse(courseName),
                            courseController.getStudent(surname,name));
                    break;
                }
                case 9: {
                    System.out.println("Enter surname and name to DELETE current student");
                    System.out.println("Enter surname: ");
                    String surname = scanner.nextLine();
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    courseController.deleteStudent(courseController.getStudent(surname, name).getId());
                    break;
                }
                case 10 : {
                    System.out.println("Enter course name to DELETE it: ");
                    String name = scanner.nextLine();
                    courseController.deleteCourse(name);
                    break;
                }
                case 11: {
                    checker = false;
                    break;
                }
            }
        }
    }

}
