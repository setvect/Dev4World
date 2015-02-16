package com.dev4world.example.z_annotation;

import org.junit.Test;

public class Reflection {
	@Test
	public void testRelection() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> object = Class.forName("com.dev4world.z_etc.NewsArticle");
		Object obj = object.newInstance();
		System.out.println(obj);
	}
}
