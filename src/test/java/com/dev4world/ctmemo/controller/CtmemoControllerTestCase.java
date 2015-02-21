package com.dev4world.ctmemo.controller;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.dev4world.ctmemo.CtmemoTestBase;
import com.dev4world.ctmemo.web.CtmemoController;

public class CtmemoControllerTestCase extends CtmemoTestBase {
	@Inject
	private CtmemoController controller;

	@Test
	public void test() {
		String jspPage = controller.ctmemoPage();
		Assert.assertThat(jspPage, CoreMatchers.is("/ctmemoPage/main"));

	}
}
