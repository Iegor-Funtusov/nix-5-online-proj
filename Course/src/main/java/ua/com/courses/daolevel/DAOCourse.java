package ua.com.courses.daolevel;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.courses.entity.Course;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

import static org.apache.commons.beanutils.BeanUtils.copyProperties;

public class DAOCourse {

    private static final Logger loggerError = LoggerFactory.getLogger("error");

    private static int size = 100;
    private Course[] courses = new Course[size];
    private int position;

    public void create(Course course) {
        courses[position++] = course;
    }

    public void update(Course course){
        if(StringUtils.isNoneBlank(course.getName())){
            Course current = getCoursebyName(course.getName());
            try {
                copyProperties(current, course);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        else{
            loggerError.error("Course name is empty");
            throw new RuntimeException("Course name is empty");
        }
    }

    public void delete(String name) {
        if (StringUtils.isNotBlank(name)) {
            Course current = getCoursebyName(name);
            if (current == null) {
                loggerError.error("Can not update course" + name + " it doesn`t exist");
                throw new RuntimeException("Can not delete course - it does not exist");
            }
            int length = position;
            Course[] courses1 = new Course[courses.length];
            for(int i = 0, j = 0; i < length; i++) {
                if(!courses[i].getName().equals(name)) {
                    courses1[j++] = courses[i];
                } else {
                    position--;
                }
            }
            courses = courses1;
        }
        else {
            loggerError.error("Course name is empty");
            throw new RuntimeException("Course name is empty");
        }
    }

    public Course read(String name){
        boolean c = checkIfExistCourse(name);
        if(StringUtils.isNoneBlank(name) && c){
            Course current = getCoursebyName(name);
            if(current == null){
                loggerError.error("Can not read course - it doesn`t exist");
                throw new RuntimeException("Can not read course - it does not exist");
            }
            return current;
        }
        else{
            loggerError.error("Can not read course - it does not exist or empty field");
           throw new RuntimeException("Can not read course - it does not exist or empty field");
        }
    }

    public Course [] readAll(){
        return Arrays.stream(courses).filter(Objects::nonNull)
                .toArray(Course[]::new);
    }

    private Course getCoursebyName(String name) {
        Course course = Arrays.stream(courses)
                .filter(e -> e.getName().equals(name))
                .findAny()
                .orElse(null);
        return course;
    }

    public boolean checkIfExistCourse(String name) {
        Course [] courses = readAll();
        for(Course c : courses){
            if(c.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}


