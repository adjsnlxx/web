package com.web.groovytest;

import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;

public class GroovyTest {

	public static void main(String[] args) {
		String curPath = System.getProperty("user.dir");
		System.out.println(curPath);

		String path = curPath + "\\develop-test\\src\\groovyscript\\";
		try {
			GroovyUitl.init(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		long curTime = System.currentTimeMillis();

		String result = null;
		try {
			result = (String) GroovyUitl.invokeMethod("hello.groovy", "helloWithoutParam", null);
			System.out.println("helloWithoutParam result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("firstTime = " + (System.currentTimeMillis() - curTime));

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		curTime = System.currentTimeMillis();
		try {
			result = (String) GroovyUitl.invokeMethod("hello.groovy", "helloWithoutParam", null);
			System.out.println("helloWithoutParam result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("secondTime = " + (System.currentTimeMillis() - curTime));

		Person person = new Person("sam", "local", 18);
		try {
			result = (String) GroovyUitl.invokeMethod("hello.groovy", "helloWithParam", person, "1");
			System.out.println("helloWithParam result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static class GroovyUitl {

		private static String path;
		private static GroovyScriptEngine engine;

		public static void init(String tempPath) throws IOException {
			path = tempPath;

			try {
				engine = new GroovyScriptEngine(path);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}

		/**
		 * 用于调用指定Groovy脚本中的指定方法
		 *
		 * @param scriptName
		 * @param methodName
		 * @param params
		 * @return
		 */
		public static Object invokeMethod(String scriptName, String methodName, Object... params) throws Exception {
			Object ret = null;
			Class scriptClass = null;
			GroovyObject scriptInstance = null;

			try {
				scriptClass = engine.loadScriptByName(scriptName);
				scriptInstance = (GroovyObject) scriptClass.newInstance();
			} catch (ResourceException | ScriptException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				throw new Exception("load script " + scriptName + " failed");
			}

			ret = scriptInstance.invokeMethod(methodName, params);
			return ret;
		}

	}
}
