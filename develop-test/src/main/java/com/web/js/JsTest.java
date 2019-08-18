package com.web.js;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsTest {

	private static final String jsFile = "test.js";

	public static void main(String[] args) throws Exception {
		String script = "print(greeting)";
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");

		// Attribute from ScriptEngineManager
		manager.put("greeting", "Hello from ScriptEngineManager");
		engine.eval(script);

		// Attribute from ScriptEngine
		engine.put("greeting", "Hello from ScriptEngine");
		engine.eval(script);

		// Attribute from eval method
		ScriptContext context = new SimpleScriptContext();
		context.setAttribute("greeting", "Hello from eval method", ScriptContext.ENGINE_SCOPE);
		engine.eval(script, context);

		executeFromFile();
	}

	private static void executeFromFile() {
		String curPath = System.getProperty("user.dir");
		System.out.println(curPath);

		String file = curPath + "\\develop-test\\src\\script\\" + jsFile;

		try {
			ScriptEngine engine = ScriptUtil.executeScript(new FileReader(file));
			System.out.println("sum of 1 and 2 is: " + engine.eval("sum(1, 2)"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	private static class ScriptUtil {
		public static ScriptEngine executeScript(String function) throws ScriptException {
			ScriptEngineManager engineManager = new ScriptEngineManager();
			ScriptEngine engine = engineManager.getEngineByName("nashorn");
			engine.eval(function);
			return engine;
		}

		public static ScriptEngine executeScript(FileReader file) throws ScriptException {
			ScriptEngineManager engineManager = new ScriptEngineManager();
			ScriptEngine engine = engineManager.getEngineByName("nashorn");
			engine.eval(file);
			return engine;
		}
	}
}
