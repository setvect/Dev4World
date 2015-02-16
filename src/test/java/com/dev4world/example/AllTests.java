package com.dev4world.example;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.dev4world.example.a_hello.HelloWorldTestCase;
import com.dev4world.example.b_di.DiRunWithSpringTestCase;
import com.dev4world.example.b_di.DiTestCase;
import com.dev4world.example.c_aop.AopTestCase;
import com.dev4world.example.z_annotation.AnnotationTestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses({ HelloWorldTestCase.class, DiTestCase.class, DiRunWithSpringTestCase.class, AopTestCase.class,
		AnnotationTestCase.class })
public class AllTests {
}
