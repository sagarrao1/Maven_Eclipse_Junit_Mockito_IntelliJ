package com.learning.in28minutes.data.api;

import java.util.List;

public interface TodoService {

	public List<String> retriveTodos(String user);
	public void deleteTodo(String todo);

}
