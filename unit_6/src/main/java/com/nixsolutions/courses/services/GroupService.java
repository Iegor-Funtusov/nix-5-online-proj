package com.nixsolutions.courses.services;

import com.nixsolutions.courses.data.Group;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.InstanceAlreadyExistsException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

public class GroupService {

    public final static int STORAGE_CAPACITY = 10;
    private final static Group[] db = new Group[STORAGE_CAPACITY];
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public void create(Group group) throws InstanceAlreadyExistsException {
        loggerInfo.info("Started creating group: " + group.getName());
        boolean created = false;
        for (int i = 0; i < db.length; i++) {
            if (db[i] == null) {
                db[i] = group;
                created = true;
                break;
            } else if (db[i].getName().equals(group.getName())) {
                loggerError.error("Group already exists: " + group.getName());
                throw new InstanceAlreadyExistsException();
            }
        }
        if (!created) {
            System.out.println("Storage is full");
            loggerError.error("Storage is full");
        } else {
            loggerInfo.info("Ended creating group");
        }
    }

    public Group read(String name) {
        try {
            if (StringUtils.isNotBlank(name)) {
                return getByName(name);
            } else {
                loggerError.error("Entity does not exist");
                throw new RuntimeException("Entity does not exist");
            }
        } catch (NullPointerException e) {
            loggerError.error("Entity does not exist");
            throw new RuntimeException("Entity does not exist");
        }
    }

    public void update(Group group, String name) {
        loggerInfo.info("Started updating group: " + group.getName());
        Group current = read(name);
        try {
            BeanUtils.copyProperties(current, group);
        } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        loggerInfo.info("Ended updating group");
    }

    public void delete(String name) {
        loggerWarn.warn("Started removing group by name: " + name);
        Group current = read(name);
        for (int i = 0; i < db.length; i++) {
            if (db[i].getName().equals(current.getName())) {
                db[i] = null;
                break;
            }
        }
        loggerWarn.warn("Ended removing group");
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
