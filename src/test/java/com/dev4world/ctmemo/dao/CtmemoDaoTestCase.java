package com.dev4world.ctmemo.dao;

import java.util.List;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.dev4world.ctmemo.CtmemoVo;
import com.dev4world.ctmemo.CtmemoSearchCondition;
import com.dev4world.ctmemo.CtmemoTestBase;
import com.dev4world.ctmemo.CtmemoTestUtil;

public class CtmemoDaoTestCase extends CtmemoTestBase {

	@Inject
	private CtmemoDao dao;

	@Test
	public void test() throws InterruptedException {
		System.out.println(dao);

		CtmemoVo ctmemo = CtmemoTestUtil.getCtmemoTestData();

		dao.insert(ctmemo);

		CtmemoVo getmemo = dao.getCtmemo(ctmemo.getCtmemoSeq());
		Assert.assertThat(ctmemo, CoreMatchers.is(getmemo));

		CtmemoSearchCondition condition = new CtmemoSearchCondition();
		List<CtmemoVo> list = dao.listCtmemo(condition);
		Assert.assertThat(list.size(), CoreMatchers.is(1));

		String content = "내사랑 복슬이";
		ctmemo.setContent(content);
		dao.updateCtmemo(ctmemo);

		CtmemoVo result = dao.getCtmemo(ctmemo.getCtmemoSeq());
		Assert.assertThat(content, CoreMatchers.is(result.getContent()));

		dao.deleteCtmemo(ctmemo.getCtmemoSeq());
		list = dao.listCtmemo(condition);
		Assert.assertThat(list.size(), CoreMatchers.is(0));

	}

	
}
