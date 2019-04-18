package com.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * <pre>
 * Log print log4j2
 * </pre>
 */
public class Log {

	private static final String msgSep = "\r\n";
	private static boolean isMsgSep = false;
	private static Logger logger; //= LogManager.getLogger(Log.class);

	public static boolean init(String log4j2Path, boolean msgSep) {
		isMsgSep = msgSep;

		File config = new File(log4j2Path);
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(config), config);
			Configurator.initialize(null, source);
		} catch (FileNotFoundException e) {
			System.err.println("Log log4j2 xml not find or reload error path:" + log4j2Path);
			return false;
		}
		logger = LogManager.getLogger("");
		return true;
	}

	public static void debug(Object msg) {
		debug0(msg, null);
	}

	public static void debug(Object msg, Object... obj) {
		debug0(msg, null);
	}

	public static void debug(Object msg, Throwable e) {
		debug0(msg, getExceptionString(e));
	}

	public static void debug(Object msg, Throwable e, Object... params) {
		debug0(msg, getExceptionString(e), params);
	}

	public static void info(Object msg) {
		String str = info0(msg, null);
	}

	public static void info(Object msg, Object... params) {
		String str = info0(msg, null, params);
	}

	public static void info(Object msg, Throwable e) {
		String str = info0(msg, getExceptionString(e));
	}

	public static void info(Object msg, Throwable e, Object... params) {
		String str = info0(msg, getExceptionString(e), params);
	}

	public static void warn(Object msg) {
		String str = warn0(msg, null);
	}

	public static void warn(Object msg, Object... params) {
		String str = warn0(msg, null, params);
	}

	public static void warn(Object msg, boolean showStack) {
		String str = warn0(msg, (showStack) ? getStackTraceString(3, 0) : null);
	}

	public static void warn(Object msg, Throwable e) {
		String str = warn0(msg, getExceptionString(e));
	}

	public static void warn(Object msg, Throwable e, Object... params) {
		String str = warn0(msg, getExceptionString(e), params);
	}

	public static void error(Object msg, boolean showStack) {
		String str = error0(msg, (showStack) ? getStackTraceString(3, 0) : null);
	}

	public static void error(Object msg) {
		String str = error0(msg, null);
	}

	public static void error(Object msg, Object... params) {
		String str = error0(msg, null, params);
	}

	public static void error(Object msg, Throwable e) {
		error0(msg, getExceptionString(e));
	}

	public static void error(Object msg, Throwable e, Object... params) {
		error0(msg, getExceptionString(e), params);
	}

	private static String paramToString(Object... params) {
		return Arrays.toString(params);
	}

	public static void debug0(Object msg, Object other) {
		String str = getString(msg, other);
		// 输出
		if (logger != null) {
			logger.debug(str.toString());
		} else {
			System.out.println(str.toString());
		}
	}

	protected static void debug0(Object msg, Object other, Object... params) {
		String str = getString(msg, other);
		// 输出
		if (logger != null) {
			logger.debug(str.toString(), params);
		} else {
			System.out.println(str.toString());
		}
	}

	protected static String info0(Object msg, Object other) {
		String str = getString(msg, other);
		// 输出
		if (logger != null) {
			logger.info(str.toString());
		} else {
			System.out.println(str.toString());
		}
		return str;
	}

	protected static String info0(Object msg, Object other, Object... params) {
		String str = getString(msg, other);
		// 输出
		if (logger != null) {
			logger.info(str.toString(), params);
		} else {
			System.out.println(str.toString());
		}
		return str;
	}

	protected static String warn0(Object msg, Object other) {
		String str = getString(msg, other);
		// 输出
		if (logger != null) {
			logger.warn(str.toString());
		} else {
			System.out.println(str.toString());
		}
		return str;
	}

	protected static String warn0(Object msg, Object other, Object... params) {
		String str = getString(msg, other);
		// 输出
		if (logger != null) {
			logger.warn(str.toString(), params);
		} else {
			System.out.println(str.toString());
		}
		return str;
	}

	protected static String error0(Object msg, Object other) {
		String str = getString(msg, other);
		// 输出
		if (logger != null) {
			logger.error(str.toString());
		} else {
			System.out.println(str.toString());
		}
		return str;
	}

	protected static String error0(Object msg, Object other, Object... params) {
		String str = getString(msg, other);
		// 输出
		if (logger != null) {
			logger.error(str.toString(), params);
		} else {
			System.out.println(str.toString());
		}
		return str;
	}

	protected static String getString(Object msg, Object other) {
		String fn = getStackTraceString(5, 1);
		// 内容拼接
		StringBuilder strBdr = new StringBuilder();
		strBdr.append(isMsgSep ? msgSep : " ");
		strBdr.append(fn);
		strBdr.append(isMsgSep ? msgSep : " ");
		strBdr.append(msg);

		if (other != null) {
			strBdr.append(" ");
			strBdr.append(other);
		}

		return strBdr.toString();
	}

	/**
	 * 显示堆栈, 过滤最顶几层.
	 **/
	protected static boolean showStack(StringBuilder strBdr, int limitCount) {
		// 获取堆栈信息.
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		if (ste == null) {
			return false;
		}
		// 遍历输出
		int len = ste.length;
		for (int i = limitCount; i < len; i++) {
			StackTraceElement s = ste[i];
			strBdr.append("\tat ");
			strBdr.append(s.toString());
			strBdr.append(msgSep);
		}
		return true;
	}

	/**
	 * 将异常信息转化成字符串
	 **/
	public static String getExceptionString(Throwable e) {
		if (e == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			e.printStackTrace(new PrintStream(baos));
		} finally {
			try {
				baos.close();
			} catch (Exception ee) {
			}
		}
		return baos.toString();
	}

	/**
	 * 获取堆栈信息(输出第几条, 自己控制)
	 **/
	protected static String getStackTraceString(int stackIndex, int depth) {
		// 获取堆栈信息
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		if (st == null) {
			return "";
		}
		// 判断范围
		if (stackIndex >= st.length || stackIndex < 0) {
			return "";
		}
		// 第一条肯定是java.lang.Thread.getStackTrace(Thread.java:1552), 因此过滤掉.
		// 第二条就是这个函数
		if (depth == 1) {
			return st[stackIndex].toString();
		}
		depth = (depth != 0) ? depth : st.length - stackIndex;
		// 层级输出
		StringBuilder strBdr = new StringBuilder();
		strBdr.append(st[stackIndex].toString());
		for (int i = 0; i < depth - 1; i++) {
			strBdr.append("\r\n\tat ");
			strBdr.append(st[stackIndex + i + 1].toString());
		}
		return strBdr.toString();
	}

}
