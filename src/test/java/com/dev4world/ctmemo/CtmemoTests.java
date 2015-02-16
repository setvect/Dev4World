package com.dev4world.ctmemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.dev4world.ctmemo.dao.CtmemoDaoTestCase;
import com.dev4world.ctmemo.service.CtmemoServiceTestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CtmemoDaoTestCase.class, CtmemoServiceTestCase.class })
public class CtmemoTests {
}
