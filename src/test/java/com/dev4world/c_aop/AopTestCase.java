package com.dev4world.c_aop;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTestCase {

	private static final String CONFIG_FILE = "c_aop/config_aop.xml";
	// private static final String CONFIG_FILE =
	// "c_aop/config_aop_annotation.xml";
	private static final Logger LOGGER = LoggerFactory.getLogger(AopTestCase.class);

	@Test
	public void AOP_테스트() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_FILE)) {
			MemoProcessor myMemo = (MemoProcessor) context.getBean("memoProcessor");
			Assert.assertNotNull(myMemo);
			myMemo.add("코스모스가 많이도 핀 가을날...");
			String title = myMemo.get(1);
			System.out.println("리턴값: " + title);
		}
	}
}
