package com.zhouzhongping.cn.util;

public class ZhouUtil {
	public static String getValiteCode() {
		//随机生成验证码
		String valiteCode ="";
		for(int i=0;i<6;i++) {
			int j = (int) (10*Math.random());
			valiteCode = valiteCode+j;
		}
		return valiteCode;
	}
	public static String getPhoneNumber() {
		//随机生成验证码
		String phoneNumber ="";
		for(int i=0;i<9;i++) {
			int j = (int) (10*Math.random());
			phoneNumber = phoneNumber+j;
		}
		return phoneNumber;
	}
}
