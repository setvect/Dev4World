package com.dev4world.c_aop;

public class MemoProcessor {
	public void add(String title) {
		System.out.println("add() 호출");
	}

	public String get(int id) {
		System.out.println("get() 호출");
		return "메모장 내용";
	}

	public void delete(int id) {
		System.out.println("delete() 호출");
	}
}
