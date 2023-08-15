package com.learning.in28minutes.business;

import java.util.ArrayList;
import java.util.List;

import com.learning.in28minutes.data.api.TodoService;

//TodoBusinessImpl   --> SUT (system under test)
//TodoService	 --> dependency
public class TodoBusinessImpl {

	private TodoService todoService;
		
	public TodoBusinessImpl(TodoService todoService) {
		this.todoService = todoService;
	}

	public List<String> retriveTodosRealtedToSpring(String user){
		List<String> filteredTodos = new ArrayList<String>();
		
		List<String> retriveTodos = todoService.retriveTodos(user);
		
		for (String todo : retriveTodos) {
			if (todo.contains("Spring") ) {
				filteredTodos.add(todo);
			}
		}
		
		return filteredTodos;
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
