package com.sagar;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FirstLambda {

	public static void main(String[] args) {
		
		//Supplier
		Supplier<String> supplier = () -> {
			System.out.println("in supplier");
			return "Hello world!"; 
		};

		String string = supplier.get();
		System.out.println(string);
		
		
		//Consumer
		Consumer<String> consumer = (String s) -> {
			System.out.println("Inside consumer");
			System.out.println(s);
		};
		
		consumer.accept("this is cosumer");
		
	}

}
