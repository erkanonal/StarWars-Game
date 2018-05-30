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

import GameLogic.BossBulletHandler;
import GameLogic.GameEngine;

public class Soldier extends GameObject implements EntityB{
	
	GameEngine gameEngine;
	BossBulletHandler bossBulletHandler;
	
	Timer timer;
	
	BufferedImage imageFront;
	BufferedImage fireBall;
	
	private FireOne fireOne;
	private int speed;
	private boolean left;
	
	private Random random;
	private int chanceOfFire;
	private int chanceOfX2Up;
	private int chanceOfX3Up;
	private int chanceOfLife;
	
	private int width, height;
	
	private boolean isDestroyed;
		
	public Soldier(int x, int y)
	{
		super(x, y);
		
		gameEngine = GameEngine.getInstance();
		//enemyBulletHandler = EnemyBulletHandler.getInstance();
		fireOne = null;
		isDestroyed = false;
		
		timer = new Timer();
		
		random = new Random();
		speed = random.nextInt(5) + 1 ;
		chanceOfFire = random.nextInt(100) + 1;
		chanceOfX2Up = random.nextInt(100) + 1;
		chanceOfX3Up = random.nextInt(100) + 1;
		chanceOfLife = random.nextInt(100) + 1;
		left = true;
		
		try 
		{
			imageFront = ImageIO.read(new File("res\\images\\stormtrooperFront.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		width = imageFront.getWidth();
		height = imageFront.getHeight();
		
		//fire();
	}
	
	public void move() {
		y = y + speed;
		
		if(y > gameEngine.getFrame().getHeight())
		{
			y = -10;
			x = random.nextInt(gameEngine.getFrame().getWidth()) + 1;
		}
	}
	
	/*public void fire(){
			timer.schedule(new TimerTask() {
			    @Override
			    public void run() {
			       chanceOfFire = random.nextInt(99) + 1;	       
			       if(chanceOfFire > 70){
			    	   fireOne = new FireOne(x, y + 5,speed + 1);
			    	   enemyBulletHandler.addBullet(fireOne);
			       }
			    }
			}, 0, 2000);
	}
	*/
		
	public void render(Graphics g)
	{
		g.drawImage(imageFront, x, y, imageFront.getWidth(null), imageFront.getHeight(null), null);	
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	public Life dropLife(){
		Life life = new Life(x + 15, y + 20, 2);
		return life;
	}
	
	public X2UP dropX2(){
		X2UP x2up = new X2UP(x + 15, y + 20, 2);
		return x2up;
	}
	
	public X3UP dropX3(){
		X3UP x3up = new X3UP(x + 15, y + 20, 2);
		return x3up;
	}
	
	public FireOne getFireOne() {
		return fireOne;
	}
	
	public void setFireOne(FireOne fireOne) {
		this.fireOne = fireOne;
	}
	
	public void setDestroyed(boolean b){
		this.isDestroyed= b;
	}
	
	public boolean getDestroyed(){
		return isDestroyed;
	}
	
	public boolean getChanceOfLife() {
		if(chanceOfLife > 85){
			return true;
		}
		return false;
	}
	
	public boolean getChanceOfX2Up() {
		if(chanceOfX2Up > 90){
			return true;
		}
		return false;
	}
	
	public boolean getChanceOfX3Up() {
		if(chanceOfX3Up > 95){
			return true;
		}
		return false;
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
