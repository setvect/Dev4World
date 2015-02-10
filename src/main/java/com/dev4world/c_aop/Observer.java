package com.dev4world.c_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Observer {

	@Pointcut("execution(* com.dev4world.c_aop.*.*(..))")
	private void selectAll() {
	}

	@Before("selectAll()")
	public void beforeAdvice() {
		System.out.println("----- 메소드 호출전");
	}

	@After("selectAll()")
	public void afterAdvice() {
		System.out.println("----- 메소드 호출후");
	}

	@AfterReturning(pointcut = "selectAll()", returning = "retVal")
	public void afterReturningAdvice(Object retVal) {
		if (retVal != null) {
			System.out.printf("----- 리턴값: %s", retVal);
		}
	}

	@Around("selectAll()")
	public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
		Object result = pjp.proceed();
		String manipulation = "[조작] " + result;
		System.out.printf("----- 리턴값 조작: 실재 값: %s, 조작: %s\n", result, manipulation);
		return manipulation;
	}

	@AfterThrowing(pointcut = "selectAll()", throwing = "ex")
	public void AfterThrowingAdvice(Exception ex) {
		System.out.println("----- 이크 예외가 발생했군요. " + ex.toString());
	}

}