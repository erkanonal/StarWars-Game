package com.jon.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameLogic.GameEngine;

public class X2UP extends GameObject implements EntityB, Bonus{

	GameEngine gameEngine;
	
	BufferedImage x2Up;
	
	private int speed;
	
	private int width, height;
	
	public X2UP(int x, int y, int speed) {
		super(x, y);
		gameEngine = GameEngine.getInstance();
		this.speed = speed;
		
		try {
			x2Up = ImageIO.read(new File("res\\images\\x2Up.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = x2Up.getWidth();
		height = x2Up.getHeight();

	}

	public void move() {
		y = y + speed;	
	}

	public void render(Graphics g) {
		g.drawImage(x2Up, x, y, x2Up.getWidth(null), x2Up.getHeight(null), null);		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
