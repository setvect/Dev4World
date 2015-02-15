package com.dev4world.ctmemo.dao;

import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dev4world.ctmemo.vo.Ctmemo;
import com.setvect.common.date.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/context-*.xml", })
public class CtmemoDaoTestCase {

	@Inject
	CtmemoDao dao;

	@Test
	public void test() throws InterruptedException {
		System.out.println(dao);

		Ctmemo ctmemo = new Ctmemo();
		ctmemo.setContent("내용");
		ctmemo.setBgCss("bg_1");
		ctmemo.setFontCss("font_1");
		ctmemo.setWidth(100);
		ctmemo.setHeight(100);
		ctmemo.setPositionX(100);
		ctmemo.setPositionY(100);
		ctmemo.setzIndex(1);

		Date date = DateUtil.getDate("2015-02-14");
		ctmemo.setRegDate(date);
		ctmemo.setUptDate(date);
		dao.insert(ctmemo);
		Thread.sleep(1000000);
	}
}
