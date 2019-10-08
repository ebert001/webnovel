package com.aswishes.novel.core.common.checkcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

public class SimpleVerificationCode extends VerificationCode {
	
	private final char[] CHARS = {
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
		'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
		'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
		'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
		'U', 'V', 'W', 'X', 'Y', 'Z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};
	
	// 验证码长度
	private final int LENGTH = 6;
	
	private Random random = new Random();

	public SimpleVerificationCode(OutputStream outStream) {
		super(outStream);
	}
	
	private String getRandomString() {
		StringBuilder sb = new StringBuilder();
		for (int i = LENGTH - 1; i >= 0; i--) {
			sb.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return sb.toString();
	}
	
	/**
	 * 生成随机颜色
	 * @param ll 颜色值下限(lower limit)
	 * @param ul 颜色值上限(upper limit)
	 * @return
	 */
	private Color getRandomColor(int ll, int ul) {
		if (ll > 255) {
			ll = 255;
		}
		if (ll < 1) {
			ll = 1;
		}
		if (ul < 1) {
			ul = 1;
		}
		if (ul == ll) {
			ul = ll + 1;
		}
		int r = random.nextInt(ul - ll) + ll;
		int g = random.nextInt(ul - ll) + ll;
		int b = random.nextInt(ul - ll) + ll;
		return new Color(r, g, b);
	}
	
	public BufferedImage getImage() {
		BufferedImage image = new BufferedImage(IMAGE_WIDTH / 4 * LENGTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        
        // 获取图形上下文
        Graphics graphics = image.getGraphics();
        
        // 设置背景色
        graphics.setColor(getRandomColor(1, 50));
        // 填充背景色
        graphics.fillRect(0, 0, IMAGE_WIDTH * 4, IMAGE_HEIGHT);
        
        // 设置边框颜色
        graphics.setColor(new Color(0, 255, 0));
        // 画边框
        for (int i = 0; i < 2; i++) {
            graphics.drawRect(i, i, IMAGE_WIDTH / 4 * LENGTH - 2, IMAGE_HEIGHT - 2);
        }
        
        // 设置随机干扰线条颜色
        graphics.setColor(getRandomColor(50,100));
        // 产生50条干扰线条
        for (int i = 0; i < 50; i++) {
            int x1 = random.nextInt(IMAGE_WIDTH * LENGTH - 4) + 2;
            int y1 = random.nextInt(IMAGE_HEIGHT - 4) + 2;
            int x2 = random.nextInt(IMAGE_WIDTH * LENGTH - 2 - x1) + x1;
            int y2 = y1;
            graphics.drawLine(x1, y1, x2, y2);
        }
        
        // 设置字体
        graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        
        checkCode = getRandomString();
        // 画字符串
        for (int i = 0; i < this.LENGTH; i++){
            String temp = checkCode.substring(i, i + 1);
            graphics.setColor(getRandomColor(100, 255));
            graphics.drawString(temp, 13 * i + 6, 16);
        } 
         
        // 图像生效
        graphics.dispose();
        
        return image; 
	}
}
