package com.nixsolutions.courses.services;

import com.nixsolutions.courses.data.Group;
import com.nixsolutions.courses.data.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class StudentService {

    private final GroupService groupService = new GroupService();
    private Student[] list;
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");


    public void create(Student student, Group group) {
        loggerInfo.info("Started creating student: " + student.getId());
        list = group.getList();
        student.setId(generateId(UUID.randomUUID().toString()));
        boolean created = false;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = student;
                created = true;
                break;
            }
        }
        if (!created) {
            System.out.println("Storage is full");
            loggerError.error("Storage is full");
        } else {
            loggerInfo.info("Updating list of the group: " + group.getName());
            group.setList(list);
            groupService.update(group);
            loggerInfo.info("Ended creating student");
        }
    }

    public Student read(String id, Group group) {
        try {
            if (StringUtils.isNotBlank(id)) {
                list = group.getList();
                return getById(id);
            } else {
                loggerError.error("Entity does not exist");
                throw new RuntimeException("Entity does not exist");
            }
        } catch (NullPointerException e) {
            loggerError.error("Entity does not exist");
            throw new RuntimeException("Entity does not exist");
        }
    }

    public void update(Student student, Group group) {
        loggerInfo.info("Started updating student: " + student.getId());
        list = group.getList();
        Student current = read(student.getId(), group);
        try {
            BeanUtils.copyProperties(current, student);
        } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
            loggerError.error("Coping ");
            illegalAccessException.printStackTrace();
        }
        loggerInfo.info("Ended updating student");
    }

    public void delete(String id, Group group) {
        loggerWarn.warn("Started removing student by id: " + id);
        list = group.getList();
        Student current = read(id, group);
        for (int i = 0; i < list.length; i++) {
            if (list[i].getId().equals(current.getId())) {
                list[i] = null;
                break;
            }
        }
        loggerInfo.info("Updating group: " + group.getName());
        group.setList(list);
        groupService.update(group);
        loggerWarn.warn("Ended removing student");
    }

    public Student[] readAll(Group group) {
        list = group.getList();
        return Arrays.stream(list).filter(Objects::nonNull).toArray(Student[]::new);
    }

    private Student getById(String id) {
        return Arrays.stream(list)
                .filter(i -> i.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    private String generateId(String id) {
        if (Arrays.stream(list).filter(Objects::nonNull).anyMatch(i -> i.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

}
