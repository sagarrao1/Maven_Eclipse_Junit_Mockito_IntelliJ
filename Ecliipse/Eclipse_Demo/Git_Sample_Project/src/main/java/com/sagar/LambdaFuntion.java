package com.sagar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaFuntion {

	public static void main(String[] args) {
		User user1= new User("Sagar");
		User user2= new User("Mahesh");
		User user3= new User("Sanju");
		User user4= new User("Bhavika");
		
		ArrayList<User> users=  new ArrayList<User>(List.of(user1,user2,user3,user4));
		List<String> names= new ArrayList<String>(); 
		
		Function<User, String> toName = (User usr) -> usr.getName();
		for (User user : users) {
			String string = toName.apply(user);			
			names.add(string);
		}
		
//		Consumer<User> action = (User u) -> System.out.println(u);
//		users.forEach(action);
		
		users.forEach(u -> System.out.println(u));
//		names.forEach(u -> System.out.println(u));
		names.forEach((String s) -> System.out.println(s));

	}

}
