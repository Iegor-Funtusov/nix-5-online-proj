package com.nixsolutions.courses.services;

import com.nixsolutions.courses.data.Group;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

public class GroupService {

    private final static int STORAGE_CAPACITY = 10;
    private final static Group[] db = new Group[STORAGE_CAPACITY];

    public void create(Group group) {
        boolean created = false;
        boolean unique = true;
        for (int i = 0; i < db.length; i++) {
            if (db[i] == null) {
                db[i] = group;
                created = true;
                break;
            } else if (db[i].getName().equals(group.getName())) {
                System.out.println("Group with the same name already exists");
                unique = false;
            }
        }
        if (!created && unique) {
            System.out.println("Storage is full");
        }
    }

    public Group read(String name) {
        if (StringUtils.isNotBlank(name)) {
            Group current = getByName(name);
            if (current == null) {
                throw new RuntimeException("Entity does not exist");
            }
            return current;
        } else {
            throw new RuntimeException("Entity does not exist");
        }
    }

    public void update(Group group) {
        if (StringUtils.isNotBlank(group.getName())) {
            Group current = getByName(group.getName());
            if (current == null) {
                throw new RuntimeException("Entity does not exist");
            }
            try {
                BeanUtils.copyProperties(current, group);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            throw new RuntimeException("Entity does not exist");
        }
    }

    public void delete(String name) {
        if (StringUtils.isNotBlank(name)) {
            Group current = getByName(name);
            if (current == null) {
                throw new RuntimeException("Entity does not exist");
            }
            for (int i = 0; i < db.length; i++) {
                if (db[i].getName().equals(name)) {
                    db[i] = null;
                    break;
                }
            }
        } else {
            throw new RuntimeException("Entity does not exist");
        }

    }

    public Group[] readAll() {
        return Arrays.stream(db).filter(Objects::nonNull).toArray(Group[]::new);
    }

    private Group getByName(String name) {
        return Arrays.stream(db)
                .filter(g -> g.getName().equals(name))
                .findAny()
                .orElse(null);
    }

}
