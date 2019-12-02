package com.web.groovytest;

import java.io.IOException;

public class GroovyTest1 {

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
			result = (String) GroovyUtil.invokeMethod("hello.groovy", "helloGroovy", "I", "do");
			System.out.println("run result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("firstTime = " + (System.currentTimeMillis() - curTime));

		System.out.println("wait modification ....");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		curTime = System.currentTimeMillis();
		try {
			result = (String) GroovyUtil.invokeMethod("hello.groovy", "helloGroovy", "I", "do");
			System.out.println("run result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("secondTime = " + (System.currentTimeMillis() - curTime));
	}

}
