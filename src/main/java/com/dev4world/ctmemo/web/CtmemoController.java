package com.dev4world.ctmemo.web;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev4world.ctmemo.CtmemoConstant;
import com.dev4world.ctmemo.CtmemoSearchCondition;
import com.dev4world.ctmemo.CtmemoVo;
import com.dev4world.ctmemo.service.CtmemoService;
import com.setvect.common.date.DateUtil;

@Controller
public class CtmemoController {

	@Inject
	private CtmemoService ctmemoService;
	private static boolean init = false;

	@RequestMapping("/ctmemoPage.do")
	public String ctmemoPage(ServletRequest request, ModelMap model) {
		return "/ctmemoPage/main";
	}

	@RequestMapping("/listAllCtmemo.json")
	@ResponseBody
	public List<CtmemoVo> listAllCtmemo() {
		init();
		List<CtmemoVo> result = ctmemoService.listCtmemo(new CtmemoSearchCondition());
		return result;
	}

	private void init() {
		if (!init) {
			CtmemoVo ctmemoTestData = getCtmemoTestData();
			ctmemoService.insert(ctmemoTestData);
			CtmemoVo ctmemoTestData1 = getCtmemoTestData2();
			ctmemoService.insert(ctmemoTestData1);
			init = true;
		}
	}

	@RequestMapping("/newMemo.json")
	@ResponseBody
	public CtmemoVo newMemo() {
		return ctmemoService.newMemo();
	}

	/**
	 * @param ctmemo
	 * @return 변경한 메모의 z-index 값
	 */
	@RequestMapping("/saveMemo.do")
	@ResponseBody
	public int saveMemo(@ModelAttribute("ctmemo") CtmemoVo ctmemo) {
		ctmemo.setUptDate(new Date());
		ctmemo.setzIndex(ctmemoService.getMaxZindex());
		ctmemoService.updateCtmemo(ctmemo);
		return ctmemo.getzIndex();
	}

	@RequestMapping("/deleteMemo.do")
	@ResponseBody
	public String deleteMemo(ServletRequest request) {
		int ctmemoSeq = Integer.parseInt(request.getParameter("ctmemoSeq"));
		ctmemoService.removeCtmemo(ctmemoSeq);
		return "true";
	}

	/**
	 * 삭제 취소
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/undelete.json")
	@ResponseBody
	public CtmemoVo undelete(ServletRequest request) {
		int ctmemoSeq = Integer.parseInt(request.getParameter("ctmemoSeq"));
		CtmemoVo memo = ctmemoService.undeleteCtmemo(ctmemoSeq);
		return memo;
	}

	/**
	 * 사용하는 스타일
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/listUsagestyle.json")
	@ResponseBody
	public Map<String, List<String>> listUsagestyle() {
		Map<String, List<String>> m = new HashMap<String, List<String>>();
		m.put("font", CtmemoConstant.Style.FONTSTYLE_LIST);
		m.put("bg", CtmemoConstant.Style.BGSTYLE_LIST);
		return m;
	}

	public CtmemoVo getCtmemoTestData() {
		CtmemoVo ctmemo = new CtmemoVo();
		ctmemo.setCtmemoSeq(1);
		ctmemo.setContent("내용2\n복슬이");
		ctmemo.setBgCss(CtmemoConstant.Style.BGSTYLE_1);
		ctmemo.setFontCss(CtmemoConstant.Style.FONTSTYLE_1);
		ctmemo.setWidth(160);
		ctmemo.setHeight(130);
		ctmemo.setPositionX(220);
		ctmemo.setPositionY(220);
		ctmemo.setzIndex(ctmemoService.getMaxZindex());
		Date date = DateUtil.getDateTime("2015-02-13 11:22:11");
		ctmemo.setRegDate(date);
		ctmemo.setUptDate(date);

		return ctmemo;
	}

	public CtmemoVo getCtmemoTestData2() {
		CtmemoVo ctmemo = new CtmemoVo();
		ctmemo.setCtmemoSeq(2);
		ctmemo.setContent("내용1");
		ctmemo.setBgCss(CtmemoConstant.Style.BGSTYLE_2);
		ctmemo.setFontCss(CtmemoConstant.Style.FONTSTYLE_2);
		ctmemo.setWidth(160);
		ctmemo.setHeight(150);
		ctmemo.setPositionX(100);
		ctmemo.setPositionY(100);
		ctmemo.setzIndex(1);
		Date date = DateUtil.getDate("2015-02-14");
		ctmemo.setRegDate(date);
		ctmemo.setUptDate(date);
		return ctmemo;
	}

	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
					setValue(new Date(parsedDate.getTime()));
				} catch (ParseException e) {
					setValue(null);
				}
			}
		});
	}
}
