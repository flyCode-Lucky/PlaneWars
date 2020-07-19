package com.my.client;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Tool {

	/**
	 * 根据图片名称获得图片
	 * @param name
	 * @return
	 */
	public static BufferedImage getImage (String name) {
		
		try {
			BufferedImage image=
					ImageIO.read(Tool.class.getResource("../img/"+name));
		
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
