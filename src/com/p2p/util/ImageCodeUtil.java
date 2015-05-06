package com.p2p.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageCodeUtil {

	public static BufferedImage createImageCode(String randCode) {
		// 在内存中创建图象
		int width = 65, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(230, 255));
		g.fillRect(0, 0, 100, 25);
		// 设定字体
		g.setFont(new Font("Arial", Font.CENTER_BASELINE | Font.ITALIC, 18));
		// 产生0条干扰线，
		g.drawLine(0, 0, 0, 0);
		// 取随机产生的认证码(4位数字)
		int len = randCode.length();
		for (int i = 0; i < len; i++) {
			
			
			// 将认证码显示到图象中
			g.setColor(getRandColor(100, 150));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(Character.toString(randCode.charAt(i)), 15 * i + 6, 16);
		}
		for (int i = 0; i < (random.nextInt(5) + 5); i++) {
			g.setColor(new Color(random.nextInt(255) + 1,
					random.nextInt(255) + 1, random.nextInt(255) + 1));
			g.drawLine(random.nextInt(100), random.nextInt(30),
					random.nextInt(100), random.nextInt(30));
		}

		// 将验证码存入页面KEY值的SESSION里面

		// 图象生效
		g.dispose();
		return image;
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
