package com.nixcourse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;

@RunWith(JUnit4.class)
public class TestApp {

    private final ObjectMapper mapper;

    public TestApp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void userCreation() {
        User user1 = new User(1, "Denis", "Nesterenko");
        User user2 = new User(2, "Illya", "Fedotov");
        User user3 = new User(3, "Egor", "Funtusov");
        Assert.assertNotNull(user1);
        Assert.assertEquals("Denis", user1.getName());
        Assert.assertEquals("Nesterenko", user1.getSurname());
    }

    @Test
    public void userSerializer() throws IOException {
        User user = new User(4, "Anonymous", "User");
        mapper.writeValue(new File("result.json"), user);
    }

    @Test
    public void userDeserializer() throws IOException {
        User actualUser = mapper.readValue(new File("result.json"), User.class);
        User expectedUser = new User(4, "Anonymous", "User");
        Assert.assertEquals(actualUser, expectedUser);
    }

    @AfterClass
    public static void userCounter() {
        Assert.assertEquals(5, User.getUserCounter());
    }

    public static void main(String[] args) {
        System.out.println("build finish successful");
    }
}