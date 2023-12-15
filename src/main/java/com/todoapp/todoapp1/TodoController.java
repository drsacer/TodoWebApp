package com.todoapp.todoapp1;

import com.todoapp.todoapp1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class TodoController {

    User currentUser = null;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    public TodoController(){

    }

    //TODOS

    @GetMapping("/customQ")
    public String customQ(Model model) {
        System.out.println("Count users with type 1: " + userRepository.customCounting());
        System.out.println("Count users with type 0: " + userRepository.countByType(0));

        return "login.html";

    }

    @GetMapping("/todos")
    public String showTodos(Model model){
        // model omogućuje da se lista može koristiti i prikazati u HTMLu (View) - on je spona između Controllera i View
        // držač (kontejner) svih varijabli koje kontroler proslijeđuje viewu i onda view prikazuje to na stranici
        model.addAttribute(todoRepository.findAllByUser(currentUser));
        model.addAttribute("currentUser", currentUser);

        return "employee_todo_list.html";
    }

    @GetMapping("/addNewTodo")
    public String addNewTodo(String title, String note){
        //dodaje se klikom na button Add todo in table
        Todo newTodo = new Todo(title, note, new Date());
        newTodo.setUser(currentUser);
        todoRepository.save(newTodo);
        return "redirect:/todos";
    }

    @GetMapping("/delete")
    public String deleteTodo(int id){
        todoRepository.deleteById(id);
        return "redirect:/todos";
    }

    // Employees

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute(userRepository.findAllByType(0));
        return "employees.html";
    }

    @GetMapping("/addNewEmployee")
    public String users(String fname, String lname, String oib, String email, String password, Model model){
        userRepository.save(new User(fname, lname, oib, email, password));

        return "redirect:/users";
    }

    @GetMapping("/showEditEmployee")
    public String showEditUser(int id, Model model){
        User user = userRepository.findById(id).get();

        model.addAttribute("user", user);

        return "supervisor_employee_edit";
    }

    @GetMapping("/editEmployee")
    public String editUser(int id, String fname, String lname, String oib, String email, Model model){
        User user = userRepository.findById(id).get();
        user.setFname(fname);
        user.setLname(lname);
        user.setOib(oib);
        user.setEmail(email);

        System.out.println("User " + email + " is updated.");
        return "redirect:/users";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(int id){
        userRepository.deleteById(id);
        return "redirect:/users";
    }


    // login
    @GetMapping("/loginProcess")
    public String loginProcess(@RequestParam("email") String email, @RequestParam("password") String password, Model model){
        User u = userRepository.findUserByEmailAndPassword(email,password);

        if (u == null) {
            model.addAttribute("userMessage", "User not found!");
            return "login.html";
        }
        else {
            currentUser = u;

            if(u.getType() == 0) {
                return "redirect:/todos";
            }
            else {
                return "redirect:/users";
            }
        }
    }

 //supervisor
    @GetMapping("/showToDosForUser")
    public String showToDosForUser(int id, Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute(user);

        // stavlja listu todoa od tog usera u model
        // filter todos only for that user
        model.addAttribute(todoRepository.findAllByUser(user));

        return "supervisor_employee_todos.html";
    }


    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
}
