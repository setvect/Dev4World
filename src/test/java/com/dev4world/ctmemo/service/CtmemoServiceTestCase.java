package com.dev4world.ctmemo.service;

import java.util.List;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.dev4world.ctmemo.CtmemoSearchCondition;
import com.dev4world.ctmemo.CtmemoTestBase;
import com.dev4world.ctmemo.CtmemoTestUtil;
import com.dev4world.ctmemo.vo.Ctmemo;

public class CtmemoServiceTestCase extends CtmemoTestBase {
	@Inject
	private CtmemoService service;

	@Test
	public void test() {
		Ctmemo ctmemo = CtmemoTestUtil.getCtmemoTestData();
		service.insert(ctmemo);

		Ctmemo getmemo = service.getCtmemo(ctmemo.getCtmemoSeq());
		Assert.assertThat(ctmemo, CoreMatchers.is(getmemo));

		CtmemoSearchCondition condition = new CtmemoSearchCondition();
		List<Ctmemo> list = service.listCtmemo(condition);
		Assert.assertThat(list.size(), CoreMatchers.is(1));

		String content = "내사랑 복슬이";
		ctmemo.setContent(content);
		service.updateCtmemo(ctmemo);

		Ctmemo result = service.getCtmemo(ctmemo.getCtmemoSeq());
		Assert.assertThat(content, CoreMatchers.is(result.getContent()));

		service.removeCtmemo(ctmemo.getCtmemoSeq());
		list = service.listCtmemo(condition);
		Assert.assertThat(list.size(), CoreMatchers.is(0));

	}
}
