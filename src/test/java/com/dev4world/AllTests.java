package com.dev4world;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.dev4world.a_hello.HelloWorldTestCase;
import com.dev4world.b_di.DiRunWithSpringTestCase;
import com.dev4world.b_di.DiTestCase;
import com.dev4world.c_aop.AopTestCase;
import com.dev4world.z_annotation.AnnotationTestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses({ HelloWorldTestCase.class, DiTestCase.class, DiRunWithSpringTestCase.class, AopTestCase.class,
		AnnotationTestCase.class })
public class AllTests {
}
