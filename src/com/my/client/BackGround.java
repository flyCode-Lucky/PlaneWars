package com.my.client;

import java.awt.image.BufferedImage;
import java.util.Random;

public class BackGround {
	
	public int x1,y1,x2,y2;
	public BufferedImage bg1,bg2;
	
	Random r = new Random();
	
	public BackGround() {
		int i= r.nextInt(5)+1;
		bg1=Tool.getImage("bg"+i+".jpg");
		bg2=Tool.getImage("bg"+i+".jpg");
		
		x1=y1=x2=0;
		y2=-768;
		
	}
	
	//背景1的移动
	public void move1() {
		y1+=5;
		if(y1>=768) {
			y1=-768;
		}
		
	}
	//背景2的移动
	public void move2() {
		y2+=5;
		if(y2>=768) {
			y2=-768;
		}
	}
	

}
