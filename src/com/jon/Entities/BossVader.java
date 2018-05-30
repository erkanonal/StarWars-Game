package com.jon.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import Audio.Sound;
import GameLogic.BossBulletHandler;
import GameLogic.GameEngine;

public class BossVader extends GameObject implements EntityB{

	GameEngine gameEngine;
	BufferedImage imageFront;
	
	private Timer timer;
	private BossBulletHandler bossBulletHandler;
	
	private int speed;
	private boolean left;
	private int health;
	private Random random;
	private int chanceOfFire;
	private FireOne fireOne;
	
	private int width, height;
	
	
		
	public BossVader(int x, int y)
	{
		super(x, y);
		
		speed = 4;
		left = true;
		health = 7;
		
		gameEngine = GameEngine.getInstance();
		timer = new Timer();
		bossBulletHandler = BossBulletHandler.getInstance();
		chanceOfFire = 50;
		fireOne = null;
		random = new Random();
		
		
		
		try 
		{
			imageFront = ImageIO.read(new File("res\\images\\vaderFront.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		width = imageFront.getWidth();
		height = imageFront.getHeight();
	}
	
	public void fire(){
		timer.schedule(new TimerTask() {
		    @Override
		    public void run() {
		       if(chanceOfFire > random.nextInt(101) ){
		    	   fireOne = new FireOne(x + 30, y + 50,(gameEngine.getPlayer().getX() - x) / x,3);
		    	   bossBulletHandler.addBullet(fireOne);
		       }
		    }
		}, 0, 300);
}
	
	public void move()
	{
		if(left){
			moveLeft();
		}
		else{
			moveRight();
		}
	}
	
	public void moveLeft()
	{
		x = x - speed;
		
		if(x <= 0 )
		{
			this.setLeft(false);
		}
	}
	
	public void moveRight()
	{
		x = x + speed;
		
		if(x >= gameEngine.getFrame().getWidth() - 60)
		{
			this.setLeft(true);
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(imageFront, x, y, imageFront.getWidth(null), imageFront.getHeight(null), null);	
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public Timer getTimer(){
		return timer;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x) 
	{
		this.x = x;
	}
	
	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
}
