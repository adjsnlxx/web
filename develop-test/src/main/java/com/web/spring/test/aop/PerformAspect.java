package com.web.spring.test.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformAspect {

	/**
	 * Pointcut表示在那个执行目标上执行该功能
	 */
	@Pointcut("execution(* com.web.spring.test.aop.AopTest.*(..))")
	public void performAspect() {

	}

	/**
	 * Before（在目标方法执行之前执行）
	 */
	@Before("performAspect()")
	public void testBefore() {
		System.out.println("run before");
	}

	/**
	 * After（在目标方法执行之后执行）
	 */
	@After("performAspect()")
	public void testAfter() {
		System.out.println("run after");
	}
}
