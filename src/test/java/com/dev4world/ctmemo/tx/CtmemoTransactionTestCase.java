package com.dev4world.ctmemo.tx;

import javax.inject.Inject;

import org.junit.Test;

import com.dev4world.ctmemo.CtmemoTestBase;
import com.dev4world.ctmemo.CtmemoTestUtil;
import com.dev4world.ctmemo.CtmemoVo;
import com.dev4world.ctmemo.service.CtmemoService;

/**
 * 트랜젝션 동작을 테스트
 */
public class CtmemoTransactionTestCase extends CtmemoTestBase {
	@Inject
	private CtmemoService service;

	@Test
	public void test() {
		CtmemoVo ctmemo = CtmemoTestUtil.getCtmemoTestData();
		service.insertCtmemoAndUpdate(ctmemo);
		System.out.println(ctmemo);
	}
}
