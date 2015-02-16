package com.dev4world.ctmemo.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev4world.ctmemo.CtmemoVo;
import com.dev4world.ctmemo.service.CtmemoService;
import com.setvect.common.date.DateUtil;

@Controller
public class CtmemoController {
	@Inject
	private CtmemoService ctmemoService;

	@RequestMapping("/ctmemoPage.do")
	public String ctmemoPage(ServletRequest request, ModelMap model) {
		System.out.println(ctmemoService);
		return "/ctmemoPage/main";
	}

	@RequestMapping("/listAllCtmemo.json")
	@ResponseBody
	public List<CtmemoVo> listAllCtmemo() {
		List<CtmemoVo> result = new ArrayList<CtmemoVo>();
		CtmemoVo ctmemoTestData = getCtmemoTestData();
		result.add(ctmemoTestData);
		return result;
	}

	public static CtmemoVo getCtmemoTestData() {
		CtmemoVo ctmemo = new CtmemoVo();
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
