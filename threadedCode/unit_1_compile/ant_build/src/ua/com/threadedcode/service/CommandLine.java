package ua.com.threadedcode.service;

import ua.com.threadedcode.model.TodoList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLine {
    private TodoList todoList = new TodoList();

    static {
        System.out.println("Synopsis: \n" +
                "0: exit, 1: add task, 2: show tasks");
    }

    public void readConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = reader.readLine()) != null) {
            switch (input) {
                case "0": {
                    System.exit(0);
                }

                case "1": {
                    todoList.addTask(reader);
                    readConsole();
                }

                case "2": {
                    todoList.showTask();
                    readConsole();
                }
                
            }
        }
    }
}