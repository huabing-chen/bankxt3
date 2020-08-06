package com.chb.util;

import java.util.Random;

import com.alibaba.druid.util.StringUtils;

public class CodeNumberUtil {

	private static int i = 0;
	public static final String NUMBERCHAR = "0123456789";
 
	/**
	 * 
	 * Description: 生成条码（卡号）
	 * 
	 * @return
	 * @see
	 */
	public static String getCodeNumber(String prefix) {
		if (!StringUtils.isEmpty(prefix)) {
			//可以修改生成随机数的长度，生成想要的长度的条码
			String num = generateNumString(9);
			String st = prefix + num + getUnixTime();
//			System.out.println("st----->>>"+st.length());
//			System.out.println(getCardCheckCode(st));
			return st + getCardCheckCode(st);
		}
		return "prefix不能为空";
	}
 
	/**
	 * 传入一个前缀和一个码。 【根据自己的业务定义】
	 * 
	 * @param prefix
	 * @return
	 */
	public static String getCodeNumber(String prefix, String code) {
		if (!StringUtils.isEmpty(prefix) && !StringUtils.isEmpty(code)) {
			String st = prefix + code + getUnixTime();
			return st + getCardCheckCode(st);
		}
		return "prefix和code不能为空";
	}
 
	/**
	 * 校验条码是否正确
	 * 
	 * @param cardId
	 * @return
	 */
	public static boolean checkCode(String code) {
		char bit = getCardCheckCode(code.substring(0, code.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return code.charAt(code.length() - 1) == bit;
	}
 
	/**
	 * 从不含校验位的卡号采用 Luhm 校验算法获得校验位
	 * 
	 * @param nonCheckCodeCardNo
	 * @return
	 */
	private static char getCardCheckCode(String nonCheckCodeCardNo) {
		if (nonCheckCodeCardNo == null
				|| nonCheckCodeCardNo.trim().length() == 0
				|| !nonCheckCodeCardNo.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeCardNo.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}
 
	/***
	 * 获取当前系统时间戳 并截取后8位
	 * 
	 * @return
	 */
	private static String getUnixTime() {
		try {
			Thread.sleep(10);// 快速执行时，休眠10毫秒 防止号码重复
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i++;
		i = i > 100 ? i % 10 : i;
		return ((System.currentTimeMillis() / 100) + "").substring(5)+ (i % 10);
	}
 
	/**
	 * 生成一个定长的纯数字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return 纯0字符串
	 */
	private static String generateNumString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
		}
		return sb.toString();
	}
 
	
	//测试
//	public static void main(String[] args) {
// 
//		try {
// 
//			for (int i = 0; i < 1; i++) {
//				// 生成条码
//				String code = getCodeNumber("66");
//				System.out.println(code);
//				// 检验条码
////				System.out.println(checkCode(code));
//			}
// 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
