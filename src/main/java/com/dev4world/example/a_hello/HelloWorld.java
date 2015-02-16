package com.dev4world.example.a_hello;

public class HelloWorld {
	public static void main(String[] args) {
		HelloWorld message = new HelloWorld();
		System.out.println(message.getMessage());
	}

	public String getMessage() {
		return "Hello World.";
	}
}
