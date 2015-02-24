package com.dev4world.ctmemo.web;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev4world.ctmemo.CtmemoConstant;
import com.dev4world.ctmemo.CtmemoSearchCondition;
import com.dev4world.ctmemo.CtmemoSearchHelper;
import com.dev4world.ctmemo.CtmemoVo;
import com.dev4world.ctmemo.service.CtmemoService;

@Controller
public class CtmemoController {

	@Inject
	private CtmemoService ctmemoService;
	private static boolean init = false;

	@RequestMapping("/ctmemoPage.do")
	public String ctmemoPage() {
		return "/ctmemoPage/main";
	}

	@RequestMapping("/ctmemoMobile.do")
	public String ctmemoMobile() {
		return "/ctmemoPage/mobile";
	}
	
	@RequestMapping("/ctmemoAg.do")
	public String ctmemoAg() {
		return "/ctmemoPage/ag";
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
			List<CtmemoVo> samples = CtmemoSearchHelper.getSampleData();
			for (CtmemoVo v : samples) {
				ctmemoService.insertCtmemo(v);
			}
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

	@InitBinder
	protected void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				Date parsedDate = new Date(Long.parseLong(value));
				setValue(new Date(parsedDate.getTime()));
			}
		});
	}
}
