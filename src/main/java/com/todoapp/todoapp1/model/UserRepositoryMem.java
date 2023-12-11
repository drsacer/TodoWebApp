package com.todoapp.todoapp1.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepositoryMem {

    List<User> userList = new ArrayList<>();

    public UserRepositoryMem() {
        User u = new User("Adam","Adam","123","adam@gmail.com","1");
        u.setType(1);
        userList.add(u);

        userList.add(new User("Dragan","Sačer","123","drsacer@gmail.com","1"));
        userList.add(new User("Karlo","Magdić","123","karlo@gmail.com","1"));
        userList.add(new User("Gloria","Zdrilić","123","gloria@gmail.com","1"));
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<User> getUsersEmployees(){
        List<User> usersEmployees = new ArrayList<>();
        for (User u : userList){
            if (u.getType() == 0){
                usersEmployees.add(u);
            }
        }
        return usersEmployees;
    }

    public User getUserByEmailAndPassword(String email, String password){
        User user = null;
        for (User u : userList){
            if (u.getEmail().equals(email) && u.getPassword().equals(password)){
                user = u;
            }
        }
        return user;
    }

    public User getUserById(int id) {
        User user = null;
        for (User u : userList) {
            if(u.getId() == id)
                user = u;

        }
        return user;
    }
}
