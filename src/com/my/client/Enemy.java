package com.my.client;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class Enemy {

	public int x,y,w,h;
	public BufferedImage showImage;
	int  speed;//速度
	int  hp;//生命值
	
	Random r =new Random();
	
	PlaneClient  pc;
	
	public Enemy(PlaneClient  pc) {
		int i=r.nextInt(15)+1;
		showImage=Tool.getImage("ep"+(i<10?"0"+i:i)+".png");
		w=showImage.getWidth();
		h=showImage.getHeight();
		this.pc=pc;
		//随机生成敌人的横坐标
		x=r.nextInt(512-w);
		y=-h;
		
		//不同种类的敌人有不同的血量和生命值
		if(i<=5) {
			speed=5;
			hp=1;
		}else if(i>=6&&i<=14) {
			speed=15;
			hp=2;
		}else {
			speed=30;
			hp=3;
		}
	}

	public void move() {
		y+=speed;
		if(y>=768) {
			pc.enemys.remove(this);
			pc.score--;
		}
	}
	
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}

	public void hitHero(Hero h) {
		if(getRect().intersects(h.getRect())) {
			if(--h.hp==0) {
				//游戏结束
				PlaneClient.GameOkOver=false;
			}			
		}
		
	}

	
}
