package com.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicSubjectTest {

	public static void main(String[] args) {
		RealSubject subject = new RealSubject();
		InvocationHandler invocationHandler = new DynamicSubject(subject);
		Class cls = subject.getClass();

		Subject subject1 = (Subject)Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),invocationHandler);
		subject1.request();

	}
}
