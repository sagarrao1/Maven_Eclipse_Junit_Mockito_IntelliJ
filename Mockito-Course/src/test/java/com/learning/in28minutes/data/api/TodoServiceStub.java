package com.learning.in28minutes.data.api;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {

	@Override
	public List<String> retriveTodos(String user) {

		if (user == "Sagar") {
			return Arrays.asList("Learn Spring", "Learn Spring boot", "Learn Java");			
		} else {
			return Arrays.asList("");
		}
	}

}
