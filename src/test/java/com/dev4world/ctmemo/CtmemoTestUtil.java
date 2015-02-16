package com.dev4world.ctmemo;

import java.util.Date;

import com.dev4world.ctmemo.vo.Ctmemo;
import com.setvect.common.date.DateUtil;

public class CtmemoTestUtil {
	public static Ctmemo getCtmemoTestData() {
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

		return ctmemo;
	}
}
