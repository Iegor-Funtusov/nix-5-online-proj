package ua.com.courses.daolevel;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.courses.entity.Student;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import static org.apache.commons.beanutils.BeanUtils.copyProperties;

public class DAOStudent {

    private static final Logger loggerError = LoggerFactory.getLogger("error");

    private static int size = 100;
    private Student[] students = new Student[size];
    private int position;

    public void create(Student student) {
        student.setId(generateId(UUID.randomUUID().toString()));
        students[position++] = student;
    }

    public void update(Student student){
        if(StringUtils.isNoneBlank(student.getId())){
            Student current = getEbyId(student.getId());
            try {
                copyProperties(current, student);
            } catch (IllegalAccessException |InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        else{
            loggerError.error("Can not update student" + student.getId() + " it doesn`t exist");
            throw new RuntimeException("Can not update student - it does not exist");
        }
    }

    public void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            Student current = getEbyId(id);
            if (current == null) {
                throw new RuntimeException("Can not delete student - it does not exist");
            }
            int length = position;
            Student [] students2 = new Student[students.length];
            for(int i = 0, j = 0; i < length; i++) {
                if(!(students[i].getId().equals(id))) {
                    students2[j++] = students[i];
                } else {
                    position--;
                }
            }
            students = students2;
        }
        else { loggerError.error("Can not delete student" + id + " it doesn`t exist");
            throw new RuntimeException("Can not delete entity - it does not exist");
        }
    }

    public Student read(String id){
        boolean c = checkIfExistStudent(id);
        if(StringUtils.isNoneBlank(id) && c){
            Student current = getEbyId(id);
            if(current == null){
                loggerError.error("Can not read student" + id + " it doesn`t exist");
                throw new RuntimeException("Can not read student - it does not exist");
            }
            return current;
        }
        else{
            loggerError.error("Can not read student" + id + " it doesn`t exist");
            throw new RuntimeException("Can not read student - it does not exist");
        }
    }

    public Student readSurnameName(String surname, String name){
        boolean c = checkIfExistStudentSurname(surname, name);
        if(StringUtils.isNoneBlank(surname) && c){
            Student current = getEbyName(surname, name);
            if(current == null){
                loggerError.error("Can not read student" + surname + " it doesn`t exist");
                throw new RuntimeException("Can not read student - it does not exist");
            }
            return current;
        }
        else{
            loggerError.error("Can not read student" + surname + " it doesn`t exist");
            throw new RuntimeException("Can not read student - it does not exist");
        }
    }

    public Student[] readAll(){
        return Arrays.stream(students).filter(Objects::nonNull)
                .toArray(Student[]::new);
    }

    private Student getEbyId(String id) {
        Student student = Arrays.stream(students)
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);
        return student;
    }

    private Student getEbyName(String surname, String name) {
        for(int i = 0; i < position; i++){
            if(students[i].getName().equals(name) &&
            students[i].getSurname().equals(surname)){
                return students[i];
            }
        }
        return null;
    }

    private String generateId(String id) {
        if (Arrays.stream(students).filter(Objects::nonNull)
                .anyMatch(e -> e.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

    public boolean checkIfExistStudent(String id) {
        Student [] students = readAll();
        for(Student s : students){
            if(s.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfExistStudentSurname(String surname, String name) {
        Student [] students = readAll();
        for(Student s : students){
            if(s.getSurname().equals(surname) && s.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}