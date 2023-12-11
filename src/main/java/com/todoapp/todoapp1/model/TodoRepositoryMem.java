package com.todoapp.todoapp1.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoRepositoryMem {

    @Autowired
    UserRepositoryMem userRepository;

    List<Todo> todoList = new ArrayList<>();

    public TodoRepositoryMem() {

        Todo todo1 = new Todo("Naučiti HTML","Tags", new Date());
        todo1.setUser(userRepository.getUserById(1));
        todoList.add(todo1);

        Todo todo2 = new Todo("Naučiti CSS","Selectors", new Date());
        todo2.setUser(userRepository.getUserById(2));
        todoList.add(todo2);

        Todo todo3 = new Todo("Naučiti JS", "Classes", new Date());
        todo3.setUser(userRepository.getUserById(2));
        todoList.add(todo3);

        todoList.add(new Todo("HTML","Tags", new Date()));
        todoList.add(new Todo("CSS","Selectors",new Date()));
        todoList.add(new Todo("Bootstrap","Classes", new Date()));
    }

    public List<Todo> getTodoListForUserId(int userId) {
        // filter todos only for that user
        List<Todo> todoForUser = new ArrayList<>();
        for(Todo todo: todoList) {
            if(todo.getUser() != null)
                if(todo.getUser().getId() == userId)
                    todoForUser.add(todo);
        }
        return todoForUser;
    }
}
