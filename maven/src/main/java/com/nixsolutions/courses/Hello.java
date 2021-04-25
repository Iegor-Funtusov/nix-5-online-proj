package com.nixsolutions.courses;

import com.nixsolutions.courses.service.Todo;
import com.nixsolutions.courses.service.TodoDAO;
import com.nixsolutions.courses.service.TodoUtils;

public class Hello {
    public static void main(String[] args) {

        TodoDAO service = new TodoDAO();
        Todo todo1 = new Todo(1, "Finish reading a book", "01/05/20");
        Todo todo2 = new Todo(2, "Pet the cat", "25/04/20");
        service.createTodo(todo1);
        service.createTodo(todo2);
        TodoUtils.changeDateFormat(todo1);
        TodoUtils.setUpperCase(todo2);
        TodoUtils.printList(service.findAll());
    }
}
