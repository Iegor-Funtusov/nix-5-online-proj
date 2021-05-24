package com.nixsolutions.courses;

import com.nixsolutions.courses.data.Group;
import com.nixsolutions.courses.services.GroupService;
import org.junit.jupiter.api.*;

import javax.management.InstanceAlreadyExistsException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupServiceTest {

    private final static GroupService groupService = new GroupService();
    private final static int NUM_OF_INSTANCES = GroupService.STORAGE_CAPACITY;
    private final static int GROUP_SIZE = 2;

    @BeforeAll
    public static void init() throws InstanceAlreadyExistsException {
        for(int i = 0; i < NUM_OF_INSTANCES; i++) {
            Group group = new Group(GROUP_SIZE);
            group.setName("test" + i);
            groupService.create(group);
        }
        Assertions.assertTrue(groupService.readAll().length != 0);
    }

    @Test
    @Order(1)
    public void create() throws InstanceAlreadyExistsException {
        Group group = new Group (GROUP_SIZE);
        group.setName("test");
        groupService.create(group);
        Group createdGroup = groupService.read("test");
        Assertions.assertNotNull(createdGroup);
    }

    @Test
    @Order(2)
    public void readAll() {
        Group[] groups = groupService.readAll();

        Assertions.assertTrue(groups.length != 0);
    }

    @Test
    @Order(3)
    public void update() {
        Group group = groupService.read("test");
//        group
    }

}
