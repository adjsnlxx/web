package com.web.groovytest;

import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;

public class GroovyUtil {

	private static String groovyPath;
	private static GroovyScriptEngine engine;

	public static void init(String tempPath) throws IOException {
		groovyPath = tempPath;

		try {
			engine = new GroovyScriptEngine(groovyPath);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 调用指定脚本文件、指定方法名，可传递参数
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