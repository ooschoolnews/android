package com.schoolnews.manage.application.utils;

public class LogUtil {
	private static boolean is_show = true;
    public static final void init(boolean is_show) {
		LogUtil.is_show = is_show;
    }

	public static final void init(String logFile, int level) {
		LogImpl.init(logFile, level);
	}

	public static final void d(String tag, String msg) {
		if(is_show)
		LogImpl.d(tag, buildMessage(msg));
	}


	public static final void i(String tag, String msg) {
		if(is_show)
		LogImpl.i(tag, buildMessage(msg));
	}
	
	public static final void ui(String msg) {
		if(is_show)
		LogImpl.i("ui", buildMessage(msg));
	}

	public static final void res(String msg) {
		if(is_show)
		LogImpl.i("RES", buildMessage(msg));
	}

	private static String buildMessage(String msg) {
		return msg;
	}
}
