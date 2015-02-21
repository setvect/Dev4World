package com.dev4world.ctmemo.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.dev4world.ctmemo.CtmemoConstant;
import com.dev4world.ctmemo.CtmemoTestBase;
import com.dev4world.ctmemo.CtmemoVo;
import com.dev4world.ctmemo.web.CtmemoController;

public class CtmemoControllerTestCase extends CtmemoTestBase {
	private static final String CONTENT_STRING = "복슬이 멍멍";
	@Inject
	private CtmemoController controller;

	@Test
	public void test() {
		String jspPage = controller.ctmemoPage();
		Assert.assertThat(jspPage, CoreMatchers.is("/ctmemoPage/main"));

		jspPage = controller.ctmemoMobile();
		Assert.assertThat(jspPage, CoreMatchers.is("/ctmemoPage/mobile"));

		List<CtmemoVo> result = controller.listAllCtmemo();
		Assert.assertThat(result.size(), CoreMatchers.is(3));

		CtmemoVo ctmemo = controller.newMemo();
		ctmemo.setContent(CONTENT_STRING);
		controller.saveMemo(ctmemo);

		result = controller.listAllCtmemo();
		Assert.assertThat(result.size(), CoreMatchers.is(4));

		// 최근 등록
		CtmemoVo newest = result.get(0);
		Assert.assertThat(newest.getContent(), CoreMatchers.is(CONTENT_STRING));

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("ctmemoSeq", String.valueOf(newest.getCtmemoSeq()));
		controller.deleteMemo(request);

		result = controller.listAllCtmemo();
		Assert.assertThat(result.size(), CoreMatchers.is(3));

		controller.undelete(request);
		result = controller.listAllCtmemo();
		Assert.assertThat(result.size(), CoreMatchers.is(4));

		Map<String, List<String>> styleList = controller.listUsagestyle();
		Assert.assertThat(styleList.get("font"), CoreMatchers.is(CtmemoConstant.Style.FONTSTYLE_LIST));
		Assert.assertThat(styleList.get("bg"), CoreMatchers.is(CtmemoConstant.Style.BGSTYLE_LIST));

	}
}
