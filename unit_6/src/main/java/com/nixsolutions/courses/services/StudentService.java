package com.nixsolutions.courses.services;

import com.nixsolutions.courses.data.Group;
import com.nixsolutions.courses.data.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class StudentService {

    GroupService groupService = new GroupService();
//    Group group = new Group();
    Student[] list;

//    public StudentService(Group group) {
//        this.group = group;
//        list = group.getList();
//    }
//
//    public void init(Group group) {
//        list = group.getList();
//        this.group = group;
//    }

    public void create(Student student, Group group) {
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
        } else {
            group.setList(list);
            groupService.update(group);
        }
    }

    public Student read(String id, Group group) {
        if (StringUtils.isNotBlank(id)) {
            list = group.getList();
            Student current = getById(id);
            if (current == null) {
                throw new RuntimeException("Entity does not exist");
            }
            return current;
        } else {
            throw new RuntimeException("Entity does not exist");
        }
    }

    public void update(Student student, Group group) {
        if (StringUtils.isNotBlank(student.getId())) {
            list = group.getList();
            Student current = getById(student.getId());
            if (current == null) {
                throw new RuntimeException("Entity does not exist");
            }
            try {
                BeanUtils.copyProperties(current, student);
//                group
//                groupService.update(group);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            throw new RuntimeException("Entity does not exist");
        }
    }

    public void delete(String id, Group group) {
        if (StringUtils.isNotBlank(id)) {
            list = group.getList();
            Student current = getById(id);
            if (current == null) {
                throw new RuntimeException("Entity does not exist");
            }
            for (int i = 0; i < list.length; i++) {
                if (list[i].getId().equals(id)) {
                    list[i] = null;
                    break;
                }
            }
            group.setList(list);
            groupService.update(group);
        } else {
            throw new RuntimeException("Entity does not exist");
        }

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
        if(Arrays.stream(list).filter(Objects::nonNull).anyMatch(i -> i.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

}
