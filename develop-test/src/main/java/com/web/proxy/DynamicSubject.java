package com.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {

	private Object subject;

	public DynamicSubject(Object subject) {
		this.subject = subject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before call " + method);
		method.invoke(subject, args);
		System.out.println("after call " + method);

		return null;
	}
}
