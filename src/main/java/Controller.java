import DataClasses.Course;
import DataClasses.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controller {
    private final Service service;
    private final BufferedReader bf;

    Controller(){
        service = new Service();
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public void userInterface(){
        try{
            do {
                System.out.println("Choose the role: 1-work with courses, 2-work with students");
                switch (Integer.parseInt(bf.readLine())){
                    case 1 -> courseInterface();
                    case 2 -> studentInterface();
                    default -> System.out.println("Incorrect value entered");
                }
                System.out.println("Do you want to continue using program at all? 1-yes, else-no");
            } while (Integer.parseInt(bf.readLine()) == 1);
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


    private void courseInterface(){
        try{
            do{
                System.out.println("""
                    Choose what do you want to do:\s 
                    1-add new course\s
                    2-get course\s
                    3-get all courses\s
                    4-get all students at course\s
                    5-get all students\s    
                    6-delete course
                    7-delete student from course""");

                switch (Integer.parseInt(bf.readLine())){
                    case 1 -> addCourse();
                    case 2 -> getCourse();
                    case 3 -> getAllCourses();
                    case 4 -> getAllStudentsAtCourse();
                    case 5 -> getAllStudents();
                    case 6 -> deleteCourse();
                    case 7 -> deleteStudentFromCourse();
                    default -> System.out.println("Incorrect value entered");
                }

                System.out.println("Do you want to continue working with courses? 1-yes, else-no");
            } while (Integer.parseInt(bf.readLine()) == 1);

        } catch (NumberFormatException e){
            System.out.println("Incorrect value entered");
        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


    private void studentInterface(){
        try {
            do {
                System.out.println("""
                Choose what do you want to do:\s
                1-add new student
                2-enroll a student on a course
                3-get student
                4-get all students
                5-delete student """);

                switch (Integer.parseInt(bf.readLine())){
                    case 1 -> addStudent();
                    case 2 -> enrollStudentToCourse();
                    case 3 -> getStudent();
                    case 4 -> getAllStudents();
                    case 5 -> deleteStudent();
                    default -> System.out.println("Incorrect value entered");
                }

                System.out.println("Do you want to continue working with students? 1-yes, else-no");
            } while (Integer.parseInt(bf.readLine()) == 1);

        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }


    private void addStudent() throws IOException{
        Student student = new Student();
        System.out.println("Enter the name of the student:");
        student.setName(bf.readLine());
        if(service.addStudent(student)){
            System.out.println("Successfully created. " + student.toString());
            System.out.println("Do you want to enroll student to course? 1-yes, else-no");
            if(Integer.parseInt(bf.readLine()) == 1){
                enrollStudentToCourse(student);
            }
        }
    }



    private void enrollStudentToCourse(Student student) throws IOException{
        if(student == null){
            System.out.println("Such student is not exists");
            return;
        }
        System.out.println("Choose the course:");
        Course chosenCourse = chooseCourse();
        if(chosenCourse == null){
            System.out.println("Such course is not exists");
            return;
        }
        if(service.addStudentToCourse(student, chosenCourse)){
            student.setCourse(chosenCourse);
            System.out.println("Student was successfully added to this course");
            return;
        }
        System.out.println("Error");
    }


    private void enrollStudentToCourse() throws IOException {
        boolean enrollToNewCourseFlag = false;
        try{
            System.out.println("Choose the student which you want to enroll to the course:");
            Student studentToEnroll = chooseStudent();
            if(studentToEnroll == null){
                System.out.println("Such student is not exists");
                return;
            }
            if(studentToEnroll.getCourse() != null){
                System.out.println("Student is already enrolled in the course. Enroll him to another course? 1-yes, else-no");
                if(Integer.parseInt(bf.readLine()) != 1){
                    return;
                }
                enrollToNewCourseFlag = true;
            }
            System.out.println("Choose the course:");
            Course chosenCourse = chooseCourse();
            if(chosenCourse == null){
                System.out.println("Such course is not exists");
                return;
            }
            //Проверка на то, что студент хочет запиисаться на тот курс, на котором он сейчас
            if(enrollToNewCourseFlag){
                if(studentToEnroll.getCourse().equals(chosenCourse)){
                    System.out.println("This student is already at this course");
                    return;
                }
            }
            if(service.addStudentToCourse(studentToEnroll, chosenCourse)){
                studentToEnroll.setCourse(chosenCourse);
                System.out.println("Student was successfully added to this course");
                return;
            }
            System.out.println("Error");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        };
    }


    private void getStudent() throws IOException{
        Student student;
        System.out.println("1-get student by name, else-get student by ID");
        if(Integer.parseInt(bf.readLine()) == 1){
            System.out.println("Enter name of the student:");
            String studentName = bf.readLine();
            student = service.getStudentByName(studentName);
        }
        else{
            System.out.println("Enter id of the student");
            String studentID = bf.readLine();
            student = service.getStudentById(studentID);
        }

        if(student == null){
            System.out.println("Student is not exists");
            return;
        }

        System.out.println(student.toString());
    }


    private void getAllStudents(){
        ArrayList<Student> allStudents = service.getAllStudents();
        if(allStudents == null){
            throw new RuntimeException("There are no students");
        }

        for(int i = 0; i < allStudents.size(); i++){
            System.out.println("Student #" + (i+1) + " " + allStudents.get(i));
        }
    }


    private void deleteStudentFromCourse() throws IOException{
        System.out.println("Select student which you want to delete");
        Student studentToDelete = chooseStudent();
        if(studentToDelete == null){
            System.out.println("Such student is not exists");
            return;
        }
        System.out.println("Choose course from which you want to delete the student");
        Course course = chooseCourse();
        if(course == null){
            System.out.println("Such course is not exists");
            return;
        }
        if(service.deleteStudentFromCourse(studentToDelete, course)){
            studentToDelete.setCourse(null);
            if(!service.updStudent(studentToDelete)){
                System.out.println("Error");
                return;
            }
            System.out.println("Delete was successful. Do you want to see all current students at this course? 1-yes, else-no");
            if(Integer.parseInt(bf.readLine()) == 1){
                getAllStudentsAtCourse(course);
            }
            return;
        }
        System.out.println("Can not delete this student from this course");

    }


    private void deleteStudent() throws IOException{
        System.out.println("Select student which you want to delete");
        Student studentToDelete = chooseStudent();
        if(studentToDelete == null){
            System.out.println("Such student is not exists");
            return;
        }
        if(service.deleteStudent(studentToDelete)){
            System.out.println("Successfully deleted. Do you want to see all current students? 1-yes, else-no");
            if(Integer.parseInt(bf.readLine()) == 1){
                getAllStudents();
            }
            return;
        }
        System.out.println("Cannot delete such student");
    }


    private void deleteCourse() throws IOException{
        System.out.println("Choose course which you want to delete");
        Course courseToDelete = chooseCourse();
        if(courseToDelete == null){
            System.out.println("Such course is not exists");
            return;
        }
        if(service.deleteCourse(courseToDelete)){
            System.out.println("Successfully deleted. Do you want to see all current courses? 1-yes, else-no");
            if(Integer.parseInt(bf.readLine()) == 1){
                getAllCourses();
            }
            return;
        }
        System.out.println("Cannot delete such course");
    }




    private void addCourse() throws IOException{
        Course courseToAdd = new Course();
        System.out.println("Enter name of the course:");
        courseToAdd.setNameOfCourse(bf.readLine());
        System.out.println("Enter name of the teacher:");
        courseToAdd.setNameOfTeacher(bf.readLine());

        if(service.addCourse(courseToAdd)){
            System.out.println("Successfully created. " + courseToAdd.toString());
            return;
        }
        System.out.println("Can not add new course");
    }



    private void getCourse() throws IOException{
        Course course;
        System.out.println("1-get course by course name, else-get course by name of teacher");
        if(Integer.parseInt(bf.readLine()) == 1){
            System.out.println("Enter name of the course:");
            String courseName = bf.readLine();
            course = service.getCourseByName(courseName);
        }
        else{
            System.out.println("Enter name of the teacher:");
            String teacherName = bf.readLine();
            course = service.getCourseByTeacher(teacherName);
        }

        if(course == null){
            System.out.println("Such course is not exists");
            return;
        }
        System.out.println(course.toString());
    }


    private void getAllCourses(){
        ArrayList<Course> allCourses = service.getAllCourses();
        if(allCourses == null){
            throw new RuntimeException("There are no courses");
        }
        if(allCourses.size() == 0){
            throw new RuntimeException("There are no courses");
        }

        for(int i = 0; i < allCourses.size(); i++){
            System.out.println("Course #" + (i+1) + " " + allCourses.get(i));
        }
    }



    private void getAllStudentsAtCourse() throws IOException {
        System.out.println("Please choose the course:");
        Course courseToGet = chooseCourse();
        if(courseToGet == null){
            System.out.println("Course is not exists");
            return;
        }
        ArrayList<Student> studentsAtCourse = service.getAllStudentsAtCourse(courseToGet);
        if(studentsAtCourse == null || studentsAtCourse.size() == 0){
            System.out.println("There are no students at this course");
            return;
        }

        for(Student item : studentsAtCourse){
            if(item != null){
                System.out.println(item.toString());
            }
        }
    }


    private void getAllStudentsAtCourse(Course course) {
        if(course == null){
            System.out.println("Course is not exists");
            return;
        }
        Student []studentsAtCourse = course.getStudents();
        for(Student item: studentsAtCourse){
            if(item != null){
                System.out.println(item.toString());
            }
        }
    }



    private Course chooseCourse() throws IOException{
        getAllCourses();
        System.out.println("Enter index of a course:");
        int courseIndex = Integer.parseInt(bf.readLine()) - 1;
        if(courseIndex < 0 || courseIndex > service.getAllCourses().size()){
            return null;
        }
        return service.getAllCourses().get(courseIndex);

    }



    private Student chooseStudent() throws IOException, RuntimeException{
        getAllStudents();
        System.out.println("Enter index of a student:");
        int studentIndex = Integer.parseInt(bf.readLine()) - 1;
        if(studentIndex < 0 || studentIndex > service.getAllStudents().size()){
            return null;
        }
        return service.getAllStudents().get(studentIndex);
    }

}