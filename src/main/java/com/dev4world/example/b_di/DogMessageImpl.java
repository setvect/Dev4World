package com.dev4world.example.b_di;

public class DogMessageImpl implements Pet {
	@Override
	public String getMessage() {
		return "주인님";
	}
}
