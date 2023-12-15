package com.todoapp.todoapp1.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {


    Iterable<Todo> findAllByUser(User currentUser);
}
