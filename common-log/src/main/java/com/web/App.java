package com.web;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {

	public static void main(String[] args) {
		String log4jPath = "D:\\work\\web\\common-log\\config\\log4j2.xml";

		System.setProperty("server_path", "D:\\work\\web\\common-log\\log" + File.separator);
		System.setProperty("server_name", "testlog");

		Log.init(log4jPath, false);

		Log.error("test error");
		Log.info("test info");
		Log.warn("test warn");
		Log.debug("test debug");

		try{
			int a = 1 / 0;
		}catch (Exception e){
			Log.error("error",e);
		}

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
