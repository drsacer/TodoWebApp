package com.todoapp.todoapp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Todoapp1Application {

	public static void main(String[] args) {

		SpringApplication.run(Todoapp1Application.class, args);
		System.out.println("Started on port 9090");
	}
}
