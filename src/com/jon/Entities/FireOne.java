package com.jon.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import GameLogic.GameEngine;

public class FireOne extends GameObject implements EntityB{

	GameEngine gameEngine;
	
	BufferedImage fireBall;
	
	private int speedX;
	private int speedY;
	
	private int width, height;
	
	public FireOne(int x, int y, int speedX, int speedY) {
		super(x, y);

		gameEngine = GameEngine.getInstance();
		
		this.speedX = speedX;
		this.speedY = speedY;
		
		try 
		{
			fireBall = ImageIO.read(new File("res\\images\\fireBall.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		width = fireBall.getWidth();
		height = fireBall.getHeight();
	}

	public void move() {
		x = x + speedX;
		y = y + speedY;
	}

	public void render(Graphics g) {
		g.drawImage(fireBall, x, y, fireBall.getWidth(null), fireBall.getHeight(null), null);		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
