package com.my.client;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Hero {

	public int x,y;
	public int w,h;
	public BufferedImage showImage;
	
	//生命值
	int  hp;
	
	PlaneClient pc;
	
	public Hero(int x,int y,PlaneClient pc) {
		this.x=x;
		this.y=y;
		showImage=Tool.getImage("My_plane.png");
		this.w=showImage.getWidth();
		this.h=showImage.getHeight();
		this.pc=pc;
		this.hp=100;
		
	}

	public void move(int key) {
		switch (key) {
		case KeyEvent.VK_LEFT:x-=10;break;
		case KeyEvent.VK_RIGHT:x+=10;break;
		case KeyEvent.VK_UP:y-=10;break;
		case KeyEvent.VK_DOWN:y+=10;break;
		case KeyEvent.VK_SPACE:fire();break;

		default:
			break;
		}
		
		//边界处理
		if(x<0)x=0;
		if(y<30)y=30;
		if(x>512-w)x=512-w;
		if(y>768-h)y=768-h;
		
	}

	private void fire() {
		Missile  m=new Missile(x, y,pc);
		pc.missiles.add(m);
		
	}
	
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}
	
	
	
	
}
