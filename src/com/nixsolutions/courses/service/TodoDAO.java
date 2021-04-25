package com.nixsolutions.courses.service;

import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    private List<Todo> todoList = new ArrayList<>();

    public void createTodo(Todo todo) {
        todoList.add(todo);
    }

    public List<Todo> findAll() {
        return this.todoList;
    }
}
