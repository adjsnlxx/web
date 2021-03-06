package com.web.groovytest;

import java.io.IOException;

public class GroovyTest {

	public static void main(String[] args) {
		String curPath = System.getProperty("user.dir");
		System.out.println(curPath);

		String path = curPath + "\\develop-test\\src\\groovyscript\\";
		try {
			GroovyUtil.init(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		long curTime = System.currentTimeMillis();

		String result = null;
		try {
			result = (String) GroovyUtil.invokeMethod("hello.groovy", "helloWithoutParam", null);
			System.out.println("helloWithoutParam result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("firstTime = " + (System.currentTimeMillis() - curTime));

		System.out.println("wait ....");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		curTime = System.currentTimeMillis();
		try {
			result = (String) GroovyUtil.invokeMethod("hello.groovy", "helloWithoutParam", null);
			System.out.println("helloWithoutParam result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("secondTime = " + (System.currentTimeMillis() - curTime));

		Person person = new Person("sam", "local", 18);
		try {
			result = (String) GroovyUtil.invokeMethod("hello.groovy", "helloWithParam", person, "1");
			System.out.println("helloWithParam result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
