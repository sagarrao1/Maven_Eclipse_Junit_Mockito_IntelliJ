package com.learning.in28minutes.business;

import java.util.ArrayList;
import java.util.List;

import com.learning.in28minutes.data.api.TodoService;

//TodoBusinessImpl   --> SUT (system under test)
//TodoService	 --> dependency
public class TodoBusinessImpl {
	private TodoService todoService;

	public TodoBusinessImpl(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	public List<String> retriveTodoRelatedToSpring(String user) {
		List<String> srpingTodos = new ArrayList<String>();
		List<String> todos = todoService.retriveTodos(user);

		for (String todo : todos) {
			if (todo.contains("Spring")) {
				srpingTodos.add(todo);
			}

		}
		return srpingTodos;
	}

	public void deleteTodoNotRelatedToSpring(String user) {	
		List<String> todos = todoService.retriveTodos(user);

		for (String todo : todos) {
			if (!todo.contains("Spring")) {
				todoService.deleteTodo(todo);
			}
		}
	}



}
