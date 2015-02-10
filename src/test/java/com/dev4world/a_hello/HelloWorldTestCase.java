package com.dev4world.a_hello;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test; 

public class HelloWorldTestCase {
	@Test
	public void 메시지_반환_확인() {
		HelloWorld world = new HelloWorld();
		Assert.assertThat(world.getMessage(), is("Hello World."));
	}
}
