package com.dev4world.example.c_aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class Observer {

	public void beforeAdvice() {
		System.out.println("----- 메소드 호출전");
	}

	public void afterAdvice() {
		System.out.println("----- 메소드 호출후");
	}

	public void afterReturningAdvice(Object retVal) {
		if (retVal != null) {
			System.out.printf("----- 리턴값: %s\n", retVal);
		}
	}

	public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
		Object result = pjp.proceed();
		String manipulation = "[조작] " + result;
		System.out.printf("----- 리턴값 조작: 실재 값: %s, 조작: %s\n", result, manipulation);
		return manipulation;
	}

	public void AfterThrowingAdvice(Exception ex) {
		System.out.println("----- 이크 예외가 발생했군요. " + ex.toString());
	}

}