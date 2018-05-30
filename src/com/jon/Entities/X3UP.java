package com.jon.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameLogic.GameEngine;

public class X3UP extends GameObject implements EntityB, Bonus{

GameEngine gameEngine;
	
	BufferedImage x3Up;
	
	private int speed;
	
	private int width, height;
	
	public X3UP(int x, int y, int speed) {
		super(x, y);
		gameEngine = GameEngine.getInstance();
		this.speed = speed;
		
		try {
			x3Up = ImageIO.read(new File("res\\images\\x3Up.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = x3Up.getWidth();
		height = x3Up.getHeight();

	}

	public void move() {
		y = y + speed;	
	}

	public void render(Graphics g) {
		g.drawImage(x3Up, x, y, x3Up.getWidth(null), x3Up.getHeight(null), null);		
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
