package com.aswishes.novel.common.checkcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public abstract class VerificationCode {

	/**
	 * 使用时，需要通过构造函数注入输出流，指定输出位置
	 * @param outStream 输出流
	 */
	public VerificationCode(OutputStream outStream) {
		this.outStream = outStream;
	}
	
	/**
	 * 打印验证码
	 * @throws IOException
	 */
	public void printImage() throws IOException {
		BufferedImage bi = getImage();
		ImageIO.write(bi, "png", outStream);
		outStream.flush();
	}
	
	/**
	 * 获取用户需要输入的验证码结果
	 * @return 用户输入结果
	 */
	public String getCheckCode() {
		return checkCode;
	}
	
	public abstract BufferedImage getImage();
	
	// 验证码图片的宽度
	protected int IMAGE_WIDTH = 60;
	
	// 验证码图片的高度
	protected int IMAGE_HEIGHT = 25;
	
	protected String checkCode = "";
	
	private OutputStream outStream = null;
}
