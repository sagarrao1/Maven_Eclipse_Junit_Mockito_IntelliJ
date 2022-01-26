package com.sagar;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FunctionalProgRunner {

	public static void main(String[] args) {
		ArrayList<String> list=  new ArrayList<>( List.of("Apple","Banana","cat","Dog","Bat"));
		
		Consumer<String> action =  (String s) -> System.out.println(s);
//		list.forEach(action);

		Predicate<String> toFilter= (String str) -> str.endsWith("at");
//		list.stream().filter(toFilter).forEach(action);
		
		
//		list.stream().filter(s -> s.endsWith("at")).forEach(t -> System.out.println(t));
		
		
	
		ArrayList<Integer> numbers=  new ArrayList<>( List.of(1,4,7,8,9));
		
		numbers.stream().filter(n -> !(n%2==0)).forEach(t -> System.out.println(t));
		
		
	}

}
