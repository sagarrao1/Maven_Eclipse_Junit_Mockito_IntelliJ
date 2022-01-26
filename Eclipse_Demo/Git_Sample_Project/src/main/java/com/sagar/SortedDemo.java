package com.sagar;

import java.util.List;
import java.util.stream.IntStream;

public class SortedDemo {

	public static void main(String[] args) {

//		List<Integer> numbers= List.of(1,3,15,4,6,3,19,213,56,4);		
		
//		numbers.stream().distinct().sorted().forEach(e -> System.out.println(e));
		
//		numbers.stream().distinct().map(n -> n*2).sorted()
//		.forEach(e -> System.out.println(e));
		
		
//		Print Squares of first 10 numbers
		System.out.println("Print Squares of first 10 numbers");
		IntStream intStream = IntStream.range(1, 11);		
		intStream.map(n -> n*n).forEach(e -> System.out.println(e));
		
//		Print all lower case for list of elements
		System.out.println();
		System.out.println("Print all lower case for list of elements");
		List<String> list= List.of("Apple","Ant","Bat","Sanju");
		
		list.stream().map(s -> s.toLowerCase()).forEach(e -> System.out.println(e));
		
		
//		Print length of each element
		System.out.println();
		System.out.println("Print length of each element");
//		List<String> list= List.of("Apple","Ant","Bat","Sanju");
		
		list.stream().map(s -> {
			System.out.println("length of "+s +" is ");
			return s.length();
		  }
		)
		.forEach(e -> System.out.println(e));
		
	}

}
