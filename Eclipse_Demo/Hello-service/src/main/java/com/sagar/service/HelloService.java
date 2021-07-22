package com.sagar.service;

public class HelloService {
	public String getMessage() {
		return "Hello world!";
	}

	public static void main(String[] args) {
		HelloService sr=  new HelloService();
		System.out.println(sr.getMessage());
	}
}
