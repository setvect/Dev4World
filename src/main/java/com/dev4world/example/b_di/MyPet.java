package com.dev4world.example.b_di;

public class MyPet {
	public Pet pet;

	public void setPet(Pet myPet) {
		this.pet = myPet;
	}

	public String getMessage() {
		return pet.getMessage();
	}
}
