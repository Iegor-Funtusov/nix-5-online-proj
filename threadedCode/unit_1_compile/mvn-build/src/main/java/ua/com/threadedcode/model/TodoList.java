package ua.com.threadedcode.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.*;
import ua.com.threadedcode.Task;

public class TodoList {
    private List<Task> tasksStorage = new ArrayList<>();

    public void addTask(BufferedReader reader) throws IOException {
        Task task = new Task();

        System.out.println("enter title");
        String title = reader.readLine();
        task.setTitle(title);

        System.out.println("enter descriptions");
        String description = reader.readLine();
        task.setDescription(description);

        addTask(task);
    }

    public void addTask(Task task) {
        tasksStorage.add(task);
    }

    public void showTask() {
        tasksStorage.stream()
                .forEach(item -> System.out.println(StringUtils.upperCase(item.getTitle()) + ": " +
                        item.getDescription() + "\n" +
                        "=================="));
        System.exit(0);
    }
}