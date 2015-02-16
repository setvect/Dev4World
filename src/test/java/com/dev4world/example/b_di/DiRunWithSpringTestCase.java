package com.dev4world.example.b_di;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dev4world.example.b_di.MyPet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:example/config_di.xml" })
public class DiRunWithSpringTestCase {
	private static final Logger LOGGER = LoggerFactory.getLogger(DiRunWithSpringTestCase.class);

	@Autowired
	@Qualifier("myDog")
	private MyPet dogPet;

	@Autowired
	@Qualifier("myCat")
	private MyPet catPet;

	@Test
	public void DI테스트_Dog() {
		Assert.assertNotNull(dogPet);
		String message = dogPet.getMessage();
		LOGGER.info(message);
		Assert.assertThat(message, is("주인님"));
	}

	@Test
	public void DI테스트_Cat() {
		Assert.assertNotNull(catPet);
		String message = catPet.getMessage();
		LOGGER.info(message);
		Assert.assertThat(message, is("집사 놈아"));
	}
}
