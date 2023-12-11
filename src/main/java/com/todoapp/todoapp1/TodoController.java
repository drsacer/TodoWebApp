package com.todoapp.todoapp1;

import com.todoapp.todoapp1.model.Todo;
import com.todoapp.todoapp1.model.TodoRepositoryMem;
import com.todoapp.todoapp1.model.User;
import com.todoapp.todoapp1.model.UserRepositoryMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TodoController {

    List<Todo> todoList = new ArrayList<>();
    //List<User> userList = new ArrayList<>();

   // User currentUser;

    List<User> userList = new ArrayList<>();

    @Autowired
    UserRepositoryMem userRepo = new UserRepositoryMem();
    //TodoRepositoryMem todoRepo = new TodoRepositoryMem();

    public TodoController(){
        todoList.add(new Todo("HTML","Tags", new Date()));
        todoList.add(new Todo("CSS","Selectors",new Date()));
        todoList.add(new Todo("Bootstrap","Classes", new Date()));
    }

    //TODOS

    @GetMapping("/todos")
    public String showTodos(Model model){
        // model omogućuje da se lista može koristiti i prikazati u HTMLu (View) - on je spona između Controllera i View
        // držač (kontejner) svih varijabli koje kontroler proslijeđuje viewu i onda view prikazuje to na stranici
        model.addAttribute(todoList);
       /* System.out.println(currentUser.getFname());
        model.addAttribute(currentUser);*/
        return "employee_todo_list.html";
    }
    @GetMapping("/addNewTodo")
    public String addNewTodo(String title, String note){
        //dodaje se klikom na button Add todo in table
        todoList.add(new Todo(title, note, new Date()));
        return "redirect:/todos";
    }

    @GetMapping("/delete")
    public String deleteTodo(String title){
        //iterira se po listi i briše todo po title-u
        for (Todo todo: todoList) {
            if(todo.getTitle().equals(title)){
                todoList.remove(todo);
                break;
            }
        }
        return "redirect:/todos";
    }

    // Employees
    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute(userRepo.getUsersEmployees());
        return "employees.html";
    }

    @GetMapping("/addNewEmployee")
    public String users(String fname, String lname, String oib, String email, String password, Model model){
        userRepo.getUserList().add(new User(fname, lname, oib, email, password));
        model.addAttribute(userRepo.getUsersEmployees());

        return "redirect:/users";
    }

    // login
    @GetMapping("/loginProcess")
    public String loginProcess(@RequestParam("email") String email, @RequestParam("password") String password, Model model){
        User u = userRepo.getUserByEmailAndPassword(email,password);

        if (u == null) {
            model.addAttribute("userMessage", "User not found!");
            return "login.html";
        }
        else {
           // currentUser = u;

            if(u.getType() == 0) {
                return "redirect:/todos";
            }
            else {
                return "redirect:/users";
            }
        }
    }

/*    //supervisor
    @GetMapping("/showToDosForUser")
    public String showToDosForUser(int userId, Model model) {
        User user = userRepo.getUserById(userId);
        model.addAttribute(user);

        // stavlja listu todoa od tog usera u model
        // filter todos only for that user
        model.addAttribute(todoRepo.getTodoListForUserId(userId));

        return "supervisor_employee_todos.html";
    }*/


    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
}
