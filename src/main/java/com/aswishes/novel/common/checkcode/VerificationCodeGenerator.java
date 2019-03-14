package com.aswishes.novel.common.checkcode;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码生成器
 * @author Administrator
 */
public class VerificationCodeGenerator {
	
	// 验证码复杂程度，简单
	public static final int SIMPLE = 1;
	
	// 验证码复杂程度，困难
	public static final int HARD = 2;
	
	/**
	 * 验证码生成工厂
	 * @param outStream 输出流。需要注意，生成器在使用完输出流后，并没有关闭输出流
	 * @param level 验证码的复杂等级
	 * @return 最终的用户输入结果
	 * @throws IOException 在生成或输出验证码发生错误时，抛出此异常
	 */
	public static String generate(OutputStream outStream, int level) throws IOException {
		VerificationCode checkcode = null;
		if (level == HARD) {
		} else {
			// 默认就是最简单的验证码
			checkcode = new SimpleVerificationCode(outStream);
		}
		checkcode.printImage();
		return checkcode.getCheckCode();
	}
	
}
