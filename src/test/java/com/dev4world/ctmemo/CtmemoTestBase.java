package com.dev4world.ctmemo;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/context-common.xml", "classpath:spring/context-hibernate.xml", "classpath:springmvc/context-*.xml" })
//@ContextConfiguration(locations = { "classpath:spring/context-common.xml", "classpath:spring/context-mybatis.xml", "classpath:springmvc/context-*.xml" })
//@ContextConfiguration(locations = { "classpath:spring/context-common.xml", "classpath:spring/context-mongo.xml", "classpath:springmvc/context-*.xml" })
public class CtmemoTestBase {

}
