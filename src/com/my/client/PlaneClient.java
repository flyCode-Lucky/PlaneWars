package com.my.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.my.client.Tool;
/**
 * 1.滑动的背景（绘制窗口，设置背景图，使之动起来）
 *
 */
public class PlaneClient extends JFrame{

	
	
	BackGround bg;
	Hero h;
	
	List<Missile> missiles=new ArrayList<Missile>();
	List<Enemy> enemys=new ArrayList<Enemy>();
	
	//分数(打死一个敌人+1分，一个敌人跑掉-1)
	int score;
	
	//游戏是开始的
	public static boolean GameOkOver=true;
	
	public PlaneClient() {
		bg =new BackGround();
		h=new Hero(210, 600,this);
		/*绘制窗口*/
		//设置左上角那个图标
		this.setIconImage(Toolkit.getDefaultToolkit()
				.createImage(PlaneClient.class.getResource("../img/w_zdd.jpg")));
		this.setTitle("飞机大战");
		this.setSize(512,768);
		//尺寸固定不能被改变
		this.setResizable(false);
		//设置窗口相对于指定组件的位置。 null就是中央
		this.setLocationRelativeTo(null);
		//设置关闭窗口就exit，否则会默认关闭隐藏
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//开启线程背景移动
		new Thread(new MyThread()).start();
		//接收键盘事件
		this.addKeyListener(new MyListener());
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					enemys.add(new Enemy(PlaneClient.this));
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}	
	
	//绘制画面
	public void paint(Graphics g) {
		g.drawImage(bg.bg1, bg.x1, bg.y1, this);
		g.drawImage(bg.bg2, bg.x2, bg.y2, this);
		//画自己的飞机
		g.drawImage(h.showImage, h.x, h.y, this);
		//子弹
		for(int i=0;i<missiles.size();i++) {
			 Missile m = missiles.get(i);
			 g.drawImage(m.showImage, m.x, m.y, this);
			 m.move();
			 //打击敌人
			 m.hitEnemys(enemys);
		}
		//画出敌人
		for(int i=0;i<enemys.size();i++) {
			Enemy e = enemys.get(i);
			g.drawImage(e.showImage, e.x, e.y, e.w,e.h,this);
			e.move();
			//敌人碰到Hero
			e.hitHero(h);
			
		}
		
		Font f=new Font("宋体",Font.BOLD,30);
		g.setFont(f);
		g.setColor(Color.RED);
		if(GameOkOver) {
			g.drawString("英雄的血量："+h.hp, 10, 70);
			g.drawString("英雄的分数："+score, 10, 110);
		}
		if(!GameOkOver) {
			enemys.clear();
			g.drawString("游戏结束!", 200, 380);
		}
		
		
		
	}
	
	//键盘
	private class MyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			//System.out.println(key);
			h.move(key);
		}
	}
	
	
	//单独开一个线程去实现背景的移动
	private class MyThread implements Runnable{
		public void run() {
			while(true) {
				bg.move1();
				bg.move2();
			
				try {
					Thread.sleep(100);
					repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		 new PlaneClient();
	}
}
