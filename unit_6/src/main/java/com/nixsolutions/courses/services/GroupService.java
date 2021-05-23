package com.nixsolutions.courses.services;

import com.nixsolutions.courses.data.Group;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.management.InstanceAlreadyExistsException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

public class GroupService {

    private final static int STORAGE_CAPACITY = 10;
    private final static Group[] db = new Group[STORAGE_CAPACITY];

    public void create(Group group) throws InstanceAlreadyExistsException {
        boolean created = false;
        for (int i = 0; i < db.length; i++) {
            if (db[i] == null) {
                db[i] = group;
                created = true;
                break;
            } else if (db[i].getName().equals(group.getName())) {
                throw new InstanceAlreadyExistsException();
            }
        }
        if (!created) {
            System.out.println("Storage is full");
        }
    }

    public Group read(String name) {
        try {
            if (StringUtils.isNotBlank(name)) {
                return getByName(name);
            } else {
                throw new RuntimeException("Entity does not exist");
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("Entity does not exist");
        }
    }

    public void update(Group group) {
        Group current = read(group.getName());
        try {
            BeanUtils.copyProperties(current, group);
        } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }

    public void delete(String name) {
            Group current = read(name);
            for (int i = 0; i < db.length; i++) {
                if (db[i].getName().equals(current.getName())) {
                    db[i] = null;
                    break;
                }
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
