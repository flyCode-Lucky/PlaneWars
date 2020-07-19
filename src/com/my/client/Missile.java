package com.my.client;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

public class Missile {

	public int x,y,w,h;
	public BufferedImage showImage;
	
	PlaneClient pc;
	
	public Missile(int x,int y,PlaneClient pc) {
		this.x=x;
		this.y=y;
		showImage=Tool.getImage("fire.png");
		w=showImage.getWidth();
		h=showImage.getHeight();
		this.pc=pc;
	}
	
	public void move() {
		y-=30;
	}
	//外切四边形
	public  Rectangle  getRect() {
		return new Rectangle(x, y, w, h);
	}
	
	//子弹可以打击所有敌人
	public void hitEnemys(List<Enemy> enemys) {
		for(Enemy  e:enemys) {
			//打中某一个敌人
			if(this.hitEnemy(e))return;
		}
	}
	
	public boolean hitEnemy(Enemy e) {
		if(getRect().intersects(e.getRect())) {
			//击中
			if(--e.hp==0) {
				//敌人死亡
				pc.enemys.remove(e);
				pc.score++;
			}	
			//击中之后回收子弹
			pc.missiles.remove(this);
			
			return true;
		}
		return false;
	}
}
