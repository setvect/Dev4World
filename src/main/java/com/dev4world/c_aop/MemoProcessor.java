package com.dev4world.c_aop;

public class MemoProcessor {
	public void add(String title) {
		System.out.println(title + " 저장 완료");
	}

	public String get(int id) {
		return "메모장 내용";
	}

	public void delete(int id) {
		System.out.println(id + " 삭제 완료");
	}
}
