package com.dev4world.ctmemo.web;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev4world.ctmemo.service.CtmemoService;

@Controller
public class CtmemoController {
	@Inject
	private CtmemoService ctmemoService;

	@RequestMapping("/ctmemoPage.do")
	public String mainPage(ServletRequest request, ModelMap model) {
		return "ctmemoPage/main";
	}
}
