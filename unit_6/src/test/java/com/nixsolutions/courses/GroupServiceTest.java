package com.nixsolutions.courses;

import com.nixsolutions.courses.data.Group;
import com.nixsolutions.courses.services.GroupService;
import org.junit.jupiter.api.*;

import javax.management.InstanceAlreadyExistsException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupServiceTest {

    private final static GroupService groupService = new GroupService();
    private final static int NUM_OF_INSTANCES = GroupService.STORAGE_CAPACITY;
    private final static int GROUP_SIZE = 5;

    @Test
    @Order(1)
    public void create()  {
        try {
            Group group = new Group(GROUP_SIZE);
            group.setName("test");
            groupService.create(group);
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        }
        Group createdGroup = groupService.read("test");
        Assertions.assertNotNull(createdGroup);
    }

    @Test
    @Order(2)
    public void createDuplicate() {
        Group group = new Group(GROUP_SIZE);
        group.setName("test");
        Assertions.assertThrows(InstanceAlreadyExistsException.class, () ->{
            groupService.create(group);
        });
    }

    @Test
    @Order(3)
    public void readAll() {
        Group[] groups = groupService.readAll();

        Assertions.assertTrue(groups.length != 0);
    }

    @Test
    @Order(4)
    public void update() {
        Group group = groupService.read("test");
        groupService.update(group, "UPDATE");

        Assertions.assertThrows(RuntimeException.class, () ->{
            groupService.read("test");
        });
    }

    @Test
    @Order(5)
    public void delete() {
        groupService.delete("UPDATE");

        Assertions.assertThrows(RuntimeException.class, () ->{
            groupService.read("UPDATE");
        });
    }
}
