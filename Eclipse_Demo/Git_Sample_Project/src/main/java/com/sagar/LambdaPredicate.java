package com.sagar;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaPredicate {

	public static void main(String[] args) {
		ArrayList<String> list= new ArrayList<>(List.of("one","two",
				"three","four","five"));
		
		Predicate<String> filter= (String s) -> !s.startsWith("t");
		list.removeIf(filter);
//		list.removeIf(s -> !s.startsWith("t")); // Simpler way
		
		Consumer<String> action = (String string) -> System.out.println(string);
		list.forEach(action);
	}

}
