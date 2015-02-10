package com.dev4world.c_aop;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(AopTestCase.class);

	@Test
	public void AOP_테스트() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("c_aop/config_aop.xml")) {
			MemoProcessor myMemo = (MemoProcessor) context.getBean("memoProcessor");
			Assert.assertNotNull(myMemo);
			myMemo.add("코스모스가 많이도 핀 가을날...");
			String title = myMemo.get(1);
			LOGGER.info("리턴값: " + title);
		}
	}
}
