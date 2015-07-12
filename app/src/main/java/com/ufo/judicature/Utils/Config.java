package com.ufo.judicature.Utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;

import com.ufo.judicature.JudiApplication;

/**
 * SharedPreferences配置信息
 * 
 */
public class Config {

	// 用户信息
	private static String USER_INFO = "USER_INFO";

	// 第一次启动
	private static String FIRST_START = "FIRST_START";

	public static SharedPreferences UserInfoPreferences = JudiApplication.getContext().getSharedPreferences(USER_INFO, 0);

	public static boolean isFirst() {
		return UserInfoPreferences.getBoolean(FIRST_START, true);
	}

	public static void setFirst(boolean isFirst) {
		UserInfoPreferences.edit().putBoolean(FIRST_START, isFirst).commit();
	}

	/**
	 * 清空数据
	 */
	public static void cleanData() {
		UserInfoPreferences.edit().clear();
	}
	
	public static int width = 0;
	public static int height = 0;
	public static float density;

	/**
	 * 得到屏幕长宽
	 * @param activity
	 */
	public static void setScreenSize(Activity activity) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		width = displayMetrics.widthPixels;
		height = displayMetrics.heightPixels;
		density = displayMetrics.density;
	}
}
