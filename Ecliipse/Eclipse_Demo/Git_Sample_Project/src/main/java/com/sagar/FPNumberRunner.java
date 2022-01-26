package com.sagar;

import java.util.List;

public class FPNumberRunner {

	public static void main(String[] args) {
		List<Integer> numbers= List.of(1,3,15,4,6,3,19);
		
		Integer sum = numbers.stream().filter(n -> n%2==1)
				.reduce(0, (number1, number2) -> {
					System.out.println(number1 +" " + number2);
					return number1+ number2;
					}
				);
		
		
		
		System.out.println(sum);
	}

}
