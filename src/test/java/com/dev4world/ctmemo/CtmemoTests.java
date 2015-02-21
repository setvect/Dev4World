package com.dev4world.ctmemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.dev4world.ctmemo.controller.CtmemoControllerTestCase;
import com.dev4world.ctmemo.dao.CtmemoDaoTestCase;
import com.dev4world.ctmemo.service.CtmemoServiceTestCase;
import com.dev4world.ctmemo.tx.CtmemoTransactionTestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CtmemoDaoTestCase.class, CtmemoServiceTestCase.class, CtmemoControllerTestCase.class,
		CtmemoTransactionTestCase.class })
public class CtmemoTests {
}
