package com.todoapp.todoapp1.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    long countByType(int type);

    @Query(value = "select count(*) from app_user where type = 1", nativeQuery = true)
    long customCounting();

    User findUserByEmailAndPassword(String email, String password);

    Iterable<User> findAllByType(int type);
}
