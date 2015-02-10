package com.dev4world.b_di;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiTestCase {
	private static final Logger LOGGER = LoggerFactory.getLogger(DiTestCase.class);

	@Test
	public void DI테스트_Dog() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("b_di/config_di.xml")) {
			MyPet pet = (MyPet) context.getBean("myDog");
			Assert.assertNotNull(pet);
			String message = pet.getMessage();
			LOGGER.info(message);
			Assert.assertThat(message, is("주인님"));
		}
	}

	@Test
	public void DI테스트_Cat() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("b_di/config_di.xml")) {
			MyPet pet = (MyPet) context.getBean("myCat");
			Assert.assertNotNull(pet);
			String message = pet.getMessage();
			LOGGER.info(message);
			Assert.assertThat(message, is("집사 놈아"));
		}
	}
}
